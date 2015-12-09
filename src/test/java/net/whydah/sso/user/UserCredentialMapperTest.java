package net.whydah.sso.user;

import net.whydah.sso.user.mappers.UserCredentialMapper;
import net.whydah.sso.user.types.UserCredential;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserCredentialMapperTest {

    @Test
    public void createFromXml() throws Exception {
        UserCredential uC = new UserCredential("Ole", "passord");
        String ucJson = UserCredentialMapper.toXML(uC);

        UserCredential uC2 = UserCredentialMapper.fromXml(ucJson);
        assertTrue(uC.getUserName().equals(uC2.getUserName()));
        assertTrue(uC.getPassword().equals(uC2.getPassword()));
    }

}
