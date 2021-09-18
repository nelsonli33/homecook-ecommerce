package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractIntegerAttributeConverter;

import java.util.Map;

public enum ProductAttrInputType implements OrdinalType
{
    MANUAL_INPUT(0),
    SELECT_FROM_LIST(1);

    private int value;

    ProductAttrInputType(int value)
    {
        this.value = value;
    }

    @Override
    public int getValue()
    {
        return value;
    }

    private static Map map = OrdinalType.getValues(ProductAttrInputType.class);

    public static ProductAttrInputType valueOf(int value) {
        ProductAttrInputType type = (ProductAttrInputType) map.get(value);
        if (type == null){
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return type;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractIntegerAttributeConverter<ProductAttrInputType>
    {
        public Converter() {
            super(ProductAttrInputType.class);
        }
    }
}
