package net.whydah.sso.user;

import net.whydah.sso.user.mappers.UserIdentityMapper;
import net.whydah.sso.user.types.UserIdentity;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.DocumentBuilderFactory;

import static org.junit.Assert.assertEquals;

public class UserIdentityMapperTest {
    private static final Logger log = LoggerFactory.getLogger(UserIdentityMapperTest.class);

    private static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private String username = null;
    private UserIdentity userIdentity = null;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void createFromJson() throws Exception {
        String userJson = "\n" +
                "{\"username\":\"helloMe\", \"firstName\":\"hello\", \"lastName\":\"me\", \"personRef\":\"\", \"email\":\"hello.me@example.com\", \"cellPhone\":\"+47 90221133\"}";
        UserIdentity minimalUser = UserIdentityMapper.fromJson(userJson);
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
        UserIdentity minimalUser = UserIdentityMapper.fromJson(signupJson);
        assertEquals(minimalUser.getUsername(), "totto@totto.org");

    }

    @Test
    public void testJsonFromUIB() throws Exception {
        String exampleJson = "{\"username\":\"weqew\",\"firstName\":\"Thor\",\"lastName\":\"qweqweqe\",\"personRef\":\"1\",\"email\":\"test@ttyt.no\",\"cellPhone\":\"123123\",\"password\":null,\"uid\":\"cb3a3256-12fa-4588-87b7-4628a4adfc4c\",\"personName\":\"Thor qweqweqe\"}";
        UserIdentity minimalUser = UserIdentityMapper.fromJson(exampleJson);
        assertEquals(minimalUser.getUsername(), "weqew");

    }
}