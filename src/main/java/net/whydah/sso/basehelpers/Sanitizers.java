package net.whydah.sso.basehelpers;

import net.whydah.sso.application.mappers.ApplicationCredentialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sanitizers {

    private static final Logger log = LoggerFactory.getLogger(ApplicationCredentialMapper.class);

    /**
     * Wrapper functions, should be replaced with good library where they exists..
     *
     * @param inputString
     * @return
     */

    public static boolean isSane(String inputString) {
        if (inputString == null || inputString.length() != sanitize(inputString).length()) {
            log.trace("verifyApplicationCredentialAgainstLocalAndUAS_UIB - suspicious XML received, rejected.");
            return false;
        }
        return true;
    }

    public static String sanitize(String string) {
        if (string == null || string.length() < 3) {
            return string;
        }
        return string
                .replaceAll("(?i)%3c%2fnoscript%3e", "")   // case 1
                .replaceAll("(?i)%2fscript%3e", "")   // case 1
                .replaceAll("(?i)<script.*?>.*?</script.*?>", "")   // case 1
                .replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
                .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "") // case 3
                .replaceAll("alert", "")    // alerts
                .replaceAll("prompt", "")    // prompt
                .replaceAll("ENTITY", "")//ENTITY
//                .replaceAll("entity", "")//ENTITY
                .replaceAll("DOCTYPE", "")//DOCTYPE
                .replaceAll("doctype", "")//DOCTYPE
                .replaceAll("onLoad", "")//DOCTYPE
                .replaceAll("onload", "")//DOCTYPE
                .replaceAll("%00", "")    // null byte
                .replaceAll("\0", "")    // null byte
                .replaceAll("confirm", "");  // confirms
    }

    public static String sanitizeHTML(String string) {
        if (string == null || string.length() < 3) {
            return string;
        }
        return string
                .replaceAll("(?i)%3c%2fnoscript%3e", "")   // case 1
                .replaceAll("(?i)%2fscript%3e", "")   // case 1
                .replaceAll("(?i)<script.*?>.*?</script.*?>", "")   // case 1
                .replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
                .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "") // case 3
                .replaceAll("alert", "")    // alerts
                .replaceAll("prompt", "")    // prompt
                .replaceAll("%00", "")    // null byte
                .replaceAll("\0", "")    // null byte
                .replaceAll("confirm", "");  // confirms
    }

    public static String sanitizeXML(String string) {
        if (string == null || string.length() < 3) {
            return string;
        }
        return string
                .replaceAll("(?i)%3c%2fnoscript%3e", "")   // case 1
                .replaceAll("(?i)%2fscript%3e", "")   // case 1
                .replaceAll("(?i)<script.*?>.*?</script.*?>", "")   // case 1
                .replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
                .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "") // case 3
                .replaceAll("alert", "")    // alerts
                .replaceAll("prompt", "")    // prompt
                .replaceAll("ENTITY", "")//ENTITY
//                .replaceAll("entity", "")//ENTITY
                .replaceAll("DOCTYPE", "")//DOCTYPE
                .replaceAll("doctype", "")//DOCTYPE
                .replaceAll("%00", "")    // null byte
                .replaceAll("\0", "")    // null byte
                .replaceAll("confirm", "");  // confirms
    }
}
