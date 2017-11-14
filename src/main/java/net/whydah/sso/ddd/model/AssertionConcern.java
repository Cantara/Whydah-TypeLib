package net.whydah.sso.ddd.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.whydah.sso.basehelpers.Validator;

import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssertionConcern {

	protected final static Logger log = LoggerFactory.getLogger(AssertionConcern.class);
	  
    protected AssertionConcern() {
        super();
    }

    protected void assertArgumentEquals(Object anObject1, Object anObject2, String aMessage) {
        if (!anObject1.equals(anObject2)) {
        	throwException(aMessage);
        }
    }

    protected void assertArgumentFalse(boolean aBoolean, String aMessage) {
        if (aBoolean) {
            throwException(aMessage);
        }
    }

    protected void assertArgumentLength(String aString, int aMaximum, String aMessage) {
        int length = aString.trim().length();
        if (length > aMaximum) {
        	throwException(aMessage);
        }
    }
    
    protected void assertArgumentWithSafeInput(String aString, int aMinimum, int aMaximum, String aMessage){
    	boolean isValid = Validator.isValidTextInput(aString, aMinimum, aMaximum);
    	if(!isValid){
    		throwException(aMessage);
    	}
    }
    
    protected void assertArgumentWithSafeInput(String aString, int aMinimum, int aMaximum, String patterns, String aMessage){
    	boolean isValid = Validator.isValidTextInput(aString, aMinimum, aMaximum, patterns);
    	if(!isValid){
    		throwException(aMessage);
    	}
    }
    
    protected void assertArgumentWithSafeInput(String aString, int aMinimum, int aMaximum, String patterns, String[] invalidCharacters, String aMessage){
    	boolean isValid = Validator.isValidTextInput(aString, aMinimum, aMaximum, patterns, invalidCharacters);
    	if(!isValid){
    		throwException(aMessage);
    	}
    }
    
    protected void assertArgumentWithSafeInput(String aString, int aMinimum, int aMaximum, String patterns, String[] invalidCharacters, boolean XpathInjectionCheck, Whitelist htmlWhiteListPolicy, String aMessage){
    	boolean isValid = Validator.isValidTextInput(aString, aMinimum, aMaximum, patterns, invalidCharacters, XpathInjectionCheck, htmlWhiteListPolicy);
    	if(!isValid){
    		throwException(aMessage);
    	}
    }

    protected void assertArgumentLength(String aString, int aMinimum, int aMaximum, String aMessage) {
        int length = aString.trim().length();
        if (length < aMinimum || length > aMaximum) {
        	throwException(aMessage);
        }
    }

    protected void assertArgumentNotEmpty(String aString, String aMessage) {
        if (aString == null || aString.trim().isEmpty()) {
        	throwException(aMessage);
        }
    }

    protected void assertArgumentNotEquals(Object anObject1, Object anObject2, String aMessage) {
        if (anObject1.equals(anObject2)) {
        	throwException(aMessage);
        }
    }

    protected void assertArgumentNotNull(Object anObject, String aMessage) {
        if (anObject == null) {
        	throwException(aMessage);
        }
    }

    protected void assertArgumentRange(double aValue, double aMinimum, double aMaximum, String aMessage) {
        if (aValue < aMinimum || aValue > aMaximum) {
        	throwException(aMessage);
        }
    }

    protected void assertArgumentRange(float aValue, float aMinimum, float aMaximum, String aMessage) {
        if (aValue < aMinimum || aValue > aMaximum) {
        	throwException(aMessage);
        }
    }

    protected void assertArgumentRange(int aValue, int aMinimum, int aMaximum, String aMessage) {
        if (aValue < aMinimum || aValue > aMaximum) {
        	throwException(aMessage);
        }
    }

    protected void assertArgumentRange(long aValue, long aMinimum, long aMaximum, String aMessage) {
        if (aValue < aMinimum || aValue > aMaximum) {
        	throwException(aMessage);
        }
    }

    protected void throwException(String aMessage) {
		log.error(aMessage);
		throw new IllegalArgumentException(aMessage);
	}

    protected void assertArgumentTrue(boolean aBoolean, String aMessage) {
        if (!aBoolean) {
        	throwException(aMessage);
        }
    }

    protected void assertStateFalse(boolean aBoolean, String aMessage) {
        if (aBoolean) {
        	throwException(aMessage);
        }
    }

    protected void assertStateTrue(boolean aBoolean, String aMessage) {
        if (!aBoolean) {
        	throwException(aMessage);
        }
    }

    protected void assertValidXmlText(String xml, String aMessage){
    	boolean isValid = Validator.isValidXml(xml);
    	if(!isValid){
    		throwException(aMessage);
    	}
    }
    
    protected void assertValidHtmlText(String html, Whitelist policy, String aMessage){
    	boolean isValid = Validator.isValidHtml(html, policy);
    	if(!isValid){
    		throwException(aMessage);
    	}
    }
    
    protected void assertArgumentWithAPattern(String aString, String pattern, String aMessage){
    	
        Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(aString);
		boolean matches = m.matches();
    	assertArgumentTrue(matches, aMessage);
    }

    protected void assertArgumentShouldNotContainSpecifiedChars(String aString, String[] chars, String aMessage){
    	boolean hasAnInvalidChar = Validator.containsInvalidCharacters(aString, chars);
    	if(hasAnInvalidChar){
    		throwException(aMessage);
    	}
    	
    }

    
}