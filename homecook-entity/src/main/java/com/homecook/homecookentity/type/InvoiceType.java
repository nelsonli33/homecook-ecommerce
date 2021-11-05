package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractStringAttributeConverter;

import java.util.Map;

public enum InvoiceType implements StringType
{
    PERSON("personal"),

    COMPANY("company"),

    DONATION("donation");

    private String code;

    InvoiceType(String code)
    {
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return code;
    }

    private static Map map = StringType.getValues(InvoiceType.class);

    public static InvoiceType fromCode(String code)
    {
        InvoiceType type = (InvoiceType) map.get(code);
        if (type == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + code);
        }
        return type;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringAttributeConverter<InvoiceType>
    {
        public Converter()
        {
            super(InvoiceType.class);
        }
    }
}
