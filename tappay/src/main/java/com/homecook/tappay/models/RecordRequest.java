package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.homecook.tappay.exception.ArgumentsValidException;

public class RecordRequest
{
    @JsonProperty("partner_key")
    private String partnerKey;

    @JsonProperty("records_per_page")
    private Integer recordsPerPage;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("filters")
    private RecordFilters filters;

    @JsonProperty("order_by")
    private RecordOrderBy orderBy;

    public String getPartnerKey()
    {
        return partnerKey;
    }

    public void setPartnerKey(String partnerKey)
    {
        this.partnerKey = partnerKey;
    }

    public Integer getRecordsPerPage()
    {
        return recordsPerPage;
    }

    public void setRecordsPerPage(Integer recordsPerPage)
    {
        this.recordsPerPage = recordsPerPage;
    }

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public RecordFilters getFilters()
    {
        return filters;
    }

    public void setFilters(RecordFilters filters)
    {
        this.filters = filters;
    }

    public RecordOrderBy getOrderBy()
    {
        return orderBy;
    }

    public void setOrderBy(RecordOrderBy orderBy)
    {
        this.orderBy = orderBy;
    }

    @Override
    public String toString()
    {
        return "RecordRequest{" +
                "partnerKey='" + partnerKey + '\'' +
                ", recordsPerPage=" + recordsPerPage +
                ", page=" + page +
                ", filters=" + filters +
                ", orderBy=" + orderBy +
                '}';
    }

    public static class Builder
    {
        private String partnerKey;
        private Integer recordsPerPage = 50;
        private Integer page = 0;
        private RecordFilters filters;
        private RecordOrderBy orderBy;

        public Builder partnerKey(String partnerKey)
        {
            this.partnerKey = partnerKey;
            return this;
        }

        public Builder recordsPerPage(Integer recordsPerPage)
        {
            this.recordsPerPage = recordsPerPage;
            return this;
        }

        public Builder page(Integer page)
        {
            this.page = page;
            return this;
        }

        public Builder filters(RecordFilters filters)
        {
            this.filters = filters;
            return this;
        }

        public Builder orderBy(RecordOrderBy orderBy)
        {
            this.orderBy = orderBy;
            return this;
        }

        public RecordRequest build() throws ArgumentsValidException
        {
            if (filters != null)
            {
                if (filters.getAmount() != null
                        && (filters.getAmount().getLowerLimit() > filters.getAmount().getUpperLimit()))
                {
                    throw new ArgumentsValidException("filters > amount > lower_limit can't more than the upper_limit");
                }

                if (filters.getTime() != null
                        && (filters.getTime().getStartTimeMills() > filters.getTime().getEndTimeMills()))
                {
                    throw new ArgumentsValidException("filters > time > start_time can't more than the end_time");
                }
            }

            if (page < 0)
            {
                throw new ArgumentsValidException("page out of range");
            }

            RecordRequest recordRequest = new RecordRequest();
            recordRequest.setPartnerKey(partnerKey);
            recordRequest.setRecordsPerPage(recordsPerPage);
            recordRequest.setPage(page);
            recordRequest.setFilters(filters);
            recordRequest.setOrderBy(orderBy);
            return recordRequest;
        }
    }
}
