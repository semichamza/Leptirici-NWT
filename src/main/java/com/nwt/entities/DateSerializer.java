package com.nwt.entities;



import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSerializer extends StdScalarSerializer<Date> {
    public DateSerializer()
    {
        super(Date.class);
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws
            IOException, JsonProcessingException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String formattedDate = formatter.format(value);
        gen.writeString(formattedDate);
    }
}
