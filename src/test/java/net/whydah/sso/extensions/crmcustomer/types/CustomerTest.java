package net.whydah.sso.extensions.crmcustomer.types;

import net.whydah.sso.extensions.crmcustomer.mappers.CustomerMapper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test
    public void testNoAddressLabel() {
        Customer c = new Customer();

        c.setDefaultAddressLabel(null);
        assertEquals(null, c.getDefaultAddressLabel());

        c.setDefaultAddressLabel("dummy-label");
        assertEquals(null, c.getDefaultAddressLabel());
    }
    @Test
    public void testNoEmailLabel() {
        Customer c = new Customer();

        c.setDefaultEmailLabel(null);
        assertEquals(null, c.getDefaultEmailLabel());

        c.setDefaultEmailLabel("dummy-label");
        assertEquals(null, c.getDefaultEmail());
    }
    @Test
    public void testNoPhoneLabel() {
        Customer c = new Customer();

        c.setDefaultPhoneLabel(null);
        assertEquals(null, c.getDefaultPhoneLabel());

        c.setDefaultPhoneLabel("dummy-label");
        assertEquals(null, c.getDefaultPhoneLabel());
    }

    @Test
    public void testDefaultAddressLabelSet() {
        Customer c = new Customer();

        Map<String, DeliveryAddress> map = new HashMap<>();
        map.put("testAddress", new DeliveryAddress());
        c.setDeliveryaddresses(map);

        c.setDefaultAddressLabel(null);
        assertEquals("testAddress", c.getDefaultAddressLabel());
    }

    @Test
    public void testDefaultEmailLabelSet() {
        Customer c = new Customer();

        Map<String, EmailAddress> map = new HashMap<>();
        map.put("testEmail", new EmailAddress());
        c.setEmailaddresses(map);

        c.setDefaultEmailLabel(null);
        assertEquals("testEmail", c.getDefaultEmail());
    }

    @Test
    public void testDefaultPhoneLabelSet() {
        Customer c = new Customer();

        Map<String, PhoneNumber> map = new HashMap<>();
        map.put("testPhone", new PhoneNumber());
        c.setPhonenumbers(map);

        c.setDefaultPhoneLabel(null);
        assertEquals("testPhone", c.getDefaultPhoneLabel());
    }

    @Test
    public void testCommandCustomer() {
        Customer c = CustomerMapper.fromJson(generateDummyCustomerData(UUID.randomUUID().toString()));
    }

    protected String generateDummyCustomerData(String customerRefId) {
        String personJsonData = "{\n" +
                "  \"id\" : \"" + customerRefId + "\",\n" +
                "  \"firstname\" : \"First\",\n" +
                "  \"middlename\" : \"middle\",\n" +
                "  \"lastname\" : \"Lastname\",\n" +
                "  \"emailaddresses\" : {\n" +
                "    \"jobb\" : {\n" +
                "      \"emailaddress\" : \"totto@capraconsulting.no\",\n" +
                "      \"tags\" : \"jobb, Capra\"\n" +
                "    },\n" +
                "    \"kobb-kunde\" : {\n" +
                "      \"emailaddress\" : \"thor.henning.hetland@nmd.no\",\n" +
                "      \"tags\" : \"jobb, kunde\"\n" +
                "    },\n" +
                "    \"community\" : {\n" +
                "      \"emailaddress\" : \"totto@cantara.no\",\n" +
                "      \"tags\" : \"opensource, privat, Whydah\"\n" +
                "    },\n" +
                "    \"hjemme\" : {\n" +
                "      \"emailaddress\" : \"totto@totto.org\",\n" +
                "      \"tags\" : \"hjemme, privat, OID\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"phonenumbers\" : {\n" +
                "    \"tja\" : {\n" +
                "      \"phonenumber\" : \"96909999\",\n" +
                "      \"tags\" : \"96909999\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"defaultAddressLabel\" : null,\n" +
                "  \"deliveryaddresses\" : {\n" +
                "    \"work, override\" : {\n" +
                "      \"addressLine1\" : \"Stenersgata 2\",\n" +
                "      \"addressLine2\" : null,\n" +
                "      \"postalcode\" : \"0184\",\n" +
                "      \"postalcity\" : \"Oslo\"\n" +
                "    },\n" +
                "    \"home\" : {\n" +
                "      \"addressLine1\" : \"Møllefaret 30E\",\n" +
                "      \"addressLine2\" : null,\n" +
                "      \"postalcode\" : \"0750\",\n" +
                "      \"postalcity\" : \"Oslo\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        return personJsonData;
    }


}