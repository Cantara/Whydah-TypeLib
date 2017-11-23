package net.whydah.sso.ddd.user;


import net.whydah.sso.ddd.model.sso.UserTokenLifespan;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTokenLifespanTest {

    @Test
    public void testIllegalUserTokenLifespan() {
        try {
            assertFalse(UserTokenLifespan.isValid(-1));
            assertFalse(UserTokenLifespan.isValid(-432472));
            assertFalse(UserTokenLifespan.isValid("-1"));
            assertFalse(UserTokenLifespan.isValid("-432472"));
        } catch (Exception e) {
            assertFalse(false);
        }
    }

    @Test
    public void testOKUserTokenLifespan() throws Exception {
        try {
            assertTrue(UserTokenLifespan.isValid(100));
            assertTrue(UserTokenLifespan.isValid(243543));
            assertTrue(UserTokenLifespan.isValid(2336566));

            assertTrue(UserTokenLifespan.isValid(86400000));
            assertTrue(UserTokenLifespan.isValid("86400000"));

        } catch (Exception e) {
            assertFalse(false);
        }

    }




}