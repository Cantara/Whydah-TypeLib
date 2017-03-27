package net.whydah.sso.user;

import net.whydah.sso.user.mappers.UserCredentialMapper;
import net.whydah.sso.user.types.UserCredential;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserCredentialMapperTest {

    @Test
    public void createFromXml() throws Exception {
        UserCredential uC = new UserCredential("Ole", "passord");
        String ucJson = UserCredentialMapper.toXML(uC);

        UserCredential uC2 = UserCredentialMapper.fromXml(ucJson);
        assertTrue(uC.getUserName().equals(uC2.getUserName()));
        assertTrue(uC.getPassword().equals(uC2.getPassword()));
        assertFalse(uC2.toSafeXML().contains(uC2.getPassword()));
    }

    String userNameAndPasswordCredential = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n " +
            "<usercredential>\n" +
            "    <params>\n" +
            "        <username>userName</username>\n" +
            "        <password>secretPw</password>\n" +
            "    </params> \n" +
            "</usercredential>\n";

    String fbUserCredential = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n " +
            "<usercredential>\n" +
            "    <params>\n" +
            "        <fbId>87768798</fbId>\n" +
            "        <username>petter</username>\n" +
            "    </params> \n" +
            "</usercredential>\n";

    String netIqCredential = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n " +
            "<usercredential>\n" +
            "    <params>\n" +
            "        <netiqId>netiqid</netiqId>\n" +
            "        <username>username</username>\n" +
            "    </params> \n" +
            "</usercredential>\n";


}
