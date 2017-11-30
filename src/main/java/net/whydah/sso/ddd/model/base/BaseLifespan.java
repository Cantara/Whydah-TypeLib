package net.whydah.sso.ddd.model.base;

import net.whydah.sso.ddd.model.user.TimeStamp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class BaseLifespan extends ValueObject {
    private long nowTimestamp = System.currentTimeMillis();
    private final long lifespanInMilliseconds;
    private final long invalid_value = -232323;
    private long max_lifespan_range = max_lifespan_range_default; //around 27 hours*10
    private static final long max_lifespan_range_default = 100000000; //around 27 hours
    private int length = 10;

    public BaseLifespan(String lifespanInMilliseconds) {
        this(lifespanInMilliseconds, max_lifespan_range_default);
    }

    public BaseLifespan(String lifespanInMilliseconds, long max_expiry_milisecs) {
        super(lifespanInMilliseconds);
        if (lifespanInMilliseconds == null) {
            this.lifespanInMilliseconds = invalid_value;
            return;
        }
        this.max_lifespan_range = max_expiry_milisecs;
        length = String.valueOf(max_lifespan_range).length() + 1;//adjust length

        if (lifespanInMilliseconds.contains("-") && lifespanInMilliseconds.contains(" ")) {
            TimeStamp ts = new TimeStamp(lifespanInMilliseconds);
            long temp = ts.getValue() - nowTimestamp;
            assertArgumentRange(temp, 0, max_lifespan_range, "Attempt to create an illegal BaseLifespan - illegal value: " + lifespanInMilliseconds);
            this.lifespanInMilliseconds = temp;
        } else if (lifespanInMilliseconds.length() < length) {
            Long temp = (Long.parseLong(lifespanInMilliseconds));
            assertArgumentRange(temp, 0, max_lifespan_range, "Attempt to create an illegal BaseLifespan - illegal value: " + lifespanInMilliseconds + "  - max value:" + max_lifespan_range);
            this.lifespanInMilliseconds = temp;
        } else {
            Date time = new Date(Long.parseLong(lifespanInMilliseconds));
            Long expiresAdjusted = time.toInstant().toEpochMilli() - nowTimestamp;
            assertArgumentRange(expiresAdjusted, 0, max_lifespan_range, "Attempt to create an illegal BaseLifespan - illegal value: " + lifespanInMilliseconds + "- expiresAdjusted: " + expiresAdjusted);
            this.lifespanInMilliseconds = expiresAdjusted;
        }

    }

    public BaseLifespan(long lifeCycleInMilliseconds) {
        this(lifeCycleInMilliseconds, max_lifespan_range_default);
    }

    public BaseLifespan(long lifespanInMilliseconds, long max_expiry_milisecs) {
        super(String.valueOf(lifespanInMilliseconds));
        this.max_lifespan_range = max_expiry_milisecs;

        if (lifespanInMilliseconds > nowTimestamp - 2000) {
            long relativeLifespanInMillis = lifespanInMilliseconds - nowTimestamp;
            assertStateTrue(relativeLifespanInMillis + 2000 >= 0, String.format("Attempt to create an illegal BaseLifespan - time in the past: %s time now: %s", lifespanInMilliseconds, nowTimestamp));
            assertStateTrue(relativeLifespanInMillis <= max_expiry_milisecs, String.format("Attempt to create an illegal BaseLifespan - time too far in the future: %s time now: %s", lifespanInMilliseconds, nowTimestamp));
            this.lifespanInMilliseconds = relativeLifespanInMillis;
        } else {
            assertArgumentRange(lifespanInMilliseconds, 0, max_lifespan_range, "Attempt to create an illegal BaseLifespan - illegal value: " + lifespanInMilliseconds);
            this.lifespanInMilliseconds = lifespanInMilliseconds;
        }

    }

    public long getTimeoutInterval() {
        return lifespanInMilliseconds;
    }

    public long getValue() {
        return lifespanInMilliseconds;
    }

    public boolean isValid() {
        return lifespanInMilliseconds != invalid_value;
    }

    public long getValueAsAbsoluteTimeInMilliseconds() {
        return lifespanInMilliseconds + nowTimestamp;
    }

    public long getValueAsRelativeTimeInMilliseconds() {
        return lifespanInMilliseconds;
    }

    public long getSecondValue() {
        return (lifespanInMilliseconds) / 1000;
    }

    public long getMillisecondValue() {
        return lifespanInMilliseconds;
    }

    public String getDateFormatted() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(nowTimestamp + lifespanInMilliseconds);
    }

    public static boolean isValid(String s) {
        return new BaseLifespan(s).isValid();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimeoutInterval());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof BaseLifespan)) {
            return false;
        }
        BaseLifespan n = (BaseLifespan) o;
        return Objects.equals(n.getTimeoutInterval(), getTimeoutInterval());
    }

    public static long addPeriod(int type, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(type, amount);
        java.util.Date dt = cal.getTime();
        return dt.toInstant().toEpochMilli();
    }

    @Override
    public String[] getWhiteList() {
        return null;
    }
}