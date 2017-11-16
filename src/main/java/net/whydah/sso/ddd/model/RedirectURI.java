package net.whydah.sso.ddd.model;

import net.whydah.sso.application.mappers.ApplicationMapper;
import net.whydah.sso.application.types.Application;
import net.whydah.sso.ddd.model.base.AbstractName;

import java.util.List;

//import net.whydah.sso.basehelpers.Sanitizers;


public class RedirectURI extends AbstractName {

	final List<Application> applicationList; 
	final String whiteListedLocalDomain;

	public RedirectURI(String inputRedirectURI, List<Application> applicationList, String whiteListedLocalDomain) {
		super(inputRedirectURI);
		this.applicationList = applicationList;
		this.whiteListedLocalDomain = whiteListedLocalDomain;
		
	}
	
	@Override
	protected void validateInput(String input) {
		
		assertArgumentNotEmpty(input, "Attempt to create an illegal Redirect - value is null or empty");
		assertArgumentWithSafeInput(input, 3, 136, null, new String[]{"(", ")", "'", "[", "]", ",", "*"}, "The input must have the length 3 - 136 and without invalid characters.");
		
		if(input.startsWith("localhost")||input.startsWith("http://localhost")){
			return;
		}			
		if (input.length() < 25 && !input.contains("http")) {  // allow short local paths
            return;
        }

		if (whiteListedLocalDomain != null && whiteListedLocalDomain.length() > 5 && input.contains(whiteListedLocalDomain)) {  // allow short local domain paths
			return;
		}
		
		if(applicationList!=null && applicationList.size()>0){
			String validBaseline = ApplicationMapper.toShortListJson(applicationList);
			if (validBaseline.contains(input)) {
				return;
			} else {
				throwException("This Url is not existing in our database");
			}
		}
	}
	
	public static boolean isValid(String input) {
		return isValid(input, null, "");
	}
	
	public static boolean isValid(String inputRedirectURI, List<Application> applicationList, String whiteListedLocalDomain){
		try {
			new RedirectURI(inputRedirectURI, applicationList, whiteListedLocalDomain);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
