//package net.whydah.sso.ddd;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.Serializable;
//
//
//public class WhydahName implements Serializable {
//
//    private final String name;
//    private final static Logger log = LoggerFactory.getLogger(WhydahName.class);
//
//    public WhydahName(String whydahName) {
//
//        // Must not be null
//        if (whydahName == null) {
//            log.error("Attempt to create an illegal WhydahName - value is null");
//            this.name = null;
//            // max length 250
//        } else if (whydahName.length() > 250) {
//            log.error("Attempt to create an illegal WhydahName - illegal length:{}", whydahName.length());
//            this.name = null;
//            // Must be of only whitelisted characters
//        } else if (!isSane(whydahName)) {
//            log.error("Attempt to create an illegal WhydahName - illegal characters - whydahId:{}", whydahName);
//            this.name = null;
//        } else {
//            this.name = whydahName;
//        }
//    }
//
//
//    public static boolean isSane(String inputString) {
//        if (inputString == null || inputString.length() != sanitize(inputString).length()) {
//            log.trace("verifyApplicationCredentialAgainstLocalAndUAS_UIB - suspicious XML received, rejected.");
//            return false;
//        }
//        return true;
//    }
//
//    public static String sanitize(String string) {
//        if (string == null || string.length() < 3) {
//            return string;
//        }
//        return string
//                .replaceAll("(?i)%3c%2fnoscript%3e", "")   // case 1
//                .replaceAll("(?i)%2fscript%3e", "")   // case 1
//                .replaceAll("(?i)<script.*?>.*?</script.*?>", "")   // case 1
//                .replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
//                .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "") // case 3
//                .replaceAll("alert", "")    // alerts
//                .replaceAll("prompt", "")    // prompt
//                .replaceAll("DOCTYPE", "")//DOCTYPE
//                .replaceAll("doctype", "")//DOCTYPE
//                .replaceAll("HTML", "")//DOCTYPE
//                .replaceAll("html", "")//DOCTYPE
//                .replaceAll("%00", "")    // null byte
//                .replaceAll("\0", "")    // null byte
//                .replaceAll("confirm", "");  // confirms
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public boolean isValid() {
//        return name != null;
//    }
//
//
//    public static boolean isValid(String whydahNameToValidate) {
//        try {
//            return new WhydahName(whydahNameToValidate).isValid();
//        } catch (Exception e) {
//        }
//        return false;
//    }
//
//
//    @Override
//    public String toString() {
//        return name;
//    }
//}