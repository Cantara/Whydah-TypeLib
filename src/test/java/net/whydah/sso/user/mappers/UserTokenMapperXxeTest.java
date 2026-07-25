package net.whydah.sso.user.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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
 * The mappers parse XML that originates outside this process, so they must not resolve
 * external entities.
 *
 * fromUserTokenXml hardens the shared static DocumentBuilderFactory at call time, as a side
 * effect. fromUserAggregateXml uses the same factory but never hardens it, so whether it is
 * safe depends on whether some other call happened to harden the global first.
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

        // Model a JVM in which nothing has hardened the shared factory yet. Without this the
        // result would depend on whether some earlier test in the same JVM called
        // fromUserTokenXml, which mutates this global.
        UserTokenMapper.dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "all");
        UserTokenMapper.dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "all");
    }

    private String withExternalEntity(String rootOpen, String body, String rootClose) {
        return "<?xml version=\"1.0\"?>"
                + "<!DOCTYPE foo [ <!ENTITY xxe SYSTEM \"" + canaryFile.toURI() + "\"> ]>"
                + rootOpen + body + rootClose;
    }

    @Test
    public void fromUserAggregateXmlDoesNotResolveExternalEntities() {
        String hostile = withExternalEntity(
                "<whydahuser><identity>",
                "<UID>uid1</UID><username>&xxe;</username><firstname>&xxe;</firstname>"
                        + "<lastname>Nordmann</lastname><email>olav@example.no</email><cellPhone>12345678</cellPhone>",
                "</identity><applications></applications></whydahuser>");

        UserToken token = UserTokenMapper.fromUserAggregateXml(hostile);

        String rendered = String.valueOf(token);
        assertFalse("external entity was resolved - local file contents leaked into the UserToken: " + rendered,
                rendered.contains(CANARY));
    }

    @Test
    public void fromUserTokenXmlDoesNotResolveExternalEntities() {
        String hostile = withExternalEntity(
                "<usertoken id=\"tok1\">",
                "<uid>uid1</uid><username>&xxe;</username><email>&xxe;</email>",
                "</usertoken>");

        UserToken token = UserTokenMapper.fromUserTokenXml(hostile);

        String rendered = String.valueOf(token);
        assertFalse("external entity was resolved - local file contents leaked into the UserToken: " + rendered,
                rendered.contains(CANARY));
    }

    @Test
    public void fromUserAggregateXmlStillParsesBenignXml() {
        String benign = "<whydahuser><identity><UID>uid1</UID><username>olav</username>"
                + "<firstname>Olav</firstname><lastname>Nordmann</lastname>"
                + "<email>olav@example.no</email><cellPhone>12345678</cellPhone>"
                + "</identity><applications></applications></whydahuser>";

        UserToken token = UserTokenMapper.fromUserAggregateXml(benign);

        assertNotNull("hardening must not break parsing of ordinary user aggregate XML", token);
        assertEquals("olav", token.getUserName());
    }
}
