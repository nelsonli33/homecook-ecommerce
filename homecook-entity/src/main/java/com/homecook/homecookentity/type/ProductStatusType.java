package com.homecook.homecookentity.type;


import com.homecook.homecookentity.entity.converter.AbstractIntegerAttributeConverter;

import java.util.Map;

public enum ProductStatusType implements OrdinalType
{
    DRAFT(0),
    ACTIVE(1),
    ARCHIVED(2);


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

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractIntegerAttributeConverter<ProductStatusType>
    {
        public Converter() {
            super(ProductStatusType.class);
        }
    }
}
