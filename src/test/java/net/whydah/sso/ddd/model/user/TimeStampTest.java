package net.whydah.sso.ddd.model.user;

import java.util.Calendar;

import net.whydah.sso.ddd.model.base.BaseLifespan;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimeStampTest {

    private static final Logger log = LoggerFactory.getLogger(TimeStampTest.class);

    //SEE testTimeStamp
//    @Test
//    public void testIllegalTimeStamp() throws Exception {
//        assertFalse(TimeStamp.isValid(432472186));  // Too high interval
//        assertFalse(TimeStamp.isValid("1432472186"));  // Too high interval
//        assertFalse(TimeStamp.isValid(140943309377L));  // Too far in the past
//        assertFalse(TimeStamp.isValid(1409343309377L));  // Too far in the past
//        assertFalse(TimeStamp.isValid(1709343309377L));  // Too far in the future
//        assertFalse(TimeStamp.isValid("1709343309377"));  // Too far in the future
//        assertFalse(TimeStamp.isValid(-1));  // Negative delta does not give a meaning
//        assertFalse(TimeStamp.isValid("-1"));  // Negative delta does not give a meaning
//    }

    @Test
    public void testOKTimeStamp() throws Exception {
        assertTrue(TimeStamp.isValid(String.valueOf((System.currentTimeMillis()) - 300 * 1000)));  // Time in the past is OK
        assertTrue(TimeStamp.isValid(String.valueOf((System.currentTimeMillis()) - 3000 * 1000)));  // Time in the past is OK
        assertTrue(TimeStamp.isValid(String.valueOf((System.currentTimeMillis()) - 30000 * 1000)));  // Time in the past is OK
        assertTrue(TimeStamp.isValid(String.valueOf((System.currentTimeMillis()) + 300 * 1000)));  // time in the future
        new TimeStamp("Wed Nov 15 22:08:41 CET 2017");
        assertTrue(TimeStamp.isValid("Wed Nov 15 22:08:41 CET 2017"));  // Time in the past is OK

    }
    
    @Test
    public void testTimeStamp(){
    	
    	//still valid in the future
    	long time = BaseLifespan.addPeriod(Calendar.YEAR, 10);
    	assertTrue(TimeStamp.isValid(time));
    	
    	time = BaseLifespan.addPeriod(Calendar.YEAR, 100);
    	assertTrue(TimeStamp.isValid(time));
    	
    	//still valid in the past
    	time = BaseLifespan.addPeriod(Calendar.YEAR, -10);
    	assertTrue(TimeStamp.isValid(time));
    	
    	time = BaseLifespan.addPeriod(Calendar.YEAR, -100);
    	assertTrue(TimeStamp.isValid(time));
    	
    	//invalid too far
    	time = BaseLifespan.addPeriod(Calendar.YEAR, 101);
    	assertFalse(TimeStamp.isValid(time));
    	
    	//invalid too old
    	time = BaseLifespan.addPeriod(Calendar.YEAR, -101);
    	assertFalse(TimeStamp.isValid(time));
    	
    	
    }
}