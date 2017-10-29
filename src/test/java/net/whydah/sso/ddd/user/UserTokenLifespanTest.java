package net.whydah.sso.ddd.user;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTokenLifespanTest {

    @Test
    public void testIllegalUserTokenLifespan() {
        assertFalse(new UserTokenLifespan(-1).isValid());
        assertFalse(new UserTokenLifespan(-432472).isValid());
    }

    @Test
    public void testOKUserTokenLifespan() {
        assertTrue(new UserTokenLifespan(100).isValid());
        assertTrue(new UserTokenLifespan(243543).isValid());
        assertTrue(new UserTokenLifespan(2336566).isValid());
    }


}