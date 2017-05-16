package net.whydah.sso.user;

import net.whydah.sso.user.helpers.UserHelper;
import net.whydah.sso.user.mappers.UserAggregateMapper;
import net.whydah.sso.user.mappers.UserIdentityMapper;
import net.whydah.sso.user.mappers.UserTokenMapper;
import net.whydah.sso.user.types.UserAggregate;
import net.whydah.sso.user.types.UserApplicationRoleEntry;
import net.whydah.sso.user.types.UserIdentity;
import net.whydah.sso.user.types.UserToken;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

public class UserAggregateMapperTest {

    private static final Logger log = LoggerFactory.getLogger(UserAggregateMapperTest.class);

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
        UserToken ut = UserTokenMapper.fromUserTokenXml(UserHelper.getDummyUserToken());
        assertNotNull(ut);
        UserToken ut2 = UserTokenMapper.fromUserAggregateJson(UserAggregateMapper.toJson(minimalUser));
        assertNotNull(ut2);
        log.trace(ut.toString());
    }


    @Test
    public void testFailingExample() {
        String userAggregateXML = "<whydahuser>\n" +
                "    <identity>\n" +
                "        <username>whydahadmin</username>\n" +
                "        <cellPhone>+1555406789</cellPhone>\n" +
                "        <email>totto@totto.org</email>\n" +
                "        <firstname>User</firstname>\n" +
                "        <lastname>Admin</lastname>\n" +
                "        <personRef>0</personRef>\n" +
                "        <UID>useradmin</UID>\n" +
                "    </identity>\n" +
                "    <applications>\n" +
                "        <application>\n" +
                "            <appId>2212</appId>\n" +
                "            <applicationName>Whydah-UserAdminService</applicationName>\n" +
                "            <orgName>Whydah</orgName>\n" +
                "            <roleName>WhydahUserAdmin</roleName>\n" +
                "            <roleValue>1</roleValue>\n" +
                "        </application>\n" +
                "        <application>\n" +
                "            <appId>2210</appId>\n" +
                "            <applicationName>Whydah-UserIdentityBackend</applicationName>\n" +
                "            <orgName>Whydah</orgName>\n" +
                "            <roleName>WhydahUserAdmin</roleName>\n" +
                "            <roleValue>1</roleValue>\n" +
                "        </application>\n" +
                "        <application>\n" +
                "            <appId>2219</appId>\n" +
                "            <applicationName>Whydah-UserAdminWebApp</applicationName>\n" +
                "            <orgName>Whydah</orgName>\n" +
                "            <roleName>WhydahUserAdmin</roleName>\n" +
                "            <roleValue>1</roleValue>\n" +
                "        </application>\n" +
                "        <application>\n" +
                "            <appId>2212</appId>\n" +
                "            <applicationName>Whydah-UserAdminService</applicationName>\n" +
                "            <orgName>Whydah</orgName>\n" +
                "            <roleName>PW_SET</roleName>\n" +
                "            <roleValue>true</roleValue>\n" +
                "        </application>\n" +
                "    </applications>\n" +
                "</whydahuser>\n";
        UserToken userToken = UserTokenMapper.fromUserAggregateXml(userAggregateXML);
        List<UserApplicationRoleEntry> roles = userToken.getRoleList();
        for (UserApplicationRoleEntry role : roles) {
            log.trace("Role:" + role);

        }

    }

    @Test
    public void testJSONFromUAWA() {
        String uawaJson = "{\n" +
                "   \"personRef\": \"1\",\n" +
                "   \"uid\": \"xxx\",\n" +
                "   \"username\": \"acsemployee\",\n" +
                "   \"firstName\": \"Samuel Black Sam\",\n" +
                "   \"lastName\": \"Bellamy\",\n" +
                "   \"email\": \"acsemp@whydah.no\",\n" +
                "   \"cellPhone\": \"0943993828\",\n" +
                "   \"uri\": \"http://localhost:9995/uib/useradmin/users/acsemployee/\",\n" +
                "   \"isSelected\": true,\n" +
                "   \"roles\": [\n" +
                "     {\n" +
                "       \"roleId\": \"3ef16749-863d-4e8c-96fa-f5c0adbf4963\",\n" +
                "       \"uid\": \"acsemployee\",\n" +
                "       \"applicationId\": \"100\",\n" +
                "       \"applicationName\": \"ACS\",\n" +
                "       \"organizationName\": \"acsorg\",\n" +
                "       \"applicationRoleName\": \"Employee\",\n" +
                "       \"applicationRoleValue\": \"1\"\n" +
                "     }\n" +
                "   ]\n" +
                " }";
        UserAggregate workingUserAggregate = UserAggregateMapper.fromJson(uawaJson);
        String uid = workingUserAggregate.getUid();
        assertTrue("xxx".equalsIgnoreCase(workingUserAggregate.getUid().trim()));
    }

    @Test
    public void testBigRealWorldExample() {

        String json = "{\"uid\":\"useradmin\",\"username\":\"useradmin\",\"firstName\":\"UserAdmin\",\"lastName\":\"UserAdminWebApp\",\"personRef\":\"42\",\"email\":\"whydahadmin@getwhydah.com\",\"cellPhone\":\"87654321\",\"roles\": [{\"applicationId\":\"2219\",\"applicationName\":\"null\",\"applicationRoleName\":\"WhydahUserAdmin\",\"applicationRoleValue\":\"1\",\"organizationName\":\"Support\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename111f653f-7958-474e-b5ff-9ef428323e27\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgaa45f5be-7b44-4f82-ac4e-6466ddd0573f\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename5799625e-76e3-4b12-9585-466595434ae9\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg799d8184-28f7-4c15-9897-b280876cc157\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamea94b609d-111b-4cd7-adf7-491482e1fb63\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg89d41383-83b3-413d-a459-c8a9b8206ada\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename7041694f-76b7-45ad-af55-667a68f2db0b\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg583b5b86-560c-47da-9d01-0d04649d9e14\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamea66ae3df-01da-4242-93c7-a23c73b5b099\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgade51d19-1ae4-4709-861e-f72be79d0061\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename9f11fbe6-cd59-4e01-8e8e-d522d3abb0ec\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg95ce02bd-36bc-4b6c-8bac-7662a8fef0b4\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename7b834bc3-c451-4d78-9334-f3d7a3c59c71\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgda1340d3-6e90-41e3-acfc-b3ba9145745a\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename7b4bf569-a20b-4dc5-aef6-fa32b0e3e37c\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgca52a5d3-27b1-4926-956b-8fb34a1551ef\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename43b142c9-3b42-4b13-9933-0b73d75c1ec6\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgf59eeea7-fb88-48a5-98c9-cf166c269dd6\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamea95e6619-ab06-496b-ae7c-56b4e1177d64\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg7aeb20b2-0503-4b5a-b78e-2397ec0b97e7\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename74fefc91-5133-4e38-98c1-56fdd70a06b1\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgb5a7fb7e-c27b-439e-9723-eb4c870710c3\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename81965944-e032-4f00-80aa-b7e736d98583\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg2c95e855-54ff-4342-abb8-e4ad8fa79990\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename7528c893-d2dc-47ab-ab20-7cae214d4def\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg617f0d6a-7e3d-4082-945f-4326a206ebce\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenameaec4443c-eecc-4f86-892b-7f9d13bb415e\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg3ab3b3c5-9108-442f-81e2-b5e061a7479e\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename3c197761-1acf-421b-a107-dc7ad38f87ae\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg372998c3-6a53-4b4f-a313-0e8e5572b0f6\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename0a671740-ae3e-4026-a1e0-a6382a75a9b8\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgf941cea7-cdf7-497e-9a1d-a4c7c4a4cb3d\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename64c09a81-0a7c-48c6-802a-a9f2a952aefc\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg4c53920b-4d30-4d16-a625-b4ee084160c0\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamed598a4dc-d747-4c55-a288-d8c5e9084575\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgf36108c9-c7ef-4a05-a1dd-9cc52b736822\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenameb935fbdc-89f2-4c06-addc-da8001d56df8\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgf51d8d3b-82a7-4b37-b44e-3dff22ceaa6a\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename6c236a70-375d-4e6b-8847-a0deb2b8cb71\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgab75495c-81ce-4ad1-9143-6fdb9c8776c2\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename7e81db9c-4514-4742-a1b9-4e2a05c47644\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg777e905a-b1d7-4073-9ee8-b1799f7c7883\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamed971b926-c93d-45c4-bbfd-f23e6fe28bd9\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg22e2de56-c007-4c0c-a34c-750cf06752be\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename3ae1527c-ec00-4cf6-84b7-1dd6773310b0\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgffc3ee4f-b7ba-4061-b89b-e68cd425f166\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename89fbb1ce-bad2-43ce-a817-5b23baafb8d6\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgf87ee845-932c-4d68-b4ca-3f09f1e9a1bc\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename0d9a20ae-8d4e-495e-999c-e68c447248c3\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg33cee9b8-a1b6-4a79-b53c-30a84fe554ff\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename896d1692-2bf0-494d-8cc9-0ab37ebe5021\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg7f26cf7d-8935-40d2-a749-cada6ac66c04\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename261ab457-c061-4771-a1ca-226dd950163e\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg75de0758-b871-455d-a960-348f4722d97c\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenameb7a4f2d6-66b8-4a65-b4a5-ea24f958aee1\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg274b1882-2695-406f-849f-befc8f328395\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamedc365d4f-f2b1-4305-b533-fa1e3ee816d7\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg12c9b5db-1645-4bad-9ef6-e5b750619c3f\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename5efb137f-bd97-47c2-b68c-481b569bceab\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg69b754a2-d79c-4eec-b988-8da74aac6351\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamee7f3ddc2-5553-48cf-a657-35396e165df4\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgcd085211-d35d-4e3f-badd-69db9aacadf7\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamed765b960-da67-42e2-b641-3932da720e49\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgcb901e34-a90c-4d74-acdd-9c8bd74a30e3\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename97b3a874-028a-4d2c-8051-c01e894b8e33\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgcc6ac738-417f-484b-b7e2-0be153aa567b\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamefa252da6-5444-46c0-880c-3d946a6e7114\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg1d6664ae-e78a-474b-b606-499f6a6733ef\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename46a810c5-fa8a-4ea7-97ed-b6ac56e4171b\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgb4572050-4220-4f4e-9d21-d048c987b2c0\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename177f1e46-9074-4b3b-b26e-a50e94a7e1c9\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg61892690-640a-4d7b-ac83-fbb8fdb1febf\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename2e9d5acb-7121-4446-a912-ce0c769d0a47\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgf6f2f870-647e-4896-a057-59572d31625c\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename712f37f1-bca0-48f9-af43-31791de5160c\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgdc16735b-7d5a-4d3d-9a4d-06aa305cfacc\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename71cc4230-c7a2-4575-ab1b-7a4ed6d782d6\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrga93d0d1d-2a18-4d23-a5f9-c418aa0c2515\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamee6667460-279f-4277-ae3d-2adeb7707163\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg2dfef8d6-c106-4a1c-818e-bd761634332b\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename6d1a025d-edb0-4eb4-b235-010682fe7425\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgb81e9e25-3678-457e-b20c-da37a1a41397\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename5655d2a3-8e35-4f1f-b780-2068e4636ef3\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg73a73beb-a14c-41c3-978e-d70fc923a0c9\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRoleName-fa75c7e6-171a-4e58-9263-255f79902991\",\"applicationRoleValue\":\"TestRoleValue\",\"organizationName\":\"TestOrg-11e43094-f498-4e84-b8eb-6ca4da345360\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRoleName-66e04941-6cc6-4ed2-8e18-bd9e4ffcd842\",\"applicationRoleValue\":\"TestRoleValue\",\"organizationName\":\"TestOrg-af910345-41eb-4d22-bdfa-fb1007c4ab59\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRoleName-f6a78750-ecfa-44f5-8355-e2ff8b40fad8\",\"applicationRoleValue\":\"TestRoleValue\",\"organizationName\":\"TestOrg-13ba760d-5b2e-470f-9cda-1d8bf17a1bc5\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRoleName-876690b7-b1e0-42ec-bd4e-70cb5adf074f\",\"applicationRoleValue\":\"TestRoleValue\",\"organizationName\":\"TestOrg-59a6d025-b2e0-47d9-b0ab-e043ba1cc7dc\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRoleName-36cb14f9-fae1-4b45-ab73-265c264e18d6\",\"applicationRoleValue\":\"TestRoleValue\",\"organizationName\":\"TestOrg-e2ad0266-82bc-4b23-a30c-c8171ca014aa\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRoleName-2dae985b-9f5c-49a5-9da2-28300f2561c1\",\"applicationRoleValue\":\"TestRoleValue\",\"organizationName\":\"TestOrg-8acce42b-975f-4bbf-8a4a-da242d5a4280\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRoleName-3d327fd6-7407-4ffd-b480-ac7d9569d41b\",\"applicationRoleValue\":\"TestRoleValue\",\"organizationName\":\"TestOrg-786cd21e-34fa-4336-8139-ba5adaf6372b\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename028b3942-5431-44c0-a36f-8fe987449f2e\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg02cae756-4063-4219-b3fc-fb36146a3f99\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename60a2656c-057d-412a-add2-7f5ee4a1d404\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgb0043d15-c552-452d-ab73-bdc336b0abf4\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRoleName-91e3f661-8e16-46ce-a01c-968b3bef5c67\",\"applicationRoleValue\":\"TestRoleValue\",\"organizationName\":\"TestOrg-0a301573-a542-4288-9b49-d3dfbb21a96e\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRoleName-cc1ca0f3-48a6-450c-a7d1-780a0b16c643\",\"applicationRoleValue\":\"TestRoleValue\",\"organizationName\":\"TestOrg-e0e361d7-bef5-4ffb-b886-6f756e2a82c0\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRoleName-2d953ece-9a0f-4932-b419-b3023954fd65\",\"applicationRoleValue\":\"TestRoleValue\",\"organizationName\":\"TestOrg-c17097ee-aee1-49e7-a79d-da7650670d59\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename5ed9bc63-3b4e-4630-bfba-c88372a59093\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgd40d622b-c64f-47ee-936f-41febc59830c\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenameceff8ab3-f35d-41b6-992e-7206f1620ca8\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrga7de41c1-4da4-4f3f-8895-1cc14f9af7f8\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamea75a9eb5-db66-4301-9b5f-ac04587a832d\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg013a8a65-ea85-472b-ba3b-6d23f5f0ec58\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename0c2e09b3-04c3-4fad-80b3-394ed60e5941\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgfd45acc4-07af-4f8e-b4bb-b9a0500744b5\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename02198c19-c929-49bd-bd50-6b5df98e8450\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrge6bbb7c9-18d8-4a97-a516-9391a05a9ce1\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamef60d236f-d457-4fc3-a13e-8f53b48851a6\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg9ae27b28-f00a-4517-91cd-efec56ed92c5\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename87e33973-fec7-4a16-ab3f-b19e580a0a76\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg980bdfdb-8ee2-40c2-b983-3869ad39c7ad\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename12464667-4d4a-4b81-82cc-fdc0086c1761\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg786358bf-b942-44aa-8bef-5ebd59ee6a66\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenameecd88882-f965-4196-a621-45730d8bab5a\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg6d11e64f-20b0-4924-a540-120bbe248d94\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename821906d7-b001-48c9-9ab3-d434baf81931\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrgf5793af9-1601-4a5c-b746-25f36553f358\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamec37ed305-ee93-4b0a-ba77-955ea053a634\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg8b6d3ff0-1fce-4fd1-b2a9-360cb6950296\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolename015c107e-eae2-41dc-ae91-28908b0ca8bd\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrg1c349d9a-3387-423b-bf40-88065a91f93c\"},{\"applicationId\":\"100\",\"applicationName\":\"null\",\"applicationRoleName\":\"TestRolenamed5470068-aa62-41f5-bdc4-59381169bfeb\",\"applicationRoleValue\":\"testRoleValue\",\"organizationName\":\"TestOrge10753b6-68d5-4317-a4c7-f9e8ec5dd8aa\"}]}";
        log.trace(UserAggregateMapper.toJson(UserAggregateMapper.fromUserAggregateNoIdentityJson(json)));
    }

    @Test
    public void testUIBJson() {
        String realworldexample = "{\n" +
                "\"uid\":\"acsmanager\",\n" +
                "\"username\":\"acsmanager\",\n" +
                "\"firstName\":\"Sir Humphrey\",\n" +
                "\"lastName\":\"Morice\",\n" +
                "\"email\":\"acsmanager@whydah.no\",\n" +
                "\"cellPhone\":\"98765432\",\n" +
                "\"personRef\":\"002\",\n" +
                "\"roles\":[\n" +
                "      {\n" +
                "        \"applicationId\": \"100\",\n" +
                "        \"applicationName\": \"\",\n" +
                "        \"organizationName\": \"acsorg\",\n" +
                "        \"applicationRoleName\": \"Manager\",\n" +
                "        \"applicationRoleValue\": \"1\"\n" +
                "    }\n" +
                "],\n" +
                "\"uri\":\"http://ip-172-31-55-84.ec2.internal:9995/uib/useradmin/users/acsmanager\\/\"\n" +
                "}\n";
        UserAggregate minimalUser = UserAggregateMapper.fromUserAggregateNoIdentityJson(realworldexample);
        assertNotNull(minimalUser);
        assertTrue("acsmanager".equalsIgnoreCase(minimalUser.getUid()));
    }

    @Test
    public void testUIBJsonExample() {
        String realworldexample2 = " {\"username\":\"acsmanager\",\"firstName\":\"Sir Humphrey\",\"lastName\":\"Morice\",\"personRef\":\"002\",\"email\":\"acsmanager@whydah.no\",\"cellPhone\":\"98765432\",\"password\":null,\"uid\":\"acsmanager\",\"personName\":\"Sir Humphrey Morice\"}";
        UserAggregate minimalUser = UserAggregateMapper.fromUserAggregateNoIdentityJson(realworldexample2);
        assertNotNull(minimalUser);
        assertTrue("acsmanager".equalsIgnoreCase(minimalUser.getUid()));
        UserIdentity userIdentity = UserIdentityMapper.fromUserIdentityJson(realworldexample2);
        assertNotNull(userIdentity);
        assertTrue("acsmanager".equalsIgnoreCase(userIdentity.getUid()));


    }
}
