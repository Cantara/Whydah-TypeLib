package net.whydah.basehelpers;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import net.whydah.sso.user.helpers.TagsParser;

public class TagsParserTest {
	
	static class dummy{
		
		public dummy(){}
		
		public String dum1;
		public boolean dum2;
		public List<String> dum3;
		public long dum4;
		
	}
	
	@Test
	public void testHavocCase() {
	
		try {
			System.out.println(TagsParser.addTag("", "item", "i1"));
			//the output
			//{
			//  "item" : "i1"
			//}
			System.out.println(TagsParser.addTag("item1; item2", "item", "i1")); 
			System.out.println(TagsParser.addTag("item1 item2", "item", "i1")); 
			System.out.println(TagsParser.addTag("item1,item2", "item", "i1"));
			System.out.println(TagsParser.addTag("item1:item2", "item", "i1"));
			//the output looks like
			//{
			//	"item" : "i1",
			//	"TAG_item1" : "item1",
			//	"TAG_item2" : "item2"
			//}
			System.out.println(TagsParser.addTag(null, "item", "i1"));
			//the output
			//{
			//  "item" : "i1"
			//}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testTagsReadAndWrite() {
		try {
			
			////////read///////////
			
			//add tag
			String tags = TagsParser.addTag("{}", "name", "tester");
			assertTrue(tags.contains("name") && tags.contains("tester"));
			System.out.println(tags);
			
			//new tag
			tags = TagsParser.addTag(tags, "email", "someone@test.com");
			assertTrue(tags.contains("name") && tags.contains("tester"));
			assertTrue(tags.contains("email")  && tags.contains("someone@test.com"));
			System.out.println(tags);
			
			//new tag with some array
			List<String> items = new ArrayList<>();
			items.add("coke");
			items.add("burger");
			items.add("potato");
			items.add("chicken nugget");
			tags = TagsParser.addTag(tags, "items", items);
			assertTrue(tags.contains("name") && tags.contains("tester"));
			assertTrue(tags.contains("email")  && tags.contains("someone@test.com"));
			assertTrue(tags.contains("coke")  && tags.contains("burger") && tags.contains("potato") && tags.contains("chicken nugget"));
			System.out.println(tags);
			
			//some object
			dummy d = new dummy();
			d.dum1 = "dum1";
			d.dum2 = true;
			d.dum3 = Arrays.asList("a", "b", "c");
			d.dum4 = 99999999;
			tags = TagsParser.addTag(tags, "dummy", d);
			assertTrue(tags.contains("name") && tags.contains("tester"));
			assertTrue(tags.contains("email")  && tags.contains("someone@test.com"));
			assertTrue(tags.contains("coke")  && tags.contains("burger") && tags.contains("potato") && tags.contains("chicken nugget"));
			assertTrue(tags.contains("dum1")  && tags.contains("true") && tags.contains("a") && tags.contains("b")&& tags.contains("c") && tags.contains("99999999"));
			
			System.out.println(tags);
			
			////////////write////////////
			assertTrue(TagsParser.getTag(tags, "name").equals("tester"));
			assertTrue(TagsParser.getTag(tags, "email").equals("someone@test.com"));
			List<String> i= (List<String>) TagsParser.getTag(tags, "items");
			assertTrue(i.size()==4);
			dummy d2 = TagsParser.getTag(tags, "dummy", dummy.class);
			assertTrue(d2.dum1.equals(d.dum1));
			assertTrue(d2.dum2==d.dum2);
			assertTrue(d2.dum3.size() + d.dum3.size()==6);
			assertTrue(d2.dum4==d.dum4);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
