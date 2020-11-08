package net.whydah.sso.ddd.model.userrole;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoleValueTest {


    private static final Logger log = LoggerFactory.getLogger(RoleValueTest.class);

    @Test
    public void testIllegalRoleValue() {
        assertFalse(RoleValue.isValid("<javascript:"));
        assertFalse(RoleValue.isValid("<html>"));
        //This is valid
        //assertFalse(RoleValue.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        //This is valid
        //assertFalse(RoleValue.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        //This is valid
        //assertFalse(RoleValue.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        //This is valid
        //assertFalse(RoleValue.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        //assertFalse(RoleValue.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        //assertFalse(RoleValue.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        
    }

    @Test
    public void testOKRoleValue() {
        assertTrue(RoleValue.isValid(""));
        assertTrue(RoleValue.isValid("abc"));  // to short
        assertTrue(RoleValue.isValid("243543"));
        assertTrue(RoleValue.isValid("+4722334455"));
        assertTrue(RoleValue.isValid("asadadsaYUYI"));
        assertTrue(RoleValue.isValid("ola.nordman"));
        assertTrue(RoleValue.isValid("ola.nordman@test.no"));
        assertTrue(RoleValue.isValid("22334455"));
        assertTrue(RoleValue.isValid("TestRolename111f653f-7958-474e-b5ff-9ef428323e27"));
        assertTrue(RoleValue.isValid(UUID.randomUUID().toString()));
        assertTrue(RoleValue.isValid("Employee"));
        assertTrue(RoleValue.isValid("{\"deliveryaddress\":{\"reference\":\"\",\"postalcode\":\"0151\",\"countryCode\":\"no\",\"contact\":{\"phoneNumber\":\"91908252\",\"emailConfirmed\":true,\"name\":\"Brian Weeteling\",\"email\":\"brian@geta.no\",\"phoneNumberConfirmed\":\"true\"},\"name\":\"Brian Weeteling\",\"postalcity\":\"Oslo\",\"addressLine1\":\"Rådhusgata 30B\",\"company\":\"\",\"addressLine2\":\"null\",\"deliveryinformation\":{\"pickupPoint\":\"\",\"additionalAddressInfo\":\"\",\"Deliverytime\":\"\"},\"tags\":\"\"}}"));
        assertTrue(RoleValue.isValid("234324+2342"));
        String original = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
        		"<user>    <params>   <aadAccessToken>eyJ0eXAiOiJKV1QiLCJub25jZSI6Il8tM0laMV96MVdnNjN6Y1FMdkU4UGlxcXY0N0pSMl9ubmdXMDB2MEh5RVEiLCJhbGciOiJSUzI1NiIsIng1dCI6ImtnMkxZczJUMENUaklmajRydDZKSXluZW4zOCIsImtpZCI6ImtnMkxZczJUMENUaklmajRydDZKSXluZW4zOCJ9.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTAwMDAtYzAwMC0wMDAwMDAwMDAwMDAiLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC84ZWU5ZjU3My00ZTgwLTQ0M2EtODdiYy03NGRmODAxNTdmZWMvIiwiaWF0IjoxNjA0ODIzNzY1LCJuYmYiOjE2MDQ4MjM3NjUsImV4cCI6MTYwNDgyNzY2NSwiYWNjdCI6MSwiYWNyIjoiMSIsImFpbyI6IkFVUUF1LzhSQUFBQWdLNnNFRzNiOWJIMUhuWjhaaXRtODNQTThhMjN1aU44UWJ6NFZ2R1JwVU1LM05jNmE2eFYxMnFWUUJ1M2VzSlpQVG05UXNjVlpJeHNSekNGMVoybUlnPT0iLCJhbHRzZWNpZCI6IjE6bGl2ZS5jb206MDAwMzAwMDBDRUEzMDU1RiIsImFtciI6WyJwd2QiXSwiYXBwX2Rpc3BsYXluYW1lIjoiRW50cmFTU08iLCJhcHBpZCI6IjY3NzRmYzZhLTI0OTUtNDlkNS1hMDExLTJhYWU1ZGExNGQzMyIsImFwcGlkYWNyIjoiMSIsImVtYWlsIjoibWlzdGVyaHV5ZG9AZ21haWwuY29tIiwiZmFtaWx5X25hbWUiOiJkbyIsImdpdmVuX25hbWUiOiJodXktZG9zcyIsImlkcCI6ImxpdmUuY29tIiwiaWR0eXAiOiJ1c2VyIiwiaXBhZGRyIjoiMTEzLjE3My41LjUiLCJuYW1lIjoiaHV5LWRvc3MgZG8iLCJvaWQiOiJjYjU5YWFjMi02YjI0LTQwNzMtYTQzZi1mNGMwZTljZWU2ZTkiLCJwbGF0ZiI6IjMiLCJwdWlkIjoiMTAwMzIwMDBGMjU1REM1NCIsInJoIjoiMC5BQUFBY19YcGpvQk9Pa1NIdkhUZmdCVl83R3I4ZEdlVkpOVkpvQkVxcmwyaFRUTldBSG8uIiwic2NwIjoib3BlbmlkIHByb2ZpbGUgVXNlci5SZWFkIGVtYWlsIiwic2lnbmluX3N0YXRlIjpbImttc2kiXSwic3ViIjoiM1dGVFZnZW4wU3hjbHB0Y2ViQUY1Z0Z1WWVkMEQ3RlRybG0yS0lvT3FSQSIsInRlbmFudF9yZWdpb25fc2NvcGUiOiJBUyIsInRpZCI6IjhlZTlmNTczLTRlODAtNDQzYS04N2JjLTc0ZGY4MDE1N2ZlYyIsInVuaXF1ZV9uYW1lIjoibGl2ZS5jb20jbWlzdGVyaHV5ZG9AZ21haWwuY29tIiwidXRpIjoiV1N0SkJIbGFwVU9jZTNQWjYzTVVBQSIsInZlciI6IjEuMCIsIndpZHMiOlsiMTk0YWU0Y2ItYjEyNi00MGIyLWJkNWItNjA5MWIzODA5NzdkIiwiY2YxYzM4ZTUtMzYyMS00MDA0LWE3Y2ItODc5NjI0ZGNlZDdjIiwiMTNiZDFjNzItNmY0YS00ZGNmLTk4NWYtMThkM2I4MGYyMDhhIl0sInhtc19zdCI6eyJzdWIiOiJVRkFTUEctQjNxamI5OHJ5V2hucFJ6SlZYR3lEZnRVZUVUWDFIOVd6bWJvIn0sInhtc190Y2R0IjoxNjAwNjg2NjkwfQ.cry2ggeGvR7wXVtd6eJaBc9apQxoqIaUIS-RVozuJBig2fz-FrJIb7SDojDkRzYL3441GQySuXAq0cOK_GJUVX7L-iHMfjmFdzTrkg60qU9JCsSCDYUedjluYkhEpKoCwYnw2FdeRir0g_1478SV7RhK2oQO6XYl686kz4cg_qEjXIhb94I9TPR3Wy9vu_MgYSJC0NzGAbguQHFFO4TmFZc47Cq4oj7QEyDC7Xl8vDw45W9fYEV_n6tl1eYGp-JB5sVOvCzBBPnF0h_HhfRqg12vGtHwJBGfUXtqXiaUA3aRS7wt-b6dKqIyCAKi_mWEA5CgESp-vI6jUohDlDw1Ig</aadAccessToken>   <appRoles/>        <userId>cb59aac2-6b24-4073-a43f-f4c0e9cee6e9</userId>        <firstName>huy-doss</firstName>        <lastName>do</lastName>        <username>aad-misterhuydo@gmail.com</username>        <email>misterhuydo@gmail.com</email>        <cellPhone>0943993828</cellPhone>        <personRef/>    </params> </user>";
        String role = "<![CDATA[" + original + "]]>"; 
        assertTrue(RoleValue.isValid(role));
    }


}