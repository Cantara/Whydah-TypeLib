package net.whydah.sso.basehelpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Escape tool made for easy hiding of characters that we want to use for control. The hiding is performed by replacing
 * such a character with a sequence of two characters. First an escape-control-character, second a configured
 * replace-character. The benefit of this tool is that it is easy to choose control and escape characters that fit the
 * needs of easily readable strings while maintaining the integrity across aggregations.
 */
public class EscapeHelper {

    private static final Logger log = LoggerFactory.getLogger(EscapeHelper.class);

    private final char escapeControl; // character used to start an escape sequence
    private final char[] escapeChars = new char[128]; // character encoding replacements at ASCII range, 0 (default) means no replacement.
    private final char[] reverseChars = new char[128];

    public EscapeHelper(char escapeControl, char... escapePairs) {
        this.escapeControl = escapeControl;
        escapeChars[escapeControl] = escapeControl;
        reverseChars[escapeControl] = escapeControl;
        for (int i = 0; i < escapePairs.length; i += 2) {
            if (escapePairs[i] >= escapeChars.length || escapePairs[i + 1] >= escapeChars.length) {
                throw new IllegalArgumentException(String.format("Illegal escape-pair, must be within ascii-range: ('%c', '%c')", escapePairs[i], escapePairs[i + 1]));
            }
            escapeChars[escapePairs[i]] = escapePairs[i + 1];
            reverseChars[escapePairs[i + 1]] = escapePairs[i];
        }
    }

    public String encode(String plain) {
        if (plain == null) {
            return null;
        }
        StringBuilder encoded = new StringBuilder(plain.length() + 10);
        for (int i = 0; i < plain.length(); i++) {
            char c = plain.charAt(i);
            if (c < escapeChars.length && escapeChars[c] != 0) {
                // character need to be escaped
                encoded.append(escapeControl).append(escapeChars[c]);
            } else {
                // character should not be escaped
                encoded.append(c);
            }
        }
        return encoded.toString();
    }

    public String decode(String encoded) {
        if (encoded == null) {
            return null;
        }
        StringBuilder plain = new StringBuilder(encoded.length());
        for (int i = 0; i < encoded.length(); i++) {
            char c0 = encoded.charAt(i);
            if (i + 1 < encoded.length()) {
                char c1 = encoded.charAt(i + 1);
                if (c0 == escapeControl) {
                    if (c1 < reverseChars.length) {
                        if (reverseChars[c1] != 0) {
                            plain.append(reverseChars[c1]);
                            i++; // consumed c1, so advance one extra
                        } else {
                            log.warn("Encoding error. Character after escape-control character '{}' is not within ascii range. input: '{}', index: {}", escapeControl, encoded, i + 1);
                            plain.append(c0);
                        }
                    } else {
                        // encoding error, ignore and echo control-character
                        log.warn("Encoding error. Character after escape-control character '{}' is not within ascii range. input: '{}', index: {}", escapeControl, encoded, i + 1);
                        plain.append(c0);
                    }
                } else {
                    plain.append(c0);
                }
            } else {
                // c0 is the last-character of encoded string
                if (c0 == escapeControl) {
                    // encoding error, ignore and echo control-character
                    log.warn("Encoding error. Last character of string is escape-control character '{}'. input: '{}'", escapeControl, encoded);
                }
                plain.append(c0);
            }
        }
        return plain.toString();
    }
}
