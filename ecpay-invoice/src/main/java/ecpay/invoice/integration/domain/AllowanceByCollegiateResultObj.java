package ecpay.invoice.integration.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AllowanceByCollegiateResultObj
{
    @JsonProperty("RtnCode")
    private int rtnCode;

    @JsonProperty("RtnMsg")
    private String rtnMsg;

    /**
     * IA_Allow_No
     * 折讓單號
     */
    @JsonProperty("IA_Allow_No")
    private String allowNo;

    /**
     * IA_Invoice_No
     * 發票號碼
     */
    @JsonProperty("IA_Invoice_No")
    private String invoiceNo;

    /**
     * IA_TempDate
     * 線上折讓時間
     */
    @JsonProperty("IA_TempDate")
    private String date;

    /**
     * IA_TempExpireDate
     * 線上折讓同意到期日
     */
    @JsonProperty("IA_TempExpireDate")
    private String tempExpireDate;

    /**
     * IA_Remain_Allowance_Amt
     * 建立成功，會回傳開立折讓後剩餘金額
     * 建立失敗，則會回傳空值
     */
    @JsonProperty("IA_Remain_Allowance_Amt")
    private int remainAllowanceAmt;

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

    public String getInvoiceNo()
    {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo)
    {
        this.invoiceNo = invoiceNo;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTempExpireDate()
    {
        return tempExpireDate;
    }

    public void setTempExpireDate(String tempExpireDate)
    {
        this.tempExpireDate = tempExpireDate;
    }

    public int getRemainAllowanceAmt()
    {
        return remainAllowanceAmt;
    }

    public void setRemainAllowanceAmt(int remainAllowanceAmt)
    {
        this.remainAllowanceAmt = remainAllowanceAmt;
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
        return "AllowanceByCollegiateResultObj{" +
                "rtnCode=" + rtnCode +
                ", rtnMsg='" + rtnMsg + '\'' +
                ", allowNo='" + allowNo + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", date='" + date + '\'' +
                ", tempExpireDate='" + tempExpireDate + '\'' +
                ", remainAllowanceAmt=" + remainAllowanceAmt +
                ", checkMacValue='" + checkMacValue + '\'' +
                '}';
    }
}