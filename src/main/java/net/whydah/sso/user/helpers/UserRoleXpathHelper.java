package net.whydah.sso.user.helpers;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import net.whydah.sso.user.types.UserRoleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class UserRoleXpathHelper {

    private static final Logger log = LoggerFactory.getLogger(UserRoleXpathHelper.class);

    public static UserRoleVO fromXml(String roleXml) {

        String id = UserXpathHelper.findValue(roleXml, "/application/id");
        String userId = UserXpathHelper.findValue(roleXml, "/application/uid");
        String appId = UserXpathHelper.findValue(roleXml, "/application/appId");
        String appName = UserXpathHelper.findValue(roleXml, "/application/applicationName");
        String orgName = UserXpathHelper.findValue(roleXml, "/application/orgName");
        String roleName = UserXpathHelper.findValue(roleXml, "/application/roleName");
        String roleValue = UserXpathHelper.findValue(roleXml, "/application/roleValue");
        UserRoleVO userRole = new UserRoleVO(null, appId, orgName, roleName, roleValue);
        userRole.setId(id);
        userRole.setUserId(userId);
        return userRole;
    }


    public static UserRoleVO[] getUserRoleFromUserTokenXml(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty userTokenId.");
        } else {
            String appid;
            String orgName;
            String rolename;
            String roleValue;
            String userName=findXpathValue(userTokenXml, "/usertoken/username");
            String expression = "count(/usertoken/application)";
            int noOfApps= Integer.valueOf(findXpathValue(userTokenXml,expression));
            int noOfRoles =  Integer.valueOf(findXpathValue(userTokenXml, "count(//role)"));
            UserRoleVO[] result = new UserRoleVO[noOfRoles];
            int roleindex=0;
            for (int n=1;n<=noOfApps;n++) {
                    appid = findXpathValue(userTokenXml, "//usertoken/application["+n+"]/@ID");
                    int noSubRoles=Integer.valueOf(findXpathValue(userTokenXml,"count(//application["+n+"]/organizationName)"));
                    for (int m=1;m<=noSubRoles;m++){
                        try {
                        orgName = findXpathValue(userTokenXml, "//application["+n+"]/organizationName["+m+"]");
                        rolename = findXpathValue(userTokenXml, "//application["+n+"]/role["+m+"]/@name");
                        roleValue = findXpathValue(userTokenXml, "//application["+n+"]/role["+m+"]/@value");
                        UserRoleVO ur = new UserRoleVO(userName, appid, orgName, rolename, roleValue);
                        result[roleindex++] = ur;
                        } catch (PathNotFoundException pnpe) {
                            return null;
                        }

                    }
            }
            return result;
        }
        return null;
    }

    public static List<UserRoleVO> getUserRoleFromUserAggregateXml(String userAggregateXML) {
        List<UserRoleVO> userRoles = new ArrayList<>();
        if (userAggregateXML == null) {
            log.debug("userAggregateXML was empty, so returning null.");
        } else {
            String appid;
            String orgName;
            String rolename;
            String roleValue;
            String userName=findXpathValue(userAggregateXML, "/whydahuser/identity/username");
            String expression = "count(/whydahuser/applications/application)";
            int noOfRoles= Integer.valueOf(findXpathValue(userAggregateXML,expression));

            for (int n=1;n<=noOfRoles;n++) {
                try {
                    appid = findXpathValue(userAggregateXML, "/whydahuser/applications/application[" + n + "]/appId");
                    orgName = findXpathValue(userAggregateXML, "/whydahuser/applications/application[" + n + "]/orgName");
                    rolename = findXpathValue(userAggregateXML, "/whydahuser/applications/application[" + n + "]/roleName");
                    roleValue = findXpathValue(userAggregateXML, "/whydahuser/applications/application[" + n + "]/roleValue");
                    UserRoleVO userRole = new UserRoleVO(userName, appid, orgName, rolename, roleValue);
                    userRoles.add(userRole);
                } catch (PathNotFoundException pnpe) {
                    log.warn("Could not parse userAggregateXml {}, reason {}", userAggregateXML,pnpe.getMessage());
//                    return null;
                }
            }

        }
        return userRoles;
    }

    public static UserRoleVO[] getUserRoleFromUserAggregateJson(String userAggregateJson) {
        if (userAggregateJson == null) {
            log.debug("userAggregateJson was empty, so returning null.");
        } else {
            String appid;
            String orgName;
            String rolename;
            String roleValue;
            List<String> roles=findJsonpathList(userAggregateJson, "$.roles[*]");
            String userName= findJsonPathValue(userAggregateJson, "$.username");
            UserRoleVO[] result = new UserRoleVO[roles.size()];
            for (int n=0;n<roles.size();n++){
                try {
                    appid = findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationId");
                    orgName = findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationName");
                    rolename = findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationRoleName");
                    roleValue = findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationRoleValue");
                    UserRoleVO ur = new UserRoleVO(userName,appid, orgName, rolename, roleValue);
                    result[n]=ur;
                } catch (PathNotFoundException pnpe){
                    return null;
                }

            }
            return result;
        }
        return null;
    }



    public static String getOrgNameFromRoleXml(String roleXml) {
        String orgName = "";
        if (roleXml == null) {
            log.debug("roleXml was empty, so returning empty orgName.");
        } else {
            String expression = "/application/orgName";
            orgName = findXpathValue(roleXml, expression);
        }
        return orgName;
    }


    private static String findXpathValue(String xmlString,  String expression) {
        String value = "";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(xmlString)));
            XPath xPath = XPathFactory.newInstance().newXPath();


            XPathExpression xPathExpression = xPath.compile(expression);
            value = xPathExpression.evaluate(doc);
        } catch (Exception e) {
            log.warn("Failed to parse xml. Expression {}, xml {}, ", expression, xmlString, e);
        }
        return value;
    }

    private static List<String> findJsonpathList(String jsonString,  String expression) throws PathNotFoundException {
        List<String> result=null;
        try {
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
            result= JsonPath.read(document, expression);

        } catch (Exception e) {
            log.warn("Failed to parse JSON. Expression {}, JSON {}, ", expression, jsonString, e);
        }
        return result;
    }

    private static String findJsonPathValue(String jsonString, String expression) throws PathNotFoundException {
        String value = "";
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
            String result= JsonPath.read(document, expression);
            value=result.toString();

        return value;
    }


}
