package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BackendNotifyRequest
{
    @JsonProperty("status")
    private int status;

    @JsonProperty("msg")
    private String message;

    @JsonProperty("rec_trade_id")
    private String recTradeId;

    @JsonProperty("auth_code")
    private String authCode;

    @JsonProperty("bank_transaction_id")
    private String bankTransactionId;

    @JsonProperty("bank_order_number")
    private String bankOrderNumber;

    @JsonProperty("orderNumber")
    private String orderNumber;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("transaction_time_millis")
    private long transactionTimeMillis;

    @JsonProperty("pay_info")
    private PayInfo payInfo;

    @JsonProperty("acquirer")
    private String acquirer;

    @JsonProperty("card_identifier")
    private String cardIdentifier;

    @JsonProperty("bank_result_code")
    private String bankResultCode;

    @JsonProperty("bank_result_msg")
    private String bankResultMsg;

    @JsonProperty("instalment_info")
    private InstalmentInfo instalmentInfo;


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

    public String getAuthCode()
    {
        return authCode;
    }

    public void setAuthCode(String authCode)
    {
        this.authCode = authCode;
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

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public long getTransactionTimeMillis()
    {
        return transactionTimeMillis;
    }

    public void setTransactionTimeMillis(long transactionTimeMillis)
    {
        this.transactionTimeMillis = transactionTimeMillis;
    }

    public PayInfo getPayInfo()
    {
        return payInfo;
    }

    public void setPayInfo(PayInfo payInfo)
    {
        this.payInfo = payInfo;
    }

    public String getAcquirer()
    {
        return acquirer;
    }

    public void setAcquirer(String acquirer)
    {
        this.acquirer = acquirer;
    }

    public String getCardIdentifier()
    {
        return cardIdentifier;
    }

    public void setCardIdentifier(String cardIdentifier)
    {
        this.cardIdentifier = cardIdentifier;
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

    public InstalmentInfo getInstalmentInfo()
    {
        return instalmentInfo;
    }

    public void setInstalmentInfo(InstalmentInfo instalmentInfo)
    {
        this.instalmentInfo = instalmentInfo;
    }

    @Override
    public String toString()
    {
        return "BackendNotifyRequest{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", recTradeId='" + recTradeId + '\'' +
                ", authCode='" + authCode + '\'' +
                ", bankTransactionId='" + bankTransactionId + '\'' +
                ", bankOrderNumber='" + bankOrderNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", amount=" + amount +
                ", transactionTimeMillis=" + transactionTimeMillis +
                ", payInfo=" + payInfo +
                ", acquirer='" + acquirer + '\'' +
                ", cardIdentifier='" + cardIdentifier + '\'' +
                ", bankResultCode='" + bankResultCode + '\'' +
                ", bankResultMsg='" + bankResultMsg + '\'' +
                ", instalmentInfo=" + instalmentInfo +
                '}';
    }
}
