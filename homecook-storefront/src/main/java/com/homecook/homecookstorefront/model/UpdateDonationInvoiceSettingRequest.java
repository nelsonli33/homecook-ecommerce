package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateDonationInvoiceSettingRequest
{
    @JsonProperty("charityLovecode")
    private String charityLovecode;

    @JsonProperty("charityName")
    private String charityName;

    public String getCharityLovecode()
    {
        return charityLovecode;
    }

    public void setCharityLovecode(String charityLovecode)
    {
        this.charityLovecode = charityLovecode;
    }

    public String getCharityName()
    {
        return charityName;
    }

    public void setCharityName(String charityName)
    {
        this.charityName = charityName;
    }
}
