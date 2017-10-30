package net.whydah.sso.ddd.sso;

import net.whydah.sso.application.mappers.ApplicationMapper;
import net.whydah.sso.application.types.Application;
import net.whydah.sso.basehelpers.Sanitizers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

public class RedirectURI implements Serializable {

    private final String redirectURI;
    private final String ILLEGAL_REDIRECTURI = "ILLEGAL_REDIRECT";
    private final static Logger log = LoggerFactory.getLogger(RedirectURI.class);
    Pattern p = Pattern.compile("[^a-zA-Z0-9\\-]");

    public RedirectURI(String inputRedirectURI, List<Application> applicationList, String whiteListedLocalDomain) {
        // Must not be null
        if (inputRedirectURI == null) {
            log.error("Attempt to create an illegal RedirectURI - value is null");
            this.redirectURI = null;
            // Must be a string, min length=3, max length 36
        } else if (inputRedirectURI.length() < 3 || inputRedirectURI.length() > 136) {
            log.error("Attempt to create an illegal RedirectURI - illegal length:{}", inputRedirectURI.length());
            this.redirectURI = null;
            // Must be of only whitelisted characters
        } else {
            this.redirectURI = validateRedirectURIAgainstSanityAndApplicationModel(inputRedirectURI, applicationList, true, whiteListedLocalDomain);
        }
    }

    public RedirectURI(String inputRedirectURI, List<Application> applicationList) {
        // Must not be null
        if (inputRedirectURI == null) {
            log.error("Attempt to create an illegal RedirectURI - value is null");
            this.redirectURI = null;
            // Must be a string, min length=3, max length 36
        } else if (inputRedirectURI.length() < 3 || inputRedirectURI.length() > 136) {
            log.error("Attempt to create an illegal RedirectURI - illegal length:{}", inputRedirectURI.length());
            this.redirectURI = null;
            // Must be of only whitelisted characters
        } else {
            this.redirectURI = validateRedirectURIAgainstSanityAndApplicationModel(inputRedirectURI, applicationList, true, "");
        }
    }

    public RedirectURI(String inputRedirectURI) {
        // Must not be null
        if (inputRedirectURI == null) {
            log.error("Attempt to create an illegal RedirectURI - value is null");
            this.redirectURI = null;
            // Must be a string, min length=3, max length 36
        } else if (inputRedirectURI.length() < 3 || inputRedirectURI.length() > 136) {
            log.error("Attempt to create an illegal RedirectURI - illegal length:{}", inputRedirectURI.length());
            this.redirectURI = null;
            // Must be of only whitelisted characters
        } else {
            this.redirectURI = validateRedirectURIAgainstSanityAndApplicationModel(inputRedirectURI, null, false, "");
        }
    }


    public String getRedirectURI() {
        return redirectURI;
    }

    public boolean isValid() {
        return redirectURI != null && !redirectURI.equalsIgnoreCase(ILLEGAL_REDIRECTURI);
    }


    public static boolean isValid(String redirectURIToValidate) {
        try {
            return new RedirectURI(redirectURIToValidate).isValid();
        } catch (Exception e) {
        }
        return false;
    }



    @Override
    public String toString() {
        return redirectURI;
    }


    private String validateRedirectURIAgainstSanityAndApplicationModel(String baseLineRedirectURI, List<Application> applicationList, boolean matchRedirectURLtoModel, String domain) {


        if (baseLineRedirectURI.length() != Sanitizers.sanitize(baseLineRedirectURI).length()) {  // allow short local paths on domain
            log.error("Attempt to create an illegal RedirectURI - insane content:{}");
            return ILLEGAL_REDIRECTURI;
        }
        if (baseLineRedirectURI.contains("<html>")) {  // allow short local domain paths
            log.error("Attempt to create an illegal RedirectURI - html-content:{}");
            return ILLEGAL_REDIRECTURI;
        }
        if (baseLineRedirectURI.contains("<javascript")) {  // allow short local domain paths
            log.error("Attempt to create an illegal RedirectURI - javascript-content:{}");
            return ILLEGAL_REDIRECTURI;
        }

        if (baseLineRedirectURI.length() < 25 && !baseLineRedirectURI.contains("http")) {  // allow short local paths
            return baseLineRedirectURI;
        }
        if (domain != null && domain.length() > 5 && baseLineRedirectURI.contains(domain)) {  // allow short local domain paths
            return baseLineRedirectURI;
        }

        if (!matchRedirectURLtoModel) {
            return baseLineRedirectURI;
        }
        String validBaseline = ApplicationMapper.toShortListJson(applicationList);
        if (validBaseline.contains(baseLineRedirectURI)) {
            return baseLineRedirectURI;
        } else {
            return ILLEGAL_REDIRECTURI;
        }
    }
}
