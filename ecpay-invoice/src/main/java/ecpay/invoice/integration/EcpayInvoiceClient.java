package ecpay.invoice.integration;

import ecpay.invoice.integration.domain.*;
import ecpay.invoice.integration.ecpayOperator.EcpayFunction;
import ecpay.invoice.integration.errorMsg.ErrorMessage;
import ecpay.invoice.integration.exception.EcpayException;
import ecpay.invoice.integration.verification.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class EcpayInvoiceClient
{
    private final static Logger log = Logger.getLogger(EcpayInvoiceClient.class.getName());

    private final String operatingMode;
    private final String HashKey;
    private final String HashIV;
    private final String MerchantID;
    private String issueUrl;
    private String delayIssueUrl;
    private String triggerIssueUrl;
    private String allowanceUrl;
    private String allowancebycollegiateUrl;
    private String issueInvalidUrl;
    private String allowanceInvalidUrl;
    private String queryIssueUrl;
    private String queryAllowanceUrl;
    private String queryIssueInvalidUrl;
    private String queryAllowanceInvalidUrl;
    private String invoiceNotifyUrl;
    private String checkMobileBarCodeUrl;
    private String checkLoveCodeUrl;

    private EcpayInvoiceClient(String operatingMode, String hashKey, String hashIV, String merchantID)
    {
        this.operatingMode = operatingMode;
        this.HashKey = hashKey;
        this.HashIV = hashIV;
        this.MerchantID = merchantID;

        Logger.getRootLogger().setLevel(Level.OFF);
    }

    public String issue(IssueObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        log.info("issue params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyIssue verify = new VerifyIssue();
            issueUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            obj.setCustomerName(EcpayFunction.urlEncode(obj.getCustomerName()));
            obj.setCustomerAddr(EcpayFunction.urlEncode(obj.getCustomerAddr()));
            obj.setCustomerEmail(EcpayFunction.urlEncode(obj.getCustomerEmail()));
            obj.setInvoiceRemark(EcpayFunction.urlEncode(obj.getInvoiceRemark()));
            obj.setItemName(EcpayFunction.urlEncode(obj.getItemName()));
            obj.setItemWord(EcpayFunction.urlEncode(obj.getItemWord()));
            obj.setItemRemark(EcpayFunction.urlEncode(obj.getItemRemark()));
            obj.setCarruerNum(obj.getCarruerNum().replaceAll("\\+", " "));
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("issue generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("issue post String: " + httpValue);
            result = EcpayFunction.httpPost(issueUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }

    public String delayIssue(DelayIssueObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        log.info("delayIssue params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyDelayIssue verify = new VerifyDelayIssue();
            delayIssueUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            obj.setCustomerName(EcpayFunction.urlEncode(obj.getCustomerName()));
            obj.setCustomerAddr(EcpayFunction.urlEncode(obj.getCustomerAddr()));
            obj.setCustomerEmail(EcpayFunction.urlEncode(obj.getCustomerEmail()));
            obj.setInvoiceRemark(EcpayFunction.urlEncode(obj.getInvoiceRemark()));
            obj.setItemName(EcpayFunction.urlEncode(obj.getItemName()));
            obj.setItemWord(EcpayFunction.urlEncode(obj.getItemWord()));
            obj.setCarruerNum(obj.getCarruerNum().replaceAll("\\+", " "));
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("delayIssue generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("delayIssue post String: " + httpValue);
            result = EcpayFunction.httpPost(delayIssueUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }

    public String triggerIssue(TriggerIssueObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        log.info("triggerIssue params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyTriggerIssue verify = new VerifyTriggerIssue();
            triggerIssueUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("triggerIssue generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("triggerIssue post String: " + httpValue);
            result = EcpayFunction.httpPost(triggerIssueUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }

    public String allowance(AllowanceObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        log.info("allowance params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyAllowance verify = new VerifyAllowance();
            allowanceUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            obj.setCustomerName(EcpayFunction.urlEncode(obj.getCustomerName()));
            obj.setNotifyMail(EcpayFunction.urlEncode(obj.getNotifyMail()));
            obj.setItemName(EcpayFunction.urlEncode(obj.getItemName()));
            obj.setItemWord(EcpayFunction.urlEncode(obj.getItemWord()));
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("allowance generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("allowance post String: " + httpValue);
            result = EcpayFunction.httpPost(allowanceUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }

    public String allowancebycollegiate(AllowanceByCollegiateObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        log.info("allowance params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyAllowanceByCollegiate verify = new VerifyAllowanceByCollegiate();
            allowancebycollegiateUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            obj.setCustomerName(EcpayFunction.urlEncode(obj.getCustomerName()));
            obj.setNotifyMail(EcpayFunction.urlEncode(obj.getNotifyMail()));
            obj.setItemName(EcpayFunction.urlEncode(obj.getItemName()));
            obj.setItemWord(EcpayFunction.urlEncode(obj.getItemWord()));
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("allowance generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("allowance post String: " + httpValue);
            result = EcpayFunction.httpPost(allowancebycollegiateUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }

    public String issueInvalid(IssueInvalidObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        log.info("issueInvalid params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyIssueInvalid verify = new VerifyIssueInvalid();
            issueInvalidUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            obj.setReason(EcpayFunction.urlEncode(obj.getReason()));
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("issueInvalid generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("issueInvalid post String: " + httpValue);
            result = EcpayFunction.httpPost(issueInvalidUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }

    public String allowanceInvalid(AllowanceInvalidObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        log.info("allowanceInvalid params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyAllowanceInvalid verify = new VerifyAllowanceInvalid();
            allowanceInvalidUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            obj.setReason(EcpayFunction.urlEncode(obj.getReason()));
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("allowanceInvalid generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("allowanceInvalid post String: " + httpValue);
            result = EcpayFunction.httpPost(allowanceInvalidUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }

    public String queryIssue(QueryIssueObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        log.info("queryIssue params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyQueryIssue verify = new VerifyQueryIssue();
            queryIssueUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("queryIssue generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("queryIssue post String: " + httpValue);
            result = EcpayFunction.httpPost(queryIssueUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }

    public String queryAllowance(QueryAllowanceObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        log.info("queryAllowance params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyQueryAllowance verify = new VerifyQueryAllowance();
            queryAllowanceUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("queryAllowance generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("queryAllowance post String: " + httpValue);
            result = EcpayFunction.httpPost(queryAllowanceUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }

    public String queryIssueInvalid(QueryIssueInvalidObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        log.info("queryIssueInvalid params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyQueryIssueInvalid verify = new VerifyQueryIssueInvalid();
            queryIssueInvalidUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("queryIssueInvalid generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("queryIssueInvalid post String: " + httpValue);
            result = EcpayFunction.httpPost(queryIssueInvalidUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }

    public String queryAllowanceInvalid(QueryAllowanceInvalidObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        log.info("queryAllowanceInvalid params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyQueryAllowanceInvalid verify = new VerifyQueryAllowanceInvalid();
            queryAllowanceInvalidUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("queryAllowanceInvalid generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("queryAllowanceInvalid post String: " + httpValue);
            result = EcpayFunction.httpPost(queryAllowanceInvalidUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }

    public String invoiceNotify(InvoiceNotifyObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        log.info("invoiceNotify params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyInvoiceNotify verify = new VerifyInvoiceNotify();
            invoiceNotifyUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            obj.setNotifyMail(EcpayFunction.urlEncode(obj.getNotifyMail()));
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("invoiceNotify generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("invoiceNotify post String: " + httpValue);
            result = EcpayFunction.httpPost(invoiceNotifyUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }

    public String checkMobileBarCode(CheckMobileBarCodeObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        if (obj.getBarCode().contains("+"))
        {
            String tmp = obj.getBarCode().replaceAll("\\+", " ");
            obj.setBarCode(tmp);
        }
        log.info("checkMobileBarCode params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyCheckMobileBarCode verify = new VerifyCheckMobileBarCode();
            checkMobileBarCodeUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("checkMobileBarCode generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("checkMobileBarCode post String: " + httpValue);
            result = EcpayFunction.httpPost(checkMobileBarCodeUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }

    public String checkLoveCode(CheckLoveCodeObj obj)
    {
        obj.setMerchantID(MerchantID);
        obj.setTimeStamp(EcpayFunction.genUnixTimeStamp());
        log.info("checkLoveCode params: " + obj.toString());
        String result = "";
        String CheckMacValue = "";
        try
        {
            VerifyCheckLoveCode verify = new VerifyCheckLoveCode();
            checkLoveCodeUrl = verify.getAPIUrl(operatingMode);
            verify.verifyParams(obj);
            CheckMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
            log.info("checkLoveCode generate CheckMacValue: " + CheckMacValue);
            String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
            log.info("checkLoveCode post String: " + httpValue);
            result = EcpayFunction.httpPost(checkLoveCodeUrl, httpValue, "UTF-8");
        }
        catch (EcpayException e)
        {
            e.ShowExceptionMessage();
            log.error(e.getNewExceptionMessage());
            throw new EcpayException(e.getNewExceptionMessage());
        }
        return result;
    }


    public static final class Builder
    {
        private String operatingMode;
        private String isProjectContractor = "N";
        private String HashKey;
        private String HashIV;
        private String MerchantID;

        public Builder operatingMode(OperatingMode operatingMode)
        {
            this.operatingMode = operatingMode.getValue();
            return this;
        }


        public Builder isProjectContractor(String isProjectContractor)
        {
            this.isProjectContractor = isProjectContractor.equalsIgnoreCase("N") ? "" : MerchantID;
            return this;
        }

        public Builder HashKey(String HashKey)
        {
            this.HashKey = HashKey;
            return this;
        }

        public Builder HashIV(String HashIV)
        {
            this.HashIV = HashIV;
            return this;
        }

        public Builder MerchantID(String MerchantID)
        {
            this.MerchantID = MerchantID;
            return this;
        }

        public EcpayInvoiceClient build()
        {
            if (HashKey == null)
            {
                throw new EcpayException(ErrorMessage.MInfo_NOT_SETTING);
            }

            return new EcpayInvoiceClient(operatingMode, HashKey, HashIV, MerchantID);
        }
    }
}
