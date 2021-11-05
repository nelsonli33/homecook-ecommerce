package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InstalmentInfo
{
    @JsonProperty("number_of_instalments")
    private int numberOfInstalments;

    @JsonProperty("first_payment")
    private int firstPayment;

    @JsonProperty("each_payment")
    private int eachPayment;

    public int getNumberOfInstalments()
    {
        return numberOfInstalments;
    }

    public void setNumberOfInstalments(int numberOfInstalments)
    {
        this.numberOfInstalments = numberOfInstalments;
    }

    public int getFirstPayment()
    {
        return firstPayment;
    }

    public void setFirstPayment(int firstPayment)
    {
        this.firstPayment = firstPayment;
    }

    public int getEachPayment()
    {
        return eachPayment;
    }

    public void setEachPayment(int eachPayment)
    {
        this.eachPayment = eachPayment;
    }

    @Override
    public String toString()
    {
        return "InstalmentInfo{" +
                "numberOfInstalments=" + numberOfInstalments +
                ", firstPayment=" + firstPayment +
                ", eachPayment=" + eachPayment +
                '}';
    }
}
