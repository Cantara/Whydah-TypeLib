package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddressLineTest {


    private static final Logger log = LoggerFactory.getLogger(AddressLineTest.class);

    @Test
    public void testIllegalAddressLine() {
        assertFalse(AddressLine.isValid("<javascript:"));
        assertFalse(AddressLine.isValid("<html>"));
        assertFalse(AddressLine.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(AddressLine.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(AddressLine.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(AddressLine.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(AddressLine.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(AddressLine.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));


    }

    @Test
    public void testOKAddressLine() {
        assertTrue(AddressLine.isValid(""));
//        assertTrue(AddressLine.isValid("+4722334455"));
        assertTrue(AddressLine.isValid("100"));
        assertTrue(AddressLine.isValid("243543"));
        assertTrue(AddressLine.isValid("asadadsaYUYI"));
        assertTrue(AddressLine.isValid("asadad/saYUYI"));
        assertTrue(AddressLine.isValid("234324-2RT2"));
        assertTrue(AddressLine.isValid("234324+2342"));
        assertTrue(AddressLine.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertTrue(AddressLine.isValid("2342424-2342-2342342-342-2342342-24"));
        assertTrue(AddressLine.isValid(UUID.randomUUID().toString()));
        assertTrue(AddressLine.isValid(" {      \"name\": \"Bård Lind\",      \"company\":\"\",      \"addressLine1\":\"null\",      \"addressLine2\":\"null\",      \"postalcode\":\"\",      \"postalcity\":\"\",      \"countryCode\":\"no\",      \"reference\":\"\",      \"tags\":\"\",      \"contact\": {\"name\":\"Bård Lind\",\"email\":\"bli@capraconsulting.no\",\"emailConfirmed\":\"false\",\"phoneNumber\":\"93234963\", \"phoneNumberConfirmed\":\"true\"},      \"deliveryinformation\": {\"additionalAddressInfo\":\"\",\"pickupPoint\":\"\",\"Deliverytime\":\"\"}}}"));
        assertTrue(AddressLine.isValid("{      'name': '',      'company':'',      'addressLine1':'Stenersgata 2',      'addressLine2':'null',      'postalCode':'0184',      'city':'Oslo',      'countryCode':'no',      'reference':'',      'tags':'',      'contact': {   'name':'',   'email':'',   'emailConfirmed':'Confirmed',   'phoneNumber':'',    'phoneNumberConfirmed':'Confirmed'  },      'deliveryinformation': {   'additionalAddressInfo':'',   'GPS/pickupPoint':'',   'Deliverytime':''  }}}"));
        assertTrue(AddressLine.isValid("{      \"name\": \"Not Set\",      \"company\":\"\",      \"addressLine1\":\"Address\",      \"addressLine2\":\"null\",      \"postalcode\":\"#postalcode\",      \"postalcity\":\"#postalcity\",      \"countryCode\":\"no\",      \"reference\":\"\",      \"tags\":\"delivery_tag\",      \"contact\": {\"name\":\"Not Set\",\"email\":\"fill@me.in\",\"emailConfirmed\":\"false\",\"phoneNumber\":\"Phonenumber\", \"phoneNumberConfirmed\":\"false\"},      \"deliveryinformation\": {\"additionalAddressInfo\":\"\",\"pickupPoint\":\"\",\"Deliverytime\":\"\"}}} should not contain any invalid characters\n"));
        assertTrue(AddressLine.isValid("{\"deliveryaddress\": {      \"name\": \"\",      \"company\":\"\",      \"addressLine1\":\"{\"deliveryaddress\": {      \"name\": \"Thor Hetland\",      \"company\":\"1969\",      \"addressLine1\":\"M?lefaret 30E\",      \"addressLine2\":\"\",      \"postalCode\":\"0750\",      \"city\":\"OSLO\",      \"countryCode\":\"no\",      \"reference\":\"\",      \"tags\":\"56e67f7e-ca8c-4f65-a087-3da5e3da111d\",      \"contact\": {   \"name\":\"Thor Hetland\",   \"email\":\"\",   \"emailConfirmed\":\"Confirmed\",   \"phoneNumber\":\"\",    \"phoneNumberConfirmed\":\"Confirmed\"  },      \"deliveryinformation\": {   \"additionalAddressInfo\":\"\",   \"GPS/pickupPoint\":\"\",   \"Deliverytime\":\"\"  }}}\",      \"addressLine2\":\"null\",      \"postalCode\":\"null\",      \"city\":\"null\",      \"countryCode\":\"\",      \"reference\":\"\",      \"tags\":\"\",      \"contact\": {   \"name\":\"\",   \"email\":\"\",   \"emailConfirmed\":\"Confirmed\",   \"phoneNumber\":\"\",    \"phoneNumberConfirmed\":\"Confirmed\"  },      \"deliveryinformation\": {   \"additionalAddressInfo\":\"\",   \"GPS/pickupPoint\":\"\",   \"Deliverytime\":\"\"  }}} "));
    }


}