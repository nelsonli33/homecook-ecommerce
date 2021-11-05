package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractStringAttributeConverter;

import java.util.Map;

public enum InvoiceStatusType implements StringType
{
    SUCCESS("SUCCESS"),

    FAILURE("FAILURE"),

    PENDING("PENDING"),

    VOIDED("VOIDED"); // 已作廢

    private String code;

    InvoiceStatusType(String code)
    {
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return code;
    }

    private static Map map = StringType.getValues(InvoiceStatusType.class);

    public static InvoiceStatusType fromCode(String code)
    {
        InvoiceStatusType statusType = (InvoiceStatusType) map.get(code);
        if (statusType == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + code);
        }
        return statusType;
    }


    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringAttributeConverter<InvoiceStatusType>
    {
        public Converter()
        {
            super(InvoiceStatusType.class);
        }
    }
}
