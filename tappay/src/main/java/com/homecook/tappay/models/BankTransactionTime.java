package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankTransactionTime
{
    @JsonProperty("start_time_millis")
    private String startTimeMills;

    @JsonProperty("end_time_millis")
    private String endTimeMills;

    public String getStartTimeMills()
    {
        return startTimeMills;
    }

    public void setStartTimeMills(String startTimeMills)
    {
        this.startTimeMills = startTimeMills;
    }

    public String getEndTimeMills()
    {
        return endTimeMills;
    }

    public void setEndTimeMills(String endTimeMills)
    {
        this.endTimeMills = endTimeMills;
    }

    @Override
    public String toString()
    {
        return "BankTransactionTime{" +
                "startTimeMills='" + startTimeMills + '\'' +
                ", endTimeMills='" + endTimeMills + '\'' +
                '}';
    }
}
