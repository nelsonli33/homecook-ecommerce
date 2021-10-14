package ecpay.invoice.integration.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryAllowanceInvalidResultObj
{
    @JsonProperty("RtnCode")
    private int rtnCode;

    @JsonProperty("RtnMsg")
    private String rtnMsg;
    /**
     * AI_Allow_Date
     * 折讓單日期
     */
    @JsonProperty("AI_Allow_Date")
    private String allowDate;

    @JsonProperty("AI_Allow_No")
    private String allowNo;

    /**
     * 0000000000 代表沒有統編
     */
    @JsonProperty("AI_Buyer_Identifier")
    private String buyerIdentifier;

    @JsonProperty("AI_Date")
    private String date;

    @JsonProperty("AI_Invoice_No")
    private String invoiceNo;

    /**
     * AI_Mer_ID
     * 特店代號
     */
    @JsonProperty("AI_Mer_ID")
    private String merID;

    @JsonProperty("Reason")
    private String reason;

    /**
     * AI_Seller_Identifier
     * 賣方統編
     */
    @JsonProperty("AI_Seller_Identifier")
    private String sellerIdentifier;

    @JsonProperty("AI_Upload_Date")
    private String uploadDate;

    @JsonProperty("AI_Upload_Status")
    private String uploadStatus;

    @JsonProperty("CheckMacValue")
    private String checkMacValue;

    public int getRtnCode()
    {
        return rtnCode;
    }

    public void setRtnCode(int rtnCode)
    {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg()
    {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg)
    {
        this.rtnMsg = rtnMsg;
    }

    public String getAllowDate()
    {
        return allowDate;
    }

    public void setAllowDate(String allowDate)
    {
        this.allowDate = allowDate;
    }

    public String getAllowNo()
    {
        return allowNo;
    }

    public void setAllowNo(String allowNo)
    {
        this.allowNo = allowNo;
    }

    public String getBuyerIdentifier()
    {
        return buyerIdentifier;
    }

    public void setBuyerIdentifier(String buyerIdentifier)
    {
        this.buyerIdentifier = buyerIdentifier;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getInvoiceNo()
    {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo)
    {
        this.invoiceNo = invoiceNo;
    }

    public String getMerID()
    {
        return merID;
    }

    public void setMerID(String merID)
    {
        this.merID = merID;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getSellerIdentifier()
    {
        return sellerIdentifier;
    }

    public void setSellerIdentifier(String sellerIdentifier)
    {
        this.sellerIdentifier = sellerIdentifier;
    }

    public String getUploadDate()
    {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate)
    {
        this.uploadDate = uploadDate;
    }

    public String getUploadStatus()
    {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus)
    {
        this.uploadStatus = uploadStatus;
    }

    public String getCheckMacValue()
    {
        return checkMacValue;
    }

    public void setCheckMacValue(String checkMacValue)
    {
        this.checkMacValue = checkMacValue;
    }

    @Override
    public String toString()
    {
        return "QueryAllowanceInvalidResultObj{" +
                "rtnCode=" + rtnCode +
                ", rtnMsg='" + rtnMsg + '\'' +
                ", allowDate='" + allowDate + '\'' +
                ", allowNo='" + allowNo + '\'' +
                ", buyerIdentifier='" + buyerIdentifier + '\'' +
                ", date='" + date + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", merID='" + merID + '\'' +
                ", reason='" + reason + '\'' +
                ", sellerIdentifier='" + sellerIdentifier + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", uploadStatus='" + uploadStatus + '\'' +
                ", checkMacValue='" + checkMacValue + '\'' +
                '}';
    }
}