package net.whydah.sso.ddd.model.application;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompanyNameTest {

    private static final Logger log = LoggerFactory.getLogger(CompanyNameTest.class);

    @Test
    public void testIllegalCompanyName() {
        assertFalse(CompanyName.isValid("httpa://whydahdev.cantara.no"));  // strange characters
        assertFalse(CompanyName.isValid("-1"));  // short
//        assertFalse(CompanyName.isValid("143"));  // No characters
        assertFalse(CompanyName.isValid(UUID.randomUUID().toString()));
    }

    @Test
    public void testOKCompanyName() {
        assertTrue(CompanyName.isValid("useradmin"));
        assertTrue(CompanyName.isValid("11173648731648525"));
        assertTrue(CompanyName.isValid("jeg gikk en tur i skogen og hørte noe"));
        
        assertTrue(CompanyName.isValid("petter_smart "));
        assertTrue(CompanyName.isValid("abc"));

    }


}