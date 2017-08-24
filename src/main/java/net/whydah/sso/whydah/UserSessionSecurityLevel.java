package net.whydah.sso.whydah;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserSessionSecurityLevel {
    @JsonProperty("UserSessionSecurityLevel")
    LEVEL0(0),
    LEVEL1(1),
    LEVEL2(2),
    LEVEL3(3),
    LEVEL4(4),
    LEVEL5(5);


    private int numVal;

    UserSessionSecurityLevel(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}