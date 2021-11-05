package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PayByPrimeRequest
{
    @JsonProperty("prime")
    private String prime;

    @JsonProperty("partner_key")
    private String partnerKey;

    @JsonProperty("merchant_id")
    private String merchantId;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("order_number")
    private String orderNumber;

    @JsonProperty("bank_transaction_id")
    private String bankTransactionId;

    @JsonProperty("details")
    private String details;

    @JsonProperty("cardholder")
    private Cardholder cardholder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("instalment")
    private Integer instalment;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("delay_capture_in_days")
    private Integer delayCaptureInDays;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("three_domain_secure")
    private Boolean threeDomainSecure;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("result_url")
    private ResultUrl resultUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("remember")
    private Boolean remember;

    public String getPrime()
    {
        return prime;
    }

    public void setPrime(String prime)
    {
        this.prime = prime;
    }

    public String getPartnerKey()
    {
        return partnerKey;
    }

    public void setPartnerKey(String partnerKey)
    {
        this.partnerKey = partnerKey;
    }

    public String getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(String merchantId)
    {
        this.merchantId = merchantId;
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getBankTransactionId()
    {
        return bankTransactionId;
    }

    public void setBankTransactionId(String bankTransactionId)
    {
        this.bankTransactionId = bankTransactionId;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public Cardholder getCardholder()
    {
        return cardholder;
    }

    public void setCardholder(Cardholder cardholder)
    {
        this.cardholder = cardholder;
    }

    public Integer getInstalment()
    {
        return instalment;
    }

    public void setInstalment(Integer instalment)
    {
        this.instalment = instalment;
    }

    public Integer getDelayCaptureInDays()
    {
        return delayCaptureInDays;
    }

    public void setDelayCaptureInDays(Integer delayCaptureInDays)
    {
        this.delayCaptureInDays = delayCaptureInDays;
    }

    public Boolean getThreeDomainSecure()
    {
        return threeDomainSecure;
    }

    public void setThreeDomainSecure(Boolean threeDomainSecure)
    {
        this.threeDomainSecure = threeDomainSecure;
    }

    public ResultUrl getResultUrl()
    {
        return resultUrl;
    }

    public void setResultUrl(ResultUrl resultUrl)
    {
        this.resultUrl = resultUrl;
    }

    public Boolean getRemember()
    {
        return remember;
    }

    public void setRemember(Boolean remember)
    {
        this.remember = remember;
    }


    @Override
    public String toString()
    {
        return "PayByPrimeRequest{" +
                "prime='" + prime + '\'' +
                ", partnerKey='" + partnerKey + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", bankTransactionId='" + bankTransactionId + '\'' +
                ", details='" + details + '\'' +
                ", cardholder=" + cardholder +
                ", instalment=" + instalment +
                ", delayCaptureInDays=" + delayCaptureInDays +
                ", threeDomainSecure=" + threeDomainSecure +
                ", resultUrl=" + resultUrl +
                ", remember=" + remember +
                '}';
    }


    public static final class Builder
    {
        private String prime;
        private String partnerKey;
        private String merchantId;
        private Integer amount;
        private String currency = "TWD";
        private String orderNumber;
        private String bankTransactionId;
        private String details;
        private Cardholder cardholder;
        private Integer instalment = 0;
        private Integer delayCaptureInDays = 0;
        private Boolean threeDomainSecure;
        private ResultUrl resultUrl;
        private Boolean remember;

        public Builder prime(String prime)
        {
            this.prime = prime;
            return this;
        }

        public Builder partnerKey(String partnerKey)
        {
            this.partnerKey = partnerKey;
            return this;
        }

        public Builder merchantId(String merchantId)
        {
            this.merchantId = merchantId;
            return this;
        }

        public Builder amount(Integer amount)
        {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency)
        {
            this.currency = currency;
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

        public Builder details(String details)
        {
            this.details = details;
            return this;
        }

        public Builder cardholder(Cardholder cardholder)
        {
            this.cardholder = cardholder;
            return this;
        }

        public Builder instalment(Integer instalment)
        {
            this.instalment = instalment;
            return this;
        }

        public Builder delayCaptureInDays(Integer delayCaptureInDays)
        {
            this.delayCaptureInDays = delayCaptureInDays;
            return this;
        }

        public Builder threeDomainSecure(Boolean threeDomainSecure)
        {
            this.threeDomainSecure = threeDomainSecure;
            return this;
        }

        public Builder resultUrl(ResultUrl resultUrl)
        {
            this.resultUrl = resultUrl;
            return this;
        }

        public Builder remember(Boolean remember)
        {
            this.remember = remember;
            return this;
        }

        public PayByPrimeRequest build()
        {
            PayByPrimeRequest payByPrimeRequest = new PayByPrimeRequest();
            payByPrimeRequest.setPrime(prime);
            payByPrimeRequest.setPartnerKey(partnerKey);
            payByPrimeRequest.setMerchantId(merchantId);
            payByPrimeRequest.setAmount(amount);
            payByPrimeRequest.setCurrency(currency);
            payByPrimeRequest.setOrderNumber(orderNumber);
            payByPrimeRequest.setBankTransactionId(bankTransactionId);
            payByPrimeRequest.setDetails(details);
            payByPrimeRequest.setCardholder(cardholder);
            payByPrimeRequest.setInstalment(instalment);
            payByPrimeRequest.setDelayCaptureInDays(delayCaptureInDays);
            payByPrimeRequest.setThreeDomainSecure(threeDomainSecure);
            payByPrimeRequest.setResultUrl(resultUrl);
            payByPrimeRequest.setRemember(remember);
            return payByPrimeRequest;
        }
    }
}
