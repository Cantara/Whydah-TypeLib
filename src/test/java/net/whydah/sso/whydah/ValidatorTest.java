package net.whydah.sso.whydah;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

import net.whydah.sso.basehelpers.ValidationConfig;
import net.whydah.sso.basehelpers.Validator;

import org.jsoup.safety.Whitelist;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidatorTest {
	
	 
	
    @Test
    public void test_isValidTextInput_url() throws Exception {
        //Valid URLS
    	assertTrue(Validator.isValidTextInput("http://127.0.0.1:8080/oidsso/login", ValidationConfig.DEFAULT_MIN_LENGTH, ValidationConfig.DEFAULT_MAX_LENGTH, Validator.DEFAULT_URL_PATTERN));
        assertTrue(Validator.isValidTextInput("https://google.com/search?q=test", ValidationConfig.DEFAULT_MIN_LENGTH, ValidationConfig.DEFAULT_MAX_LENGTH, Validator.DEFAULT_URL_PATTERN));
    	assertTrue(Validator.isValidTextInput("https://google.com/search?q=test+with+google&rlz=1C1CHBF_enV", ValidationConfig.DEFAULT_MIN_LENGTH, ValidationConfig.DEFAULT_MAX_LENGTH, Validator.DEFAULT_URL_PATTERN));
    	assertTrue(Validator.isValidTextInput("https://google.com/search?q=test+with+google&rlz=1C1CHBF_enV", ValidationConfig.DEFAULT_MIN_LENGTH, ValidationConfig.DEFAULT_MAX_LENGTH, Validator.DEFAULT_URL_PATTERN));
    	assertTrue(Validator.isValidTextInput("https://google.com/search?q=test+with+google&rlz=1C1CHBF_enV", ValidationConfig.DEFAULT_MIN_LENGTH, ValidationConfig.DEFAULT_MAX_LENGTH, Validator.DEFAULT_URL_PATTERN));
    	
    	//Invalid ones
    	assertFalse(Validator.isValidTextInput("localhost/login", 1, 100, Validator.DEFAULT_URL_PATTERN));
    	assertFalse(Validator.isValidTextInput("/login", 1, 100, Validator.DEFAULT_URL_PATTERN));
    	assertFalse(Validator.isValidTextInput("http://localhost:19997/oidsso/login", 1, 100, Validator.DEFAULT_URL_PATTERN));
    	assertFalse(Validator.isValidTextInput("http://...login", 1, 100, Validator.DEFAULT_URL_PATTERN));
    }
    
    @Test
    public void test_isValidTextInput_email() throws Exception {
        //Valid URLS
    	assertTrue(Validator.isValidTextInput("misterhuydo@gmail.com", ValidationConfig.DEFAULT_MIN_LENGTH, ValidationConfig.DEFAULT_MAX_LENGTH, Validator.DEFAULT_EMAIL_PATTERN));
        assertTrue(Validator.isValidTextInput("totto@totto.net", ValidationConfig.DEFAULT_MIN_LENGTH, ValidationConfig.DEFAULT_MAX_LENGTH, Validator.DEFAULT_EMAIL_PATTERN));
    	
    	//Invalid ones
    	assertFalse(Validator.isValidTextInput("someone@com", 1, 100, Validator.DEFAULT_EMAIL_PATTERN));

    }

    @Test
    public void test_isValidTextInput_normalText() throws Exception {
        //Valid text
    	assertTrue(Validator.isValidTextInput("'%20or%20'1'='1", 1, 5120));
    	assertTrue(Validator.isValidTextInput("this is the normal text 1234 - ^%*45", 1, 5120));
    	assertTrue(Validator.isValidTextInput("this is the normal text 1234 - 22()", 1, 5120));
    	assertTrue(Validator.isValidTextInput("this is text 1234 - 2%%^^*()_)+_)!@#$3", 1, 5120));
    	
    	//Invalid ones
    	assertFalse(Validator.isValidTextInput("test test <script type='javascript'></script>", 1, 100));
    	assertFalse(Validator.isValidTextInput("<b>test test</b>", 1, 100));
    	
    }

    @Test
    public void test_isValidTextInput_normalText_with_strict_policies(){
    	
    	//valid with required policies
    	//allow UTF-8 text
    	assertTrue(Validator.isValidTextInput("Ærinbjørn Ånet", 1, 100));
    	//allow only simple text formatting: b, em, i, strong, u. All other HTML (tags and attributes) will be removed.
    	assertTrue(Validator.isValidTextInput("<b>Username:</b> totto", 1, 100, null, null, false, Whitelist.simpleText()));
    	//only text and no html
    	assertTrue(Validator.isValidTextInput("Username: totto", 1, 100, null, null, false, Whitelist.none()));
    	//allow characters not in the blacklist
    	assertTrue(Validator.isValidTextInput("test test `~", 1, 100, null, new String[] {"^","&","*","(",")", "#","%", "@","!"}));
    	//should meet the pattern
    	assertTrue(Validator.isValidTextInput("test12345test", 1, 100, "^test\\d+test"));
    	
    	
    	//invalid:
    	//max length problem
    	assertFalse(Validator.isValidTextInput("test test <script type='javascript'></script>", 1, 10));
    	//min length problem
    	assertFalse(Validator.isValidTextInput("test", 10, 50));
    	//should not contains any script
    	assertFalse(Validator.isValidTextInput("test test <script type='javascript'>alert('hello world!')</script>", 1, 100));
    	//should not contains invalid characters
    	assertFalse(Validator.isValidTextInput("test test ^", 1, 100, null, new String[] {"^","&","*"}));
    	assertFalse(Validator.isValidTextInput("test test $", 1, 100, null, Validator.DEFAULT_INVALID_CHARACTERS_FOR_NOSQL_API_ATTACK));
    	//should meet the pattern
    	assertFalse(Validator.isValidTextInput("test test", 1, 100, "^test\\dtest"));
    	assertFalse(Validator.isValidTextInput("hell test12345test", 1, 100, "^test\\d+test"));
    	//should detect xpath injection if required
    	assertFalse(Validator.isValidTextInput("'%20or%20'1'='1", 1, 100, null, null, true, Whitelist.none()));
    	//only allows simple text, detect vulnerable scripts
    	assertFalse(Validator.isValidTextInput("<h1>hello</h1><iframe src='https://tesd--.ji'></iframe>", 1, 100, null, null, false, Whitelist.simpleText()));
    	assertFalse(Validator.isValidTextInput("http://server/cgi-bin/testcgi.exe?<SCRIPT>alert(“Cookie”+document.cookie)</SCRIPT>", 1, 100, null, null, false, Whitelist.simpleText()));
    	assertFalse(Validator.isValidTextInput("http://server/cgi-bin/article.php?title=<meta%20http-equiv='refresh'%20content='0;'>", 1, 100));
    	assertFalse(Validator.isValidTextInput("<script type=text/vbscript>alert(DOCUMENT.COOKIE)</script", 1, 100));
    }

    @Test
    public void test_sanitize(){
    	assertEquals("Ærinbjørn Ånet", Validator.sanitize("Ærinbjørn Ånet"));
    	assertEquals("hello", Validator.sanitize("<h1>hello</h1><iframe src='https://tesd--.ji'></iframe>"));
    	assertEquals("&lt;?xml version=1.0 encoding=UTF-8?&gt;&lt;俄语 լեզու='ռուսերեն'&gt;данные&lt;/俄语&gt;&lt;/xml&gt;", Validator.sanitize("<iframe src='https://tesd--.ji'><?xml version=1.0 encoding=UTF-8?><俄语 լեզու='ռուսերեն'>данные</俄语></xml></iframe>"));
    	assertEquals("<b>hello</b>",  Validator.sanitizeHtml("<div><b>hello</b></div><iframe src='https://tesd--.ji'></iframe>", Whitelist.simpleText()));
    	
    }
    
    @Test
    public void test_sanitizeHtml(){
    	assertEquals("", Validator.sanitizeHtml("<script>alert(document.cookie);</script>", Whitelist.basic()));
    	assertEquals("", Validator.sanitizeHtml("%3cscript src=http://www.example.com/malicious-code.js%3e%3c/script%3e", Whitelist.basic()));
    	assertEquals("http://server/cgi-bin/testcgi.exe?", Validator.sanitizeHtml("http://server/cgi-bin/testcgi.exe?<SCRIPT>alert(“Cookie”+document.cookie)</SCRIPT>", Whitelist.none()));
    	
    }
     
    @Test
    public void test_sanitizeXml(){
    	String sample = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<!DOCTYPE foo [  " +
                "<!ELEMENT foo ANY >" +
                "<!ENTITY xxe SYSTEM \"file:///dev/random\" >]><foo>&xxe;</foo>";
    	
    	sample = Validator.sanitizeXml(sample);
    	assertTrue(!sample.contains("ENTITY")); //If we don't use ENTITY in our XML we will be safe
    	
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