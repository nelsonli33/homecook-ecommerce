package com.homecook.homecookstorefront.error.type;

import com.homecook.homecookentity.type.OrdinalType;

import java.util.Map;

public enum CartLineItemInvalidType implements OrdinalType
{
    PRDOUCT_UNAVAILABLE(1),

    PRODUCT_NO_STOCK(2),

    PRODUCT_LOW_STOCK(3),

    PRODUCT_INFO_HAS_CHANGED(4);

    private int value;

    CartLineItemInvalidType(int value)
    {
        this.value = value;
    }

    private static Map map = OrdinalType.getValues(CartLineItemInvalidType.class);

    public static CartLineItemInvalidType valueOf(int value)
    {
        CartLineItemInvalidType type = (CartLineItemInvalidType) map.get(value);
        if (type == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return type;
    }

    @Override
    public int getValue()
    {
        return value;
    }
}
