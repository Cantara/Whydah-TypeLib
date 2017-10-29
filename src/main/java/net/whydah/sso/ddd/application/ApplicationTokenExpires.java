package net.whydah.sso.ddd.application;

import net.whydah.sso.ddd.user.UserTokenLifespan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class ApplicationTokenExpires implements Serializable {

    private final long nowTimestamp = System.currentTimeMillis();
    private final long expiresInMilliseconds;
    public final long ILLEGAL_EXPIRES = -34343434;
    private final static Logger log = LoggerFactory.getLogger(UserTokenLifespan.class);

    public ApplicationTokenExpires(String lifeCycleInMilliseconds) {
        this(Long.parseLong(lifeCycleInMilliseconds));
    }

    public ApplicationTokenExpires(long lifeCycleInMilliseconds) {


        if (lifeCycleInMilliseconds < 0 || lifeCycleInMilliseconds > 100000000) {
            log.error("Attempt to create an illegal ApplicationTokenExpires - illegal length:{}", lifeCycleInMilliseconds);
            this.expiresInMilliseconds = ILLEGAL_EXPIRES;
        } else {
            this.expiresInMilliseconds = lifeCycleInMilliseconds;
        }
    }


    public long getValue() {
        return expiresInMilliseconds;
    }

    public long getSecondValue() {
        return expiresInMilliseconds / 1000;
    }

    public long getMillisecondValue() {
        return expiresInMilliseconds;
    }

    public boolean isValid() {
        return expiresInMilliseconds != ILLEGAL_EXPIRES;
    }


    public String getDateFormatted() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(nowTimestamp + expiresInMilliseconds);
    }

    @Override
    public String toString() {
        return Long.toString(expiresInMilliseconds);
    }
}