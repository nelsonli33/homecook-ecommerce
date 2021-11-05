package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayInfo
{
    @JsonProperty("method")
    private String method;

    @JsonProperty("masked_credit_card_number")
    private String maskedCreditCardNumber;

    @JsonProperty("credit_card")
    private int creditCard;

    @JsonProperty("balance")
    private int balance;

    @JsonProperty("bank_account")
    private int bankAccount;

    @JsonProperty("point")
    private int point;

    @JsonProperty("discount")
    private int discount;

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getMaskedCreditCardNumber()
    {
        return maskedCreditCardNumber;
    }

    public void setMaskedCreditCardNumber(String maskedCreditCardNumber)
    {
        this.maskedCreditCardNumber = maskedCreditCardNumber;
    }

    public int getCreditCard()
    {
        return creditCard;
    }

    public void setCreditCard(int creditCard)
    {
        this.creditCard = creditCard;
    }

    public int getBalance()
    {
        return balance;
    }

    public void setBalance(int balance)
    {
        this.balance = balance;
    }

    public int getBankAccount()
    {
        return bankAccount;
    }

    public void setBankAccount(int bankAccount)
    {
        this.bankAccount = bankAccount;
    }

    public int getPoint()
    {
        return point;
    }

    public void setPoint(int point)
    {
        this.point = point;
    }

    public int getDiscount()
    {
        return discount;
    }

    public void setDiscount(int discount)
    {
        this.discount = discount;
    }

    @Override
    public String toString()
    {
        return "PayInfo{" +
                "method='" + method + '\'' +
                ", maskedCreditCardNumber='" + maskedCreditCardNumber + '\'' +
                ", creditCard=" + creditCard +
                ", balance=" + balance +
                ", bankAccount=" + bankAccount +
                ", point=" + point +
                ", discount=" + discount +
                '}';
    }
}
