package net.whydah.sso.ddd.model.base;

import net.whydah.sso.ddd.model.TimeStamp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class BaseLifespan extends ValueObject {
    private long nowTimestamp = System.currentTimeMillis();
    private final long expiresInMilliseconds;
    private final long invalid_value = -232323;
    private long max_expiry_range = max_expiry_range_default; //around 27 hours*10
    private static final long max_expiry_range_default = 100000000; //around 27 hours
    private int length = 10;

    public BaseLifespan(String lifeCycleInMilliseconds) {
        this(lifeCycleInMilliseconds, max_expiry_range_default);
    }

    public BaseLifespan(String lifeCycleInMilliseconds, long max_expiry_milisecs) {
        super(lifeCycleInMilliseconds);
        if (lifeCycleInMilliseconds == null) {
            expiresInMilliseconds = invalid_value;
            return;
        }
        this.max_expiry_range = max_expiry_milisecs;
        length = String.valueOf(max_expiry_range).length() + 1;//adjust length

        if (lifeCycleInMilliseconds.contains("-") && lifeCycleInMilliseconds.contains(" ")) {
            TimeStamp ts = new TimeStamp(lifeCycleInMilliseconds);
            long temp = ts.getValue() - nowTimestamp;
            assertArgumentRange(temp, 0, max_expiry_range, "Attempt to create an illegal BaseExpires - illegal value: " + lifeCycleInMilliseconds);
            this.expiresInMilliseconds = temp;
        } else if (lifeCycleInMilliseconds.length() < length) {
            Long temp = (Long.parseLong(lifeCycleInMilliseconds));
            assertArgumentRange(temp, 0, max_expiry_range, "Attempt to create an illegal BaseExpires - illegal value: " + lifeCycleInMilliseconds);
            this.expiresInMilliseconds = temp;
        } else {
            Date time = new Date(Long.parseLong(lifeCycleInMilliseconds));
            Long expiresAdjusted = time.toInstant().toEpochMilli() - nowTimestamp;
            assertArgumentRange(expiresAdjusted, 0, max_expiry_range, "Attempt to create an illegal BaseExpires - illegal value: " + lifeCycleInMilliseconds + "- expiresAdjusted: " + expiresAdjusted);
            this.expiresInMilliseconds = expiresAdjusted;
        }

    }

    public BaseLifespan(long lifeCycleInMilliseconds) {
        this(lifeCycleInMilliseconds, max_expiry_range_default);
    }

    public BaseLifespan(long lifeCycleInMilliseconds, long max_expiry_milisecs) {
        super(String.valueOf(lifeCycleInMilliseconds));
        this.max_expiry_range = max_expiry_milisecs;

        if (lifeCycleInMilliseconds > 1500000000000L && lifeCycleInMilliseconds < 1520000000000L) {
            assertStateTrue(lifeCycleInMilliseconds - nowTimestamp >= 0, String.format("Attempt to create an illegal BaseExpires - time in the past:%s time now:%s", lifeCycleInMilliseconds, nowTimestamp));
            this.expiresInMilliseconds = lifeCycleInMilliseconds - nowTimestamp;
        } else {
            assertArgumentRange(lifeCycleInMilliseconds, 0, max_expiry_range, "Attempt to create an illegal BaseExpires - illegal value: " + lifeCycleInMilliseconds);
            this.expiresInMilliseconds = lifeCycleInMilliseconds;
        }

    }

    public long getTimeoutInterval() {
        return expiresInMilliseconds;
    }

    public long getValue() {
        return expiresInMilliseconds;
    }

    public boolean isValid() {
        return expiresInMilliseconds != invalid_value;
    }

    public long getValueAsAbsoluteTimeInMilliseconds() {
        return expiresInMilliseconds + nowTimestamp;
    }

    public long getValueAsRelativeTimeInMilliseconds() {
        return expiresInMilliseconds;
    }

    public long getSecondValue() {
        return (expiresInMilliseconds) / 1000;
    }

    public long getMillisecondValue() {
        return expiresInMilliseconds;
    }

    public String getDateFormatted() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(nowTimestamp + expiresInMilliseconds);
    }

    public static boolean isvalid(String s) {
        return new BaseExpires(s).isvalid();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimeoutInterval());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof BaseExpires)) {
            return false;
        }
        BaseExpires n = (BaseExpires) o;
        return Objects.equals(n.getTimeoutInterval(), getTimeoutInterval());
    }

    public static long addPeriod(int type, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(type, 1);
        java.util.Date dt = cal.getTime();
        return dt.toInstant().toEpochMilli();
    }

    @Override
    public String[] getWhiteList() {
        return null;
    }
}