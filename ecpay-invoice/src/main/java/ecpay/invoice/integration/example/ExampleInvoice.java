package ecpay.invoice.integration.example;

import ecpay.invoice.integration.EcpayInvoiceClient;
import ecpay.invoice.integration.OperatingMode;
import ecpay.invoice.integration.domain.*;

import java.util.UUID;

public class ExampleInvoice
{

    private static EcpayInvoiceClient client = new EcpayInvoiceClient.Builder()
            .operatingMode(OperatingMode.TEST)
            .MerchantID("2000132")
            .HashKey("ejCk326UnaZWKisg")
            .HashIV("q9jcZX8Ib9LM8wYK")
            .build();

    public static void main(String[] args)
    {
//        System.out.println("Issue: "+postIssue());
//		System.out.println("DelayIssue: "+postDelayIssue());
//		System.out.println("TriggerIssue: "+postTriggerIssue());
//		System.out.println("Allowance: "+postAllowance());
//		System.out.println("AllowanceByCollegiate: "+postAllowanceByCollegiate());
//		System.out.println("IssueInvalid: "+postIssueInvalid());
//		System.out.println("AllowanceInvalid: "+postAllowanceInvalid());
//		System.out.println("QueryIssue: "+postQueryIssue());
//		System.out.println("QueryAllowance: "+postQueryAllowance());
//		System.out.println("QueryIssueInvalid: "+postQueryIssueInvalid());
//		System.out.println("QueryAllowanceInvalid: "+postQueryAllowanceInvalid());
//		System.out.println("InvoiceNotify: "+postInvoiceNotify());
//		System.out.println("CheckMobileBarCode: "+postCheckMobileBarCode());
//		System.out.println("CheckLoveCode: "+postCheckLoveCode());
    }


    public static IssueResultObj postIssue()
    {
        IssueObj obj = new IssueObj();
        UUID uid = UUID.randomUUID();
        obj.setRelateNumber(uid.toString().replaceAll("-", "").substring(0, 30));
        obj.setCustomerName("Mark");
        obj.setCustomerAddr("Taiwan");
        obj.setCustomerIdentifier("");
        obj.setCustomerPhone("0912345678");
        obj.setCarruerType("");
        obj.setCarruerNum("");
        obj.setPrint("0");
        obj.setDonation("1");
        obj.setTaxType("1");
        obj.setLoveCode("1234");
        obj.setSalesAmount("100");
        obj.setItemName("¹B°Ê¥Î«~");
        obj.setItemCount("1");
        obj.setItemWord("½c");
        obj.setItemPrice("100.3");
        obj.setItemAmount("100.3");
        return client.issue(obj);
    }

    public static DelayIssueResultObj postDelayIssue()
    {
        DelayIssueObj obj = new DelayIssueObj();
        UUID uid = UUID.randomUUID();
        String num = uid.toString().replaceAll("-", "").substring(0, 30);
        obj.setRelateNumber(num);
        obj.setCustomerName("Mark");
        obj.setCustomerAddr("Taiwan");
        obj.setCustomerPhone("0912345678");
        obj.setPrint("1");
        obj.setDonation("0");
        obj.setTaxType("1");
        obj.setSalesAmount("100");
        obj.setItemName("¹B°Ê¥Î«~");
        obj.setItemCount("1");
        obj.setItemWord("½c");
        obj.setItemPrice("100.3");
        obj.setItemAmount("100.3");
        obj.setDelayFlag("2");
        obj.setDelayDay("0");
        obj.setTsr(num);
        return client.delayIssue(obj);
    }

    public static TriggerIssueResultObj postTriggerIssue()
    {
        TriggerIssueObj obj = new TriggerIssueObj();
        obj.setTsr("b12b2018e8814e208b1adf00264337");
        return client.triggerIssue(obj);
    }

    public static AllowanceResultObj postAllowance()
    {
        AllowanceObj obj = new AllowanceObj();
        obj.setInvoiceNo("FX60011787");
        obj.setAllowanceNotify("A");
        obj.setCustomerName("Mark");
        obj.setNotifyMail("abc@opay.com.tw");
        obj.setNotifyPhone("0912345678");
        obj.setAllowanceAmount("100");
        obj.setItemName("¹B°Ê¥Î«~");
        obj.setItemCount("1");
        obj.setItemWord("½c");
        obj.setItemPrice("100.3");
        obj.setItemAmount("100.3");
        return client.allowance(obj);
    }

    public static AllowanceByCollegiateResultObj postAllowanceByCollegiate()
    {
        AllowanceByCollegiateObj obj = new AllowanceByCollegiateObj();
        obj.setInvoiceNo("TE10032604");
        obj.setAllowanceNotify("A");
        obj.setCustomerName("Mark");
        obj.setNotifyMail("test@test.com");
        obj.setNotifyPhone("0912345678");
        obj.setAllowanceAmount("100");
        obj.setItemName("¹B°Ê¥Î«~");
        obj.setItemCount("1");
        obj.setItemWord("½c");
        obj.setItemPrice("100");
        obj.setItemAmount("100");
        obj.setItemTaxType("3");
        obj.setReturnURL("http://test.test.com");
        return client.allowancebycollegiate(obj);
    }

    public static IssueInvalidResultObj postIssueInvalid()
    {
        IssueInvalidObj obj = new IssueInvalidObj();
        obj.setInvoiceNumber("XN12345678");
        obj.setReason("·å²«");
        return client.issueInvalid(obj);
    }

    public static AllowanceInvalidResultObj postAllowanceInvalid()
    {
        AllowanceInvalidObj obj = new AllowanceInvalidObj();
        obj.setInvoiceNo("XN12345678");
        obj.setAllowanceNo("1234123412341234");
        obj.setReason("´ú¸Õ");
        return client.allowanceInvalid(obj);
    }

    public static QueryIssueResultObj postQueryIssue()
    {
        QueryIssueObj obj = new QueryIssueObj();
        obj.setRelateNumber("AA20211013000075163");
        return client.queryIssue(obj);
    }

    public static String postQueryAllowance()
    {
        QueryAllowanceObj obj = new QueryAllowanceObj();
        obj.setInvoiceNo("TT00012440");
        obj.setAllowanceNo("2017063010319868");
        return client.queryAllowance(obj);
    }

    public static QueryIssueInvalidResultObj postQueryIssueInvalid()
    {
        QueryIssueInvalidObj obj = new QueryIssueInvalidObj();
        obj.setRelateNumber("AA20211014000012723");
        return client.queryIssueInvalid(obj);
    }

    public static QueryAllowanceInvalidResultObj postQueryAllowanceInvalid()
    {
        QueryAllowanceInvalidObj obj = new QueryAllowanceInvalidObj();
        obj.setInvoiceNo("NQ50007604");
        obj.setAllowanceNo("2021101317419735");
        return client.queryAllowanceInvalid(obj);
    }

    public static InvoiceNotifyResultObj postInvoiceNotify()
    {
        InvoiceNotifyObj obj = new InvoiceNotifyObj();
        obj.setInvoiceNo("XN12345678");
        obj.setAllowanceNo("Allpay0123456789");
        obj.setPhone("0912345678");
        obj.setNotifyMail("abc@ecpay.com");
        obj.setNotify("A");
        obj.setInvoiceTag("I");
        obj.setNotified("A");
        return client.invoiceNotify(obj);
    }

    public static CheckMobileBarCodeResultObj postCheckMobileBarCode()
    {
        CheckMobileBarCodeObj obj = new CheckMobileBarCodeObj();
        obj.setBarCode("/6G.X3LQ");
        return client.checkMobileBarCode(obj);
    }

    public static CheckLoveCodeResultObj postCheckLoveCode()
    {
        CheckLoveCodeObj obj = new CheckLoveCodeObj();
        obj.setLoveCode("919");
        return client.checkLoveCode(obj);
    }
}
