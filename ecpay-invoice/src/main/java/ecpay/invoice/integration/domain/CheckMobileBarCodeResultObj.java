package ecpay.invoice.integration.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckMobileBarCodeResultObj
{
    @JsonProperty("RtnCode")
    private int rtnCode;

    @JsonProperty("RtnMsg")
    private String rtnMsg;

    @JsonProperty("IsExist")
    private String isExist;

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

    public String getIsExist()
    {
        return isExist;
    }

    public void setIsExist(String isExist)
    {
        this.isExist = isExist;
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
        return "CheckMobileBarCodeResultObj{" +
                "rtnCode=" + rtnCode +
                ", rtnMsg='" + rtnMsg + '\'' +
                ", isExist='" + isExist + '\'' +
                ", checkMacValue='" + checkMacValue + '\'' +
                '}';
    }
}
