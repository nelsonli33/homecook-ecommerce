package com.homecook.homecookentity.type;


import com.homecook.homecookentity.entity.converter.AbstractIntegerAttributeConverter;

import java.util.Map;

public enum ProductAttrSelectType implements OrdinalType
{
    RADIO(0),
    CHECKBOX(1);


    private int value;

    ProductAttrSelectType(int value)
    {
        this.value = value;
    }

    @Override
    public int getValue()
    {
        return value;
    }

    private static Map map = OrdinalType.getValues(ProductAttrSelectType.class);

    public static ProductAttrSelectType valueOf(int value) {
        ProductAttrSelectType type = (ProductAttrSelectType) map.get(value);
        if (type == null){
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return type;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractIntegerAttributeConverter<ProductAttrSelectType>
    {
        public Converter() {
            super(ProductAttrSelectType.class);
        }
    }
}
