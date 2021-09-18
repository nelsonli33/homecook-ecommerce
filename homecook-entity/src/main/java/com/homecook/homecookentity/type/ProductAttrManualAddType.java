package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractIntegerAttributeConverter;

import java.util.Map;

public enum ProductAttrManualAddType implements OrdinalType
{
    YES(0),
    NO(1);

    private int value;

    ProductAttrManualAddType(int value)
    {
        this.value = value;
    }

    @Override
    public int getValue()
    {
        return value;
    }

    private static Map map = OrdinalType.getValues(ProductAttrManualAddType.class);

    public static ProductAttrManualAddType valueOf(int value) {
        ProductAttrManualAddType type = (ProductAttrManualAddType) map.get(value);
        if (type == null){
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return type;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractIntegerAttributeConverter<ProductAttrManualAddType>
    {
        public Converter() {
            super(ProductAttrManualAddType.class);
        }
    }
}
