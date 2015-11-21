package net.whydah.sso.application.helpers;

import java.util.Random;

public class ApplicationHelper {

    static Random ran = new Random();

    public static String getDummyApplicationToken() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n " +

                "    <applicationtoken>\n" +
                "        <params>\n" +
                "            <applicationtokenID>47289347982137421</applicationtokenID>\n" +
                "            <applicationid>45</applicationid>\n" +
                "            <applicationname>dummyapp</applicationname>\n" +
                "            <expires>3453453</expires>\n" +
                "        </params> \n" +
                "           <Url type=\"application/xml\"" +
                "                template=\"http://example.com/user/47289347982137421/get_usertoken_by_usertokenid\"/>" +
                "    </applicationtoken>\n";
    }


    public static String getDummyAppllicationListJson() {
        return
                "\n" +
                        "[\n" +
                        "   {\n" +
                        "      \"id\": \"100\",\n" +
                        "      \"name\": \"ACS\",\n" +
                        "      \"description\": \"Finn den kompetansen du trenger, når du trenger det. Lag eksklusive CV'er tillpasset leseren.\",\n" +
                        "      \"company\" : \"Norway AS\",\n" +
                        "      \"tags\" : \"HIDDEN, JURISDICTION_NORWAY\",\n" +
                        "      \"applicationUrl\": null,\n" +
                        "      \"logoUrl\": null,\n" +
                        "      \"roles\": [{\n" +
                        "           \"id\" : \"roleId1\",\n" +
                        "           \"name\" : \"roleName1\"\n" +
                        "      } ],\n" +
                        "      \"organizationNames\": [{\n" +
                        "            \"id\" : \"orgId\",\n" +
                        "            \"name\" : \"organizationName1\"\n" +
                        "          }, {\n" +
                        "            \"id\" : \"orgidxx\",\n" +
                        "            \"name\" : \"defaultOrgName\"\n" +
                        "          } ],\n" +
                        "      \"defaultRoleName\": \"Employee\",\n" +
                        "      \"defaultOrganizationName\": \"ACSOrganization\",\n" +
                        "      \"security\": {\n" +
                        "         \"minSecurityLevel\": \"0\",\n" +
                        "         \"minDEFCON\": \"DEFCON5\",\n" +
                        "         \"maxSessionTimoutSeconds\": \"86400\",\n" +
                        "         \"allowedIpAddresses\": [\n" +
                        "            \"0.0.0.0/0\"\n" +
                        "         ],\n" +
                        "         \"userTokenFilter\": \"true\",\n" +
                        "         \"secret\": \"45fhRM6nbKZ2wfC6RMmMuzXpk\"\n" +
                        "      },\n" +
                        "      \"acl\": [{\n" +
                        "          \"applicationId\" : \"11\",\n" +
                        "           \"applicationACLPath\" : \"/user\"\n" +
                        "      }]\n" +
                        "   },\n" +
                        "   {\n" +
                        "      \"id\": \"101\",\n" +
                        "      \"name\": \"ACSResource\",\n" +
                        "      \"description\": \"Finn den kompetansen du trenger, når du trenger det. Lag eksklusive CV'er tillpasset leseren.\",\n" +
                        "      \"company\" : \"Norway AS\",\n" +
                        "      \"tags\" : \"HIDDEN, JURISDICTION_NORWAY\",\n" +
                        "      \"applicationUrl\": null,\n" +
                        "      \"logoUrl\": null,\n" +
                        "      \"roles\": [{\n" +
                        "           \"id\" : \"roleId1\",\n" +
                        "           \"name\" : \"roleName1\"\n" +
                        "      } ],\n" +
                        "      \"organizationNames\": [{\n" +
                        "            \"id\" : \"orgId\",\n" +
                        "            \"name\" : \"organizationName1\"\n" +
                        "          }, {\n" +
                        "            \"id\" : \"orgidxx\",\n" +
                        "            \"name\" : \"defaultOrgName\"\n" +
                        "          } ],\n" +
                        "      \"defaultRoleName\": \"Employee\",\n" +
                        "      \"defaultOrganizationName\": \"ACSOrganization\",\n" +
                        "      \"security\": {\n" +
                        "         \"minSecurityLevel\": \"0\",\n" +
                        "         \"minDEFCON\": \"DEFCON5\",\n" +
                        "         \"maxSessionTimoutSeconds\": \"86400\",\n" +
                        "         \"allowedIpAddresses\": [\n" +
                        "            \"0.0.0.0/0\"\n" +
                        "         ],\n" +
                        "         \"userTokenFilter\": \"true\",\n" +
                        "         \"secret\": \"55fhRM6nbKZ2wfC6RMmMuzXpk\"\n" +
                        "      },\n" +
                        "      \"acl\": [{\n" +
                        "          \"applicationId\" : \"2211\",\n" +
                        "           \"applicationACLPath\" : \"/user\"\n" +
                        "      }]\n" +
                        "   },\n" +
                        "   {\n" +
                        "      \"id\": \"2201\",\n" +
                        "      \"name\": \"Whydah-UserIdentityBackend\",\n" +
                        "      \"description\": \"The UserIdentityBackend module of the Whydah IAM/SSO\",\n" +
                        "      \"company\" : \"Norway AS\",\n" +
                        "      \"tags\" : \"HIDDEN, JURISDICTION_NORWAY\",\n" +
                        "      \"applicationUrl\": \"https://whydahdev.cantara.no/uib/\",\n" +
                        "      \"logoUrl\": \"https://whydahdev.cantara.no/useradmin/img/whydah.png\",\n" +
                        "      \"roles\": [{\n" +
                        "           \"id\" : \"roleId1\",\n" +
                        "           \"name\" : \"roleName1\"\n" +
                        "      } ],\n" +
                        "      \"organizationNames\": [{\n" +
                        "            \"id\" : \"orgId\",\n" +
                        "            \"name\" : \"organizationName1\"\n" +
                        "          }, {\n" +
                        "            \"id\" : \"orgidxx\",\n" +
                        "            \"name\" : \"defaultOrgName\"\n" +
                        "          } ],\n" +
                        "      \"defaultRoleName\": \"Whydah-SSOLoginWebApp\",\n" +
                        "      \"defaultOrganizationName\": \"Whydah\",\n" +
                        "      \"security\": {\n" +
                        "         \"minSecurityLevel\": \"0\",\n" +
                        "         \"minDEFCON\": \"DEFCON5\",\n" +
                        "         \"maxSessionTimoutSeconds\": \"86400\",\n" +
                        "         \"allowedIpAddresses\": [\n" +
                        "            \"0.0.0.0/0\"\n" +
                        "         ],\n" +
                        "         \"userTokenFilter\": \"true\",\n" +
                        "         \"secret\": \"6r46g3q986Ep6By7B9J46m96D\"\n" +
                        "      },\n" +
                        "      \"acl\": [{\n" +
                        "          \"applicationId\" : \"2211\",\n" +
                        "           \"applicationACLPath\" : \"/user\"\n" +
                        "      }]\n" +
                        "   },\n" +
                        "   {\n" +
                        "      \"id\": \"2211\",\n" +
                        "      \"name\": \"Whydah-SecurityTokenService\",\n" +
                        "      \"description\": \"The ApplicationToken and UserToken module in Whydah IAM/SSO\",\n" +
                        "      \"company\" : \"Norway AS\",\n" +
                        "      \"tags\" : \"HIDDEN, JURISDICTION_NORWAY\",\n" +
                        "      \"applicationUrl\": \"https://whydahdev.cantara.no/sts/\",\n" +
                        "      \"logoUrl\": \"https://whydahdev.cantara.no/useradmin/img/whydah.png\",\n" +
                        "      \"roles\": [{\n" +
                        "           \"id\" : \"roleId1\",\n" +
                        "           \"name\" : \"roleName1\"\n" +
                        "      } ],\n" +
                        "      \"organizationNames\": [{\n" +
                        "            \"id\" : \"orgId\",\n" +
                        "            \"name\" : \"organizationName1\"\n" +
                        "          }, {\n" +
                        "            \"id\" : \"orgidxx\",\n" +
                        "            \"name\" : \"defaultOrgName\"\n" +
                        "          } ],\n" +
                        "      \"defaultRoleName\": \"Whydah-SSOLoginWebApp\",\n" +
                        "      \"defaultOrganizationName\": \"Whydah\",\n" +
                        "      \"security\": {\n" +
                        "         \"minSecurityLevel\": \"0\",\n" +
                        "         \"minDEFCON\": \"DEFCON5\",\n" +
                        "         \"maxSessionTimoutSeconds\": \"86400\",\n" +
                        "         \"allowedIpAddresses\": [\n" +
                        "            \"0.0.0.0/0\"\n" +
                        "         ],\n" +
                        "         \"userTokenFilter\": \"true\",\n" +
                        "         \"secret\": \"6r46g3q986Ep6By7B9J46m96D\"\n" +
                        "      },\n" +
                        "      \"acl\": [{\n" +
                        "          \"applicationId\" : \"2211\",\n" +
                        "           \"applicationACLPath\" : \"/user\"\n" +
                        "      }]\n" +
                        "   },\n" +
                        "   {\n" +
                        "      \"id\": \"2212\",\n" +
                        "      \"name\": \"Whydah-UserAdminService\",\n" +
                        "      \"description\": \"Responsible for configuring which API/useradministration services Whydah IAM/SSO should provide for 3rd parties (outside the innermost firewall)\",\n" +
                        "      \"company\" : \"Norway AS\",\n" +
                        "      \"tags\" : \"HIDDEN, JURISDICTION_NORWAY\",\n" +
                        "      \"applicationUrl\": \"https://whydahdev.cantara.no/uas/\",\n" +
                        "      \"logoUrl\": \"https://whydahdev.cantara.no/useradmin/img/whydah.png\",\n" +
                        "      \"roles\": [{\n" +
                        "           \"id\" : \"roleId1\",\n" +
                        "           \"name\" : \"roleName1\"\n" +
                        "      } ],\n" +
                        "      \"organizationNames\": [{\n" +
                        "            \"id\" : \"orgId\",\n" +
                        "            \"name\" : \"organizationName1\"\n" +
                        "          }, {\n" +
                        "            \"id\" : \"orgidxx\",\n" +
                        "            \"name\" : \"defaultOrgName\"\n" +
                        "          } ],\n" +
                        "      \"defaultRoleName\": \"Whydah-UserAdminWebApp\",\n" +
                        "      \"defaultOrganizationName\": \"Whydah\",\n" +
                        "      \"security\": {\n" +
                        "         \"minSecurityLevel\": \"0\",\n" +
                        "         \"minDEFCON\": \"DEFCON5\",\n" +
                        "         \"maxSessionTimoutSeconds\": \"86400\",\n" +
                        "         \"allowedIpAddresses\": [\n" +
                        "            \"0.0.0.0/0\"\n" +
                        "         ],\n" +
                        "         \"userTokenFilter\": \"true\",\n" +
                        "         \"secret\": \"9ju592A4t8dzz8mz7a5QQJ7Px\"\n" +
                        "      },\n" +
                        "      \"acl\": [{\n" +
                        "          \"applicationId\" : \"2211\",\n" +
                        "           \"applicationACLPath\" : \"/user\"\n" +
                        "      }]\n" +
                        "   },\n" +
                        "   {\n" +
                        "      \"id\": \"2215\",\n" +
                        "      \"name\": \"Whydah-SSOLoginWebApp\",\n" +
                        "      \"description\": \"The SSO / WebFrontend module of the Whydah IAM/SSO\",\n" +
                        "      \"company\" : \"Norway AS\",\n" +
                        "      \"tags\" : \"HIDDEN, JURISDICTION_NORWAY\",\n" +
                        "      \"applicationUrl\": \"https://whydahdev.cantara.no/sso/\",\n" +
                        "      \"logoUrl\": \"https://whydahdev.cantara.no/useradmin/img/whydah.png\",\n" +
                        "      \"roles\": [{\n" +
                        "           \"id\" : \"roleId1\",\n" +
                        "           \"name\" : \"roleName1\"\n" +
                        "      } ],\n" +
                        "      \"organizationNames\": [{\n" +
                        "            \"id\" : \"orgId\",\n" +
                        "            \"name\" : \"organizationName1\"\n" +
                        "          }, {\n" +
                        "            \"id\" : \"orgidxx\",\n" +
                        "            \"name\" : \"defaultOrgName\"\n" +
                        "          } ],\n" +
                        "      \"defaultRoleName\": \"SSOApplication\",\n" +
                        "      \"defaultOrganizationName\": \"Whydah\",\n" +
                        "      \"security\": {\n" +
                        "         \"minSecurityLevel\": \"0\",\n" +
                        "         \"minDEFCON\": \"DEFCON5\",\n" +
                        "         \"maxSessionTimoutSeconds\": \"86400\",\n" +
                        "         \"allowedIpAddresses\": [\n" +
                        "            \"0.0.0.0/0\"\n" +
                        "         ],\n" +
                        "         \"userTokenFilter\": \"true\",\n" +
                        "         \"secret\": \"33779936R6Jr47D4Hj5R6p9qT\"\n" +
                        "      },\n" +
                        "      \"acl\": [{\n" +
                        "          \"applicationId\" : \"2211\",\n" +
                        "           \"applicationACLPath\" : \"/user\"\n" +
                        "      }]\n" +
                        "   },\n" +
                        "   {\n" +
                        "      \"id\": \"2219\",\n" +
                        "      \"name\": \"Whydah-UserAdminWebApp\",\n" +
                        "      \"description\": \"The back-office User Administration module of the Whydah IAM/SSO\",\n" +
                        "      \"company\" : \"Norway AS\",\n" +
                        "      \"tags\" : \"HIDDEN, JURISDICTION_NORWAY\",\n" +
                        "      \"applicationUrl\": \"https://whydahdev.cantara.no/useradmin/\",\n" +
                        "      \"logoUrl\": \"https://whydahdev.cantara.no/useradmin/img/whydah.png\",\n" +
                        "      \"roles\": [{\n" +
                        "           \"id\" : \"roleId1\",\n" +
                        "           \"name\" : \"roleName1\"\n" +
                        "      } ],\n" +
                        "      \"organizationNames\": [{\n" +
                        "            \"id\" : \"orgId\",\n" +
                        "            \"name\" : \"organizationName1\"\n" +
                        "          }, {\n" +
                        "            \"id\" : \"orgidxx\",\n" +
                        "            \"name\" : \"defaultOrgName\"\n" +
                        "          } ],\n" +
                        "      \"defaultRoleName\": \"WhydahUserAdmin\",\n" +
                        "      \"defaultOrganizationName\": \"Whydah\",\n" +
                        "      \"security\": {\n" +
                        "         \"minSecurityLevel\": \"0\",\n" +
                        "         \"minDEFCON\": \"DEFCON5\",\n" +
                        "         \"maxSessionTimoutSeconds\": \"86400\",\n" +
                        "         \"allowedIpAddresses\": [\n" +
                        "            \"0.0.0.0/0\"\n" +
                        "         ],\n" +
                        "         \"userTokenFilter\": \"true\",\n" +
                        "         \"secret\": \"9EH5u5wJFKsUvJFmhypwK7j6D\"\n" +
                        "      },\n" +
                        "      \"acl\": [{\n" +
                        "          \"applicationId\" : \"2211\",\n" +
                        "           \"applicationACLPath\" : \"/user\"\n" +
                        "      }]\n" +
                        "   },\n" +
                        "   {\n" +
                        "      \"id\": \"2299\",\n" +
                        "      \"name\": \"Whydah-TestWebApplication\",\n" +
                        "      \"description\": \"The different example webapps to explain how to use/integrate with the Whydah IAM/SSO\",\n" +
                        "      \"company\" : \"Norway AS\",\n" +
                        "      \"tags\" : \"HIDDEN, JURISDICTION_NORWAY\",\n" +
                        "      \"applicationUrl\": \"https://whydahdev.cantara.no/test/\",\n" +
                        "      \"logoUrl\": \"https://whydahdev.cantara.no/useradmin/img/whydah.png\",\n" +
                        "      \"roles\": [{\n" +
                        "           \"id\" : \"roleId1\",\n" +
                        "           \"name\" : \"Whydah-TestWebApplication-user\"\n" +
                        "      } ],\n" +
                        "      \"organizationNames\": [{\n" +
                        "            \"id\" : \"orgId\",\n" +
                        "            \"name\" : \"organizationName1\"\n" +
                        "          }, {\n" +
                        "            \"id\" : \"orgidxx\",\n" +
                        "            \"name\" : \"defaultOrgName\"\n" +
                        "          } ],\n" +
                        "      \"defaultRoleName\": \"Whydah-TestWebApplication-user\",\n" +
                        "      \"defaultOrganizationName\": \"Whydah\",\n" +
                        "      \"security\": {\n" +
                        "         \"minSecurityLevel\": \"0\",\n" +
                        "         \"minDEFCON\": \"DEFCON5\",\n" +
                        "         \"maxSessionTimoutSeconds\": \"86400\",\n" +
                        "         \"allowedIpAddresses\": [\n" +
                        "            \"0.0.0.0/0\"\n" +
                        "         ],\n" +
                        "         \"userTokenFilter\": \"true\",\n" +
                        "         \"secret\": \"33879936R6Jr47D4Hj5R6p9qT\"\n" +
                        "      },\n" +
                        "      \"acl\": [{\n" +
                        "          \"applicationId\" : \"2211\",\n" +
                        "           \"applicationACLPath\" : \"/user\"\n" +
                        "      }]\n" +
                        "   },\n" +
                        "   {\n" +
                        "      \"id\": \"2001\",\n" +
                        "      \"name\": \"m2Circle\",\n" +
                        "      \"description\": null,\n" +
                        "      \"company\" : \"Norway AS\",\n" +
                        "      \"tags\" : \"HIDDEN, JURISDICTION_NORWAY\",\n" +
                        "      \"applicationUrl\": null,\n" +
                        "      \"logoUrl\": null,\n" +
                        "      \"roles\": [{\n" +
                        "           \"id\" : \"roleId1\",\n" +
                        "           \"name\" : \"roleName1\"\n" +
                        "      } ],\n" +
                        "      \"organizationNames\": [{\n" +
                        "            \"id\" : \"orgId\",\n" +
                        "            \"name\" : \"organizationName1\"\n" +
                        "          }, {\n" +
                        "            \"id\" : \"orgidxx\",\n" +
                        "            \"name\" : \"defaultOrgName\"\n" +
                        "          } ],\n" +
                        "      \"defaultRoleName\": \"member\",\n" +
                        "      \"defaultOrganizationName\": \"Whydah\",\n" +
                        "      \"security\": {\n" +
                        "         \"minSecurityLevel\": \"0\",\n" +
                        "         \"minDEFCON\": \"DEFCON5\",\n" +
                        "         \"maxSessionTimoutSeconds\": \"86400\",\n" +
                        "         \"allowedIpAddresses\": [\n" +
                        "            \"0.0.0.0/0\"\n" +
                        "         ],\n" +
                        "         \"userTokenFilter\": \"true\",\n" +
                        "         \"secret\": \"YKHH54bNpnvQEF2vCJSWtctB\"\n" +
                        "      },\n" +
                        "      \"acl\": [{\n" +
                        "          \"applicationId\" : \"2211\",\n" +
                        "           \"applicationACLPath\" : \"/user\"\n" +
                        "      }]\n" +
                        "   }\n" +
                        "]";
    }


    public static String getDummyApplicationJson() {
        return "{\n" +
                "  \"id\" : \"" + ran.nextInt(9999) + "\",\n" +
                "  \"name\" : \"ACS_" + ran.nextInt(9999) + "\",\n" +
                "  \"description\" : \"Finn den kompetansen du trenger, når du trenger det. Lag eksklusive CV'er tilpasset leseren.\",\n" +
                "  \"company\" : \"Norway AS\",\n" +
                "  \"tags\" : \"HIDDEN, JURISDICTION_NORWAY\",\n" +
                "  \"applicationUrl\" : \"http://my.application.com\",\n" +
                "  \"logoUrl\" : \"http://my.application.com/mylogo.png\",\n" +
                "  \"roles\" : [ {\n" +
                "    \"id\" : \"roleId-133\",\n" +
                "    \"name\" : \"superuser\"\n" +
                "  } ],\n" +
                "  \"defaultRoleName\" : \"Employee\",\n" +
                "  \"organizationNames\" : [ {\n" +
                "    \"id\" : \"orgid-12345\",\n" +
                "    \"name\" : \"ACSOrganization\"\n" +
                "  }, {\n" +
                "    \"id\" : \"my.application.com\",\n" +
                "    \"name\" : \"application.com\"\n" +
                "  } ],\n" +
                "  \"defaultOrganizationName\" : \"ACSOrganization\",\n" +
                "  \"security\" : {\n" +
                "    \"minSecurityLevel\" : \"0\",\n" +
                "    \"minDEFCON\" : \"DEFCON5\",\n" +
                "    \"maxSessionTimoutSeconds\" : \"86400\",\n" +
                "    \"allowedIpAddresses\" : [ \"0.0.0.0/0\" ],\n" +
                "    \"userTokenFilter\" : \"true\",\n" +
                "    \"secret\" : \"45fhRM6nbKZ2wfC6RMmMuzXpk\"\n" +
                "  },\n" +
                "  \"acl\" : [ ]\n" +
                "}";
    }

}
