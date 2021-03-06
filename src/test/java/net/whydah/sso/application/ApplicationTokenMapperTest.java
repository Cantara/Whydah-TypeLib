package net.whydah.sso.application;

import net.whydah.sso.application.mappers.ApplicationTokenMapper;
import net.whydah.sso.application.types.ApplicationToken;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.whydah.sso.application.helpers.ApplicationCredentialHelper.getDummyApplicationCredential;

public class ApplicationTokenMapperTest {

    private final static Logger log = LoggerFactory.getLogger(ApplicationTokenMapperTest.class);


    String applicationToken = " <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n" +
            "  <applicationtoken>\n" +
            "     <params>\n" +
            "         <applicationtokenID>1cca06386f52f91d9610aa1dbd95b9a9</applicationtokenID>\n" +
            "         <applicationid>2210</applicationid>\n" +
            "         <applicationname>Whydah-UserIdentityBackend</applicationname>\n" +
            "         <expires>%s</expires>\n" +
            "     </params> \n" +
            "     <Url type=\"application/xml\" method=\"POST\"                 template=\"https://whydahdev.cantara.no/tokenservice/user/1cca06386f52f91d9610aa1dbd95b9a9/get_usertoken_by_usertokenid\"/> \n" +
            " </applicationtoken>\n";


    @Test
    public void testApplicationTokenMapperFromXml() {
    	//have to set the expiry date
    	applicationToken = String.format(applicationToken, System.currentTimeMillis() + 60*60*1000);
        ApplicationToken uibApplicationToken = ApplicationTokenMapper.fromXml(applicationToken);
        log.trace(ApplicationTokenMapper.toXML(uibApplicationToken));

    }

    @Test
    public void testApplicationTokenHelper() {
        ApplicationToken t = ApplicationTokenMapper.fromApplicationCredentialXML(getDummyApplicationCredential());

    }


}

