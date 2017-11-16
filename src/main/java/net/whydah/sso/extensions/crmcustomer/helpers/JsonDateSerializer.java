package net.whydah.sso.extensions.crmcustomer.helpers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateSerializer extends JsonSerializer<Date> {

    @Override
    public synchronized void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = dateFormat.format(date);
        gen.writeString(formattedDate);
    }
}