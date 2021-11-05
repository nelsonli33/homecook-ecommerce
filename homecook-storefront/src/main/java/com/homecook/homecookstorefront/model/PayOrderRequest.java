package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayOrderRequest
{
    @JsonProperty("prime")
    private String prime;

    public String getPrime()
    {
        return prime;
    }

    public void setPrime(String prime)
    {
        this.prime = prime;
    }
}
