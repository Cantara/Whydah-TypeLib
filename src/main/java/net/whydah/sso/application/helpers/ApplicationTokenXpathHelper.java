package net.whydah.sso.application.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

public class ApplicationTokenXpathHelper {

    private final static Logger log = LoggerFactory.getLogger(ApplicationTokenXpathHelper.class);


    public static String getApplicationIDFromApplicationCredential(String applicationCredentialXML) {
        log.debug("applicationCredentialXML: {}", applicationCredentialXML);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(applicationCredentialXML)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/applicationcredential/*/applicationID[1]";
            XPathExpression xPathExpression = xPath.compile(expression);
            String appId = xPathExpression.evaluate(doc);
            log.debug("XML parse: applicationid = {}", appId);
            return appId;
        } catch (Exception e) {
            log.error("Could not get applicationID from XML: " + applicationCredentialXML, e);
        }
        return "";
    }

    public static String getApplicationNameFromApplicationCredential(String applicationCredentialXML) {
        log.debug("applicationCredentialXML: {}", applicationCredentialXML);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(applicationCredentialXML)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/applicationcredential/*/applicationName[1]";
            XPathExpression xPathExpression = xPath.compile(expression);
            String appName = xPathExpression.evaluate(doc);
            log.debug("XML parse: applicationName = {}", appName);
            return appName;
        } catch (Exception e) {
            log.error("Could not get applicationName from XML: " + applicationCredentialXML, e);
        }
        return "";
    }

    public static String getApplicationSecretFromApplicationCredential(String applicationCredentialXML) {
        log.debug("applicationCredentialXML: {}", applicationCredentialXML);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(applicationCredentialXML)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/applicationcredential/*/applicationSecret[1]";
            XPathExpression xPathExpression = xPath.compile(expression);
            String appId = xPathExpression.evaluate(doc);
            log.debug("XML parse: applicationSecret = {}", appId);
            return appId;
        } catch (Exception e) {
            log.error("Could not get applicationID from XML: " + applicationCredentialXML, e);
        }
        return "";
    }

    public static String getApplicationTokenIDFromApplicationToken(String applicationTokenXML) {
        log.debug("applicationTokenXML: {}", applicationTokenXML);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(applicationTokenXML)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/token/*/applicationtoken[1]";
            XPathExpression xPathExpression = xPath.compile(expression);
            String appTokenId = xPathExpression.evaluate(doc);
            log.debug("XML parse: applicationTokenID = {}", appTokenId);
            return appTokenId;
        } catch (Exception e) {
            log.error("Could not get applicationTokenID from XML: " + applicationTokenXML, e);
        }
        return "";
    }


}
