package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecordFiltersAmount
{
    @JsonProperty("upperlimit")
    private Integer upperLimit;

    @JsonProperty("lowerlimit")
    private Integer lowerLimit;

    public Integer getUpperLimit()
    {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit)
    {
        this.upperLimit = upperLimit;
    }

    public Integer getLowerLimit()
    {
        return lowerLimit;
    }

    public void setLowerLimit(Integer lowerLimit)
    {
        this.lowerLimit = lowerLimit;
    }

    @Override
    public String toString()
    {
        return "RecordFiltersAmount{" +
                "upperLimit=" + upperLimit +
                ", lowerLimit=" + lowerLimit +
                '}';
    }


    public static final class Builder
    {
        private Integer upperLimit;
        private Integer lowerLimit;

        public Builder upperLimit(Integer upperLimit)
        {
            this.upperLimit = upperLimit;
            return this;
        }

        public Builder lowerLimit(Integer lowerLimit)
        {
            this.lowerLimit = lowerLimit;
            return this;
        }

        public RecordFiltersAmount build()
        {
            RecordFiltersAmount recordFiltersAmount = new RecordFiltersAmount();
            recordFiltersAmount.setUpperLimit(upperLimit);
            recordFiltersAmount.setLowerLimit(lowerLimit);
            return recordFiltersAmount;
        }
    }
}
