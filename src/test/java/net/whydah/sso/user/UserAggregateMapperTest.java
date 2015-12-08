package net.whydah.sso.user;

import net.whydah.sso.user.mappers.UserAggregateMapper;
import net.whydah.sso.user.types.UserAggregate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserAggregateMapperTest {

    @Test
    public void createFromJson() throws Exception {
        String userJson = "\n" +
                "{\"username\":\"helloMe\", \"firstName\":\"hello\", \"lastName\":\"me\", \"personRef\":\"\", \"email\":\"hello.me@example.com\", \"cellPhone\":\"+47 90221133\"}";
        UserAggregate minimalUser = UserAggregateMapper.fromJson(userJson);
        assertEquals(minimalUser.getUsername(), "helloMe");
        assertEquals(minimalUser.getFirstName(), "hello");
        assertEquals(minimalUser.getLastName(), "me");
        assertEquals(minimalUser.getPersonRef(), "");
        assertEquals(minimalUser.getEmail(), "hello.me@example.com");
        assertEquals(minimalUser.getCellPhone(), "+47 90221133");
    }

    @Test
    public void createFromSignupJson() throws Exception {
        String signupJson = "{\"uid\":\"[]\",\"username\":\"totto@totto.org\",\"firstName\":\"Thor Henning\",\"lastName\":\"Hetland\",\"personRef\":\"\",\"email\":\"totto@totto.org\",\"cellPhone\":\"91905054\"}";
        UserAggregate minimalUser = UserAggregateMapper.fromJson(signupJson);
        assertEquals(minimalUser.getUsername(), "totto@totto.org");

    }
}
