package net.whydah.sso.ddd.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationTokenExpires extends ValueObject {

    private long nowTimestamp = System.currentTimeMillis();
    private long expiresInMilliseconds;
    public final long ILLEGAL_EXPIRES = -34343434;
    

    public ApplicationTokenExpires(String lifeCycleInMilliseconds) {
//        if (lifeCycleInMilliseconds.contains("-") && lifeCycleInMilliseconds.contains(" ")) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//            Date parsedDate = dateFormat.parse(lifeCycleInMilliseconds);
//            Long tempdate = parsedDate.toInstant().toEpochMilli();
//            Long temp = tempdate - nowTimestamp;
//            if (temp < 0 || temp > 100000000) {
//                log.error("Attempt to create an illegal ApplicationTokenExpires - illegal value:{}", lifeCycleInMilliseconds);
//                this.expiresInMilliseconds = ILLEGAL_EXPIRES;
//            } else {
//                this.expiresInMilliseconds = temp;
//            }
//
//        } else if (lifeCycleInMilliseconds.length() < 10) {
//            Long temp = (Long.parseLong(lifeCycleInMilliseconds));
//
//            if (temp < 0 || temp > 100000000) {
//                log.error("Attempt to create an illegal ApplicationTokenExpires - illegal value:{}", lifeCycleInMilliseconds);
//                this.expiresInMilliseconds = ILLEGAL_EXPIRES;
//            } else {
//                this.expiresInMilliseconds = temp;
//            }
//
//        } else {
//            Date time = new Date(Long.parseLong(lifeCycleInMilliseconds));
//            Long expiresAdjusted = time.toInstant().toEpochMilli() - nowTimestamp;
//            if (expiresAdjusted < 0 || expiresAdjusted > 100000000) {
//                log.error("Attempt to create an illegal ApplicationTokenExpires - illegal value:{} - expiresAdjusted: {}", lifeCycleInMilliseconds, expiresAdjusted);
//                this.expiresInMilliseconds = ILLEGAL_EXPIRES;
//            } else {
//                this.expiresInMilliseconds = expiresAdjusted;
//            }
//        }
    	
    	  if (lifeCycleInMilliseconds.contains("-") && lifeCycleInMilliseconds.contains(" ")) {
              SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
              Date parsedDate;
  			try {
  				parsedDate = dateFormat.parse(lifeCycleInMilliseconds);
  				Long tempdate = parsedDate.toInstant().toEpochMilli();
  				Long temp = tempdate - nowTimestamp;
  				assertArgumentRange(temp, 0, 100000000, "Attempt to create an illegal ApplicationTokenExpires - illegal value: " + lifeCycleInMilliseconds);
  	            this.expiresInMilliseconds = temp;
  			} catch (ParseException e) {
  				 e.printStackTrace();
  				 throwException("Attempt to create an illegal ApplicationTokenExpires - illegal value: " + lifeCycleInMilliseconds);
  			}
              
          } else if (lifeCycleInMilliseconds.length() < 10) {
              Long temp = (Long.parseLong(lifeCycleInMilliseconds));
              assertArgumentRange(temp, 0, 100000000, "Attempt to create an illegal ApplicationTokenExpires - illegal value: " + lifeCycleInMilliseconds);
              this.expiresInMilliseconds = temp;
          } else {
              Date time = new Date(Long.parseLong(lifeCycleInMilliseconds));
              Long expiresAdjusted = time.toInstant().toEpochMilli() - nowTimestamp;
              assertArgumentRange(expiresAdjusted, 0, 100000000, "Attempt to create an illegal ApplicationTokenExpires - illegal value: " + lifeCycleInMilliseconds + "- expiresAdjusted: " + expiresAdjusted);
              this.expiresInMilliseconds = expiresAdjusted;
          }

    }

    public ApplicationTokenExpires(long lifeCycleInMilliseconds) {


        // Let us be nice and handle absolute time inputs too i.e.               1509267713753L
//        if (lifeCycleInMilliseconds > 1500000000000L && lifeCycleInMilliseconds < 1520000000000L) {
//            if (lifeCycleInMilliseconds - nowTimestamp < 0) {
//                log.error("Attempt to create an illegal ApplicationTokenExpires - time in the past:{} time now:{}", lifeCycleInMilliseconds, nowTimestamp);
//                this.expiresInMilliseconds = ILLEGAL_EXPIRES;
//            } else {
//                this.expiresInMilliseconds = lifeCycleInMilliseconds - nowTimestamp;
//            }
//        } else if (lifeCycleInMilliseconds < 0 || lifeCycleInMilliseconds > 100000000) {
//            log.error("Attempt to create an illegal ApplicationTokenExpires - illegal length:{}", lifeCycleInMilliseconds);
//            this.expiresInMilliseconds = ILLEGAL_EXPIRES;
//        } else {
//            this.expiresInMilliseconds = lifeCycleInMilliseconds;
//        }
    	assertArgumentRange(lifeCycleInMilliseconds, 0, 100000000, "Attempt to create an illegal ApplicationTokenExpires - illegal value: " + lifeCycleInMilliseconds);
        if (lifeCycleInMilliseconds > 1500000000000L && lifeCycleInMilliseconds < 1520000000000L) {
            assertStateTrue(lifeCycleInMilliseconds - nowTimestamp >= 0, String.format("Attempt to create an illegal ApplicationTokenExpires - time in the past:{} time now:{}", lifeCycleInMilliseconds, nowTimestamp));
            this.expiresInMilliseconds = lifeCycleInMilliseconds - nowTimestamp;
        } else {
            this.expiresInMilliseconds = lifeCycleInMilliseconds;
        }
    }


    public long getValue() {
        return expiresInMilliseconds + nowTimestamp;
    }

    public long getValueAsAbsoluteTimeInMilliseconds() {
        return expiresInMilliseconds + nowTimestamp;
    }

    public long getValueAsRelativeTimeInMilliseconds() {
        return expiresInMilliseconds + nowTimestamp;
    }

    public long getSecondValue() {
        return expiresInMilliseconds + nowTimestamp / 1000;
    }

    public long getMillisecondValue() {
        return expiresInMilliseconds + nowTimestamp;
    }


    public boolean isValid() {
        return expiresInMilliseconds != ILLEGAL_EXPIRES;
    }


    public String getDateFormatted() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(nowTimestamp + expiresInMilliseconds);
    }


    public static boolean isValid(String expiresToValidate) {
        try {
            new ApplicationTokenExpires(expiresToValidate);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isValid(long expiresToValidate) {
        try {
            new ApplicationTokenExpires(expiresToValidate);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public String toString() {
        return Long.toString(expiresInMilliseconds);
    }
}