package com.homecook.homecookentity.type;

import com.homecook.homecookentity.entity.converter.AbstractStringAttributeConverter;

import java.util.Map;

public enum PaymentStatusType implements StringType
{
    UNPAID("UNPAID"),

    PAID_OVER_TIME("PAID_OVER_TIME"), // 超過付款時間

    PAID("PAID"),

    REFUNDED("REFUNDED"),

    VOIDED("VOIDED"); // 已作廢

    private String code;

    PaymentStatusType(String code)
    {
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return code;
    }

    private static Map map = StringType.getValues(PaymentStatusType.class);

    public static PaymentStatusType fromCode(String code)
    {
        PaymentStatusType statusType = (PaymentStatusType) map.get(code);
        if (statusType == null)
        {
            throw new IllegalArgumentException("Not Enum constant was found for value : " + code);
        }
        return statusType;
    }


    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringAttributeConverter<PaymentStatusType>
    {
        public Converter()
        {
            super(PaymentStatusType.class);
        }
    }
}
