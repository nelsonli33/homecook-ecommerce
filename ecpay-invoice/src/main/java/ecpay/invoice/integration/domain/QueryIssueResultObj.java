package ecpay.invoice.integration.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryIssueResultObj
{
    @JsonProperty("RtnCode")
    private int rtnCode;

    @JsonProperty("RtnMsg")
    private String rtnMsg;

    @JsonProperty("IIS_Award_Flag")
    private String awardFlag;

    @JsonProperty("IIS_Award_Type")
    private String awardType;

    @JsonProperty("IIS_Carruer_Num")
    private String carruerNum;

    @JsonProperty("IIS_Carruer_Type")
    private String carruerType;

    @JsonProperty("IIS_Category")
    private String category;

    @JsonProperty("IIS_Check_Number")
    private String checkNumber;

    @JsonProperty("IIS_Clearance_Mark")
    private String clearanceMark;

    @JsonProperty("IIS_Create_Date")
    private String createDate;

    @JsonProperty("IIS_Customer_Addr")
    private String customerAddr;

    @JsonProperty("IIS_Customer_Email")
    private String customerEmail;

    @JsonProperty("IIS_Customer_ID")
    private String customerId;

    @JsonProperty("IIS_Customer_Name")
    private String customerName;

    @JsonProperty("IIS_Customer_Phone")
    private String customerPhone;

    @JsonProperty("IIS_Identifier")
    private String buyerIdentifier;

    @JsonProperty("IIS_Invalid_Status")
    private String invalidStatus;

    @JsonProperty("IIS_IP")
    private String issueip;

    @JsonProperty("IIS_Issue_Status")
    private String issueStatus;

    @JsonProperty("IIS_Love_Code")
    private String loveCode;

    @JsonProperty("IIS_Mer_ID")
    private String merID;

    @JsonProperty("IIS_Number")
    private String invoiceNumber;

    @JsonProperty("IIS_Print_Flag")
    private String printFlag;

    @JsonProperty("IIS_Random_Number")
    private String randomNumber;

    @JsonProperty("IIS_Relate_Number")
    private String relateNumber;

    @JsonProperty("IIS_Remain_Allowance_Amt")
    private String remainAllowanceAmt;

    @JsonProperty("IIS_Sales_Amount")
    private String salesAmount;

    @JsonProperty("IIS_Tax_Amount")
    private int taxAmount;

    @JsonProperty("IIS_Tax_Rate")
    private String taxRate;

    @JsonProperty("IIS_Tax_Type")
    private String taxType;

    @JsonProperty("IIS_Turnkey_Status")
    private String turnkeyStatus;

    @JsonProperty("IIS_Type")
    private String invoiceType;

    @JsonProperty("IIS_Upload_Status")
    private String uploadStatus;

    @JsonProperty("IIS_Upload_Date")
    private String uploadDate;

    @JsonProperty("InvoiceRemark")
    private String invoiceRemark;

    @JsonProperty("ItemName")
    private String itemName;

    @JsonProperty("ItemCount")
    private String itemCount;

    @JsonProperty("ItemPrice")
    private String itemPrice;

    @JsonProperty("ItemAmount")
    private String ItemAmount;

    @JsonProperty("ItemRemark")
    private String itemRemark;

    @JsonProperty("ItemTaxType")
    private String itemTaxType;

    @JsonProperty("ItemWord")
    private String itemWord;

    @JsonProperty("PosBarCode")
    private String posBarCode;

    @JsonProperty("QRCode_Left")
    private String qrcodeLeft;

    @JsonProperty("QRCode_Right")
    private String qrcodeRight;

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

    public String getAwardFlag()
    {
        return awardFlag;
    }

    public void setAwardFlag(String awardFlag)
    {
        this.awardFlag = awardFlag;
    }

    public String getAwardType()
    {
        return awardType;
    }

    public void setAwardType(String awardType)
    {
        this.awardType = awardType;
    }

    public String getCarruerNum()
    {
        return carruerNum;
    }

    public void setCarruerNum(String carruerNum)
    {
        this.carruerNum = carruerNum;
    }

    public String getCarruerType()
    {
        return carruerType;
    }

    public void setCarruerType(String carruerType)
    {
        this.carruerType = carruerType;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCheckNumber()
    {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber)
    {
        this.checkNumber = checkNumber;
    }

    public String getClearanceMark()
    {
        return clearanceMark;
    }

    public void setClearanceMark(String clearanceMark)
    {
        this.clearanceMark = clearanceMark;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getCustomerAddr()
    {
        return customerAddr;
    }

    public void setCustomerAddr(String customerAddr)
    {
        this.customerAddr = customerAddr;
    }

    public String getCustomerEmail()
    {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail)
    {
        this.customerEmail = customerEmail;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerPhone()
    {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone)
    {
        this.customerPhone = customerPhone;
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

    public String getIssueip()
    {
        return issueip;
    }

    public void setIssueip(String issueip)
    {
        this.issueip = issueip;
    }

    public String getIssueStatus()
    {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus)
    {
        this.issueStatus = issueStatus;
    }

    public String getLoveCode()
    {
        return loveCode;
    }

    public void setLoveCode(String loveCode)
    {
        this.loveCode = loveCode;
    }

    public String getMerID()
    {
        return merID;
    }

    public void setMerID(String merID)
    {
        this.merID = merID;
    }

    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    public String getPrintFlag()
    {
        return printFlag;
    }

    public void setPrintFlag(String printFlag)
    {
        this.printFlag = printFlag;
    }

    public String getRandomNumber()
    {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber)
    {
        this.randomNumber = randomNumber;
    }

    public String getRelateNumber()
    {
        return relateNumber;
    }

    public void setRelateNumber(String relateNumber)
    {
        this.relateNumber = relateNumber;
    }

    public String getRemainAllowanceAmt()
    {
        return remainAllowanceAmt;
    }

    public void setRemainAllowanceAmt(String remainAllowanceAmt)
    {
        this.remainAllowanceAmt = remainAllowanceAmt;
    }

    public String getSalesAmount()
    {
        return salesAmount;
    }

    public void setSalesAmount(String salesAmount)
    {
        this.salesAmount = salesAmount;
    }

    public int getTaxAmount()
    {
        return taxAmount;
    }

    public void setTaxAmount(int taxAmount)
    {
        this.taxAmount = taxAmount;
    }

    public String getTaxRate()
    {
        return taxRate;
    }

    public void setTaxRate(String taxRate)
    {
        this.taxRate = taxRate;
    }

    public String getTaxType()
    {
        return taxType;
    }

    public void setTaxType(String taxType)
    {
        this.taxType = taxType;
    }

    public String getTurnkeyStatus()
    {
        return turnkeyStatus;
    }

    public void setTurnkeyStatus(String turnkeyStatus)
    {
        this.turnkeyStatus = turnkeyStatus;
    }

    public String getInvoiceType()
    {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType)
    {
        this.invoiceType = invoiceType;
    }

    public String getUploadStatus()
    {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus)
    {
        this.uploadStatus = uploadStatus;
    }

    public String getUploadDate()
    {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate)
    {
        this.uploadDate = uploadDate;
    }

    public String getInvoiceRemark()
    {
        return invoiceRemark;
    }

    public void setInvoiceRemark(String invoiceRemark)
    {
        this.invoiceRemark = invoiceRemark;
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

    public String getItemPrice()
    {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public String getItemAmount()
    {
        return ItemAmount;
    }

    public void setItemAmount(String itemAmount)
    {
        ItemAmount = itemAmount;
    }

    public String getItemRemark()
    {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark)
    {
        this.itemRemark = itemRemark;
    }

    public String getItemTaxType()
    {
        return itemTaxType;
    }

    public void setItemTaxType(String itemTaxType)
    {
        this.itemTaxType = itemTaxType;
    }

    public String getItemWord()
    {
        return itemWord;
    }

    public void setItemWord(String itemWord)
    {
        this.itemWord = itemWord;
    }

    public String getPosBarCode()
    {
        return posBarCode;
    }

    public void setPosBarCode(String posBarCode)
    {
        this.posBarCode = posBarCode;
    }

    public String getQrcodeLeft()
    {
        return qrcodeLeft;
    }

    public void setQrcodeLeft(String qrcodeLeft)
    {
        this.qrcodeLeft = qrcodeLeft;
    }

    public String getQrcodeRight()
    {
        return qrcodeRight;
    }

    public void setQrcodeRight(String qrcodeRight)
    {
        this.qrcodeRight = qrcodeRight;
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
        return "QueryIssueResultObj{" +
                "rtnCode=" + rtnCode +
                ", rtnMsg='" + rtnMsg + '\'' +
                ", awardFlag='" + awardFlag + '\'' +
                ", awardType='" + awardType + '\'' +
                ", carruerNum='" + carruerNum + '\'' +
                ", carruerType='" + carruerType + '\'' +
                ", category='" + category + '\'' +
                ", checkNumber='" + checkNumber + '\'' +
                ", clearanceMark='" + clearanceMark + '\'' +
                ", createDate='" + createDate + '\'' +
                ", customerAddr='" + customerAddr + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", buyerIdentifier='" + buyerIdentifier + '\'' +
                ", invalidStatus='" + invalidStatus + '\'' +
                ", issueip='" + issueip + '\'' +
                ", issueStatus='" + issueStatus + '\'' +
                ", loveCode='" + loveCode + '\'' +
                ", merID='" + merID + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", printFlag='" + printFlag + '\'' +
                ", randomNumber='" + randomNumber + '\'' +
                ", relateNumber='" + relateNumber + '\'' +
                ", remainAllowanceAmt='" + remainAllowanceAmt + '\'' +
                ", salesAmount='" + salesAmount + '\'' +
                ", taxAmount=" + taxAmount +
                ", taxRate=" + taxRate +
                ", taxType='" + taxType + '\'' +
                ", turnkeyStatus='" + turnkeyStatus + '\'' +
                ", invoiceType='" + invoiceType + '\'' +
                ", uploadStatus='" + uploadStatus + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", invoiceRemark='" + invoiceRemark + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemCount='" + itemCount + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", ItemAmount='" + ItemAmount + '\'' +
                ", itemRemark='" + itemRemark + '\'' +
                ", itemTaxType='" + itemTaxType + '\'' +
                ", itemWord='" + itemWord + '\'' +
                ", posBarCode='" + posBarCode + '\'' +
                ", qrcodeLeft='" + qrcodeLeft + '\'' +
                ", qrcodeRight='" + qrcodeRight + '\'' +
                ", checkMacValue='" + checkMacValue + '\'' +
                '}';
    }
}
