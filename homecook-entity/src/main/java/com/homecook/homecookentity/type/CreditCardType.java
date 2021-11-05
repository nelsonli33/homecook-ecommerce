package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractStringAttributeConverter;

import java.util.Map;

public enum CreditCardType implements StringType
{
    AMEX("AMEX"),

    UNION_PAY("UnionPay"),

    VISA("VISA"),

    MASTER_CARD("MasterCard"),

    JCB("JCB");

    private String code;

    CreditCardType(final String code)
    {
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return this.code;
    }


    private static Map map = StringType.getValues(CreditCardType.class);

    public static CreditCardType fromCode(String code)
    {
        CreditCardType type = (CreditCardType) map.get(code);
        if (type == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + code);
        }
        return type;
    }


    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringAttributeConverter<CreditCardType>
    {
        public Converter()
        {
            super(CreditCardType.class);
        }
    }
}
