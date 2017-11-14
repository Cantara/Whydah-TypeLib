package net.whydah.sso.extensions.crmcustomer.types;

import net.whydah.sso.ddd.model.AddressLine;
import net.whydah.sso.ddd.model.PostalCity;
import net.whydah.sso.ddd.model.PostalCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryAddress {
    private AddressLine addressLine1;
    private AddressLine addressLine2;
    private PostalCode postalcode;
    private PostalCity postalcity;

    public DeliveryAddress(@JsonProperty("addressLine1") String address1,
                           @JsonProperty("addressLine2") String address2,
                           @JsonProperty("postalcode") String postalcode,
                           @JsonProperty("postalcity") String postalcity) {
        this.addressLine1 = new AddressLine(address1);
        this.addressLine2 = new AddressLine(address2);
        this.postalcode = new PostalCode(postalcode);
        this.postalcity = new PostalCity(postalcity);
    }

    public DeliveryAddress() {
    }

    public String getAddressLine1() {
        return addressLine1!=null?addressLine1.getInput():null;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = new AddressLine(addressLine1);
    }

    public String getAddressLine2() {
        return addressLine2!=null?addressLine2.getInput():null;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = new AddressLine(addressLine2);
    }

    public String getPostalcode() {
        return postalcode!=null?postalcode.getInput():null;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = new PostalCode(postalcode);
    }

    public String getPostalcity() {
        return postalcity!=null?postalcity.getInput():null;
    }

    public void setPostalcity(String postalcity) {
        this.postalcity = new PostalCity(postalcity);
    }

    @Override
    public String toString() {
        return "DeliveryAddress{" +
                "addressLine1='" + addressLine1.getInput() + '\'' +
                ", addressLine2='" + addressLine2.getInput() + '\'' +
                ", postalcode='" + postalcode.getInput() + '\'' +
                ", postalcity='" + postalcity.getInput() + '\'' +
                '}';
    }
}
