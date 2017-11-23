package net.whydah.sso.extensions.crmcustomer.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.whydah.sso.ddd.model.PhoneLabel;
import net.whydah.sso.ddd.model.customer.*;
import net.whydah.sso.extensions.crmcustomer.helpers.JsonDateDeserializer;
import net.whydah.sso.extensions.crmcustomer.helpers.JsonDateSerializer;

import java.util.Date;
import java.util.Map;

import static net.whydah.sso.extensions.crmcustomer.helpers.MapKeyValidator.validateKey;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private CustomerId id;
    private FirstName firstname;
    private MiddleName middlename;
    private LastName lastname;
    private Gender sex;
    private Date birthdate;
    private EmailLabel defaultEmailLabel;
    private Map<String, EmailAddress> emailaddresses;
    private PhoneLabel defaultPhoneLabel;
    private Map<String, PhoneNumber> phonenumbers;
    private AddressLabel defaultAddressLabel;
    private Map<String, DeliveryAddress> deliveryaddresses;

    public Customer(@JsonProperty("id") String id,
                    @JsonProperty("firstname") String firstname,
                    @JsonProperty("middlename") String middlename,
                    @JsonProperty("lastname") String lastname,
                    @JsonProperty("sex") String sex,
                    @JsonProperty("birthdate") Date birthdate,
                    @JsonProperty("defaultEmailLabel") String defaultEmailLabel,
                    @JsonProperty("emailaddresses") Map<String, EmailAddress> email,
                    @JsonProperty("defaultPhoneLabel") String defaultPhoneLabel,
                    @JsonProperty("phonenumbers") Map<String, PhoneNumber> phonenumber,
                    @JsonProperty("defaultAddressLabel") String defaultAddressLabel,
                    @JsonProperty("deliveryaddresses") Map<String, DeliveryAddress> addresses) {
        this.id = new CustomerId(id);
        this.firstname = new FirstName(firstname);
        this.middlename = new MiddleName(middlename);
        this.lastname = new LastName(lastname);
        this.sex = new Gender(sex);
        if (birthdate != null) {
            this.birthdate = (Date) birthdate.clone();
        }
        this.defaultEmailLabel = new EmailLabel(defaultEmailLabel);
        this.emailaddresses = email;
        this.defaultPhoneLabel = new PhoneLabel(defaultPhoneLabel);
        this.phonenumbers = phonenumber;
        this.defaultAddressLabel = new AddressLabel(defaultAddressLabel);
        this.deliveryaddresses = addresses;
    }

    public Customer() {

    }

    public String getId() {
        return id != null ? id.getInput() : null;
    }

    public void setId(String id) {
        this.id = new CustomerId(id);
    }

    public String getFirstname() {
        return firstname!=null?firstname.getInput():null;
    }

    public void setFirstname(String firstname) {
        this.firstname = new FirstName(firstname);
    }

    public String getMiddlename() {
        return middlename!=null?middlename.getInput():null;
    }

    public void setMiddlename(String middlename) {
        this.middlename = new MiddleName(middlename);
    }

    public String getLastname() {
        return lastname!=null?lastname.getInput():null;
    }

    public void setLastname(String lastname) {
        this.lastname = new LastName(lastname);
    }

    public String getSex() {
        return sex!=null?sex.getInput():null;
    }

    public void setSex(String sex) {
        this.sex = new Gender(sex);
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    @JsonDeserialize(using=JsonDateDeserializer.class)
    public Date getBirthdate() {
        if (birthdate == null) {
            return null;
        }
        return (Date) birthdate.clone();
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = (Date) birthdate.clone();
    }

    public String getDefaultEmail() {
    	if(defaultEmailLabel!=null){
    		String defaultEmail = validateKey(defaultEmailLabel.getInput(), emailaddresses);
    		return defaultEmail;
    	}
        return null;
    }

    public String getDefaultEmailLabel() {
        return defaultEmailLabel!=null?defaultEmailLabel.getInput():null;
    }

    public void setDefaultEmailLabel(String defaultEmailLabel) {
        this.defaultEmailLabel = new EmailLabel(defaultEmailLabel);
    }

    public Map<String, EmailAddress> getEmailaddresses() {
        return emailaddresses;
    }

    public void setEmailaddresses(Map<String, EmailAddress> emailaddresses) {
        this.emailaddresses = emailaddresses;
    }

    public String getDefaultPhoneLabel() {
    	if(defaultPhoneLabel!=null){
    		return validateKey(defaultPhoneLabel.getInput(), phonenumbers);
    	}
    	return null;
    }

    public void setDefaultPhoneLabel(String defaultPhoneLabel) {
        this.defaultPhoneLabel = new PhoneLabel(defaultPhoneLabel);
    }

    public Map<String, PhoneNumber> getPhonenumbers() {
        return phonenumbers;
    }

    public void setPhonenumbers(Map<String, PhoneNumber> phonenumbers) {
        this.phonenumbers = phonenumbers;
    }

    public String getDefaultAddressLabel() {
    	if(defaultAddressLabel!=null){
    		return validateKey(defaultAddressLabel.getInput(), deliveryaddresses);
    	}
    	return null;
    }

    public void setDefaultAddressLabel(String defaultAddressLabel) {
        this.defaultAddressLabel = new AddressLabel(defaultAddressLabel);
    }

    public Map<String, DeliveryAddress> getDeliveryaddresses() {
        return deliveryaddresses;
    }

    public void setDeliveryaddresses(Map<String, DeliveryAddress> deliveryaddresses) {
        this.deliveryaddresses = deliveryaddresses;
    }


}
