package net.whydah.sso.basehelpers;

import net.whydah.sso.user.helpers.Patterns;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
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

	//SOME PRECONFIGURED VALUES
	public static final String DEFAULT_SIMPLE_DATE_FORMAT = "EEE MMM d HH:mm:ss z yyyy";
	public static final int DEFAULT_MAX_LENGTH_250 = 250;
	public static final int DEFAULT_MAX_LENGTH_1024 = 1024;
	public static final int DEFAULT_MAX_LENGTH_10240 = 10240;
	public static final int DEFAULT_MAX_LENGTH_102400 = 102400;

	//More reference
	//https://www.owasp.org/index.php/Input_Validation_Cheat_Sheet#Email_Validation_Basics
	//https://www.owasp.org/index.php/Injection_Prevention_Cheat_Sheet_in_Java#HTML.2FJavaScript.2FCSS


	public static final String[] DEFAULT_INVALID_CHARACTERS_FOR_XPATH_INJECTION = new String[]{"(", ")", "=", "'", "[", "]", ":", ",", "*", "/", " "};

	public static final String[] DEFAULT_INVALID_CHARACTERS_FOR_NOSQL_API_ATTACK = new String[]{"'", "\"", "\\", ";", "{", "}", "$"};

	public static final String DEFAULT_EMAIL_PATTERN = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,8}))?$";
	public static final String DEFAULT_EMAIL_WITH_PLUS_PATTERN = "^([_a-zæøåA-Z0-9+—-]+(\\.[_a-zæøåA-Z0-9-+]+)*@[a-zöøåA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,8}))?$";
	public static final String FULL_EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~—-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	public static final String FULL_EMAIL_PATTERN2 = "(?:[a-zæøåA-Z0-9!#$%&'*+/=?^_`{|}~—-]+(?:\\.[a-zæøåA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zA-Z0-9](?:[a-zöåøA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

	public static final String DEFAULT_IMAGE_BASE64_PATTTERN = "^data:image/[a-zA-Z]*;base64,[^\"]*$";

	//"(http|https|Http|Https)://localhost(\\.\\w+)*(:[0-9]+)?/?(/[.\\w]*)+";
	//http://localhost:9998/tokenservice/user/20666d47d7b297630f662ef28ca2973e/validate_usertokenid/fc5be94c-513f-4294-8720-339c2a804c18
	//i copied in Android source code :)
	public static final String DEFAULT_URL_PATTERN = Patterns.WEB_URL.pattern(); //from Android src
	/*
	new StringBuilder()
    .append("((?:(http|https|Http|Https|rtsp|Rtsp):")
    .append("\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)")
    .append("\\,\\;\\?\\&amp;\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_")
    .append("\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&amp;\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?")
    .append("((?:(?:[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}\\.)+")   // named host
    .append("(?:")   // plus top level domain
    .append("(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])")
    .append("|(?:biz|b[abdefghijmnorstvwyz])")
    .append("|(?:cat|com|coop|c[acdfghiklmnoruvxyz])")
    .append("|(?:dev|d[ejkmoz])")
    .append("|(?:edu|e[cegrstu])")
    .append("|f[ijkmor]")
    .append("|(?:gov|g[abdefghilmnpqrstuwy])")
    .append("|h[kmnrtu]")
    .append("|(?:info|int|i[delmnoqrst])")
    .append("|(?:jobs|j[emop])")
    .append("|k[eghimnrwyz]")
    .append("|l[abcikrstuvy]")
    .append("|(?:mil|me|mobi|museum|m[acdghklmnopqrstuvwxyz])")
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
    .append("(?:\\b|$)").toString()
    
    */

	public static final String DEFAULT_NAME_PATTERN = "^[\\p{L} .'-]+$"; //\\p{L} is a Unicode Character Property for any language

	public static final String DEFAULT_PHONE_NUMBER_PATTERN = "^[+]*(?:[0-9] ?){6,14}[0-9]$";

	public static final String DEFAULT_CELLPHONE_NUMBER_PATTERN = "^[+]*(?:[0-9] ?){6,14}[0-9]$";

	public static final String DEFAULT_NORWAY_CELLPHONE_NUMBER_PATTERN = "(?:(?:^*(?:\\+?47[49]))|(?:^[459]))\\d{7}$";

	public static final String DEFAULT_TEXT_WITH_LETTERS_NUMBERS_SPACE_HYPHEN = "^[a-zæøåA-ZÆØÅ0-9\\p{L}\\s\\-]+$";

	public static final String DEFAULT_TEXT_WITH_LETTERS_NUMBERS_SPACE_UNDERSCORE = "^[a-zæøåA-ZÆØÅ0-9\\p{L}\\s_]+$";
	public static final String DEFAULT_SENSIBLE_USERNAME = "^[a-zæøåA-ZÆØÅ0-9\\p{L}\\s\\-_.]+$";
	public static final String DEFAULT_SENSIBLE_ROLENAMES = "^[a-zæøåA-ZÆØÅ, 0-9\\-_\\,.]+$";
    public static final String DEFAULT_SENSIBLE_ADDRESSLINE = "^[a-zæøåéáôüA-ZÆØÅ0-9-#.,_.' /]+$";
    public static final String DEFAULT_SENSIBLE_APPLICATIONNAME = "^[a-zæøåA-ZÆØÅ0-9\\p{L}\\s\\-_.+]+$";

    public static final String DEFAULT_TEXT_WITH_LETTERS_NUMBERS_HYPHEN_UNDERSCORE = "^[a-zæøåéáôüA-ZÆØÅ0-9\\p{L}\\-_]+$";

    public static final String DEFAULT_TEXT_WITH_LETTERS_NUMBERS_SPACE_HYPHEN_UNDERSCORE = "^[a-zæøåéáôüA-ZÆØÅ0-9\\p{L}\\s\\-_]+$";
	public static final String DEFAULT_BASE_ID = "^[a-zA-Z0-9\\p{L}\\s\\-_.@]+$";

	public static final String DEFAULT_PASSWORD_STRING = "^[a-zæøåA-ZÆØÅ0-9\\p{L}\\s\\-_*@#!~\\$%\\^&\\?\\.\\,+=]+$";

	public static final String DEFAULT_TEXT_WITH_LETTERS_NUMBERS_SPACE_HYPHEN_PLUS_UNDERSCORE = "^[a-zæøööåéáôüA-ZÆØÅ0-9.\\p{L}\\s\\-_+]+$";
	public static final String DEFAULT_SENSIBLE_REFERENCE = "^[a-zA-ZÆØÅ0-9\\p{L}\\s\\-_+/.@]+$";

    public static final String DEFAULT_TEXT_NORWEGIAN = "^[a-zæøåéáôüA-ZÆØÅ0-9\\p{L}\\s\\-_+',./()]+$";

	public static final String DEFAULT_TEXT_WITH_LETTERS_NUMBERS = "^[a-zA-Z0-9\\p{L}]+$";
	public static final String DEFAULT_BASE64 = "^[A-Z0-9]+$";

	public static final String DEFAULT_TEXT_WITH_ONLY_DIGITS = "^[0-9]+$";

	public static final String DEFAULT_TEXT_WITH_ONLY_LETTERS = "^[a-zA-Z\\p{L}]+$";
	public static final String DEFAULT_SENSIBLE_PERSON_NAME = "^[a-zæøåéíúáýóööôðüñA-ZÆØÅÑ]+(([',. -][a-zæøöåéíúöáýóôðüñA-ZÆØÅÑ ])?[a-zæøöåéíáýóúôðüñA-ZÆØÅÑ0-9.]*)*$";
	public static final String DEFAULT_SENSIBLE_ESCAPED_JSON = "^[a-zæøåéáíóúôöüñA-ZÆØÅÑ0-9-#?_.,'/+:@{}\\\\ \\\"=\\[\\]]+$";

	public static final boolean DEFAULT_CHECK_INVALID_HTML_USE = false;

    public static final boolean DEFAULT_CHECK_INVALID_JSON_USE = false;

	public static boolean containsInvalidCharacters(String value, String[] invalidChars){
		try{
			List<String> specialCharsList = Arrays.asList(invalidChars);
			//			specialCharsList.forEach(specChar -> Assert.assertFalse(value.contains(specChar)));
			for (Character valueChar : value.toCharArray()) {
				if (specialCharsList.contains(String.valueOf(valueChar)))
					throw new AssertionError("Illegal value detected");
			}
			return false;
		}catch(AssertionError e){

		}
		return true;
	}

	public static boolean isValidTextInput(String text, int minLength, int maxLength, String pattern, String[] invalidChars, boolean xpathInjectionCheck, Safelist htmlPolicy) {
		if (text == null) {
			log.error("Input value is null");
			return false;
		} else {
			//this means to change & to ampersand before validation, the reason is that Jsoup will see & as an invalid character and automatically change it to &amp; after clean() called
			//so, we should replace & with &amp; first
			text = text.replaceAll("&(?!.{2,4};)", "&amp;");
			text = text.trim();

			if (text.length() < minLength || text.length() > maxLength) {
				log.error("illegal length: {} for the input {}. Min length: {} max length: {} expected", text.length(), text, minLength, maxLength);
				return false;
			}

			boolean matches = true;
			if(pattern!=null && !pattern.equals("")) {
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(text);
				matches = m.matches();
			}

			if(matches){

				if(invalidChars!=null && invalidChars.length>0 && containsInvalidCharacters(text, invalidChars)){
					log.error("the input's value {} must not contains these characters {}", text, Arrays.toString(invalidChars));
					return false;
				} else {
					if(xpathInjectionCheck) //if required
					{
						if(containsInvalidCharacters(text, DEFAULT_INVALID_CHARACTERS_FOR_XPATH_INJECTION)){
							log.error("the input's value {} must not contains these characters {}", text, Arrays.toString(DEFAULT_INVALID_CHARACTERS_FOR_XPATH_INJECTION));
							return false;
						}
					}

					if(isValidHtml(text, htmlPolicy)){ //allow HTML tags
						return true;
					} else {
						log.error("the input's value {} does not meet specified HTML policy and may not contain vulnerable and malicious script", text);
						return false;
					}

				}

			} else {
				log.error("Pattern {} not matched for the input {}", pattern, text);
				return false;
			}
		} 
	}

	public static boolean isValidJsonInput(String text, int minLength, int maxLength, String pattern, String[] invalidChars, boolean xpathInjectionCheck, Safelist htmlPolicy) {
		if (text == null) {
			log.error("Input value is null");
			return false;
		} else {
			//this means to change & to ampersand before validation, the reason is that Jsoup will see & as an invalid character and automatically change it to &amp; after clean() called
			//so, we should replace & with &amp; first
			text = text.replaceAll("&(?!.{2,4};)", "&amp;");
			text = text.trim();

			if (text.length() < minLength || text.length() > maxLength) {
                log.error("illegal length: {} for the input {}. Min length: {} max length: {} expected", text.length(), text, minLength, maxLength);
                return false;
            }

            boolean matches = true;
            if (pattern != null && !pattern.equals("")) {
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(text);
                matches = m.matches();
            }

            if (matches) {

                if (invalidChars != null && invalidChars.length > 0 && containsInvalidCharacters(text, invalidChars)) {
                    log.error("the input's value {} must not contains these characters {}", text, Arrays.toString(invalidChars));
                    return false;
                } else {
                    if (xpathInjectionCheck) //if required
                    {
                        if (containsInvalidCharacters(text, DEFAULT_INVALID_CHARACTERS_FOR_XPATH_INJECTION)) {
                            log.error("the input's value {} must not contains these characters {}", text, Arrays.toString(DEFAULT_INVALID_CHARACTERS_FOR_XPATH_INJECTION));
                            return false;
                        }
                    }
                    return true;

				}

			} else {
				log.error("Pattern {} not matched for the input {}", pattern, text);
				return false;
			}
		}

	}

	public static boolean isValidXMLInput(String text, int minLength, int maxLength, String pattern, String[] invalidChars, boolean xpathInjectionCheck) {
		if (text == null) {
			log.error("Input value is null");
			return false;
		} else {

			if (text.length() < minLength || text.length() > maxLength) {
				log.error("illegal length: {} for the input {}. Min length: {} max length: {} expected", text.length(), text, minLength, maxLength);
				return false;
			}

			boolean matches = true;
			if (pattern != null && !pattern.equals("")) {
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(text);
				matches = m.matches();
			}

			if (matches) {

				if (invalidChars != null && invalidChars.length > 0 && containsInvalidCharacters(text, invalidChars)) {
					log.error("the input's value {} must not contains these characters {}", text, Arrays.toString(invalidChars));
					return false;
				} else {
					if (xpathInjectionCheck) //if required
					{
						if (containsInvalidCharacters(text, DEFAULT_INVALID_CHARACTERS_FOR_XPATH_INJECTION)) {
							log.error("the input's value {} must not contains these characters {}", text, Arrays.toString(DEFAULT_INVALID_CHARACTERS_FOR_XPATH_INJECTION));
							return false;
						}
					}
					return isValidXml(text);

				}

			} else {
				log.error("Pattern {} not matched for the input {}", pattern, text);
				return false;
			}
		}

	}

	public static boolean isValidTextInput(String text, int minLength, int maxLength, String pattern, String[] invalidChars) {
		//no HTML allowed, no need to check XPath injection
		return isValidTextInput(text, minLength, maxLength, pattern, invalidChars, false, Safelist.none());
	}

	public static boolean isValidTextInput(String text, int minLength, int maxLength, String pattern) {
		//no specified invalid characters, no HTML allowed, no need to check XPath injection
		return isValidTextInput(text, minLength, maxLength, pattern, null);
	}


    public static boolean isValidTextInput(String text, int minLength, int maxLength) {
        //no predefined pattern, no specified invalid characters, no HTML allowed, no need to check XPath injection
		return isValidTextInput(text, minLength, maxLength, null); 
	}

	public static boolean isValidJsonInput(String text, int minLength, int maxLength, String pattern) {
		//no specified invalid characters, no HTML allowed, no need to check XPath injection
		return isValidJsonInput(text, minLength, maxLength, pattern, null, false, Safelist.none());
	}

	public static boolean isValidJsonInput(String text, int minLength, int maxLength) {
		//no predefined pattern, no specified invalid characters, no HTML allowed, no need to check XPath injection
		return isValidJsonInput(text, minLength, maxLength, Validator.DEFAULT_SENSIBLE_ESCAPED_JSON);
	}

	public static boolean isValidXMLInput(String text, int minLength, int maxLength, String pattern) {
		//no specified invalid characters, no HTML allowed, no need to check XPath injection
		return isValidXMLInput(text, minLength, maxLength, pattern, null, false);
	}

	public static boolean isValidXMLInput(String text, int minLength, int maxLength) {
		//no predefined pattern, no specified invalid characters, no HTML allowed, no need to check XPath injection
		return isValidXMLInput(text, minLength, maxLength, null);
	}

	public static boolean isValidHtml(String inputString, Safelist htmlPolicy) {
		if (inputString == null || inputString.length() != sanitizeHtml(inputString, htmlPolicy).length()) {
			log.error("Invalid HTML input {}", inputString);
			return false;
		}
		return true;
	}

	public static final String XML_PATTERN_STR = "<(\\S+?)(.*?)>(.*?)</\\1>";


    public static boolean isValidXml(String inputString) {

        Pattern pattern;
        Matcher matcher;
        if (inputString == null || inputString.length() != sanitizeXml(inputString).length()) {
			log.error("Invalid XML input {}", inputString);
			return false;
		}
        if (inputString.trim().startsWith("<")) {

            pattern = Pattern.compile(XML_PATTERN_STR,
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);

            // RETURN TRUE IF IT HAS PASSED BOTH TESTS
            matcher = pattern.matcher(inputString);
            // TODO revisit this to make it work // return matcher.matches();
            return true;
        }
        return false;
    }

    public static boolean isValidURL(String inputString) {

       if (inputString == null || inputString.length() != sanitizeXml(inputString).length()) {
            log.error("Invalid input {}", inputString);
            return false;
        }
        return true;
       
    }
    
    

    public static String sanitize(String value) {
		return sanitizeHtml(value, Safelist.none());
	}

	public static String sanitizeHtml(String inputString, Safelist htmlPolicy) {

		String safe = Jsoup.clean(inputString, htmlPolicy);
		return safe;
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
