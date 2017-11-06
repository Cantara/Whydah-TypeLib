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
    
    public XpathHelper(String xmlString) {
         try {  
        	 if(xmlString==null || xmlString.length()==0){
        		 log.error("xml is empty, so returning empty.");
        		 doc = null;
        		 return;
        	 }
        	 if(!Validator.isValidXml(xmlString)){
        		 log.error("Failed to parse xml {} because of XML injection detected, ", xmlString);
        		 doc = null;
        		 return;
        	 }
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
             dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
             DocumentBuilder db = dbf.newDocumentBuilder();
             doc = db.parse(new InputSource(new StringReader(xmlString)));
             xPath = XPathFactory.newInstance().newXPath();
           
         } catch (Exception e) {
             log.error("Failed to parse xml {}, ", xmlString, e);
             doc = null;
         }
     
    }
    
    public boolean isValid(){
    	return doc!=null;
    }
    
    public Object findValue(String expression, QName type) throws XPathExpressionException {
    	
    	if(!isValid()){
    		return null;
    	}
    	 try {
    		 XPathExpression xPathExpression = xPath.compile(expression);
    		 return xPathExpression.evaluate(doc, type);
    	 } catch(XPathExpressionException ex) {
    		 log.warn("Failed to parse xml. Expression {}, exception {} ", expression, ex);
    		 throw ex;
    	 }
    }
    
    public Object findNullableValue(String expression, QName type) {
    	if(!isValid()){
    		return null;
    	}
    	 try {
    		 XPathExpression xPathExpression = xPath.compile(expression);
    		 return xPathExpression.evaluate(doc, type);
    	 } catch(XPathExpressionException ex) {
    		 log.warn("Failed to parse xml. Expression {}, exception {} ", expression, ex);
    		 return null;
    	 }
    }
    
    public String findValue(String expression) throws XPathExpressionException  {
    	return (String) findValue(expression, XPathConstants.STRING);
    	
    }
    
    public String findNullableValue(String expression)  {
    	Object obj = findNullableValue(expression, XPathConstants.STRING);
    	return obj ==null? null: (String) obj;
    	
    }

}
