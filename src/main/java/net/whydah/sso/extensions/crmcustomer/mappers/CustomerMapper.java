package net.whydah.sso.extensions.crmcustomer.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.whydah.sso.extensions.crmcustomer.types.Customer;

import java.io.IOException;

public class CustomerMapper {

    public static Customer fromJson(String customerJson) {
        if (customerJson == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(customerJson, Customer.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Customer customer) {
        if (customer == null) {
            return "{}";
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(customer);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
