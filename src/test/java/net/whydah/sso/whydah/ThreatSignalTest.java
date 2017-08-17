package net.whydah.sso.whydah;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static junit.framework.TestCase.assertTrue;

public class ThreatSignalTest {

    private final static Logger log = LoggerFactory.getLogger(ThreatSignalTest.class);
    private static final ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testThreadSignalMappings() throws Exception {
        ThreatSignal threatSignal = new ThreatSignal("my test signal");

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(threatSignal);

        ThreatSignal receivedSignal = mapper.readValue(json, ThreatSignal.class);
        assertTrue(receivedSignal.toString().equalsIgnoreCase(threatSignal.toString()));
    }
}
