package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractStringAttributeConverter;

import java.util.Map;

public enum OrderStatusType implements StringType
{
    UNPAID("UNPAID"),

    READY_TO_SHIP("READY_TO_SHIP"),

    SHIPPED("SHIPPED"),

    PICKED_UP("PICKED_UP"),

    CANCELLED("CANCELLED"),

    TO_RETURN("TO_RETURN"),

    COMPLETED("COMPLETED"),

    CLOSE("CLOSE"),

    PROCESS_ERROR("PROCESS_ERROR");

    private String code;

    OrderStatusType(String code)
    {
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return code;
    }

    private static Map map = StringType.getValues(OrderStatusType.class);

    public static OrderStatusType fromCode(String code)
    {
        OrderStatusType statusType = (OrderStatusType) map.get(code);
        if (statusType == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + code);
        }
        return statusType;
    }


    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringAttributeConverter<OrderStatusType>
    {
        public Converter()
        {
            super(OrderStatusType.class);
        }
    }
}
