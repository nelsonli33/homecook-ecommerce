package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardInfo
{
    @JsonProperty("bin_code")
    private String binCode;

    @JsonProperty("last_four")
    private String lastFour;

    @JsonProperty("issuer")
    private String issuer;

    @JsonProperty("issuer_zh_tw")
    private String issuerZhTw;

    @JsonProperty("bank_id")
    private String bankId;

    @JsonProperty("funding")
    private Integer funding;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("level")
    private String level;

    @JsonProperty("country")
    private String country;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("expiry_date")
    private String expiryDate;

    public String getBinCode()
    {
        return binCode;
    }

    public void setBinCode(String binCode)
    {
        this.binCode = binCode;
    }

    public String getLastFour()
    {
        return lastFour;
    }

    public void setLastFour(String lastFour)
    {
        this.lastFour = lastFour;
    }

    public String getIssuer()
    {
        return issuer;
    }

    public void setIssuer(String issuer)
    {
        this.issuer = issuer;
    }

    public String getIssuerZhTw()
    {
        return issuerZhTw;
    }

    public void setIssuerZhTw(String issuerZhTw)
    {
        this.issuerZhTw = issuerZhTw;
    }

    public String getBankId()
    {
        return bankId;
    }

    public void setBankId(String bankId)
    {
        this.bankId = bankId;
    }

    public Integer getFunding()
    {
        return funding;
    }

    public void setFunding(Integer funding)
    {
        this.funding = funding;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getExpiryDate()
    {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate)
    {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString()
    {
        return "CardInfo{" +
                "binCode='" + binCode + '\'' +
                ", lastFour='" + lastFour + '\'' +
                ", issuer='" + issuer + '\'' +
                ", issuerZhTw='" + issuerZhTw + '\'' +
                ", bankId='" + bankId + '\'' +
                ", funding=" + funding +
                ", type=" + type +
                ", level='" + level + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }
}
