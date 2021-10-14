package ecpay.invoice.integration.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryIssueInvalidResultObj
{
    @JsonProperty("RtnCode")
    private int rtnCode;

    @JsonProperty("RtnMsg")
    private String rtnMsg;

    /**
     * 買方統編
     * 0000000000: 代表沒有統編
     */
    @JsonProperty("II_Buyer_Identifier")
    private String buyerIdentifier;

    /**
     * II_Seller_Identifier
     * 賣方統編
     */
    @JsonProperty("II_Seller_Identifier")
    private String sellerIdentifier;
    /**
     * II_Date
     * 作廢時間
     */
    @JsonProperty("II_Date")
    private String date;

    /**
     * II_Invoice_No
     * 發票號碼
     */
    @JsonProperty("II_Invoice_No")
    private String invoiceNo;

    /**
     * II_Mer_ID
     * 特店編號
     */
    @JsonProperty("II_Mer_ID")
    private String merID;

    /**
     * II_Upload_Date
     * 上傳時間
     */
    @JsonProperty("II_Upload_Date")
    private String uploadDate;

    /**
     * II_Upload_Status
     * 上傳狀態
     */
    @JsonProperty("II_Upload_Status")
    private String uploadStatus;

    /**
     * Reason
     * 作廢原因
     */
    @JsonProperty("Reason")
    private String reason;

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

    public String getBuyerIdentifier()
    {
        return buyerIdentifier;
    }

    public void setBuyerIdentifier(String buyerIdentifier)
    {
        this.buyerIdentifier = buyerIdentifier;
    }

    public String getSellerIdentifier()
    {
        return sellerIdentifier;
    }

    public void setSellerIdentifier(String sellerIdentifier)
    {
        this.sellerIdentifier = sellerIdentifier;
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

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
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
        return "QueryIssueInvalidResultObj{" +
                "rtnCode=" + rtnCode +
                ", rtnMsg='" + rtnMsg + '\'' +
                ", buyerIdentifier='" + buyerIdentifier + '\'' +
                ", sellerIdentifier='" + sellerIdentifier + '\'' +
                ", date='" + date + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", merID='" + merID + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", uploadStatus='" + uploadStatus + '\'' +
                ", reason='" + reason + '\'' +
                ", checkMacValue='" + checkMacValue + '\'' +
                '}';
    }
}
