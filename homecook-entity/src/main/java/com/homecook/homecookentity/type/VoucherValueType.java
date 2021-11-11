package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractIntegerAttributeConverter;

import java.util.Map;

public enum VoucherValueType implements OrdinalType
{
    FIX_AMOUNT(1),

    DISCOUNT_PERCENTAGE(2);

    private int value;

    VoucherValueType(int value)
    {
        this.value = value;
    }

    @Override
    public int getValue()
    {
        return value;
    }

    private static Map map = OrdinalType.getValues(VoucherValueType.class);

    public static VoucherValueType valueOf(int value)
    {
        VoucherValueType type = (VoucherValueType) map.get(value);
        if (type == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return type;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractIntegerAttributeConverter<VoucherValueType>
    {
        public Converter()
        {
            super(VoucherValueType.class);
        }
    }
}
