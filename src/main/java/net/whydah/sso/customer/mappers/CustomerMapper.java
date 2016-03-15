package net.whydah.sso.customer.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.whydah.sso.customer.types.Customer;

import java.io.IOException;

public class CustomerMapper {

    public static Customer fromJson(String customerJson) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(customerJson, Customer.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Customer customer) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(customer);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
