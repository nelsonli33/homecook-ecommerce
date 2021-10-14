package ecpay.invoice.integration.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DelayIssueResultObj
{
    @JsonProperty("RtnCode")
    private int rtnCode;

    @JsonProperty("RtnMsg")
    private String rtnMsg;

    /**
     * OrderNumber
     * 交易單號(Tsr)
     * 若開立成功，則會回傳交易單號(Tsr);若開立 失敗，則會回傳空值。
     */
    @JsonProperty("OrderNumber")
    private String orderNumber;

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

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
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
        return "DelayIssueResultObj{" +
                "rtnCode=" + rtnCode +
                ", rtnMsg='" + rtnMsg + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", checkMacValue='" + checkMacValue + '\'' +
                '}';
    }
}
