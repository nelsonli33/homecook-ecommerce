package com.homecook.homecookadmin.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class BigDecimalSerializer extends JsonSerializer<BigDecimal>
{
    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
    {
        if (value != null && !"".equals(value)) {
            jsonGenerator.writeString(value.setScale(2, BigDecimal.ROUND_HALF_DOWN) + "");
        } else {
            jsonGenerator.writeString(value + "");
        }
    }
}
