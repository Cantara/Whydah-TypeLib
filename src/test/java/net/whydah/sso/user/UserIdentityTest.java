package net.whydah.sso.user;

import net.whydah.sso.user.mappers.UserIdentityMapper;
import net.whydah.sso.user.types.UserIdentity;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by baardl on 18.06.15.
 */
public class UserIdentityTest {

    private static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private String username = null;
    private UserIdentity userIdentity = null;

    @Before
    public void setUp() throws Exception {
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        username = "_temp_username_" + System.currentTimeMillis();
         userIdentity = new UserIdentity(username,"first","last","ref",username +"@example.com","+4712345678");
    }

    @Test
    public void testToXML() throws Exception {
        DocumentBuilder db = dbf.newDocumentBuilder();
        String userIdentityXml = UserIdentityMapper.toXML(userIdentity);
        Document doc = db.parse(new InputSource(new StringReader(userIdentityXml)));
        XPath xPath = XPathFactory.newInstance().newXPath();

        String usernameXml = (String) xPath.evaluate("/whydahuser/identity/username", doc, XPathConstants.STRING);
        assertEquals(username, usernameXml);
        String cellPhone = (String) xPath.evaluate("/whydahuser/identity/cellPhone", doc, XPathConstants.STRING);
        assertEquals("+4712345678", cellPhone);
        String personRef = (String) xPath.evaluate("/whydahuser/identity/personRef", doc, XPathConstants.STRING);
        assertEquals("ref", personRef);
        String firstName = (String) xPath.evaluate("/whydahuser/identity/firstname", doc, XPathConstants.STRING);
        assertEquals("first", firstName);
        String lastName = (String) xPath.evaluate("/whydahuser/identity/lastname", doc, XPathConstants.STRING);
        assertEquals("last", lastName);
        String email = (String) xPath.evaluate("/whydahuser/identity/email", doc, XPathConstants.STRING);
        assertEquals(username +"@example.com",email);
    }


    @Test
    public void createFromJson() throws Exception {
        String userJson = "\n" +
                "{\"username\":\"helloMe\", \"firstName\":\"hello\", \"lastName\":\"meLastName\", \"personRef\":\"\", \"email\":\"hello.me@example.com\", \"cellPhone\":\"+47 90221133\"}";
        UserIdentity minimalUser = UserIdentityMapper.fromUserIdentityWithNoIdentityJson(userJson);
        assertEquals(minimalUser.getUsername(), "helloMe");
        assertEquals(minimalUser.getFirstName(), "hello");
        assertEquals(minimalUser.getLastName(), "me");
        assertEquals(minimalUser.getPersonRef(), "");
        assertEquals(minimalUser.getEmail(), "hello.me@example.com");
        assertEquals(minimalUser.getCellPhone(), "+47 90221133");
        assertTrue(minimalUser.toString().indexOf("password") < 0);
    }

    @Test
    public void createFromSignupJson() throws Exception {
        String signupJson = "{\"uid\":\"4456\",\"username\":\"totto@totto.org\",\"firstName\":\"Thor Henning\",\"lastName\":\"Hetland\",\"personRef\":\"\",\"email\":\"totto@totto.org\",\"cellPhone\":\"91905054\"}";
        UserIdentity minimalUser = UserIdentityMapper.fromUserIdentityWithNoIdentityJson(signupJson);
        assertEquals(minimalUser.getUsername(), "totto@totto.org");

    }

    @Test
    public void festFromSignupJson() throws Exception {
        String userJson = " {\"username\":\"ten\",\"firstName\":\"Therese\",\"lastName\":\"Engen\",\"personRef\":\"null\",\"email\":\"ten@capraconsulting.no\",\"cellPhone\":\"92277077\",\"password\":null,\"uid\":\"710955e2-dd20-47d4-a0ec-05a352118db0\",\"personName\":\"Therese Engen\"}";
        UserIdentityMapper.fromUserIdentityJson(userJson);
    }
}