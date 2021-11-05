package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RecordFilters
{
    @JsonProperty("time")
    private RecordFiltersTime time;

    @JsonProperty("amount")
    private RecordFiltersAmount amount;

    @JsonProperty("cardholder")
    private RecordFiltersCardholder cardholder;

    @JsonProperty("merchant_id")
    private List<String> merchantId;

    @JsonProperty("record_status")
    private Integer recordStatus;

    @JsonProperty("rec_trade_id")
    private String recTradeId;

    @JsonProperty("order_number")
    private String orderNumber;

    @JsonProperty("bank_transaction_id")
    private String bankTransactionId;

    @JsonProperty("currency")
    private String currency;

    public RecordFiltersTime getTime()
    {
        return time;
    }

    public RecordFiltersAmount getAmount()
    {
        return amount;
    }

    public RecordFiltersCardholder getCardholder()
    {
        return cardholder;
    }

    public List<String> getMerchantId()
    {
        return merchantId;
    }

    public Integer getRecordStatus()
    {
        return recordStatus;
    }

    public String getRecTradeId()
    {
        return recTradeId;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public String getBankTransactionId()
    {
        return bankTransactionId;
    }

    public String getCurrency()
    {
        return currency;
    }

    @Override
    public String toString()
    {
        return "RecordFilters{" +
                "time=" + time +
                ", amount=" + amount +
                ", cardholder=" + cardholder +
                ", merchantId=" + merchantId +
                ", recordStatus=" + recordStatus +
                ", recTradeId='" + recTradeId + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", bankTransactionId='" + bankTransactionId + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }


    public static final class Builder
    {
        private RecordFiltersTime time;
        private RecordFiltersAmount amount;
        private RecordFiltersCardholder cardholder;
        private List<String> merchantId;
        private Integer recordStatus;
        private String recTradeId;
        private String orderNumber;
        private String bankTransactionId;
        private String currency;

        public Builder time(RecordFiltersTime time)
        {
            this.time = time;
            return this;
        }

        public Builder amount(RecordFiltersAmount amount)
        {
            this.amount = amount;
            return this;
        }

        public Builder cardholder(RecordFiltersCardholder cardholder)
        {
            this.cardholder = cardholder;
            return this;
        }

        public Builder merchantId(List<String> merchantId)
        {
            this.merchantId = merchantId;
            return this;
        }

        public Builder recordStatus(Integer recordStatus)
        {
            this.recordStatus = recordStatus;
            return this;
        }

        public Builder recTradeId(String recTradeId)
        {
            this.recTradeId = recTradeId;
            return this;
        }

        public Builder orderNumber(String orderNumber)
        {
            this.orderNumber = orderNumber;
            return this;
        }

        public Builder bankTransactionId(String bankTransactionId)
        {
            this.bankTransactionId = bankTransactionId;
            return this;
        }

        public Builder currency(String currency)
        {
            this.currency = currency;
            return this;
        }

        public RecordFilters build()
        {
            RecordFilters recordFilters = new RecordFilters();
            recordFilters.bankTransactionId = this.bankTransactionId;
            recordFilters.recordStatus = this.recordStatus;
            recordFilters.amount = this.amount;
            recordFilters.time = this.time;
            recordFilters.recTradeId = this.recTradeId;
            recordFilters.currency = this.currency;
            recordFilters.merchantId = this.merchantId;
            recordFilters.cardholder = this.cardholder;
            recordFilters.orderNumber = this.orderNumber;
            return recordFilters;
        }
    }
}
