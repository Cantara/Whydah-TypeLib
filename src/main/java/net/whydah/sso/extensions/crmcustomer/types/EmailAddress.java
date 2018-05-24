package net.whydah.sso.extensions.crmcustomer.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.whydah.sso.ddd.model.customer.Tags;
import net.whydah.sso.ddd.model.user.Email;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailAddress {

    private Email emailaddress;
    private Tags tags;
    private boolean verified;
    private boolean pending;


    public EmailAddress(@JsonProperty("emailaddress") String emailaddress,
                        @JsonProperty("tags") String tags,
                        @JsonProperty("verified") boolean verified,
                        @JsonProperty("pending") boolean pending) {
        this.emailaddress = new Email(emailaddress);
        this.tags = new Tags(tags);
        this.verified = verified;
        this.pending = pending;
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
    
    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }
}
