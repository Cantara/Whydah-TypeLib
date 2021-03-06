package net.whydah.sso.user.types;

import net.whydah.sso.ddd.model.base.BaseLifespan;
import net.whydah.sso.ddd.model.sso.UserTokenLifespan;
import net.whydah.sso.user.mappers.UserTokenMapper;
import net.whydah.sso.whydah.DEFCON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class UserTokenCompabillityTest {


    private static final Logger log = LoggerFactory.getLogger(UserTokenCompabillityTest.class);

    @Test
    public void testSerializeUserToken() throws Exception {
        String identityXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<whydahuser>\n" +
                "    <identity>\n" +
                "        <username>admin</username>\n" +
                "        <cellPhone>+1555406789</cellPhone>\n" +
                "        <email>useradmin@getwhydah.com</email>\n" +
                "        <firstname>User</firstname>\n" +
                "        <lastname>Admin</lastname>\n" +
                "        <personref>220</personref>\n" +
                "        <UID>useradmin</UID>\n" +
                "    </identity>\n" +
                "    <applications>\n" +
                "        <application>\n" +
                "            <appId>1923</appId>\n" +
                "            <applicationName>UserAdminWebApplication</applicationName>\n" +
                "            <orgName>Support</orgName>\n" +
                "            <roleName>WhydahUserAdmin</roleName>\n" +
                "            <roleValue>1</roleValue>\n" +
                "        </application>\n" +
                "        <application>\n" +
                "            <appId>1923</appId>\n" +
                "            <applicationName>UserAdminWebApplication</applicationName>\n" +
                "            <orgName>Support</orgName>\n" +
                "            <roleName>TEST</roleName>\n" +
                "            <roleValue>13</roleValue>\n" +
                "        </application>\n" +
                "        <application>\n" +
                "            <appId>1923</appId>\n" +
                "            <applicationName>UserAdminWebApplication</applicationName>\n" +
                "            <orgName>ACS</orgName>\n" +
                "            <roleName>TULL</roleName>\n" +
                "            <roleValue>1</roleValue>\n" +
                "        </application>\n" +
                "        <application>\n" +
                "            <appId>1923</appId>\n" +
                "            <applicationName>UserAdminWebApplication</applicationName>\n" +
                "            <orgName>Support</orgName>\n" +
                "            <roleName>WhydahUserAdmin</roleName>\n" +
                "            <roleValue>1</roleValue>\n" +
                "        </application>\n" +
                "        <application>\n" +
                "            <appId>1923</appId>\n" +
                "            <applicationName>UserAdminWebApplication</applicationName>\n" +
                "            <orgName>Support</orgName>\n" +
                "            <roleName>UserAdmin</roleName>\n" +
                "            <roleValue>100</roleValue>\n" +
                "        </application>\n" +
                "    </applications>\n" +
                "</whydahuser>\n";

        UserToken userToken = UserTokenMapper.fromUserAggregateXml(identityXML);
        userToken.setLifespan(new UserTokenLifespan(BaseLifespan.addPeriod(Calendar.MONTH, 6)).toString());

        FileOutputStream fout = new FileOutputStream("usertoken_new.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(userToken);
        oos.close();

    }

    @Test
    public void testDeSerializeUserToken() {
        try {
            ObjectInputStream objectinputstream = new ObjectInputStream(loadByName("./usertoken_new.ser"));
            UserToken oldUserToken = (UserToken) objectinputstream.readObject();
            objectinputstream.close();
            log.trace("Received deserialized object: {}", oldUserToken);

            assertEquals("220", oldUserToken.getPersonRef());
            assertEquals("User", oldUserToken.getFirstName());
            assertEquals("Admin", oldUserToken.getLastName());
            assertEquals("useradmin@getwhydah.com", oldUserToken.getEmail());
            assertEquals(DEFCON.DEFCON5.toString(), oldUserToken.getDefcon());


        } catch (Exception e) {
            fail("Unable to deserialize old format");
        }
    }

    public static java.io.InputStream loadByName(String name) {
        try {
            java.io.File f = new java.io.File(name);
            if (f.isFile()) {
                log.info("Loading {} from Filesystem", name);
                return new FileInputStream(f);
            } else {

                log.info("Loading {} from Classpath", name);
                return UserTokenCompabillityTest.class.getClassLoader().getResourceAsStream(name);
            }
        } catch (Exception e) {
            log.error("Unable to access file:{}, exception: {} ", name, e);
        }
        return null;
    }

}