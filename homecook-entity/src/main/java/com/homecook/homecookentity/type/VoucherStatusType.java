package com.homecook.homecookentity.type;

import java.util.Map;

public enum VoucherStatusType implements OrdinalType
{
    SCHEDULED(1),

    RUNNING(2),

    ENDED(3);

    private int value;

    VoucherStatusType(int value)
    {
        this.value = value;
    }

    @Override
    public int getValue()
    {
        return value;
    }

    private static Map map = OrdinalType.getValues(VoucherStatusType.class);

    public static VoucherStatusType valueOf(int value)
    {
        VoucherStatusType type = (VoucherStatusType) map.get(value);
        if (type == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return type;
    }
}
