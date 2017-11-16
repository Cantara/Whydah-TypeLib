package net.whydah.sso.extensions.crmcustomer.helpers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class JsonDateDeserializer extends JsonDeserializer {


    @Override
    public synchronized Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
