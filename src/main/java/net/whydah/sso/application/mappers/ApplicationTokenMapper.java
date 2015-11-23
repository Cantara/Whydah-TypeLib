package net.whydah.sso.application.mappers;


import net.whydah.sso.application.helpers.ApplicationTokenXpathHelper;
import net.whydah.sso.application.types.ApplicationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class ApplicationTokenMapper {
    private static final Logger log = LoggerFactory.getLogger(ApplicationTokenMapper.class);


    public static String toXML(ApplicationToken applicationToken) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n " +
                " <applicationtoken>\n" +
                "     <params>\n" +
                "         <applicationtokenID>" + applicationToken.getApplicationTokenId() + "</applicationtokenID>\n" +
                "         <applicationid>" + applicationToken.getApplicationID() + "</applicationid>\n" +
                "         <applicationname>" + applicationToken.getApplicationName() + "</applicationname>\n" +
                "         <expires>" + applicationToken.getExpires() + "</expires>\n" +
                "     </params> \n" +
                "     <Url type=\"application/xml\" method=\"POST\" " +
                "                template=\"" + applicationToken.getBaseuri() + "user/" + applicationToken.getApplicationTokenId() + "/get_usertoken_by_usertokenid\"/> \n" +
                " </applicationtoken>\n";
    }


    public static ApplicationToken fromXml(String input) {
        if (input == null) {
            return null;
        }
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document dDoc = builder.parse(input);
            return extractApplicationToken(dDoc);
        } catch (SAXParseException pe) {
            String msg = "fromXml failed due to invalid xml. SAXParseException: " + pe.getMessage();
            log.debug(msg);
            throw new IllegalArgumentException(msg);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing ApplicationCredential from xml InputStream", e);
        }

    }

    private static ApplicationToken extractApplicationToken(Document dDoc) throws XPathExpressionException {
        ApplicationToken applicationToken = new ApplicationToken();

        XPath xPath = XPathFactory.newInstance().newXPath();
        applicationToken.setApplicationTokenId(xPath.evaluate("//applicationtokenID", dDoc, XPathConstants.STRING).toString());
        applicationToken.setApplicationID(xPath.evaluate("//applicationid", dDoc, XPathConstants.STRING).toString());
        applicationToken.setApplicationName(xPath.evaluate("//applicationname", dDoc, XPathConstants.STRING).toString());
        applicationToken.setExpires(xPath.evaluate("//expires", dDoc, XPathConstants.STRING).toString());
        return applicationToken;
    }


    public static ApplicationToken fromApplicationCredentialXML(String xml) {
        ApplicationToken appToken = new ApplicationToken();
        appToken.setApplicationID(ApplicationTokenXpathHelper.getApplicationIDFromApplicationCredential(xml));
        appToken.setApplicationName(ApplicationTokenXpathHelper.getApplicationNameFromApplicationCredential(xml));
        appToken.setApplicationSecret(ApplicationTokenXpathHelper.getApplicationSecretFromApplicationCredential(xml));
        appToken.setExpires(String.valueOf(System.currentTimeMillis() + 100));
        appToken.setApplicationTokenId(appToken.getMD5hash(appToken.getApplicationID() + appToken.getExpires()));
        return appToken;
    }


}
