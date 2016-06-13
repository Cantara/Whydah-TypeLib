package net.whydah.sso.extensions.crmcustomer.mappers;

import net.whydah.sso.extensions.crmcustomer.types.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    @Test
    public void testNoAddressLabel() {
        Customer c = new Customer();

        c.setDefaultAddressLabel(null);
        assertEquals(null, c.getDefaultAddressLabel());

        c.setDefaultAddressLabel("dummy-label");
        assertEquals(null, c.getDefaultAddressLabel());
        System.out.println(CustomerMapper.toJson(c));
    }
}
