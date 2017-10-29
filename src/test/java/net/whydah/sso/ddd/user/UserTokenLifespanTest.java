package net.whydah.sso.ddd.user;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTokenLifespanTest {

    @Test
    public void testIllegalUserTokenLifespan() {
        try {
            assertFalse(new UserTokenLifespan(-1).isValid());
            assertFalse(new UserTokenLifespan(-432472).isValid());
            assertFalse(new UserTokenLifespan("-1").isValid());
            assertFalse(new UserTokenLifespan("-432472").isValid());
        } catch (Exception e) {
            assertFalse(false);
        }
    }

    @Test
    public void testOKUserTokenLifespan() throws Exception {
        try {
            assertTrue(new UserTokenLifespan(100).isValid());
            assertTrue(new UserTokenLifespan(243543).isValid());
            assertTrue(new UserTokenLifespan(2336566).isValid());

            assertTrue(new UserTokenLifespan(86400000).isValid());
            assertTrue(new UserTokenLifespan("86400000").isValid());

        } catch (Exception e) {
            assertFalse(false);
        }

    }




}