package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecordOrderBy
{
    @JsonProperty("attribute")
    private String attribute;

    @JsonProperty("is_descending")
    private Boolean isDescending;

    public String getAttribute()
    {
        return attribute;
    }

    public void setAttribute(String attribute)
    {
        this.attribute = attribute;
    }

    public Boolean getDescending()
    {
        return isDescending;
    }

    public void setDescending(Boolean descending)
    {
        isDescending = descending;
    }

    @Override
    public String toString()
    {
        return "RecordOrderBy{" +
                "attribute='" + attribute + '\'' +
                ", isDescending=" + isDescending +
                '}';
    }


    public static final class Builder
    {
        private String attribute;
        private Boolean isDescending;

        public Builder attribute(String attribute)
        {
            this.attribute = attribute;
            return this;
        }

        public Builder isDescending(Boolean isDescending)
        {
            this.isDescending = isDescending;
            return this;
        }

        public RecordOrderBy build()
        {
            RecordOrderBy recordOrderBy = new RecordOrderBy();
            recordOrderBy.setAttribute(attribute);
            recordOrderBy.isDescending = this.isDescending;
            return recordOrderBy;
        }
    }
}
