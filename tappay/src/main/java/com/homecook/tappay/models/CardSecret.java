package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardSecret
{
    @JsonProperty("card_token")
    private String cardToken;

    @JsonProperty("card_key")
    private String cardKey;

    public String getCardToken()
    {
        return cardToken;
    }

    public void setCardToken(String cardToken)
    {
        this.cardToken = cardToken;
    }

    public String getCardKey()
    {
        return cardKey;
    }

    public void setCardKey(String cardKey)
    {
        this.cardKey = cardKey;
    }

    @Override
    public String toString()
    {
        return "CardSecret{" +
                "cardToken='" + cardToken + '\'' +
                ", cardKey='" + cardKey + '\'' +
                '}';
    }
}
