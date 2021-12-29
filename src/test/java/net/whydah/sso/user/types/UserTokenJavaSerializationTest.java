package net.whydah.sso.user.types;

import net.whydah.sso.user.mappers.UserTokenMapper;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;

public class UserTokenJavaSerializationTest {

    @Test
    public void thatUserTokenCanBeSerializedAndDeserialized() throws IOException, ClassNotFoundException {
        // given token
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
        UserToken expectedToken = UserTokenMapper.fromUserAggregateXml(identityXML);

        // java serialization (used by hazelcast in STS)
        ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(expectedToken);
        oos.flush();
        oos.close();

        // java deserialization (used by hazelcast in STS)
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        UserToken actualToken = (UserToken) ois.readObject();

        // compare
        assertEquals(expectedToken.getDefcon(), actualToken.getDefcon());
        assertEquals(expectedToken.getEmail(), actualToken.getEmail());
        assertEquals(expectedToken.getFirstName(), actualToken.getFirstName());
        assertEquals(expectedToken.getLastName(), actualToken.getLastName());
        assertEquals(expectedToken.getPersonRef(), actualToken.getPersonRef());
        assertEquals(expectedToken.getRoleList(), actualToken.getRoleList());
        assertEquals(expectedToken.getTimestamp(), actualToken.getTimestamp());
        assertEquals(expectedToken.getCellPhone(), actualToken.getCellPhone());
        assertEquals(expectedToken.getIssuer(), actualToken.getIssuer());
        assertEquals(expectedToken.getSecurityLevel(), actualToken.getSecurityLevel());
        assertEquals(expectedToken.getUid(), actualToken.getUid());
        assertEquals(expectedToken.getUserTokenId(), actualToken.getUserTokenId());
        assertEquals(expectedToken.getUserName(), actualToken.getUserName());
        assertEquals(expectedToken.getEmbeddedPublicKey(), actualToken.getEmbeddedPublicKey());
        assertEquals(expectedToken.getEncryptedSignature(), actualToken.getEncryptedSignature());
        assertEquals(expectedToken.getLifespan(), actualToken.getLifespan());
        assertEquals(expectedToken.getMD5(), actualToken.getMD5());
        assertEquals(expectedToken.getLastSeen(), actualToken.getLastSeen());
        assertEquals(expectedToken.getNs2link(), actualToken.getNs2link());
    }
}
