package ecpay.invoice.integration.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryAllowanceResultObj
{
    @JsonProperty("RtnCode")
    private int rtnCode;

    @JsonProperty("RtnMsg")
    private String rtnMsg;

    @JsonProperty("IA_Allow_No")
    private String allowNo;

    @JsonProperty("IA_Check_Send_Mail")
    private String checkSendEmail;

    @JsonProperty("IA_Date")
    private String date;

    @JsonProperty("ItemName")
    private String itemName;

    @JsonProperty("ItemCount")
    private String itemCount;

    @JsonProperty("ItemWord")
    private String itemWord;

    @JsonProperty("ItemPrice")
    private String itemPrice;

    @JsonProperty("ItemRateAmt")
    private String itemRateAmt;

    @JsonProperty("ItemTaxType")
    private String itemTaxType;

    @JsonProperty("ItemAmount")
    private String ItemAmount;


    @JsonProperty("IA_IP")
    private String allowip;

    @JsonProperty("IA_Identifier")
    private String buyerIdentifier;

    @JsonProperty("IA_Invalid_Status")
    private String invalidStatus;

    @JsonProperty("IA_Invoice_Issue_Date")
    private String invoiceIssueDate;

    @JsonProperty("IA_Invoice_No")
    private String invoiceNumber;

    @JsonProperty("IA_Mer_ID")
    private String merID;

    @JsonProperty("IA_Send_Mail")
    private String sendMail;

    @JsonProperty("IA_Send_Phone")
    private String sendPhone;

    @JsonProperty("IA_Tax_Amount")
    private int taxAmount;

    @JsonProperty("IA_Tax_Type")
    private String taxType;

    @JsonProperty("IA_Total_Amount")
    private int totalAmount;

    @JsonProperty("IA_Total_Tax_Amount")
    private int totalTaxAmount;

    @JsonProperty("IA_Upload_Date")
    private String uploadDate;

    @JsonProperty("IA_Upload_Status")
    private String uploadStatus;

    @JsonProperty("IIS_Customer_Name")
    private String customerName;

    @JsonProperty("IIS_Love_Code")
    private String loveCode;

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

    public String getAllowNo()
    {
        return allowNo;
    }

    public void setAllowNo(String allowNo)
    {
        this.allowNo = allowNo;
    }

    public String getCheckSendEmail()
    {
        return checkSendEmail;
    }

    public void setCheckSendEmail(String checkSendEmail)
    {
        this.checkSendEmail = checkSendEmail;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemCount()
    {
        return itemCount;
    }

    public void setItemCount(String itemCount)
    {
        this.itemCount = itemCount;
    }

    public String getItemWord()
    {
        return itemWord;
    }

    public void setItemWord(String itemWord)
    {
        this.itemWord = itemWord;
    }

    public String getItemPrice()
    {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public String getItemRateAmt()
    {
        return itemRateAmt;
    }

    public void setItemRateAmt(String itemRateAmt)
    {
        this.itemRateAmt = itemRateAmt;
    }

    public String getItemTaxType()
    {
        return itemTaxType;
    }

    public void setItemTaxType(String itemTaxType)
    {
        this.itemTaxType = itemTaxType;
    }

    public String getItemAmount()
    {
        return ItemAmount;
    }

    public void setItemAmount(String itemAmount)
    {
        ItemAmount = itemAmount;
    }

    public String getAllowip()
    {
        return allowip;
    }

    public void setAllowip(String allowip)
    {
        this.allowip = allowip;
    }

    public String getBuyerIdentifier()
    {
        return buyerIdentifier;
    }

    public void setBuyerIdentifier(String buyerIdentifier)
    {
        this.buyerIdentifier = buyerIdentifier;
    }

    public String getInvalidStatus()
    {
        return invalidStatus;
    }

    public void setInvalidStatus(String invalidStatus)
    {
        this.invalidStatus = invalidStatus;
    }

    public String getInvoiceIssueDate()
    {
        return invoiceIssueDate;
    }

    public void setInvoiceIssueDate(String invoiceIssueDate)
    {
        this.invoiceIssueDate = invoiceIssueDate;
    }

    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    public String getMerID()
    {
        return merID;
    }

    public void setMerID(String merID)
    {
        this.merID = merID;
    }

    public String getSendMail()
    {
        return sendMail;
    }

    public void setSendMail(String sendMail)
    {
        this.sendMail = sendMail;
    }

    public String getSendPhone()
    {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone)
    {
        this.sendPhone = sendPhone;
    }

    public int getTaxAmount()
    {
        return taxAmount;
    }

    public void setTaxAmount(int taxAmount)
    {
        this.taxAmount = taxAmount;
    }

    public String getTaxType()
    {
        return taxType;
    }

    public void setTaxType(String taxType)
    {
        this.taxType = taxType;
    }

    public int getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public int getTotalTaxAmount()
    {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(int totalTaxAmount)
    {
        this.totalTaxAmount = totalTaxAmount;
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

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getLoveCode()
    {
        return loveCode;
    }

    public void setLoveCode(String loveCode)
    {
        this.loveCode = loveCode;
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
        return "QueryAllowanceResultObj{" +
                "rtnCode=" + rtnCode +
                ", rtnMsg='" + rtnMsg + '\'' +
                ", allowNo='" + allowNo + '\'' +
                ", checkSendEmail='" + checkSendEmail + '\'' +
                ", date='" + date + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemCount='" + itemCount + '\'' +
                ", itemWord='" + itemWord + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", itemRateAmt='" + itemRateAmt + '\'' +
                ", itemTaxType='" + itemTaxType + '\'' +
                ", ItemAmount='" + ItemAmount + '\'' +
                ", allowip='" + allowip + '\'' +
                ", buyerIdentifier='" + buyerIdentifier + '\'' +
                ", invalidStatus='" + invalidStatus + '\'' +
                ", invoiceIssueDate='" + invoiceIssueDate + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", merID='" + merID + '\'' +
                ", sendMail='" + sendMail + '\'' +
                ", sendPhone='" + sendPhone + '\'' +
                ", taxAmount=" + taxAmount +
                ", taxType='" + taxType + '\'' +
                ", totalAmount=" + totalAmount +
                ", totalTaxAmount=" + totalTaxAmount +
                ", uploadDate='" + uploadDate + '\'' +
                ", uploadStatus='" + uploadStatus + '\'' +
                ", customerName='" + customerName + '\'' +
                ", loveCode='" + loveCode + '\'' +
                ", checkMacValue='" + checkMacValue + '\'' +
                '}';
    }
}
