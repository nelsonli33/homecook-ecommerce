package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RecordResponse
{
    @JsonProperty("status")
    private int status;

    @JsonProperty("msg")
    private String message;

    @JsonProperty("records_per_page")
    private Integer recordsPerPage;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("total_page_count")
    private Integer totalPageCount;

    @JsonProperty("number_of_transactions")
    private Long numberOfTransactions;

    @JsonProperty("trade_records")
    private List<TradeRecord> tradeRecords;

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
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

    public Integer getTotalPageCount()
    {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount)
    {
        this.totalPageCount = totalPageCount;
    }

    public Long getNumberOfTransactions()
    {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(Long numberOfTransactions)
    {
        this.numberOfTransactions = numberOfTransactions;
    }

    public List<TradeRecord> getTradeRecords()
    {
        return tradeRecords;
    }

    public void setTradeRecords(List<TradeRecord> tradeRecords)
    {
        this.tradeRecords = tradeRecords;
    }

    @Override
    public String toString()
    {
        return "RecordResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", recordsPerPage=" + recordsPerPage +
                ", page=" + page +
                ", totalPageCount=" + totalPageCount +
                ", numberOfTransactions=" + numberOfTransactions +
                ", tradeRecords=" + tradeRecords +
                '}';
    }
}
