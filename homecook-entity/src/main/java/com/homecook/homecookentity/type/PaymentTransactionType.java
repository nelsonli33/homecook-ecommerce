package com.homecook.homecookentity.type;


import com.homecook.homecookentity.entity.converter.AbstractStringAttributeConverter;

import java.util.Map;

public enum PaymentTransactionType implements StringType
{
    /**
     * Money that the customer has agreed to pay. The authorization period can be between 7 and 30 days (depending on your payment service)
     * while a store waits for a payment to be captured.
     */
    AUTHORIZATION("AUTHORIZATION"),

    /**
     * A transfer of money that was reserved during the authorization of a shop.
     */
    CAPTURE("CAPTURE"),

    /**
     * The authorization and capture of a payment performed in one single step.
     */
    SALE("SALE"),
    /**
     * The partial or full return of captured money to the customer.
     */
    REFUND("REFUND"),

    /**
     * The cancellation of a pending authorization or capture.
     */
    VOID("VOID");

    private final String code;

    PaymentTransactionType(final String code)
    {
        this.code = code.intern();
    }

    @Override
    public String getCode()
    {
        return this.code;
    }

    private static Map map = StringType.getValues(PaymentTransactionType.class);

    public static PaymentTransactionType fromCode(String code)
    {
        PaymentTransactionType type = (PaymentTransactionType) map.get(code);
        if (type == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + code);
        }
        return type;
    }


    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringAttributeConverter<PaymentTransactionType>
    {
        public Converter()
        {
            super(PaymentTransactionType.class);
        }
    }

}
