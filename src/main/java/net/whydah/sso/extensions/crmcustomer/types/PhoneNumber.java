package net.whydah.sso.extensions.crmcustomer.types;

import net.whydah.sso.ddd.model.CellPhone;
import net.whydah.sso.ddd.model.Tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneNumber {


    private CellPhone phonenumber;
    private Tags tags;
    private boolean verified;


    public PhoneNumber(@JsonProperty("phonenumber") String phonenumber,
                       @JsonProperty("tags") String tags,
                       @JsonProperty("verified") boolean verified) {
        this.phonenumber = new CellPhone(phonenumber);
        this.tags = new Tags(tags);
        this.verified = verified;
    }

    public PhoneNumber() {}

    public String getTags() {
        return tags!=null?tags.getInput():null;
    }

    public void setTags(String tags) {
        this.tags = new Tags(tags);
    }

    public String getPhonenumber() {
        return phonenumber!=null?phonenumber.getInput():null;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = new CellPhone(phonenumber);
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
