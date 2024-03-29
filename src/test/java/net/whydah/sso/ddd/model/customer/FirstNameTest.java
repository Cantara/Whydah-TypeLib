package net.whydah.sso.ddd.model.customer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FirstNameTest {

    private static final Logger log = LoggerFactory.getLogger(FirstNameTest.class);

    @Test
    public void testIllegalFirstName() {
        assertFalse(FirstName.isValid(""));
        assertFalse(FirstName.isValid("234324+2342"));
        assertFalse(FirstName.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(FirstName.isValid("<html>"));
        assertFalse(FirstName.isValid("<javascript:"));
        assertFalse(FirstName.isValid("<html>"));
        assertFalse(FirstName.isValid("a"));  // to short
        assertFalse(FirstName.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(FirstName.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(FirstName.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(FirstName.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(FirstName.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(FirstName.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(FirstName.isValid("22334455"));
        assertFalse(FirstName.isValid("+4722334455"));
        assertFalse(FirstName.isValid("243543"));
        assertFalse(FirstName.isValid("Kristian "));
        assertFalse(FirstName.isValid("Ole-Morten "));
        assertFalse(FirstName.isValid("ola.nordman@test.no"));
        assertFalse(FirstName.isValid("2342424-2342-2342342-342-2342342-24"));
        assertFalse(FirstName.isValid(UUID.randomUUID().toString()));

    }

    // bá é í ó ú ý þ ð ö

    @Test
    public void testOKFirstName() {
        assertTrue(FirstName.isValid("Ole"));
        assertTrue(FirstName.isValid("Jónas"));
        assertTrue(FirstName.isValid("Ole-Morten"));
        assertTrue(FirstName.isValid("Ole-Andre"));
        assertTrue(FirstName.isValid("Ørjan"));
        assertTrue(FirstName.isValid("Åse"));
        assertTrue(FirstName.isValid("Bjørnar"));
        assertTrue(FirstName.isValid("Thor André"));
        assertTrue(FirstName.isValid("Bogotá"));
        assertTrue(FirstName.isValid("Günthern"));
        assertTrue(FirstName.isValid("Kristine B."));

        assertTrue(FirstName.isValid("firstName0"));
        assertTrue(FirstName.isValid("Black Sam"));
//        assertTrue(FirstName.isValid("Samuel \"Black Sam\""));
        assertTrue(FirstName.isValid("Fiorella Fernández"));
        assertTrue(FirstName.isValid("Fabian Nicolás"));
        assertTrue(FirstName.isValid("Rávdná Márjá"));
        assertTrue(FirstName.isValid("Henrik W."));

    }


}