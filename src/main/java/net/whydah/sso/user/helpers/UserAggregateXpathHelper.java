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
		
		XpathHelper x;

		x = new XpathHelper(userAggregateXml);
		String result = x.findNullableValue("//identity/personref");
		if(result==null || result.length()==0){
			result =  x.findNullableValue("//identity/personRef");
		}
		return result==null?"":result;



	}
}
