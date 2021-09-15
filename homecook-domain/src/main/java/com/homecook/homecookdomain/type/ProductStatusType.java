package com.homecook.homecookdomain.type;

import java.util.Map;

public enum ProductStatusType implements OrdinalType
{
    ACTIVE(1),
    ARCHIVED(2),
    DRAFT(0);

    private int value;

    ProductStatusType(int value)
    {
        this.value = value;
    }

    private static Map map = OrdinalType.getValues(ProductStatusType.class);

    public static ProductStatusType valueOf(int value) {
        ProductStatusType statusType = (ProductStatusType) map.get(value);
        if (statusType == null){
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return statusType;
    }

    public int getValue() {
        return value;
    }
}
