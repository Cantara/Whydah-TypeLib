package net.whydah.sso.extensions.crmcustomer.mappers;

import static org.junit.Assert.assertEquals;
import net.whydah.sso.ddd.model.AbstractName;
import net.whydah.sso.ddd.model.ApplicationName;
import net.whydah.sso.extensions.crmcustomer.types.Customer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerMapperTest {

    private final static Logger log = LoggerFactory.getLogger(CustomerMapperTest.class);

    @Test
    public void testNoAddressLabel() {
        Customer c = new Customer();

        c.setDefaultAddressLabel(null);
        assertEquals(null, c.getDefaultAddressLabel());

        c.setDefaultAddressLabel("dummy-label");
        assertEquals(null, c.getDefaultAddressLabel());
        log.trace(CustomerMapper.toJson(c));
    }
   
}
