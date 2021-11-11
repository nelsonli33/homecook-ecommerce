package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractIntegerAttributeConverter;

import java.util.Map;

public enum VoucherUseStatus implements OrdinalType
{
    UNUSED(0),

    USED(1),

    EXPIRED(2);

    private int value;

    VoucherUseStatus(int value)
    {
        this.value = value;
    }

    @Override
    public int getValue()
    {
        return value;
    }

    private static Map map = OrdinalType.getValues(VoucherUseStatus.class);

    public static VoucherUseStatus valueOf(int value)
    {
        VoucherUseStatus type = (VoucherUseStatus) map.get(value);
        if (type == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return type;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractIntegerAttributeConverter<VoucherUseStatus>
    {
        public Converter()
        {
            super(VoucherUseStatus.class);
        }
    }
}
