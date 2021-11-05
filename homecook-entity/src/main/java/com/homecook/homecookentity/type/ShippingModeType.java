package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractStringAttributeConverter;

import java.util.Map;

public enum ShippingModeType implements StringType
{
    HOME("HOME"),

    CVS("CVS");

    private String code;

    ShippingModeType(String code)
    {
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return code;
    }

    private static Map map = StringType.getValues(ShippingModeType.class);

    public static ShippingModeType fromCode(String code)
    {
        ShippingModeType statusType = (ShippingModeType) map.get(code);
        if (statusType == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + code);
        }
        return statusType;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringAttributeConverter<ShippingModeType>
    {
        public Converter()
        {
            super(ShippingModeType.class);
        }
    }
}
