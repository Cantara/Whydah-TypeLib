package net.whydah.sso.extensions.crmcustomer.types;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

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
        assertEquals(null, c.getDefaultEmailLabel());
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
        assertEquals("testEmail", c.getDefaultEmailLabel());
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


}