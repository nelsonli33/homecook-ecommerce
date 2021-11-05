package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PayByPrimeResponse
{
    @JsonProperty("status")
    private int status;

    @JsonProperty("msg")
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("rec_trade_id")
    private String recTradeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("bank_transaction_id")
    private String bankTransactionId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("bank_order_number")
    private String bankOrderNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("auth_code")
    private String authCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("card_secret")
    private CardSecret cardSecret;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("amount")
    private Integer amount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("currency")
    private String currency;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("card_info")
    private CardInfo cardInfo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("order_number")
    private String orderNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("acquirer")
    private String acquirer;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("transaction_time_millis")
    private long transactionTimeMillis;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("bank_transaction_time")
    private BankTransactionTime bankTransactionTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("bank_result_code")
    private String bankResultCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("bank_result_msg")
    private String bankResultMsg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("payment_url")
    private String paymentUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("card_identifier")
    private String cardIdentifier;


    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getRecTradeId()
    {
        return recTradeId;
    }

    public void setRecTradeId(String recTradeId)
    {
        this.recTradeId = recTradeId;
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

    public String getAuthCode()
    {
        return authCode;
    }

    public void setAuthCode(String authCode)
    {
        this.authCode = authCode;
    }

    public CardSecret getCardSecret()
    {
        return cardSecret;
    }

    public void setCardSecret(CardSecret cardSecret)
    {
        this.cardSecret = cardSecret;
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

    public CardInfo getCardInfo()
    {
        return cardInfo;
    }

    public void setCardInfo(CardInfo cardInfo)
    {
        this.cardInfo = cardInfo;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getAcquirer()
    {
        return acquirer;
    }

    public void setAcquirer(String acquirer)
    {
        this.acquirer = acquirer;
    }

    public long getTransactionTimeMillis()
    {
        return transactionTimeMillis;
    }

    public void setTransactionTimeMillis(long transactionTimeMillis)
    {
        this.transactionTimeMillis = transactionTimeMillis;
    }

    public BankTransactionTime getBankTransactionTime()
    {
        return bankTransactionTime;
    }

    public void setBankTransactionTime(BankTransactionTime bankTransactionTime)
    {
        this.bankTransactionTime = bankTransactionTime;
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

    public String getPaymentUrl()
    {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl)
    {
        this.paymentUrl = paymentUrl;
    }

    public String getCardIdentifier()
    {
        return cardIdentifier;
    }

    public void setCardIdentifier(String cardIdentifier)
    {
        this.cardIdentifier = cardIdentifier;
    }

    @Override
    public String toString()
    {
        return "PayByPrimeResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", recTradeId='" + recTradeId + '\'' +
                ", bankTransactionId='" + bankTransactionId + '\'' +
                ", bankOrderNumber='" + bankOrderNumber + '\'' +
                ", authCode='" + authCode + '\'' +
                ", cardSecret=" + cardSecret +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", cardInfo=" + cardInfo +
                ", orderNumber='" + orderNumber + '\'' +
                ", acquirer='" + acquirer + '\'' +
                ", transactionTimeMillis=" + transactionTimeMillis +
                ", bankTransactionTime=" + bankTransactionTime +
                ", bankResultCode='" + bankResultCode + '\'' +
                ", bankResultMsg='" + bankResultMsg + '\'' +
                ", paymentUrl='" + paymentUrl + '\'' +
                ", cardIdentifier='" + cardIdentifier + '\'' +
                '}';
    }
}
