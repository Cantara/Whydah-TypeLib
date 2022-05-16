package net.whydah.basehelpers;

import junit.framework.TestCase;
import net.whydah.sso.basehelpers.Validator;
import org.jsoup.safety.Safelist;
import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {


    @Test
    public void test_isValidTextInput_base64_logo_data() throws Exception {
        //Valid image format
        TestCase.assertTrue(Validator.isValidTextInput("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQAQMAAAAlPW0iAAAABlBMVEUAAAD///+l2Z/dAAAAM0lEQVR4nGP4/5/h/1+G/58ZDrAz3D/McH8yw83NDDeNGe4Ug9C9zwz3gVLMDA/A6P9/AFGGFyjOXZtQAAAAAElFTkSuQmCC", 0, Validator.DEFAULT_MAX_LENGTH_10240, Validator.DEFAULT_IMAGE_BASE64_PATTTERN));
        Assert.assertFalse(Validator.isValidTextInput("iVBORw0KGgoAAAANSUhEUgAAABAAAAAQAQMAAAAlPW0iAAAABlBMVEUAAAD///+l2Z/dAAAAM0lEQVR4nGP4/5/h/1+G/58ZDrAz3D/McH8yw83NDDeNGe4Ug9C9zwz3gVLMDA/A6P9/AFGGFyjOXZtQAAAAAElFTkSuQmCC", 0, Validator.DEFAULT_MAX_LENGTH_10240, Validator.DEFAULT_IMAGE_BASE64_PATTTERN));

    }

    @Test
    public void test_isValidTextInput_url() throws Exception {
        //Valid URLS
        TestCase.assertTrue(Validator.isValidTextInput("http://127.0.0.1:8080/oidsso/login", 0, Validator.DEFAULT_MAX_LENGTH_1024, Validator.DEFAULT_URL_PATTERN));
        TestCase.assertTrue(Validator.isValidTextInput("https://google.com/search?q=test", 0, Validator.DEFAULT_MAX_LENGTH_1024, Validator.DEFAULT_URL_PATTERN));
        TestCase.assertTrue(Validator.isValidTextInput("https://google.com/search?q=test+with+google&rlz=1C1CHBF_enV", 0, Validator.DEFAULT_MAX_LENGTH_1024, Validator.DEFAULT_URL_PATTERN));
        TestCase.assertTrue(Validator.isValidTextInput("https://google.com/search?q=test+with+google&rlz=1C1CHBF_enV", 0, Validator.DEFAULT_MAX_LENGTH_1024, Validator.DEFAULT_URL_PATTERN));
        TestCase.assertTrue(Validator.isValidTextInput("https://google.com/search?q=test+with+google&rlz=1C1CHBF_enV", 0, Validator.DEFAULT_MAX_LENGTH_1024, Validator.DEFAULT_URL_PATTERN));
        TestCase.assertTrue(Validator.isValidTextInput("http://wp.localhost:8080", 0, Validator.DEFAULT_MAX_LENGTH_1024, Validator.DEFAULT_URL_PATTERN));
        
        
        //Invalid ones
        Assert.assertFalse(Validator.isValidTextInput("localhost/login", 1, 100, Validator.DEFAULT_URL_PATTERN));
        Assert.assertFalse(Validator.isValidTextInput("/login", 1, 100, Validator.DEFAULT_URL_PATTERN));
        Assert.assertFalse(Validator.isValidTextInput("http://...login", 1, 100, Validator.DEFAULT_URL_PATTERN));

    }

    @Test
    public void test_isValidJsonInput_url() throws Exception {
        Assert.assertTrue(Validator.isValidJsonInput(" {      \"name\": \"Bård Lind\",    }", 1, 500, Validator.DEFAULT_SENSIBLE_ESCAPED_JSON));
        Assert.assertTrue(Validator.isValidJsonInput(" {      \"name\": \"Bård Lind\",      \"company\":\"\",      \"addressLine1\":\"null\",      \"addressLine2\":\"null\",      \"postalcode\":\"\",      \"postalcity\":\"\",      \"countryCode\":\"no\",      \"reference\":\"\",      \"tags\":\"\",      \"contact\": {\"name\":\"Bård Lind\",\"email\":\"bli@capraconsulting.no\",\"emailConfirmed\":\"false\",\"phoneNumber\":\"93234963\", \"phoneNumberConfirmed\":\"true\"},      \"deliveryinformation\": {\"additionalAddressInfo\":\"\",\"pickupPoint\":\"\",\"Deliverytime\":\"\"}}}", 1, 500, Validator.DEFAULT_SENSIBLE_ESCAPED_JSON));


        Assert.assertFalse(Validator.isValidJsonInput("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'", 1, 500, Validator.DEFAULT_SENSIBLE_ESCAPED_JSON));
        Assert.assertFalse(Validator.isValidJsonInput("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'", 1, 500, Validator.DEFAULT_SENSIBLE_ESCAPED_JSON));
        Assert.assertFalse(Validator.isValidJsonInput("alert'%2bconfirm('XXS-PoC1')%2b'", 1, 500, Validator.DEFAULT_SENSIBLE_ESCAPED_JSON));

    }

    @Test
    public void test_isValidTextInput_email() throws Exception {
        //Valid URLS
        TestCase.assertTrue(Validator.isValidTextInput("misterhuydo@gmail.com", 0, Validator.DEFAULT_MAX_LENGTH_1024, Validator.DEFAULT_EMAIL_PATTERN));
        TestCase.assertTrue(Validator.isValidTextInput("totto@totto.net", 0, Validator.DEFAULT_MAX_LENGTH_1024, Validator.DEFAULT_EMAIL_PATTERN));

        //Invalid ones
        Assert.assertFalse(Validator.isValidTextInput("someone@com", 1, 100, Validator.DEFAULT_EMAIL_PATTERN));

    }

    @Test
    public void test_isValidTextInput_phoneNumber() throws Exception {
        //Valid URLS
        TestCase.assertTrue(Validator.isValidTextInput("+47 90221133", 0, Validator.DEFAULT_MAX_LENGTH_1024, Validator.DEFAULT_PHONE_NUMBER_PATTERN));
        TestCase.assertTrue(Validator.isValidTextInput("91905054", 0, Validator.DEFAULT_MAX_LENGTH_1024, Validator.DEFAULT_PHONE_NUMBER_PATTERN));
        TestCase.assertTrue(Validator.isValidTextInput("+84 91 90 50 54", 0, Validator.DEFAULT_MAX_LENGTH_1024, Validator.DEFAULT_PHONE_NUMBER_PATTERN));
        TestCase.assertTrue(Validator.isValidTextInput("+8491905054", 0, Validator.DEFAULT_MAX_LENGTH_1024, Validator.DEFAULT_PHONE_NUMBER_PATTERN));


        //Invalid ones
        Assert.assertFalse(Validator.isValidTextInput("-91905054", 1, 100, Validator.DEFAULT_PHONE_NUMBER_PATTERN));
        Assert.assertFalse(Validator.isValidTextInput("91+9-05054", 1, 100, Validator.DEFAULT_PHONE_NUMBER_PATTERN));

    }

    @Test
    public void test_isValidTextInput_normalText() throws Exception {
        //Valid text
        TestCase.assertTrue(Validator.isValidTextInput("'%20or%20'1'='1", 1, 5120));
        TestCase.assertTrue(Validator.isValidTextInput("this is the normal text 1234 - ^%*45", 1, 5120));
        TestCase.assertTrue(Validator.isValidTextInput("this is the normal text 1234 - 22()", 1, 5120));
        TestCase.assertTrue(Validator.isValidTextInput("this is text 1234 - 2%%^^*()_)+_)!@#$3", 1, 5120));

        //Invalid ones
        Assert.assertFalse(Validator.isValidTextInput("test test <script type='javascript'></script>", 1, 100));
        Assert.assertFalse(Validator.isValidTextInput("<b>test test</b>", 1, 100));

    }

    @Test
    public void test_isValidTextInput_normalText_with_strict_policies() {

        //valid with required policies
        //allow UTF-8 text
        TestCase.assertTrue(Validator.isValidTextInput("Ærinbjørn Ånet", 1, 100));
        //allow only simple text formatting: b, em, i, strong, u. All other HTML (tags and attributes) will be removed.
        TestCase.assertTrue(Validator.isValidTextInput("<b>Username:</b> totto", 1, 100, null, null, false, Safelist.simpleText()));
        //only text and no html
        TestCase.assertTrue(Validator.isValidTextInput("Username: totto", 1, 100, null, null, false, Safelist.none()));
        //allow characters not in the blacklist
        TestCase.assertTrue(Validator.isValidTextInput("test test `~", 1, 100, null, new String[]{"^", "&", "*", "(", ")", "#", "%", "@", "!"}));
        //should meet the pattern
        TestCase.assertTrue(Validator.isValidTextInput("test12345test", 1, 100, "^test\\d+test"));


        //invalid:
        //max length problem
        Assert.assertFalse(Validator.isValidTextInput("test test <script type='javascript'></script>", 1, 10));
        //min length problem
        Assert.assertFalse(Validator.isValidTextInput("test", 10, 50));
        //should not contains any script
        Assert.assertFalse(Validator.isValidTextInput("test test <script type='javascript'>alert('hello world!')</script>", 1, 100));
        //should not contains invalid characters
        Assert.assertFalse(Validator.isValidTextInput("test test ^", 1, 100, null, new String[]{"^", "&", "*"}));
        Assert.assertFalse(Validator.isValidTextInput("test test $", 1, 100, null, Validator.DEFAULT_INVALID_CHARACTERS_FOR_NOSQL_API_ATTACK));
        //should meet the pattern
        Assert.assertFalse(Validator.isValidTextInput("test test", 1, 100, "^test\\dtest"));
        Assert.assertFalse(Validator.isValidTextInput("hell test12345test", 1, 100, "^test\\d+test"));
        //should detect xpath injection if required
        Assert.assertFalse(Validator.isValidTextInput("'%20or%20'1'='1", 1, 100, null, null, true, Safelist.none()));
        //only allows simple text, detect vulnerable scripts
        Assert.assertFalse(Validator.isValidTextInput("<h1>hello</h1><iframe src='https://tesd--.ji'></iframe>", 1, 100, null, null, false, Safelist.simpleText()));
        Assert.assertFalse(Validator.isValidTextInput("http://server/cgi-bin/testcgi.exe?<SCRIPT>alert(“Cookie”+document.cookie)</SCRIPT>", 1, 100, null, null, false, Safelist.simpleText()));
        Assert.assertFalse(Validator.isValidTextInput("http://server/cgi-bin/article.php?title=<meta%20http-equiv='refresh'%20content='0;'>", 1, 100));
        Assert.assertFalse(Validator.isValidTextInput("<script type=text/vbscript>alert(DOCUMENT.COOKIE)</script", 1, 100));
    }

    @Test
    public void test_sanitize() {
        Assert.assertEquals("Ærinbjørn Ånet", Validator.sanitize("Ærinbjørn Ånet"));
        Assert.assertEquals("hello", Validator.sanitize("<h1>hello</h1><iframe src='https://tesd--.ji'></iframe>"));
        Assert.assertEquals("&lt;?xml version=1.0 encoding=UTF-8?&gt;&lt;俄语 լեզու='ռուսերեն'&gt;данные&lt;/俄语&gt;&lt;/xml&gt;", Validator.sanitize("<iframe src='https://tesd--.ji'><?xml version=1.0 encoding=UTF-8?><俄语 լեզու='ռուսերեն'>данные</俄语></xml></iframe>"));
        Assert.assertEquals("<b>hello</b>", Validator.sanitizeHtml("<div><b>hello</b></div><iframe src='https://tesd--.ji'></iframe>", Safelist.simpleText()));

    }

    @Test
    public void test_sanitizeHtml() {
        Assert.assertEquals("", Validator.sanitizeHtml("<script>alert(document.cookie);</script>", Safelist.basic()));
        Assert.assertEquals("http://server/cgi-bin/testcgi.exe?", Validator.sanitizeHtml("http://server/cgi-bin/testcgi.exe?<SCRIPT>alert(“Cookie”+document.cookie)</SCRIPT>", Safelist.none()));

    }

    @Test
    public void test_sanitizeXml() {
        String sample = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<!DOCTYPE foo [  " +
                "<!ELEMENT foo ANY >" +
                "<!ENTITY xxe SYSTEM \"file:///dev/random\" >]><foo>&xxe;</foo>";

        sample = Validator.sanitizeXml(sample);
        TestCase.assertTrue(!sample.contains("ENTITY")); //If we don't use ENTITY in our XML we will be safe

        //    	<?xml version="1.0"?>
        //    	<!DOCTYPE lolz [
        //    	  <!ENTITY lol "lol">
        //    	  <!ENTITY lol2 "&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;">
        //    	  <!ENTITY lol3 "&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;">
        //    	  <!ENTITY lol4 "&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;">
        //    	  <!ENTITY lol5 "&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;">
        //    	  <!ENTITY lol6 "&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;">
        //    	  <!ENTITY lol7 "&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;">
        //    	  <!ENTITY lol8 "&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;">
        //    	  <!ENTITY lol9 "&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;">
        //    	]>
        //    	<lolz>&lol9;</lolz>

        //this small (< 1 KB) block of XML will actually contain a billion “lol”s, taking up almost 3GB of memory!
        //try to load this one, you will see the surprise
        //    	void processXml(string xml)
        //    	{
        //    	    System.Xml.XmlDocument document = new XmlDocument();
        //    	    document.LoadXml(xml);
        //    	}


    }
}
