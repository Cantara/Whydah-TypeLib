package net.whydah.sso.user.helpers;

import net.whydah.sso.basehelpers.XpathHelper;

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

import static net.whydah.sso.user.mappers.UserTokenMapper.isSane;

public class UserAggregateXpathHelper {
    private static final Logger log = LoggerFactory.getLogger(UserAggregateXpathHelper.class);


    public static String getPersonref(String userAggregateXml) {
        if (userAggregateXml == null) {
            log.debug("userAggregateXml was empty, so returning empty personref");
            return "";
        }

        if (!isSane(userAggregateXml)) {
            log.warn(" XML injection detected - called with userAggregateXml:{} - Returning null", userAggregateXml);
            return null;
        }




//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(userAggregateXml)));
//            XPath xPath = XPathFactory.newInstance().newXPath();
//
//            String expression = "//identity/personref";
//            XPathExpression xPathExpression = xPath.compile(expression);
//
//            String value = xPathExpression.evaluate(doc);
//            if (value != null && value.length() > 0) {
//                return value;
//
//            }
//
//        } catch (Exception e) {
//            log.error("personRef missed - trying personRef");
//        }
//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(userAggregateXml)));
//            XPath xPath = XPathFactory.newInstance().newXPath();
//
//            String expression = "//identity/personRef";
//            XPathExpression xPathExpression = xPath.compile(expression);
//
//            return xPathExpression.evaluate(doc);
//
//        } catch (Exception e) {
//            log.error("personref missed too, returning empty");
//        }
        XpathHelper x = new XpathHelper(userAggregateXml);
        String result = x.findValue("//identity/personref");
        if(result==null){
        	result =  x.findValue("//identity/personRef");
        }
        return result==null?"":result;

    }
}
