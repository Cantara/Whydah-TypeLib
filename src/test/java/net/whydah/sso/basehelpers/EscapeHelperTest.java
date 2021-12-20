package net.whydah.sso.basehelpers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EscapeHelperTest {

    static EscapeHelper ESCAPE_HELPER = new EscapeHelper('!',
            ',', 'c',
            ':', 'C',
            ' ', 's',
            ';', 'S',
            '_', 'u'
    );

    private void escapeTest(String plain, String encoded) {
        assertEquals(encoded, ESCAPE_HELPER.encode(plain));
        assertEquals(plain, ESCAPE_HELPER.decode(encoded));
    }

    @Test
    public void nil() {
        escapeTest(null, null);
    }

    @Test
    public void empty() {
        escapeTest("", "");
    }

    @Test
    public void simple() {
        escapeTest("Hei-du.", "Hei-du.");
    }

    @Test
    public void simpleWithOneEscapeChar() {
        escapeTest("Hei du", "Hei!sdu");
    }

    @Test
    public void simpleWithOneEscapeCharAndControlChar() {
        escapeTest("Hei du!", "Hei!sdu!!");
    }

    @Test
    public void withEscapeSequenceInPlain() {
        escapeTest("Hei!sdu!!", "Hei!!sdu!!!!");
    }

    @Test
    public void withAllEscapeChars() {
        escapeTest("!Hei_du:min,venn;bob_!sa ,alice:!:!!:!", "!!Hei!udu!Cmin!cvenn!Sbob!u!!sa!s!calice!C!!!C!!!!!C!!");
    }

    @Test
    public void trickyEnd() {
        escapeTest("e !2", "e!s!!2");
    }

    @Test
    public void encodingError() {
        // should produce a log warning, as last character of encoded string is the escape-control character without a subsequent character
        assertEquals("abc!", ESCAPE_HELPER.decode("abc!"));
    }

    @Test
    public void nonAsciiChars() {
        escapeTest("Hei hå\u0278", "Hei!shå\u0278");
    }

    @Test
    public void nonAsciiCharsWithEncodingError() {
        assertEquals("a!\u0278", ESCAPE_HELPER.decode("a!\u0278"));
    }

    @Test
    public void aggregateAndSplit() {
        String key1 = "_k,one";
        String val1 = "_v;one!";
        String key2 = "_k two;";
        String val2 = "v_two";
        // use character used in both key and value for separator
        String aggregate = ESCAPE_HELPER.encode(key1) + "_" + ESCAPE_HELPER.encode(val1)
                + ", " + ESCAPE_HELPER.encode(key2) + "_" + ESCAPE_HELPER.encode(val2);
        assertEquals("!uk!cone_!uv!Sone!!, !uk!stwo!S_v!utwo", aggregate);
        // notice that aggregate is easily parsable by using comma, space, and underscore, even though these appear in
        // both keys and values.
        String[] keyValuePairList = aggregate.split(", "); // split into list of pairs separated by comma and a space
        assertEquals(2, keyValuePairList.length);
        String[] pair1 = keyValuePairList[0].split("_");
        String acualKey1 = ESCAPE_HELPER.decode(pair1[0]);
        assertEquals(key1, acualKey1);
        String acualValue1 = ESCAPE_HELPER.decode(pair1[1]);
        assertEquals(val1, acualValue1);
        String[] pair2 = keyValuePairList[1].split("_");
        String acualKey2 = ESCAPE_HELPER.decode(pair2[0]);
        assertEquals(key2, acualKey2);
        String acualValue2 = ESCAPE_HELPER.decode(pair2[1]);
        assertEquals(val2, acualValue2);
    }
}
