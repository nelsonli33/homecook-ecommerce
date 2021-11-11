package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractIntegerAttributeConverter;

import java.util.Map;

public enum VoucherUseType implements OrdinalType
{
    ALL_PRODUCTS(1),

    SPECIFIED_CATEGORY(2),

    SPECIFIED_PRODUCT(3);
    // 指定的商品分類
    private int value;

    VoucherUseType(int value)
    {
        this.value = value;
    }

    @Override
    public int getValue()
    {
        return value;
    }

    private static Map map = OrdinalType.getValues(VoucherUseType.class);

    public static VoucherUseType valueOf(int value)
    {
        VoucherUseType type = (VoucherUseType) map.get(value);
        if (type == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return type;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractIntegerAttributeConverter<VoucherUseType>
    {
        public Converter()
        {
            super(VoucherUseType.class);
        }
    }
}
