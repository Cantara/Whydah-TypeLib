//package net.whydah.sso.ddd.user;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.Serializable;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
//public class UserTokenLifespan implements Serializable {
//
//    private final long lifeSpanInMilliseconds;
//    private final long nowTimestamp = System.currentTimeMillis();
//    public final long ILLEGAL_LIFESPAN = -24323434;
//    private final static Logger log = LoggerFactory.getLogger(UserTokenLifespan.class);
//
//    public UserTokenLifespan(String lifespanInMilliseconds) throws Exception {
//        if (lifespanInMilliseconds.contains("-")) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//            Date parsedDate = dateFormat.parse(lifespanInMilliseconds);
//            Long tempdate = parsedDate.toInstant().toEpochMilli();
//            Long temp = tempdate - nowTimestamp;
//            if (temp < 0 || temp > 100000000) {
//                log.error("Attempt to create an illegal UserTokenLifeSpan - illegal value:{}", lifespanInMilliseconds);
//                this.lifeSpanInMilliseconds = ILLEGAL_LIFESPAN;
//            } else {
//                this.lifeSpanInMilliseconds = temp;
//            }
//
//        } else {
//            Long temp = (Long.parseLong(lifespanInMilliseconds));
//
//            if (temp < 0 || temp > 100000000) {
//                log.error("Attempt to create an illegal UserTokenLifeSpan - illegal value:{}", lifespanInMilliseconds);
//                this.lifeSpanInMilliseconds = ILLEGAL_LIFESPAN;
//            } else {
//                this.lifeSpanInMilliseconds = temp;
//            }
//
//        }
//    }
//
//    public UserTokenLifespan(long lifespanInMilliseconds) {
//
//
//        if (lifespanInMilliseconds < 0 || lifespanInMilliseconds > 100000000) {
//            log.error("Attempt to create an illegal UserTokenLifeSpan - illegal value:{}", lifespanInMilliseconds);
//            this.lifeSpanInMilliseconds = ILLEGAL_LIFESPAN;
//        } else {
//            this.lifeSpanInMilliseconds = lifespanInMilliseconds;
//        }
//    }
//
//
//    public long getValue() {
//        return lifeSpanInMilliseconds;
//    }
//
//    public long getSecondValue() {
//        return lifeSpanInMilliseconds / 1000;
//    }
//
//    public long getMillisecondValue() {
//        return lifeSpanInMilliseconds;
//    }
//
//    public boolean isValid() {
//        return lifeSpanInMilliseconds != ILLEGAL_LIFESPAN;
//    }
//
//    public String getDateFormatted() {
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(nowTimestamp + lifeSpanInMilliseconds);
//    }
//
//
//    public static boolean isValid(String lifeSpanToValidate) {
//        try {
//            return new UserTokenLifespan(lifeSpanToValidate).isValid();
//        } catch (Exception e) {
//        }
//        return false;
//    }
//
//    public static boolean isValid(long lifeSpanToValidate) {
//        try {
//            return new UserTokenLifespan(lifeSpanToValidate).isValid();
//        } catch (Exception e) {
//        }
//        return false;
//    }
//
//    @Override
//    public String toString() {
//        return Long.toString(lifeSpanInMilliseconds);
//    }
//}