package net.whydah.sso.basehelpers;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validator {
	
	private static final Logger log = LoggerFactory.getLogger(Validator.class);
	//XPATH INJECTION Reference
	//reference: https://www.owasp.org/index.php/XPATH_Injection_Java
	//	<?xml version="1.0" encoding="utf-8"?>
	//	<employees>
	//	  <employee id="AS789" firstname="John" lastname="Doo" annualsalary="70000"/>
	//	  <employee id="AS719" firstname="Isabela" lastname="Dobora" annualsalary="90000"/>
	//	  <employee id="AS219" firstname="Eric" lastname="Lambert" annualsalary="65000"/>
	//	</employees>
	//employees/employee[@id='EMPLOYEE_ID']
	//EMPLOYEE_ID='%20or%20'1'='1 => all will selected
	//EMPOYEER_ID='%20or%20fn:contains(fn:lower-case(@lastname),'dobora')%20or%20' => dobora is selected (without knowing the exact Id)
	//SOLUTION: 
	//	static boolean checkValueForXpathInjection(String value) throws Exception {
	//	boolean isValid = true;
	//	if ((value != null) && !"".equals(value)) {
	//		String xpathCharList = "()='[]:,*/ ";
	//		// Always to avoid encoding evading....
	//		String decodedValue = URLDecoder.decode(value, Charset.defaultCharset().name());
	//		for (char c : decodedValue.toCharArray()) {
	//			if (xpathCharList.indexOf(c) != -1) {
	//				isValid = false;
	//				break;
	//			}
	//		}
	//	}
	//	return isValid;
	//}
	
	//More reference
	//https://www.owasp.org/index.php/Input_Validation_Cheat_Sheet#Email_Validation_Basics
	//https://www.owasp.org/index.php/Injection_Prevention_Cheat_Sheet_in_Java#HTML.2FJavaScript.2FCSS
	
	
	public static String[] DEFAULT_INVALID_CHARACTERS_FOR_XPATH_INJECTION = new String[]{"(", ")", "=", "'", "[", "]", ":", ",", "*", "/", " "}; 
	
	public static String[] DEFAULT_INVALID_CHARACTERS_FOR_NOSQL_API_ATTACK = new String[]{"'", "\"", "\\", ";", "{", "}", "$"};
	
	public static String DEFAULT_EMAIL_PATTERN = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
	
	//i copied in Android source code :)
	public static String DEFAULT_URL_PATTERN = new StringBuilder()
	.append("((?:(http|https|Http|Https|rtsp|Rtsp):")
	.append("\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)")
		.append("\\,\\;\\?\\&amp;\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_")
		.append("\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&amp;\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?")
		.append("((?:(?:[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}\\.)+")   // named host
		.append("(?:")   // plus top level domain
		.append("(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])")
		.append("|(?:biz|b[abdefghijmnorstvwyz])")
		.append("|(?:cat|com|coop|c[acdfghiklmnoruvxyz])")
		.append("|d[ejkmoz]")
		.append("|(?:edu|e[cegrstu])")
		.append("|f[ijkmor]")
		.append("|(?:gov|g[abdefghilmnpqrstuwy])")
		.append("|h[kmnrtu]")
		.append("|(?:info|int|i[delmnoqrst])")
		.append("|(?:jobs|j[emop])")
		.append("|k[eghimnrwyz]")
		.append("|l[abcikrstuvy]")
		.append("|(?:mil|mobi|museum|m[acdghklmnopqrstuvwxyz])")
		.append("|(?:name|net|n[acefgilopruz])")
		.append("|(?:org|om)")
		.append("|(?:pro|p[aefghklmnrstwy])")
		.append("|qa")
		.append("|r[eouw]")
		.append("|s[abcdeghijklmnortuvyz]")
		.append("|(?:tel|travel|t[cdfghjklmnoprtvwz])")
		.append("|u[agkmsyz]")
		.append("|v[aceginu]")
		.append("|w[fs]")
		.append("|y[etu]")
		.append("|z[amw]))")
		.append("|(?:(?:25[0-5]|2[0-4]") // or ip address
		.append("[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]")
		.append("|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1]")
		.append("[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}")
		.append("|[1-9][0-9]|[0-9])))")
		.append("(?:\\:\\d{1,5})?)") // plus option port number
		.append("(\\/(?:(?:[a-zA-Z0-9\\;\\/\\?\\:\\@\\&amp;\\=\\#\\~")  // plus option query params
		.append("\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?")
		.append("(?:\\b|$)").toString();
	
	public static String DEFAULT_NAME_PATTERN = "^[\\p{L} .'-]+$"; //\\p{L} is a Unicode Character Property for any language
	
	public static String DEFAULT_PHONE_NUMBER_PATTERN = "^\\+?[0-9. ()-]{10,25}$";
	
	public static String DEFAULT_TEXT_WITH_LETTERS_NUMBERS_SPACE_HYPHEN = "^[a-zA-Z0-9\\s\\-]$";
	
	public static String DEFAULT_TEXT_WITH_LETTERS_NUMBERS_SPACE = "^[a-zA-Z0-9\\s]$";
	
	public static String DEFAULT_TEXT_WITH_LETTERS_NUMBERS_HYPHEN = "^[a-zA-Z0-9\\-]$";
	
	public static String DEFAULT_TEXT_WITH_LETTERS_NUMBERS = "^[a-zA-Z0-9]$";
	
	public static String DEFAULT_TEXT_WITH_ONLY_DIGITS = "^[0-9]$";
	
	public static String DEFAULT_TEXT_WITH_ONLY_LETTERS = "^[a-zA-Z]$";
	
	
	
	public static boolean containsInvalidCharacters(String value, String[] invalidChars){
		try{
			List<String> specialCharsList = Arrays.asList(invalidChars);
//			specialCharsList.forEach(specChar -> Assert.assertFalse(value.contains(specChar)));
            for (char valueChar : value.toCharArray()) {
                if (specialCharsList.contains(valueChar)) throw new AssertionError("Illegal value detected");
            }
            return false;
		}catch(AssertionError e){

		}
		return true;
	}
	
	public static boolean isValidTextInput(String text, int minLength, int maxLength, String pattern, String[] invalidChars, boolean xpathInjectionCheck, Whitelist htmlPolicy){
		if (text == null || text.equals("")) {
            log.error("Input value is null");
            return false;
        } else  {
        	//this means to change & to ampersand before validation, the reason is that Jsoup will see & as an invalid character and automatically change it to &amp; after clean() called
        	//so, we should replace & with &amp; first
        	text = text.replaceAll("&(?!.{2,4};)", "&amp;");
        	
        	//avoid encoding evading
        	String decodedValue = text;
        	try {
				decodedValue = URLDecoder.decode(text, Charset.defaultCharset().name());
			} catch (Exception e) {
				e.printStackTrace();
			}
        	if (decodedValue.length() < minLength || decodedValue.length() > maxLength) {
        		log.error("illegal length: {} for the input {}. Min length: {} max length: {} expected", decodedValue.length(), decodedValue, minLength, maxLength);
        		return false;
        	}
        	
        	boolean matches = true;
        	if(pattern!=null && !pattern.equals("")) {
        		Pattern p = Pattern.compile(pattern);
        		Matcher m = p.matcher(decodedValue);
        		matches = m.matches();
        		
        		//if the input value is a URL, the decoded text sometimes does not match with the specified pattern, we might try the original text
        		//for example the decoded version http://test.no:8080/login?id=test me please => is an invalid pattern
        		//but the original text http://test.no:8080/login?id=test+me+please seems to be OK
        		if(!matches){ 
        			m = p.matcher(text); //try original text
            		matches = m.matches();
            		decodedValue = text;
        		}
        	}
        	
        	if(matches){
        		
        		if(invalidChars!=null && invalidChars.length>0 && containsInvalidCharacters(decodedValue, invalidChars)){
        			log.error("the input's value {} must not contains these characters {}", decodedValue, Arrays.toString(invalidChars));
        			return false;
        		} else {
        			if(xpathInjectionCheck) //if required
        			{
        				if(containsInvalidCharacters(decodedValue, DEFAULT_INVALID_CHARACTERS_FOR_XPATH_INJECTION)){
        					log.error("the input's value {} must not contains these characters {}", decodedValue, Arrays.toString(DEFAULT_INVALID_CHARACTERS_FOR_XPATH_INJECTION));
        					return false;
        				}
        			}
        			
        			if(isValidHtml(decodedValue, htmlPolicy, false)){ //allow HTML tags
        				return true;
        			} else {
        				log.error("the input's value {} does not meet specified HTML policy and may not contain vulnerable and malicious script", decodedValue);
        				return false;
        			}
        			
        		}
        		
        	} else {
        		log.error("Pattern {} not matched for the input {}", pattern, decodedValue);
        		return false;
        	}
        } 
	}
	
	public static boolean isValidTextInput(String text, int minLength, int maxLength, String pattern, String[] invalidChars){
		//no HTML allowed, no need to check XPath injection
		return isValidTextInput(text, minLength, maxLength, pattern, invalidChars, false, Whitelist.none()); 
	}
	
	public static boolean isValidTextInput(String text, int minLength, int maxLength, String pattern){
		//no specified invalid characters, no HTML allowed, no need to check XPath injection
		return isValidTextInput(text, minLength, maxLength, pattern, null); 
	}
	
	public static boolean isValidTextInput(String text, int minLength, int maxLength){
		//no predefined pattern, no specified invalid characters, no HTML allowed, no need to check XPath injection
		return isValidTextInput(text, minLength, maxLength, null); 
	}
	
	public static boolean isValidHtml(String inputString, Whitelist htmlPolicy, boolean tryDecoding){
		if (inputString == null || inputString.length() != sanitizeHtml(inputString, htmlPolicy, tryDecoding).length()) {
			log.error("Invalid HTML input {}", inputString);
            return false;
        }
		return true;
	}
	
	public static boolean isValidHtml(String inputString, Whitelist htmlPolicy){
		if (inputString == null || inputString.length() != sanitizeHtml(inputString, htmlPolicy).length()) {
			log.error("Invalid HTML input {}", inputString);
            return false;
        }
		return true;
	}
	
	public static boolean isValidXml(String inputString){
		if (inputString == null || inputString.length() != sanitizeXml(inputString).length()) {
			log.error("Invalid XML input {}", inputString);
            return false;
        }
		return true;
	}
	
	public static String sanitize(String value) {
		return sanitizeHtml(value, Whitelist.none());
	}
	
	public static String sanitizeHtml(String inputString, Whitelist htmlPolicy, boolean tryDecoding){
		String decodedValue = inputString;
		if(tryDecoding) {
			try {
				decodedValue = URLDecoder.decode(inputString, Charset.defaultCharset().name());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String safe = Jsoup.clean(decodedValue, htmlPolicy);
		return safe;
	}
	
	public static String sanitizeHtml(String inputString, Whitelist htmlPolicy){
		//always avoid encoding evading by default
		return sanitizeHtml(inputString, htmlPolicy, true);
	}
	
	public static String sanitizeXml(String in) {
		return sanitizeXml(in, true);
	}
	
	public static String sanitizeXml(String in, boolean tryDecoding) {
	    if (in == null || ("".equals(in))) return "";
	    
	  //always avoid encoding evading
	    String decodedValue = in;
	    if(tryDecoding){
	    	try {
	    		decodedValue = URLDecoder.decode(in, Charset.defaultCharset().name());
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	
	    // ref : http://www.w3.org/TR/REC-xml/#charsets
	    // jdk 7
	    Pattern xmlInvalidChars = Pattern.compile("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\x{10000}-\\x{10FFFF}]");
	    String output =  xmlInvalidChars.matcher(decodedValue).replaceAll("");
	    //moved from totto's Sanitizer
	    return output.replaceAll("(?i)%3c%2fnoscript%3e", "")   // case 1
	      .replaceAll("(?i)%2fscript%3e", "")   // case 1
	      .replaceAll("(?i)<script.*?>.*?</script.*?>", "")   // case 1
	      .replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
	      .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "") // case 3
	      .replaceAll("alert", "")    // alerts
	      .replaceAll("prompt", "")    // prompt
	      .replaceAll("ENTITY", "")//ENTITY
	      .replaceAll("DOCTYPE", "")//DOCTYPE
	      .replaceAll("doctype", "")//DOCTYPE
	      .replaceAll("%00", "")    // null byte
	      .replaceAll("\0", "")    // null byte
	      .replaceAll("confirm", "");  // confirms
	    
	    
	    //we can use this code snippet as well
	    /*
	    StringBuilder out = new StringBuilder();
	    char current;

	    if (in == null || ("".equals(in))) return "";
	    for (int i = 0; i < in.length(); i++) {
	        current = in.charAt(i);
	        if ((current == 0x9) ||
	            (current == 0xA) ||
	            (current == 0xD) ||
	            ((current >= 0x20) && (current <= 0xD7FF)) ||
	            ((current >= 0xE000) && (current <= 0xFFFD)) ||
	            ((current >= 0x10000) && (current <= 0x10FFFF)))
	            out.append(current);
	    }
	    return out.toString();*/
	    
	  }

	


	

	


}