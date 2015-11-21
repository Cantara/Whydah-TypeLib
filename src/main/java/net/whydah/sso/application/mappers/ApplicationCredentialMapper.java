package net.whydah.sso.application.mappers;

import net.whydah.sso.application.types.ApplicationCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.io.StringReader;


public class ApplicationCredentialMapper {
    private static final Logger log = LoggerFactory.getLogger(ApplicationCredentialMapper.class);

    public static String toXML(ApplicationCredential applicationCredential) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n " +
                "<applicationcredential>\n" +
                "    <params>\n" +
                "        <applicationID>" + applicationCredential.getApplicationID() + "</applicationID>\n" +
                "        <applicationName>" + applicationCredential.getApplicationName() + "</applicationName>\n" +
                "        <applicationSecret>" + applicationCredential.getApplicationSecret() + "</applicationSecret>\n" +
                "    </params> \n" +
                "</applicationcredential>\n";
    }

    public static ApplicationCredential fromXml(InputStream input) {
        if (input == null) {
            return null;
        }
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document dDoc = builder.parse(input);
            return extractApplicationCredential(dDoc);
        } catch (SAXParseException pe) {
            String msg = "fromXml failed due to invalid xml. SAXParseException: " + pe.getMessage();
            log.debug(msg);
            throw new IllegalArgumentException(msg);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing ApplicationCredential from xml InputStream", e);
        }
    }

    public static ApplicationCredential fromXml(String xml) {
        if (xml == null) {
            return null;
        }
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document dDoc = builder.parse(new InputSource(new StringReader(xml)));
            return extractApplicationCredential(dDoc);
        } catch (SAXParseException pe) {
            String msg = "fromXml failed due to invalid xml. SAXParseException: " + pe.getMessage();
            log.debug(msg);
            throw new IllegalArgumentException(msg);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing ApplicationCredential from xml InputStream", e);
        }
    }

    private static ApplicationCredential extractApplicationCredential(Document dDoc) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String applicationId = (String) xPath.evaluate("//applicationID", dDoc, XPathConstants.STRING);
        String applicationName = (String) xPath.evaluate("//applicationName", dDoc, XPathConstants.STRING);
        String applicationSecret = (String) xPath.evaluate("//applicationSecret", dDoc, XPathConstants.STRING);
        return new ApplicationCredential(applicationId, applicationName, applicationSecret);
    }
}
