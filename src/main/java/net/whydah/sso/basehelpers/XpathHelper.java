package net.whydah.sso.basehelpers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.ctc.wstx.compat.QNameCreator;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.io.StringReader;


public class XpathHelper {

    private static final Logger log = LoggerFactory.getLogger(XpathHelper.class);

    Document doc;
    XPath xPath;
    
    public XpathHelper(String xmlString){
         try {
        	 String xml = Validator.sanitizeXml(xmlString);
        	 
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
             dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
             DocumentBuilder db = dbf.newDocumentBuilder();
             doc = db.parse(new InputSource(new StringReader(xmlString)));
             xPath = XPathFactory.newInstance().newXPath();
           
         } catch (Exception e) {
             log.warn("Failed to parse xml {}, ", xmlString, e);
             doc = null;
         }
     
    }
    
    public Object findValue(String expression, QName type) {
    	 if(doc==null){
    		 return null;
    	 }
    	 try {
    		 return xPath.evaluate(expression, doc, type);
    	 } catch(XPathExpressionException ex) {
    		 log.warn("Failed to parse xml. Expression {}, exception {} ", expression, ex);
    	 }
		return null;
    }
    
    public String findValue(String expression) {
    	return (String) findValue(expression, XPathConstants.STRING);
    }
    
    
//    
//    public static Object findValue(String xmlString, String expression, QName type) {
//        Object value = null;
//        if (!isSane(xmlString)) {
//            log.warn(" XML injection detected - called with xmlString:{} - Returning null", xmlString);
//            return null;
//        }
//      
//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
//            dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(xmlString)));
//            XPath xPath = XPathFactory.newInstance().newXPath();
//          
//            XPathExpression xPathExpression = xPath.compile(expression);
//            value = xPathExpression.evaluate(doc, type);
//        } catch (Exception e) {
//            log.warn("Failed to parse xml. Expression {}, xml {}, ", expression, xmlString, e);
//        }
//        return value;
//    }
//    
//    public static String findValue(String xmlString, String expression) {
//        return (String) findValue(xmlString, expression, XPathConstants.STRING);
//    }

}
