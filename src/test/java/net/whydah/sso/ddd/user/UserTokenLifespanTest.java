package net.whydah.sso.ddd.user;


import net.whydah.sso.ddd.model.base.BaseLifespan;
import net.whydah.sso.ddd.model.sso.UserTokenLifespan;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTokenLifespanTest {

    @Test
    public void testIllegalUserTokenLifespan() {
        assertFalse(UserTokenLifespan.isValid(null));
        assertFalse(UserTokenLifespan.isValid(-1));
        assertFalse(UserTokenLifespan.isValid(-432472));
        assertFalse(UserTokenLifespan.isValid("-1"));
        assertFalse(UserTokenLifespan.isValid("-432472"));
        assertFalse(UserTokenLifespan.isValid(BaseLifespan.addPeriod(Calendar.MONTH, 19)));  // Too far in the future
        assertFalse(UserTokenLifespan.isValid(BaseLifespan.addPeriod(Calendar.MONTH, 19) - System.currentTimeMillis()));  // Too far in the future
    }

    @Test
    public void testOKUserTokenLifespan() {
        assertTrue(UserTokenLifespan.isValid(100));
        assertTrue(UserTokenLifespan.isValid(243543));
        assertTrue(UserTokenLifespan.isValid(2336566));

        assertTrue(UserTokenLifespan.isValid(86400000));
        assertTrue(UserTokenLifespan.isValid(BaseLifespan.addPeriod(Calendar.MONTH, 6)));
        assertTrue(UserTokenLifespan.isValid("86400000"));
        System.out.println(new UserTokenLifespan("86400000").getDateFormatted());
        System.out.println(new UserTokenLifespan("86400000").getValueAsRelativeTimeInMilliseconds());
        assertTrue(UserTokenLifespan.isValid("2592000000"));
        System.out.println(new UserTokenLifespan("2592000000").getDateFormatted());
        System.out.println(new UserTokenLifespan("2592000000").getValueAsRelativeTimeInMilliseconds());


        assertTrue(UserTokenLifespan.isValid(System.currentTimeMillis()));
        assertTrue(UserTokenLifespan.isValid(BaseLifespan.addPeriod(Calendar.MONTH, 6) - 1));

        assertTrue(UserTokenLifespan.isValid(BaseLifespan.addPeriod(Calendar.MONTH, 6) - System.currentTimeMillis()));
        System.out.println(new UserTokenLifespan(BaseLifespan.addPeriod(Calendar.MONTH, 6)).getDateFormatted());

        assertTrue(UserTokenLifespan.isValid(BaseLifespan.addPeriod(Calendar.MONTH, 12)));  // Too far in the future
        assertTrue(UserTokenLifespan.isValid(BaseLifespan.addPeriod(Calendar.MONTH, 12) - System.currentTimeMillis()));  // Too far in the future

        assertTrue(UserTokenLifespan.isValid(BaseLifespan.addPeriod(Calendar.MONTH, 17)));  // Too far in the future
        assertTrue(UserTokenLifespan.isValid(BaseLifespan.addPeriod(Calendar.MONTH, 17) - System.currentTimeMillis()));  // Too far in the future
    }


}