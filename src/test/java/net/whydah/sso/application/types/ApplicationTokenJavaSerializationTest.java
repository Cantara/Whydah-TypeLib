package net.whydah.sso.application.types;

import net.whydah.sso.application.mappers.ApplicationTokenMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;

public class ApplicationTokenJavaSerializationTest {

    private final static Logger log = LoggerFactory.getLogger(ApplicationTokenJavaSerializationTest.class);

    @Test
    public void thatApplicationTokenCanBeSerializedAndDeserialized() throws IOException, ClassNotFoundException {
        // given token
        String applicationTokenXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n" +
                "<applicationtoken>\n" +
                "     <params>\n" +
                "         <applicationtokenID>1cca06386f52f91d9610aa1dbd95b9a9</applicationtokenID>\n" +
                "         <applicationid>2210</applicationid>\n" +
                "         <applicationname>Whydah-UserIdentityBackend</applicationname>\n" +
                "         <applicationtags>HIDDEN, JURISDICTION_NORWAY, JURISDICTION_SWEDEN, OWNER_96905054, COMPANY_cantara.no</applicationtags>\n" +
                "         <expires>%s</expires>\n" +
                "     </params> \n" +
                "     <Url type=\"application/xml\" method=\"POST\"                 template=\"user/1cca06386f52f91d9610aa1dbd95b9a9/get_usertoken_by_usertokenid\"/> \n" +
                " </applicationtoken>";
        applicationTokenXml = String.format(applicationTokenXml, System.currentTimeMillis() + 60 * 60 * 1000);
        ApplicationToken expectedToken = ApplicationTokenMapper.fromXml(applicationTokenXml);

        // java serialization (used by hazelcast in STS)
        ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(expectedToken);
        oos.flush();
        oos.close();

        // java deserialization (used by hazelcast in STS)
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        ApplicationToken actualToken = (ApplicationToken) ois.readObject();

        // compare
        assertEquals(expectedToken.getApplicationID(), actualToken.getApplicationID());
        assertEquals(expectedToken.getApplicationTokenId(), actualToken.getApplicationTokenId());
        assertEquals(expectedToken.getApplicationName(), actualToken.getApplicationName());
        assertEquals(expectedToken.getApplicationSecret(), actualToken.getApplicationSecret());
        assertEquals(expectedToken.getExpires(), actualToken.getExpires());
        assertEquals(expectedToken.getBaseuri(), actualToken.getBaseuri());
        assertEquals(expectedToken.getTags(), actualToken.getTags());
    }
}
