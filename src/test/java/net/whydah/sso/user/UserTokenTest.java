package net.whydah.sso.user;

import java.util.Calendar;
import java.util.Date;

import net.whydah.sso.ddd.model.base.BaseLifespan;
import net.whydah.sso.ddd.model.user.TimeStamp;
import net.whydah.sso.user.mappers.UserTokenMapper;
import net.whydah.sso.user.types.UserToken;
import net.whydah.sso.whydah.DEFCON;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by totto on 19.09.16.
 */
public class UserTokenTest {

    @Test
    public void createFromUserIdentityXML() {
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

        userToken.setDefcon(DEFCON.DEFCON2.toString());
        assertEquals(DEFCON.DEFCON2.toString(), userToken.getDefcon());

        UserToken userToken2 = UserTokenMapper.fromUserAggregateXml(identityXML);
        assertEquals(DEFCON.DEFCON2.toString(), userToken2.getDefcon());
        userToken2.setDefcon(DEFCON.DEFCON5.toString());
        assertEquals(DEFCON.DEFCON5.toString(), userToken2.getDefcon());
        assertEquals(DEFCON.DEFCON5.toString(), userToken.getDefcon());
        userToken2.setDefcon(DEFCON.DEFCON1.toString());
        UserToken userToken3 = UserTokenMapper.fromUserTokenXml(UserTokenMapper.toXML(userToken2));
        assertEquals("220", userToken3.getPersonRef());
        assertEquals("User", userToken3.getFirstName());
        assertEquals("Admin", userToken3.getLastName());
        assertEquals("useradmin@getwhydah.com", userToken3.getEmail());
        assertEquals(DEFCON.DEFCON1.toString(), userToken3.getDefcon());

        Long expires = Long.parseLong(userToken2.getTimestamp());
        Long now = System.currentTimeMillis();
        assertTrue(expires < now);


    }
    
   

}
