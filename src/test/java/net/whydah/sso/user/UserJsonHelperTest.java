package net.whydah.sso.user;


import net.whydah.sso.user.mappers.UserTokenMapper;
import net.whydah.sso.user.types.UserToken;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserJsonHelperTest {

    private final static Logger log = LoggerFactory.getLogger(UserJsonHelperTest.class);

    @Test
    public void testParseUserIdentityJson() throws Exception {
        String userJson = "{\"identity\":{\"username\":\"91905054\",\"firstName\":\"Thor Henning\",\"lastName\":\"Hetland\",\"personRef\":\"\",\"email\":\"totto@cantara.no\",\"cellPhone\":\"91905054\",\"uid\":\"00d07a25-efbe-484a-b00e-67859a106dd4\"},\"roles\":[],\"personName\":\"Thor Henning Hetland\",\"personRef\":\"\",\"uid\":\"00d07a25-efbe-484a-b00e-67859a106dd4\",\"lastName\":\"Hetland\",\"email\":\"totto@cantara.no\",\"firstName\":\"Thor Henning\",\"username\":\"91905054\",\"cellPhone\":\"91905054\"}";
        UserToken myToken = UserTokenMapper.fromUserIdentityJson(userJson);
        log.trace(myToken.toString());


    }

    @Test
    public void testUserAggregateTest() throws Exception {
        String userAggregateJson = "{\"uid\":\"useradmin\",\"username\":\"useradmin\",\"firstName\":\"UserAdmin\",\"lastName\":\"UserAdminWebApp\",\"personRef\":\"42\",\"email\":\"whydahadmin@getwhydah.com\",\"cellPhone\":\"87654321\",\"roles\": [{\"applicationId\":\"19\",\"applicationName\":\"\",\"applicationRoleName\":\"WhydahUserAdmin\",\"applicationRoleValue\":\"1\",\"organizationName\":\"\"}]}\n";
        UserToken myToken = UserTokenMapper.fromUserAggregateJson(userAggregateJson);
        log.trace(myToken.toString());
    }

}
