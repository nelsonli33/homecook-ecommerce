package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractStringAttributeConverter;

import java.util.Map;

public enum PaymentMethodType implements StringType
{
    CREDIT_CARD_ONCE("CreditCardOnce"),

    CREDIT_CARD_INSTALLMENT("CreditCardInstallment"),

    COD("CashOnDelivery");

    private String code;

    PaymentMethodType(String code)
    {
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return code;
    }

    private static Map map = StringType.getValues(PaymentMethodType.class);

    public static PaymentMethodType fromCode(String code)
    {
        PaymentMethodType statusType = (PaymentMethodType) map.get(code);
        if (statusType == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + code);
        }
        return statusType;
    }


    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringAttributeConverter<PaymentMethodType>
    {
        public Converter()
        {
            super(PaymentMethodType.class);
        }
    }
}
