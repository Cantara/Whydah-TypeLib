package net.whydah.sso.user.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.xml.XMLConstants;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import net.whydah.sso.user.types.UserToken;

/**
 * Pins the defence against XXE in the user mappers.
 *
 * The mappers parse XML that originates outside this process. They are not protected by their
 * parser configuration: the shared static DocumentBuilderFactory is created with JDK defaults,
 * which do resolve external entities, and fromUserAggregateXml never hardens it.
 *
 * What actually stops the attack is isSane -> Validator.isValidXml, which rejects any input
 * whose length changes under Validator.sanitizeXml. sanitizeXml deletes the literals "DOCTYPE",
 * "doctype" and "ENTITY", and an external entity attack cannot be expressed without a DOCTYPE
 * declaring an ENTITY, so hostile input is refused before it reaches a parser.
 *
 * That protection is incidental rather than declared, and Validator.isValidXml's own pattern
 * check is currently disabled behind an unconditional return. These tests exist so that trimming
 * the sanitizer, or completing that TODO, cannot silently reopen external entity resolution.
 */
public class UserTokenMapperXxeTest {

    private static final String CANARY = "TOPSECRET-XXE-CANARY";

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    private File canaryFile;

    @Before
    public void setUp() throws Exception {
        canaryFile = tmp.newFile("canary.txt");
        Files.write(canaryFile.toPath(), CANARY.getBytes(StandardCharsets.UTF_8));

        // Model a JVM in which nothing has hardened the shared factory yet. fromUserTokenXml
        // mutates this global at call time, so without this reset the outcome would depend on
        // whether some earlier test in the same JVM happened to call it first.
        UserTokenMapper.dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "all");
        UserTokenMapper.dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "all");
    }

    private String withExternalEntity(String rootOpen, String body, String rootClose) {
        return "<?xml version=\"1.0\"?>"
                + "<!DOCTYPE foo [ <!ENTITY xxe SYSTEM \"" + canaryFile.toURI() + "\"> ]>"
                + rootOpen + body + rootClose;
    }

    private String hostileUserAggregate() {
        return withExternalEntity(
                "<whydahuser><identity>",
                "<UID>uid1</UID><username>&xxe;</username><firstname>&xxe;</firstname>"
                        + "<lastname>Nordmann</lastname><email>olav@example.no</email><cellPhone>12345678</cellPhone>",
                "</identity><applications></applications></whydahuser>");
    }

    private String hostileUserToken() {
        return withExternalEntity(
                "<usertoken id=\"tok1\">",
                "<uid>uid1</uid><username>&xxe;</username><email>&xxe;</email>",
                "</usertoken>");
    }

    @Test
    public void isSaneRejectsDoctypeAndEntityDeclarations() {
        assertFalse("a DOCTYPE declaring an ENTITY must be refused before parsing",
                UserTokenMapper.isSane(hostileUserAggregate()));
        assertFalse("a DOCTYPE declaring an ENTITY must be refused before parsing",
                UserTokenMapper.isSane(hostileUserToken()));
    }

    @Test
    public void fromUserAggregateXmlDoesNotResolveExternalEntities() {
        UserToken token = UserTokenMapper.fromUserAggregateXml(hostileUserAggregate());

        assertNull("hostile input must be refused outright", token);
        assertFalse("local file contents leaked into the UserToken: " + token,
                String.valueOf(token).contains(CANARY));
    }

    @Test
    public void fromUserTokenXmlDoesNotResolveExternalEntities() {
        UserToken token = UserTokenMapper.fromUserTokenXml(hostileUserToken());

        assertNull("hostile input must be refused outright", token);
        assertFalse("local file contents leaked into the UserToken: " + token,
                String.valueOf(token).contains(CANARY));
    }

    @Test
    public void fromUserAggregateXmlStillParsesBenignXml() {
        String benign = "<whydahuser><identity><UID>uid1</UID><username>olav</username>"
                + "<firstname>Olav</firstname><lastname>Nordmann</lastname>"
                + "<email>olav@example.no</email><cellPhone>12345678</cellPhone>"
                + "</identity><applications></applications></whydahuser>";

        UserToken token = UserTokenMapper.fromUserAggregateXml(benign);

        assertNotNull("the guard must not reject ordinary user aggregate XML", token);
        assertEquals("olav", token.getUserName());
    }
}
