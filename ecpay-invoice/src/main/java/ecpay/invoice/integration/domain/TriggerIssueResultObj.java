package ecpay.invoice.integration.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TriggerIssueResultObj
{
    /**
     * 1. 當 DelayDay 設為大於 0 時 RtnCode 回傳結 果為 4000003 是代表延後開立成功。
     * 2. 當 DelayDay 等於 0 時 RtnCode 回傳結果為 4000004 是代表開立發票成功。
     * 3. 當 RtnCode 非上述結果，則為失敗。
     */
    @JsonProperty("RtnCode")
    private int rtnCode;

    @JsonProperty("RtnMsg")
    private String rtnMsg;

    /**
     * Tsr
     * 交易單號
     * 若開立成功，則會回傳交易單號，若開立失敗，則會回傳空值。
     */
    @JsonProperty("Tsr")
    private String tsr;

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

    public String getTsr()
    {
        return tsr;
    }

    public void setTsr(String tsr)
    {
        this.tsr = tsr;
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
        return "TriggerIssueResultObj{" +
                "rtnCode=" + rtnCode +
                ", rtnMsg='" + rtnMsg + '\'' +
                ", tsr='" + tsr + '\'' +
                ", checkMacValue='" + checkMacValue + '\'' +
                '}';
    }
}
