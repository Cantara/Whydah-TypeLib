package net.whydah.sso.ddd.model.userrole;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoleValueTest {


    private static final Logger log = LoggerFactory.getLogger(RoleValueTest.class);

    @Test
    public void testIllegalRoleValue() {
        assertFalse(RoleValue.isValid("<javascript:"));
        assertFalse(RoleValue.isValid("<html>"));
        assertFalse(RoleValue.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(RoleValue.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(RoleValue.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(RoleValue.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(RoleValue.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(RoleValue.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));

    }

    @Test
    public void testOKRoleValue() {
        assertTrue(RoleValue.isValid(""));
        assertTrue(RoleValue.isValid("abc"));  // to short
        assertTrue(RoleValue.isValid("243543"));
        assertTrue(RoleValue.isValid("+4722334455"));
        assertTrue(RoleValue.isValid("asadadsaYUYI"));
        assertTrue(RoleValue.isValid("ola.nordman"));
        assertTrue(RoleValue.isValid("ola.nordman@test.no"));
        assertTrue(RoleValue.isValid("22334455"));
        assertTrue(RoleValue.isValid("TestRolename111f653f-7958-474e-b5ff-9ef428323e27"));
        assertTrue(RoleValue.isValid(UUID.randomUUID().toString()));
        assertTrue(RoleValue.isValid("Employee"));
        assertTrue(RoleValue.isValid("{\"deliveryaddress\":{\"reference\":\"\",\"postalcode\":\"0151\",\"countryCode\":\"no\",\"contact\":{\"phoneNumber\":\"91908252\",\"emailConfirmed\":true,\"name\":\"Brian Weeteling\",\"email\":\"brian@geta.no\",\"phoneNumberConfirmed\":\"true\"},\"name\":\"Brian Weeteling\",\"postalcity\":\"Oslo\",\"addressLine1\":\"Rådhusgata 30B\",\"company\":\"\",\"addressLine2\":\"null\",\"deliveryinformation\":{\"pickupPoint\":\"\",\"additionalAddressInfo\":\"\",\"Deliverytime\":\"\"},\"tags\":\"\"}}"));
        assertTrue(RoleValue.isValid("234324+2342"));
    }


}