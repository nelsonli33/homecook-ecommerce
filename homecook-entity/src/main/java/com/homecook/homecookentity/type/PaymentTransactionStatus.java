package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractStringAttributeConverter;

import java.util.Map;

public enum PaymentTransactionStatus implements StringType
{
    PENDING("PENDING"),

    SUCCESS("SUCCESS"),

    FAILURE("FAILURE"),

    ERROR("ERROR");

    private final String code;

    PaymentTransactionStatus(final String code)
    {
        this.code = code.intern();
    }

    @Override
    public String getCode()
    {
        return this.code;
    }

    private static Map map = StringType.getValues(PaymentTransactionStatus.class);

    public static PaymentTransactionStatus fromCode(String code)
    {
        PaymentTransactionStatus type = (PaymentTransactionStatus) map.get(code);
        if (type == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + code);
        }
        return type;
    }


    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringAttributeConverter<PaymentTransactionStatus>
    {
        public Converter()
        {
            super(PaymentTransactionStatus.class);
        }
    }

}
