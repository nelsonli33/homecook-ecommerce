package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradeRecord
{
    @JsonProperty("rec_trade_id")
    private String recTradeId;

    @JsonProperty("auth_code")
    private String authCode;

    @JsonProperty("merchant_id")
    private String merchantId;

    @JsonProperty("merchant_name")
    private String merchantName;

    @JsonProperty("app_name")
    private String appName;

    @JsonProperty("time")
    private Long time;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("refunded_amount")
    private Integer refundedAmount;

    @JsonProperty("record_status")
    private Integer recordStatus;

    @JsonProperty("bank_transaction_id")
    private String bankTransactionId;

    @JsonProperty("bank_order_number")
    private String bankOrderNumber;

    // 交易會被請款的時間
    @JsonProperty("cap_millis")
    private Long capMillis;

    @JsonProperty("original_amount")
    private Integer originalAmount;

    @JsonProperty("bank_transaction_start_millis")
    private Long bankTransactionStartMillis;

    @JsonProperty("bank_transaction_end_millis")
    private Long bankTransactionEndMillis;

    @JsonProperty("is_captured")
    private Boolean isCaptured;

    @JsonProperty("bank_result_code")
    private String bankResultCode;

    @JsonProperty("bank_result_msg")
    private String bankResultMsg;

    @JsonProperty("partial_card_number")
    private String partialCardNumber;

    @JsonProperty("payment_method")
    private String paymentMethod;

    @JsonProperty("details")
    private String details;

    @JsonProperty("cardholder")
    private Cardholder cardholder;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("three_domain_secure")
    private Boolean threeDomainSecure;

    @JsonProperty("pay_by_instalment")
    private Boolean payByInstalment;

    @JsonProperty("instalment_info")
    private InstalmentInfo instalmentInfo;

    @JsonProperty("order_number")
    private String orderNumber;

    @JsonProperty("pay_info")
    private PayInfo payInfo;

    @JsonProperty("card_identifier")
    private String cardIdentifier;

    @JsonProperty("card_info")
    private CardInfo cardInfo;

    public String getRecTradeId()
    {
        return recTradeId;
    }

    public void setRecTradeId(String recTradeId)
    {
        this.recTradeId = recTradeId;
    }

    public String getAuthCode()
    {
        return authCode;
    }

    public void setAuthCode(String authCode)
    {
        this.authCode = authCode;
    }

    public String getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(String merchantId)
    {
        this.merchantId = merchantId;
    }

    public String getMerchantName()
    {
        return merchantName;
    }

    public void setMerchantName(String merchantName)
    {
        this.merchantName = merchantName;
    }

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public Long getTime()
    {
        return time;
    }

    public void setTime(Long time)
    {
        this.time = time;
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

    public Integer getRefundedAmount()
    {
        return refundedAmount;
    }

    public void setRefundedAmount(Integer refundedAmount)
    {
        this.refundedAmount = refundedAmount;
    }

    public Integer getRecordStatus()
    {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus)
    {
        this.recordStatus = recordStatus;
    }

    public String getBankTransactionId()
    {
        return bankTransactionId;
    }

    public void setBankTransactionId(String bankTransactionId)
    {
        this.bankTransactionId = bankTransactionId;
    }

    public String getBankOrderNumber()
    {
        return bankOrderNumber;
    }

    public void setBankOrderNumber(String bankOrderNumber)
    {
        this.bankOrderNumber = bankOrderNumber;
    }

    public Long getCapMillis()
    {
        return capMillis;
    }

    public void setCapMillis(Long capMillis)
    {
        this.capMillis = capMillis;
    }

    public Integer getOriginalAmount()
    {
        return originalAmount;
    }

    public void setOriginalAmount(Integer originalAmount)
    {
        this.originalAmount = originalAmount;
    }

    public Long getBankTransactionStartMillis()
    {
        return bankTransactionStartMillis;
    }

    public void setBankTransactionStartMillis(Long bankTransactionStartMillis)
    {
        this.bankTransactionStartMillis = bankTransactionStartMillis;
    }

    public Long getBankTransactionEndMillis()
    {
        return bankTransactionEndMillis;
    }

    public void setBankTransactionEndMillis(Long bankTransactionEndMillis)
    {
        this.bankTransactionEndMillis = bankTransactionEndMillis;
    }

    public Boolean getCaptured()
    {
        return isCaptured;
    }

    public void setCaptured(Boolean captured)
    {
        isCaptured = captured;
    }

    public String getBankResultCode()
    {
        return bankResultCode;
    }

    public void setBankResultCode(String bankResultCode)
    {
        this.bankResultCode = bankResultCode;
    }

    public String getBankResultMsg()
    {
        return bankResultMsg;
    }

    public void setBankResultMsg(String bankResultMsg)
    {
        this.bankResultMsg = bankResultMsg;
    }

    public String getPartialCardNumber()
    {
        return partialCardNumber;
    }

    public void setPartialCardNumber(String partialCardNumber)
    {
        this.partialCardNumber = partialCardNumber;
    }

    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
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

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public Boolean getThreeDomainSecure()
    {
        return threeDomainSecure;
    }

    public void setThreeDomainSecure(Boolean threeDomainSecure)
    {
        this.threeDomainSecure = threeDomainSecure;
    }

    public Boolean getPayByInstalment()
    {
        return payByInstalment;
    }

    public void setPayByInstalment(Boolean payByInstalment)
    {
        this.payByInstalment = payByInstalment;
    }

    public InstalmentInfo getInstalmentInfo()
    {
        return instalmentInfo;
    }

    public void setInstalmentInfo(InstalmentInfo instalmentInfo)
    {
        this.instalmentInfo = instalmentInfo;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public PayInfo getPayInfo()
    {
        return payInfo;
    }

    public void setPayInfo(PayInfo payInfo)
    {
        this.payInfo = payInfo;
    }

    public String getCardIdentifier()
    {
        return cardIdentifier;
    }

    public void setCardIdentifier(String cardIdentifier)
    {
        this.cardIdentifier = cardIdentifier;
    }

    public CardInfo getCardInfo()
    {
        return cardInfo;
    }

    public void setCardInfo(CardInfo cardInfo)
    {
        this.cardInfo = cardInfo;
    }

    @Override
    public String toString()
    {
        return "TradeRecord{" +
                "recTradeId='" + recTradeId + '\'' +
                ", authCode='" + authCode + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", appName='" + appName + '\'' +
                ", time=" + time +
                ", amount=" + amount +
                ", refundedAmount=" + refundedAmount +
                ", recordStatus=" + recordStatus +
                ", bankTransactionId='" + bankTransactionId + '\'' +
                ", bankOrderNumber='" + bankOrderNumber + '\'' +
                ", capMillis=" + capMillis +
                ", originalAmount=" + originalAmount +
                ", bankTransactionStartMillis=" + bankTransactionStartMillis +
                ", bankTransactionEndMillis=" + bankTransactionEndMillis +
                ", isCaptured=" + isCaptured +
                ", bankResultCode='" + bankResultCode + '\'' +
                ", bankResultMsg='" + bankResultMsg + '\'' +
                ", partialCardNumber='" + partialCardNumber + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", details='" + details + '\'' +
                ", cardholder=" + cardholder +
                ", currency='" + currency + '\'' +
                ", threeDomainSecure=" + threeDomainSecure +
                ", payByInstalment=" + payByInstalment +
                ", instalmentInfo=" + instalmentInfo +
                ", orderNumber='" + orderNumber + '\'' +
                ", payInfo=" + payInfo +
                ", cardIdentifier='" + cardIdentifier + '\'' +
                ", cardInfo=" + cardInfo +
                '}';
    }
}
