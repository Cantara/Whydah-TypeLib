package net.whydah.sso.whydah;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "source",
        "signal-emitter",
        "instant",
        "signal-severity",
        "text"
})
public class ThreatSignal {

    @JsonProperty("source")
    private String source = "";
    @JsonProperty("signal-emitter")
    private String signalEmitter = "";
    @JsonProperty("instant")
    private String instant = "";
    @JsonProperty("signal-severity")
    private String signalSeverity = "";
    @JsonProperty("text")
    private String text = "";

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("signal-emitter")
    public String getSignalEmitter() {
        return signalEmitter;
    }

    @JsonProperty("signal-emitter")
    public void setSignalEmitter(String signalEmitter) {
        this.signalEmitter = signalEmitter;
    }

    @JsonProperty("instant")
    public String getInstant() {
        return instant;
    }

    @JsonProperty("instant")
    public void setInstant(String instant) {
        this.instant = instant;
    }

    @JsonProperty("signal-severity")
    public String getSignalSeverity() {
        return signalSeverity;
    }

    @JsonProperty("signal-severity")
    public void setSignalSeverity(String signalSeverity) {
        this.signalSeverity = signalSeverity;
    }

    @JsonProperty("text")
    public String getTest() {
        return text;
    }

    @JsonProperty("text")
    public void setTest(String test) {
        this.text = text;
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
