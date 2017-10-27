package net.whydah.sso.user.mappers;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.whydah.sso.basehelpers.Sanitizers;
import net.whydah.sso.user.helpers.UserAggregateXpathHelper;
import net.whydah.sso.user.helpers.UserTokenXpathHelper;
import net.whydah.sso.user.types.UserApplicationRoleEntry;
import net.whydah.sso.user.types.UserToken;
import net.whydah.sso.whydah.DEFCON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.whydah.sso.basehelpers.JsonPathHelper.getStringFromJsonpathExpression;

public class UserTokenMapper {

    public static final Logger log = LoggerFactory.getLogger(UserTokenMapper.class);
    public static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


    public static UserToken fromUserTokenXml(String userTokenXml) {
        if (userTokenXml == null) {
            log.warn("fromUserTokenXml called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }
        if (!isSane(userTokenXml)) {
            log.warn("fromUserTokenXml XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String uid = (String) xPath.evaluate("/usertoken/uid", doc, XPathConstants.STRING);
            String personRef = UserTokenXpathHelper.getPersonref(userTokenXml);
            String userName = UserTokenXpathHelper.getUserName(userTokenXml);
            String firstName = UserTokenXpathHelper.getFirstName(userTokenXml);
            String lastName = UserTokenXpathHelper.getLastName(userTokenXml);
            String email = (String) xPath.evaluate("/usertoken/email", doc, XPathConstants.STRING);
            String cellPhone = UserTokenXpathHelper.getPhoneNumber(userTokenXml);
            String securityLevel = UserTokenXpathHelper.getSecurityLevelAsString(userTokenXml);

            String tokenId = (String) xPath.evaluate("/usertoken/@id", doc, XPathConstants.STRING);
            String timestamp = (String) xPath.evaluate("/usertoken/timestamp", doc, XPathConstants.STRING);
            String lastSeen = UserTokenXpathHelper.getLastSeen(userTokenXml);

            String defcon = (String) xPath.evaluate("/usertoken/DEFCON", doc, XPathConstants.STRING);
            String lifespan = (String) xPath.evaluate("/usertoken/lifespan", doc, XPathConstants.STRING);
            String issuer = (String) xPath.evaluate("/usertoken/issuer", doc, XPathConstants.STRING);


            List<UserApplicationRoleEntry> roleList = new ArrayList<>();
            NodeList applicationNodes = (NodeList) xPath.evaluate("//application", doc, XPathConstants.NODESET);
            for (int i = 0; i < applicationNodes.getLength(); i++) {
                Node appNode = applicationNodes.item(i);
                String appId = (String) xPath.evaluate("@ID", appNode, XPathConstants.STRING);
                String appName = (String) xPath.evaluate("./applicationName", appNode, XPathConstants.STRING);
                String organizationName = (String) xPath.evaluate("./organizationName", appNode, XPathConstants.STRING);
                NodeList roles = (NodeList) xPath.evaluate("./role", appNode, XPathConstants.NODESET);

                for (int k = 0; k < roles.getLength(); k++) {
                    Node roleNode = roles.item(k);
                    String roleName = (String) xPath.evaluate("@name", roleNode, XPathConstants.STRING);
                    String roleValue = (String) xPath.evaluate("@value", roleNode, XPathConstants.STRING);

                    UserApplicationRoleEntry role = new UserApplicationRoleEntry();
                    role.setApplicationId(appId);
                    role.setApplicationName(appName);
                    role.setOrgName(organizationName);
                    role.setRoleName(roleName);
                    role.setRoleValue(roleValue);
                    roleList.add(role);
                }
            }


            UserToken userToken = new UserToken();
            userToken.setUid(uid);
            userToken.setUserName(userName);
            userToken.setFirstName(firstName);
            userToken.setLastName(lastName);
            userToken.setEmail(email);
            userToken.setCellPhone(cellPhone);
            userToken.setPersonRef(personRef);
            userToken.setSecurityLevel(securityLevel);
            userToken.setRoleList(roleList);

            userToken.setUserTokenId(tokenId);
            userToken.setTimestamp(timestamp);
            userToken.setLastSeen(lastSeen);
            UserToken.setDefcon(defcon);
            userToken.setLifespan(lifespan);
            userToken.setIssuer(issuer);
            return userToken;
        } catch (SAXParseException saxe) {
            log.error("SAXParseException parsing userTokenXml " + userTokenXml, saxe);
            return null;
        } catch (Exception e) {
            log.error("Error parsing userTokenXml " + userTokenXml, e);
            return null;
        }
    }

    public static UserToken fromUserAggregateXml(String userAggregateXML) {
        if (!isSane(userAggregateXML)) {
            log.warn("fromUserAggregateXml XML injection detected - called with userTokenXml:{} - Returning null", userAggregateXML);
            return null;
        }

        try {
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document doc = documentBuilder.parse(new InputSource(new StringReader(userAggregateXML)));
            XPath xPath = XPathFactory.newInstance().newXPath();
            String uid = (String) xPath.evaluate("//UID", doc, XPathConstants.STRING);
            String userName = (String) xPath.evaluate("//identity/username", doc, XPathConstants.STRING);
            String firstName = (String) xPath.evaluate("//identity/firstname", doc, XPathConstants.STRING);
            String lastName = (String) xPath.evaluate("//identity/lastname", doc, XPathConstants.STRING);
            String email = (String) xPath.evaluate("//identity/email", doc, XPathConstants.STRING);
            String cellPhone = (String) xPath.evaluate("//identity/cellPhone", doc, XPathConstants.STRING);
            String personRef = UserAggregateXpathHelper.getPersonref(userAggregateXML);
            //(String) xPath.evaluate("//identity/personRef", doc, XPathConstants.STRING);
            String defcon = (String) xPath.evaluate("/usertoken/DEFCON", doc, XPathConstants.STRING);


            List<UserApplicationRoleEntry> roleList = new ArrayList<>();
            NodeList applicationNodes = (NodeList) xPath.evaluate("/whydahuser/applications/application/appId", doc, XPathConstants.NODESET);
            for (int i = 1; i < applicationNodes.getLength() + 1; i++) {
                UserApplicationRoleEntry role = new UserApplicationRoleEntry();
                role.setUserName(userName);
                role.setApplicationId((String) xPath.evaluate("/whydahuser/applications/application[" + i + "]/appId", doc, XPathConstants.STRING));
                role.setApplicationName((String) xPath.evaluate("/whydahuser/applications/application[" + i + "]/applicationName", doc, XPathConstants.STRING));
                role.setOrgName((String) xPath.evaluate("/whydahuser/applications/application[" + i + "]/orgName", doc, XPathConstants.STRING));
                role.setRoleName((String) xPath.evaluate("/whydahuser/applications/application[" + i + "]/roleName", doc, XPathConstants.STRING));
                role.setRoleValue((String) xPath.evaluate("/whydahuser/applications/application[" + i + "]/roleValue", doc, XPathConstants.STRING));
                roleList.add(role);
            }
            UserToken userToken = new UserToken();
            userToken.setUid(uid);
            userToken.setUserName(userName);
            userToken.setFirstName(firstName);
            userToken.setLastName(lastName);
            userToken.setEmail(email);
            String securityLevel = "0"; //UserIdentity as source = securitylevel=0
            userToken.setSecurityLevel(securityLevel);
            UserToken.setDefcon(defcon);
            userToken.setPersonRef(personRef);
            userToken.setCellPhone(cellPhone);
            userToken.setRoleList(roleList);
            return userToken;
        } catch (Exception e) {
            log.error("Error parsing userAggregateXML " + userAggregateXML, e);
            return null;
        }
    }

    //String appTokenXml
    public static UserToken fromUserIdentityJson(String userIdentityJSON) {
        UserToken userToken = parseUserIdentityJson(userIdentityJSON);
        userToken.setUserTokenId(generateID());
        userToken.setTimestamp(String.valueOf(System.currentTimeMillis()));
        String securityLevel = "0"; //UserIdentity as source = securitylevel=0
        userToken.setSecurityLevel(securityLevel);
        UserToken.setDefcon(DEFCON.DEFCON5.toString());

        //userToken.setDefcon(defcon);
        //String issuer = extractIssuer(appTokenXml);
        //userToken.setIssuer(TOKEN_ISSUER);
        //userToken.setLifespan(lifespanMs);
        return userToken;
    }


    public static UserToken fromUserAggregateJson(String userAggregateJson) {
        UserToken userToken = parseUserAggregateJson(userAggregateJson);
        userToken.setUserTokenId(generateID());
        userToken.setTimestamp(String.valueOf(System.currentTimeMillis()));
        String securityLevel = "0"; //UserIdentity as source = securitylevel=0
        userToken.setSecurityLevel(securityLevel);
        UserToken.setDefcon(DEFCON.DEFCON5.toString());


        //userToken.setDefcon(defcon);
        //String issuer = extractIssuer(appTokenXml);
        //userToken.setIssuer(TOKEN_ISSUER);
        //userToken.setLifespan(lifespanMs);
        return userToken;
    }

    /**
     * {"uid":"useradmin","username":"useradmin","firstName":"UserAdmin","lastName":"UserAdminWebApp","personRef":"42","email":"whydahadmin@getwhydah.com","cellPhone":"87654321",
     * "roles": [{"applicationId":"19","applicationName":"","applicationRoleName":"WhydahUserAdmin","applicationRoleValue":"1","organizationName":""}]}
     */
    private static UserToken parseUserAggregateJson(String userAggregateJSON) {
        String uid = "";
        try {
            uid = getStringFromJsonpathExpression("$.uid", userAggregateJSON);
        } catch (Exception e) {
            log.warn("Error parsing userAggregateJSON " + userAggregateJSON, e);
        }

        try {
            String userName = getStringFromJsonpathExpression(userAggregateJSON, "$.username");
            String firstName = getStringFromJsonpathExpression(userAggregateJSON, "$.firstName");
            String lastName = getStringFromJsonpathExpression(userAggregateJSON, "$.lastName");
            String email = getStringFromJsonpathExpression(userAggregateJSON, "$.email");
            String cellPhone = getStringFromJsonpathExpression(userAggregateJSON, "$.cellPhone");
            String personRef = getStringFromJsonpathExpression(userAggregateJSON, "$.personRef");

            // TODO  add rolemapping


            JSONObject json = (JSONObject) JSONValue.parseWithException(userAggregateJSON);
            List<UserApplicationRoleEntry> roleList = UserRoleMapper.fromJsonAsList(json.getAsString("roles"));

            UserToken userToken = new UserToken();
            userToken.setUid(uid);
            userToken.setUserName(userName);
            userToken.setFirstName(firstName);
            userToken.setLastName(lastName);
            userToken.setEmail(email);
            UserToken.setDefcon(DEFCON.DEFCON5.toString());
            userToken.setPersonRef(personRef);
            userToken.setCellPhone(cellPhone);
            userToken.setRoleList(roleList);
            return userToken;
        } catch (Exception e) {
            log.error("Error parsing userAggregateJSON " + userAggregateJSON, e);
            return null;
        }
    }

    private static UserToken parseUserIdentityJson(String userIdentityJSON) {
        try {
            String uid = getStringFromJsonpathExpression(userIdentityJSON, "$.uid");
            String userName = getStringFromJsonpathExpression(userIdentityJSON, "$.username");
            String firstName = getStringFromJsonpathExpression(userIdentityJSON, "$.firstName");
            String lastName = getStringFromJsonpathExpression(userIdentityJSON, "$.lastName");
            String email = getStringFromJsonpathExpression(userIdentityJSON, "$.email");
            String cellPhone = getStringFromJsonpathExpression(userIdentityJSON, "$.cellPhone");
            String personRef = getStringFromJsonpathExpression(userIdentityJSON, "$.personRef");


            UserToken userToken = new UserToken();
            userToken.setUid(uid);
            userToken.setUserName(userName);
            userToken.setFirstName(firstName);
            userToken.setLastName(lastName);
            userToken.setEmail(email);
            userToken.setPersonRef(personRef);
            userToken.setCellPhone(cellPhone);
            UserToken.setDefcon(DEFCON.DEFCON5.toString());
            return userToken;
        } catch (Exception e) {
            log.error("Error parsing userAggregateJSON " + userIdentityJSON, e);
            return null;
        }
    }





    private static String generateID() {
        return UUID.randomUUID().toString();
    }


    public static String toXML(UserToken userToken) {
        String userTokenXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<usertoken xmlns:ns2=\"http://www.w3.org/1999/xhtml\" id=\"" + userToken.getUserTokenId() + "\">\n" +
                "    <uid>" + userToken.getUid() + "</uid>\n" +
                "    <timestamp>" + userToken.getUid() + "</timestamp>\n" +
                "    <lifespan>" + userToken.getLifespanFormatted() + "</lifespan>\n" +
                "    <issuer>" + userToken.getIssuer() + "</issuer>\n" +
                "    <securitylevel>" + userToken.getSecurityLevel() + "</securitylevel>\n" +
                "    <DEFCON>" + userToken.getDefcon() + "</DEFCON>\n" +
                "    <username>" + userToken.getUserName() + "</username>\n" +
                "    <firstname>" + userToken.getFirstName() + "</firstname>\n" +
                "    <lastname>" + userToken.getLastName() + "</lastname>\n" +
                "    <cellphone>" + userToken.getCellPhone() + "</cellphone>\n" +
                "    <email>" + userToken.getEmail() + "</email>\n" +
                "    <personref>" + userToken.getPersonRef() + "</personref>\n";
        for (UserApplicationRoleEntry userApplicationRoleEntry : userToken.getRoleList()) {
            userTokenXML = userTokenXML +
                    "    <application ID=\"" + userApplicationRoleEntry.getApplicationId() + "\">\n" +
                    "        <applicationName>" + userApplicationRoleEntry.getApplicationName() + "</applicationName>\n" +
                    "        <organizationName>" + userApplicationRoleEntry.getOrgName() + "</organizationName>\n" +
                    "        <role name=\"" + userApplicationRoleEntry.getRoleName() + "\" value=\"" + userApplicationRoleEntry.getRoleValue() + "\"/>\n" +
                    "    </application>\n";

        }

        userTokenXML = userTokenXML +
                "    <ns2:link type=\"application/xml\" href=\"" + userToken.getNs2link() + "\" rel=\"self\"/>\n" +
                "    <hash type=\"MD5\">" + userToken.getMD5() + "</hash>\n" +
                "</usertoken>";
        return userTokenXML;
    }

    public static boolean isSane(String inputString) {
        if (inputString == null || !(inputString.indexOf("usertoken") < 70) || inputString.length() != Sanitizers.sanitize(inputString).length()) {
            log.trace(" - suspicious XML received, rejected.");
            return false;
        }
        return true;

    }
}
