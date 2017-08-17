package net.whydah.sso.whydah;


import com.fasterxml.jackson.annotation.*;

import java.time.Instant;
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
    private String signalSeverity = "LOW";
    @JsonProperty("text")
    private String text = "";


    /**
     * @param text
     * @param source
     * @param signalSeverity
     * @param signalEmitter
     * @param instant
     */
    public ThreatSignal(String source, String signalEmitter, String instant, String signalSeverity, String text) {
        super();
        this.source = source;
        this.signalEmitter = signalEmitter;
        this.instant = instant;
        this.signalSeverity = signalSeverity;
        this.text = text;
    }

    /**
     * @param text
     */
    public ThreatSignal(String text) {
        super();
        this.text = text;
        this.instant = Instant.now().toString();
        this.signalSeverity = "LOW";
    }

    /**
     */
    public ThreatSignal() {
        super();
        this.instant = Instant.now().toString();
        this.signalSeverity = "LOW";
    }

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
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ThreatSignal{" +
                "source='" + source + '\'' +
                ", signalEmitter='" + signalEmitter + '\'' +
                ", instant='" + instant + '\'' +
                ", signalSeverity='" + signalSeverity + '\'' +
                ", text='" + text + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
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
