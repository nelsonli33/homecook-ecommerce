package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractIntegerAttributeConverter;

import java.util.Map;

public enum CarrierType implements OrdinalType
{
    ECPAY_INVOICE(1),

    Citizen_Digital_Certificate(2),

    MOBILE_BARCODE(3);

    private int value;

    CarrierType(int value)
    {
        this.value = value;
    }

    @Override
    public int getValue()
    {
        return value;
    }

    private static Map map = OrdinalType.getValues(CarrierType.class);

    public static CarrierType valueOf(int value)
    {
        CarrierType type = (CarrierType) map.get(value);
        if (type == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return type;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractIntegerAttributeConverter<CarrierType>
    {
        public Converter()
        {
            super(CarrierType.class);
        }
    }
}
