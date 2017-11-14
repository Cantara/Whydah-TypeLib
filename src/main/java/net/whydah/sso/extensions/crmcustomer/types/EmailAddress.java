package net.whydah.sso.extensions.crmcustomer.types;

import net.whydah.sso.ddd.model.Email;
import net.whydah.sso.ddd.model.Tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailAddress {

    private Email emailaddress;
    private Tags tags;
    private boolean verified;


    public EmailAddress(@JsonProperty("emailaddress") String emailaddress,
                        @JsonProperty("tags") String tags,
                        @JsonProperty("verified") boolean verified) {
        this.emailaddress = new Email(emailaddress);
        this.tags = new Tags(tags);
        this.verified = verified;
    }

    public EmailAddress(){}

    public String getEmailaddress() {
        return emailaddress!=null?emailaddress.getInput():null;
    }

    public void setEmailaddress(String address) {
        this.emailaddress = new Email(address);
    }

    public String getTags() {
        return tags!=null?tags.getInput():null;
    }

    public void setTags(String tags) {
        this.tags = new Tags(tags);
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
