package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractIntegerAttributeConverter;

import java.util.Map;

public enum GenderType implements OrdinalType
{
    NOT_KNOWN(0),

    MALE(1),

    FEMALE(2),

    NOT_APPLICABLE(9);

    private int value;

    GenderType(int value)
    {
        this.value = value;
    }

    @Override
    public int getValue()
    {
        return value;
    }

    private static Map map = OrdinalType.getValues(GenderType.class);

    public static GenderType valueOf(int value)
    {
        GenderType type = (GenderType) map.get(value);
        if (type == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return type;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractIntegerAttributeConverter<GenderType>
    {
        public Converter()
        {
            super(GenderType.class);
        }
    }
}
