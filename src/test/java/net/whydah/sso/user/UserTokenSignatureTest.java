package net.whydah.sso.user;

import net.whydah.sso.user.mappers.UserTokenMapper;
import net.whydah.sso.user.types.UserToken;
import net.whydah.sso.whydah.DEFCON;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTokenSignatureTest {


    @Test
    public void testSignatureCreateFromUserIdentityXML() {
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

        assertEquals("220", userToken.getPersonRef());
        assertEquals("User", userToken.getFirstName());
        assertEquals("Admin", userToken.getLastName());
        assertEquals("useradmin@getwhydah.com", userToken.getEmail());
        assertEquals(DEFCON.DEFCON5.toString(), userToken.getDefcon());

        userToken.setIssuer("https://Selfissue.somewhere.com/");
        assertTrue(UserTokenMapper.validateUserTokenMD5Signature(UserTokenMapper.toXML(userToken)));


    }

}
