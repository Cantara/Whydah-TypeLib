package net.whydah.sso.user;

import net.whydah.sso.user.mappers.UserTokenMapper;
import net.whydah.sso.user.types.UserToken;
import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class UserTokenEncryptedSignatureTest {


    @Test
    public void testEncryptedTokenSiganture() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);

        KeyPair pair = keyGen.generateKeyPair();


        UserToken userToken = UserTokenMapper.fromUserAggregateXml(identityXML);

        // Should fail as signature has not been generated yet
        assertEquals(false, userToken.verifySignature(pair));

        // generates and set encrypted signature
        String encryptedSignature = userToken.getEncryptedSignature(pair);
        System.out.println(encryptedSignature);

        System.out.println(userToken);
        // Should validate OK as the userToken is not changes
        assertEquals(true, userToken.verifySignature(encryptedSignature, pair));
        // Should validate OK as the userToken is not changes
        assertEquals(true, userToken.verifySignature(null));

        System.out.println(UserTokenMapper.toXML(userToken));

        userToken.setPersonRef(UUID.randomUUID().toString());
        // Should fail as we have tampered with the token above
        assertEquals(false, userToken.verifySignature(encryptedSignature, pair));
        System.out.println(userToken);


    }

    @Test
    public void testEncryptedTokenSiganturehandovers() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();

        UserToken userToken = UserTokenMapper.fromUserAggregateXml(identityXML);


        // generates and set encrypted signature
        String encryptedSignature = userToken.getEncryptedSignature(pair);
        System.out.println(encryptedSignature);

        System.out.println(userToken);
        // Should validate OK as the userToken is not changes
        assertEquals(true, userToken.verifySignature(encryptedSignature, pair));
        // Should validate OK as the userToken is not changes
        assertEquals(true, userToken.verifySignature(null));
        // Should validate OK as the userToken is not changes
        assertEquals(true, userToken.verifySignature());

        String userTokenXML = UserTokenMapper.toXML(userToken);
        UserToken userToken2 = UserTokenMapper.fromUserTokenXml(userTokenXML);

        assertEquals(userToken.getEncryptedSignature(), userToken2.getEncryptedSignature());
        assertEquals(userToken.getEmbeddedPublicKey(), userToken2.getEmbeddedPublicKey());

        System.out.println(userToken2);
        // Should validate OK as the userToken is not changes
        assertEquals(true, userToken2.verifySignature(encryptedSignature, pair));
        // Should validate OK as the userToken is not changes
        assertEquals(true, userToken2.verifySignature(null));
        // Should validate OK as the userToken is not changes
        assertEquals(true, userToken2.verifySignature());


    }

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

}
