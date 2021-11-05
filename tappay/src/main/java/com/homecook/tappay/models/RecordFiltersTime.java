package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecordFiltersTime
{
    @JsonProperty("start_time")
    private Long startTimeMills;

    @JsonProperty("end_time")
    private Long endTimeMills;

    public Long getStartTimeMills()
    {
        return startTimeMills;
    }

    public void setStartTimeMills(Long startTimeMills)
    {
        this.startTimeMills = startTimeMills;
    }

    public Long getEndTimeMills()
    {
        return endTimeMills;
    }

    public void setEndTimeMills(Long endTimeMills)
    {
        this.endTimeMills = endTimeMills;
    }

    @Override
    public String toString()
    {
        return "RecordFiltersTime{" +
                "startTimeMills=" + startTimeMills +
                ", endTimeMills=" + endTimeMills +
                '}';
    }


    public static final class Builder
    {
        private Long startTimeMills;
        private Long endTimeMills;

        public Builder startTimeMills(Long startTimeMills)
        {
            this.startTimeMills = startTimeMills;
            return this;
        }

        public Builder endTimeMills(Long endTimeMills)
        {
            this.endTimeMills = endTimeMills;
            return this;
        }

        public RecordFiltersTime build()
        {
            RecordFiltersTime recordFiltersTime = new RecordFiltersTime();
            recordFiltersTime.setStartTimeMills(startTimeMills);
            recordFiltersTime.setEndTimeMills(endTimeMills);
            return recordFiltersTime;
        }
    }
}
