package net.whydah.sso.user;

import net.whydah.sso.user.mappers.UserRoleMapper;
import net.whydah.sso.user.types.UserApplicationRoleEntry;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.slf4j.LoggerFactory.getLogger;

public class UserRolesTest {

    private static final Logger log = getLogger(UserRoleTest.class);

    String rolesJsonExample = "[{\"roleId\":\"3e29a04d-dc29-4abe-9cea-0a95788bcd9c\",\"uid\":\"useradmin\",\"applicationId\":\"2219\",\"applicationName\":\"Whydah-UserAdminWebApp\",\"organizationName\":\"Support\",\"applicationRoleName\":\"WhydahUserAdmin\",\"applicationRoleValue\":\"1\"},{\"roleId\":\"e9e6f990-e330-4d1c-9b3c-5c45a5f7e8f0\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgaa45f5be-7b44-4f82-ac4e-6466ddd0573f\",\"applicationRoleName\":\"TestRolename111f653f-7958-474e-b5ff-9ef428323e27\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"26c1c000-4175-42a4-ad88-c7da94cd0ec2\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg799d8184-28f7-4c15-9897-b280876cc157\",\"applicationRoleName\":\"TestRolename5799625e-76e3-4b12-9585-466595434ae9\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"841a5c20-42ce-4510-8925-dc423ef52b8f\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg89d41383-83b3-413d-a459-c8a9b8206ada\",\"applicationRoleName\":\"TestRolenamea94b609d-111b-4cd7-adf7-491482e1fb63\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"8e386d41-cf6f-411a-aadd-9885474a904d\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg583b5b86-560c-47da-9d01-0d04649d9e14\",\"applicationRoleName\":\"TestRolename7041694f-76b7-45ad-af55-667a68f2db0b\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"7bd80ac4-b36a-4076-b1bc-da83c792f0ea\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgade51d19-1ae4-4709-861e-f72be79d0061\",\"applicationRoleName\":\"TestRolenamea66ae3df-01da-4242-93c7-a23c73b5b099\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"8bad94bd-b200-4bcf-b7c7-6f57f3adef8b\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg95ce02bd-36bc-4b6c-8bac-7662a8fef0b4\",\"applicationRoleName\":\"TestRolename9f11fbe6-cd59-4e01-8e8e-d522d3abb0ec\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"4c4d9b35-0926-4727-83c9-e8bde4904351\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgda1340d3-6e90-41e3-acfc-b3ba9145745a\",\"applicationRoleName\":\"TestRolename7b834bc3-c451-4d78-9334-f3d7a3c59c71\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"4f46801c-da67-4b86-8c55-6b24d7837a38\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgca52a5d3-27b1-4926-956b-8fb34a1551ef\",\"applicationRoleName\":\"TestRolename7b4bf569-a20b-4dc5-aef6-fa32b0e3e37c\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"2b1bfdbf-bbe5-44fb-b071-67d4780d550f\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgf59eeea7-fb88-48a5-98c9-cf166c269dd6\",\"applicationRoleName\":\"TestRolename43b142c9-3b42-4b13-9933-0b73d75c1ec6\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"73c560de-0c1a-4b17-b7b7-6001676dd939\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg7aeb20b2-0503-4b5a-b78e-2397ec0b97e7\",\"applicationRoleName\":\"TestRolenamea95e6619-ab06-496b-ae7c-56b4e1177d64\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"46e17641-bbd3-4e3f-9f9c-129d0c83827a\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgb5a7fb7e-c27b-439e-9723-eb4c870710c3\",\"applicationRoleName\":\"TestRolename74fefc91-5133-4e38-98c1-56fdd70a06b1\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"5a401ac9-7b9f-4ec3-85e6-83dcb256421c\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg2c95e855-54ff-4342-abb8-e4ad8fa79990\",\"applicationRoleName\":\"TestRolename81965944-e032-4f00-80aa-b7e736d98583\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"b9cdc297-6db7-42a1-aeac-f128d466bc96\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg617f0d6a-7e3d-4082-945f-4326a206ebce\",\"applicationRoleName\":\"TestRolename7528c893-d2dc-47ab-ab20-7cae214d4def\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"bbb0e39b-55fe-445d-94c2-d99b243e713f\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg3ab3b3c5-9108-442f-81e2-b5e061a7479e\",\"applicationRoleName\":\"TestRolenameaec4443c-eecc-4f86-892b-7f9d13bb415e\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"023f13c1-7546-45aa-85ac-f7390f7f75ff\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg372998c3-6a53-4b4f-a313-0e8e5572b0f6\",\"applicationRoleName\":\"TestRolename3c197761-1acf-421b-a107-dc7ad38f87ae\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"0f29009d-64ad-4483-b7a0-40f9d8ae09b6\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgf941cea7-cdf7-497e-9a1d-a4c7c4a4cb3d\",\"applicationRoleName\":\"TestRolename0a671740-ae3e-4026-a1e0-a6382a75a9b8\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"166e126c-59bc-44f6-85ec-0c9b95d56d2b\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg4c53920b-4d30-4d16-a625-b4ee084160c0\",\"applicationRoleName\":\"TestRolename64c09a81-0a7c-48c6-802a-a9f2a952aefc\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"08cecda6-2eb7-45ee-99bf-1f4eb6f571c5\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgf36108c9-c7ef-4a05-a1dd-9cc52b736822\",\"applicationRoleName\":\"TestRolenamed598a4dc-d747-4c55-a288-d8c5e9084575\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"cf94646c-665e-4ae2-9539-ee3202340b88\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgf51d8d3b-82a7-4b37-b44e-3dff22ceaa6a\",\"applicationRoleName\":\"TestRolenameb935fbdc-89f2-4c06-addc-da8001d56df8\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"45f5c9b4-d40c-4f6c-a7ca-c87eb45fee14\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgab75495c-81ce-4ad1-9143-6fdb9c8776c2\",\"applicationRoleName\":\"TestRolename6c236a70-375d-4e6b-8847-a0deb2b8cb71\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"da9ca8f3-ba70-4d3e-9c80-3f8542dbe819\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg777e905a-b1d7-4073-9ee8-b1799f7c7883\",\"applicationRoleName\":\"TestRolename7e81db9c-4514-4742-a1b9-4e2a05c47644\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"d23a5ed9-e118-44ae-bb76-a5a05dedda78\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg22e2de56-c007-4c0c-a34c-750cf06752be\",\"applicationRoleName\":\"TestRolenamed971b926-c93d-45c4-bbfd-f23e6fe28bd9\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"b6b934c1-5602-4a81-8110-b4a6e5a6686b\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgffc3ee4f-b7ba-4061-b89b-e68cd425f166\",\"applicationRoleName\":\"TestRolename3ae1527c-ec00-4cf6-84b7-1dd6773310b0\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"5f28dead-9a40-458e-a7f1-4fd1fb1895a4\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgf87ee845-932c-4d68-b4ca-3f09f1e9a1bc\",\"applicationRoleName\":\"TestRolename89fbb1ce-bad2-43ce-a817-5b23baafb8d6\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"ea50bce5-954d-4fac-b378-79597ba79122\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg33cee9b8-a1b6-4a79-b53c-30a84fe554ff\",\"applicationRoleName\":\"TestRolename0d9a20ae-8d4e-495e-999c-e68c447248c3\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"2f8b7f2b-fabb-475e-a748-288a19c0ca58\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg7f26cf7d-8935-40d2-a749-cada6ac66c04\",\"applicationRoleName\":\"TestRolename896d1692-2bf0-494d-8cc9-0ab37ebe5021\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"a694d6b3-66c6-4950-acba-d55cc27230c8\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg75de0758-b871-455d-a960-348f4722d97c\",\"applicationRoleName\":\"TestRolename261ab457-c061-4771-a1ca-226dd950163e\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"3d5c4a96-0dae-4acf-94a8-5536bb069bc7\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg274b1882-2695-406f-849f-befc8f328395\",\"applicationRoleName\":\"TestRolenameb7a4f2d6-66b8-4a65-b4a5-ea24f958aee1\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"77607ae0-361b-4e12-af39-6bf1b55ae859\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg12c9b5db-1645-4bad-9ef6-e5b750619c3f\",\"applicationRoleName\":\"TestRolenamedc365d4f-f2b1-4305-b533-fa1e3ee816d7\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"f5e66e79-e46d-4218-9bfd-a77b355c753d\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg69b754a2-d79c-4eec-b988-8da74aac6351\",\"applicationRoleName\":\"TestRolename5efb137f-bd97-47c2-b68c-481b569bceab\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"d0b7a148-9e79-4ce8-bcfa-233fd168c3e5\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgcd085211-d35d-4e3f-badd-69db9aacadf7\",\"applicationRoleName\":\"TestRolenamee7f3ddc2-5553-48cf-a657-35396e165df4\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"61445b46-8c58-480d-9646-3c11907fdebd\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgcb901e34-a90c-4d74-acdd-9c8bd74a30e3\",\"applicationRoleName\":\"TestRolenamed765b960-da67-42e2-b641-3932da720e49\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"32045073-da33-4274-b356-b149db5d92b8\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgcc6ac738-417f-484b-b7e2-0be153aa567b\",\"applicationRoleName\":\"TestRolename97b3a874-028a-4d2c-8051-c01e894b8e33\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"08a0fb4d-76bb-48f9-b223-7cbc9ee4f7a4\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg1d6664ae-e78a-474b-b606-499f6a6733ef\",\"applicationRoleName\":\"TestRolenamefa252da6-5444-46c0-880c-3d946a6e7114\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"46e1583f-750d-4d8f-9b52-a3addc642caa\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgb4572050-4220-4f4e-9d21-d048c987b2c0\",\"applicationRoleName\":\"TestRolename46a810c5-fa8a-4ea7-97ed-b6ac56e4171b\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"f95aeb0a-7c07-4331-aa25-353f0171a967\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrg61892690-640a-4d7b-ac83-fbb8fdb1febf\",\"applicationRoleName\":\"TestRolename177f1e46-9074-4b3b-b26e-a50e94a7e1c9\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"c2dd261b-db91-451c-a103-71d35e651fa5\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgf6f2f870-647e-4896-a057-59572d31625c\",\"applicationRoleName\":\"TestRolename2e9d5acb-7121-4446-a912-ce0c769d0a47\",\"applicationRoleValue\":\"testRoleValue\"},{\"roleId\":\"ea5425b1-8420-4874-a579-12f617fa4ffd\",\"uid\":\"useradmin\",\"applicationId\":\"100\",\"applicationName\":\"ACS\",\"organizationName\":\"TestOrgdc16735b-7d5a-4d3d-9a4d-06aa305cfacc\",\"applicationRoleName\":\"TestRolename712f37f1-bca0-48f9-af43-31791de5160c\",\"applicationRoleValue\":\"testRoleValue\"}]\n";

    @Test
    public void testRolesFromJson() throws Exception {

        List<UserApplicationRoleEntry> roles = UserRoleMapper.fromJsonAsList(rolesJsonExample);
        System.out.println("First role" + UserRoleMapper.toJson(roles.listIterator().next()));
        assertNotNull(roles);
        assertNotNull(roles.iterator().next().getId() != null);

    }


}