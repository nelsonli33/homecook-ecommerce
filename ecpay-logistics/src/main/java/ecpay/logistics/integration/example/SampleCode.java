package ecpay.logistics.integration.example;

import ecpay.logistics.integration.EcpayLogisticsClient;
import ecpay.logistics.integration.OperatingMode;
import ecpay.logistics.integration.domain.*;

import java.util.Hashtable;

public class SampleCode
{
    private static EcpayLogisticsClient client = new EcpayLogisticsClient.Builder()
            .operatingMode(OperatingMode.TEST)
            .MerchantID("2000933")
            .HashKey("XBERn1YOvpM9nfZc")
            .HashIV("h1ONHk4P4yqbl5LK")
            .build();
    ;

    public static void main(String[] args)
    {
//		System.out.println("compare CheckMacValue method testing result: "+cmprChkMacValue());
//        System.out.println("expressMap: "+postExpressMap());
        //		System.out.println("createCVS: "+postCreateCVS());
//		System.out.println("createHome: "+postCreateHome());
        System.out.println("returnHome: " + postReturnHome());
//		System.out.println("returnCVS: "+postReturnCVS());
//		System.out.println("logisticsCheckAccounts: "+postLogisticsCheckAccount());
//		System.out.println("returnHiLifeCVS: "+postReturnHiLifeCVS());
//		System.out.println("returnUniMartCVS: "+postReturnUniMartCVS());
//		System.out.println("updateShipmentInfo: "+postUpdateShipmentInfo());
//		System.out.println("updateStoreInfo: "+postUpdateStoreInfor());
//		System.out.println("cancelC2COrder: "+postCancelC2COrder());
//		System.out.println("queryLogisticsTradeInfo: "+postQueryLogisticsTradeInfo());
//		System.out.println("printTradeDocument: "+postPrintTradeDocument());
//		System.out.println("printUniMartC2COrderInfo: "+postPrintUniMartC2COrderInfo());
//		System.out.println("printFAMIC2COrderInfo: "+postPrintFAMIC2COrderInfo());
//		System.out.println("printHILIFEC2COrder: "+postPrintHILIFEC2COrderInfo());
//		System.out.println("printOKMARTC2COrder: "+postPrintOKMARTC2COrderInfo());
//		System.out.println("createTestData: "+postCreateTestData());
    }


    public static boolean cmprChkMacValue()
    {
        Hashtable<String, String> dict = new Hashtable<String, String>();
        dict.put("MerchantID", "2000132");
        dict.put("CheckMacValue", "50BE3989953C1734E32DD18EB23698241E035F9CBCAC74371CCCF09E0E15BD61");
        return client.compareCheckMacValue(dict);
    }

    public static String postExpressMap()
    {
        ExpressMapObj obj = new ExpressMapObj();
        obj.setLogisticsSubType("OKMARTC2C");
        obj.setIsCollection("N");
        obj.setServerReplyURL("https://www.yahoo.com.tw");
        return client.expressMap(obj);
    }

    public static String postCreateCVS()
    {
        CreateCVSObj obj = new CreateCVSObj();
        obj.setMerchantTradeNo("ECPAT202XA70xzx1");
        obj.setMerchantTradeDate("2021/01/27 05:05:05");
        obj.setLogisticsSubType("OKMARTC2C");
        obj.setGoodsAmount("100");
        obj.setGoodsName("asd");
        obj.setSenderName("test");
        obj.setReceiverName("Ying");
        obj.setReceiverCellPhone("0911429215");
        obj.setServerReplyURL("https://www.yahoo.com.tw");
        obj.setReceiverStoreID("001328");
        obj.setSenderCellPhone("0912345678");
        return client.create(obj);
    }

    public static String postCreateHome()
    {
        CreateHomeObj obj = new CreateHomeObj();
        obj.setMerchantTradeNo("TestCreateHome");
        obj.setMerchantTradeDate("2017/04/04 04:04:04");
        obj.setLogisticsSubType("TCAT");
        obj.setGoodsAmount("5000");
        obj.setSenderName("Mark");
        obj.setSenderCellPhone("0911429215");
        obj.setReceiverName("Ying");
        obj.setReceiverCellPhone("0911842495");
        obj.setServerReplyURL("https://www.yahoo.com.tw");
        obj.setSenderZipCode("106");
        obj.setSenderAddress("hahahahaha");
        obj.setReceiverZipCode("220");
        obj.setReceiverAddress("hahahahaha");
        obj.setTemperature("0002");
        obj.setDistance("00");
        obj.setSpecification("0001");
        obj.setScheduledPickupTime("1");
        obj.setScheduledDeliveryTime("1");
        return client.create(obj);
    }

    public static String postReturnHome()
    {
        ReturnHomeObj obj = new ReturnHomeObj();
        obj.setLogisticsSubType("TCAT");
        obj.setServerReplyURL("https://www.yahoo.com.tw");
        obj.setSenderName("Mark");
        obj.setSenderZipCode("106");
        obj.setSenderAddress("hahahahaha");
        obj.setSenderCellPhone("0911429215");
        obj.setReceiverName("Ying");
        obj.setReceiverZipCode("104");
        obj.setReceiverAddress("hahahaha");
        obj.setReceiverCellPhone("0911842495");
        obj.setGoodsAmount("5000");
        obj.setTemperature("0002");
        obj.setDistance("00");
        obj.setSpecification("0001");
        obj.setScheduledPickupTime("1");
        obj.setScheduledDeliveryTime("1");
        obj.setPackageCount("1");
        return client.returnHome(obj);
    }

    public static String postReturnCVS()
    {
        ReturnCVSObj obj = new ReturnCVSObj();
        obj.setAllPayLogisticsID("10035");
        obj.setServerReplyURL("https://www.yahoo.com.tw");
        obj.setGoodsAmount("5000");
        obj.setSenderName("Mark");
        return client.returnCVS(obj);
    }

    public static String postLogisticsCheckAccount()
    {
        LogisticsCheckAccountsObj obj = new LogisticsCheckAccountsObj();
        obj.setRtnMerchantTradeNo("1706271531582");
        return client.logisticsCheckAccounts(obj);
    }

    public static String postReturnHiLifeCVS()
    {
        ReturnHiLifeCVSObj obj = new ReturnHiLifeCVSObj();
        obj.setAllPayLogisticsID("10035");
        obj.setServerReplyURL("https://www.yahoo.com.tw");
        obj.setGoodsAmount("1000");
        obj.setSenderName("Mark");
        obj.setSenderPhone("0911429215");
        return client.returnHiLifeCVS(obj);
    }

    public static String postReturnUniMartCVS()
    {
        ReturnUniMartCVSObj obj = new ReturnUniMartCVSObj();
        obj.setAllPayLogisticsID("");
        obj.setServerReplyURL("https://www.yahoo.com.tw");
        obj.setGoodsAmount("100");
        obj.setSenderName("Mark");
        return client.returnUniMartCVS(obj);
    }

    public static String postUpdateShipmentInfo()
    {
        UpdateShipmentInfoObj obj = new UpdateShipmentInfoObj();
        obj.setAllPayLogisticsID("10035");
        obj.setReceiverStoreID("991182");
        return client.updateShipmentInfo(obj);
    }

    public static String postUpdateStoreInfor()
    {
        UpdateStoreInfoObj obj = new UpdateStoreInfoObj();
        obj.setAllPayLogisticsID("49050");
        obj.setCVSPaymentNo("G6551191");
        obj.setCVSValidationNo("4718");
        obj.setReceiverStoreID("870766");
        obj.setStoreType("01");
        return client.updateStoreInfo(obj);
    }

    public static String postCancelC2COrder()
    {
        CancelC2COrderObj obj = new CancelC2COrderObj();
        obj.setAllPayLogisticsID("49051");
        obj.setCVSPaymentNo("G6551192");
        obj.setCVSValidationNo("2029");
        return client.cancelC2COrder(obj);
    }

    public static String postQueryLogisticsTradeInfo()
    {
        QueryLogisticsTradeInfoObj obj = new QueryLogisticsTradeInfoObj();
        obj.setAllPayLogisticsID("49051");
        return client.queryLogisticsTradeInfo(obj);
    }

    public static String postPrintTradeDocument()
    {
        PrintTradeDocumentObj obj = new PrintTradeDocumentObj();
        obj.setAllPayLogisticsID("49051");
        return client.printTradeDocument(obj);
    }

    public static String postPrintUniMartC2COrderInfo()
    {
        PrintUniMartC2COrderInfoObj obj = new PrintUniMartC2COrderInfoObj();
        obj.setAllPayLogisticsID("49053");
        obj.setCVSPaymentNo("G6551193");
        obj.setCVSValidationNo("2073");
        return client.printUniMartC2COrderInfo(obj);
    }

    public static String postPrintFAMIC2COrderInfo()
    {
        PrintFAMIC2COrderInfoObj obj = new PrintFAMIC2COrderInfoObj();
        obj.setAllPayLogisticsID("49054");
        obj.setCVSPaymentNo("07903971454");
        return client.printFAMIC2COrderInfo(obj);
    }

    public static String postPrintHILIFEC2COrderInfo()
    {
        PrintHILIFEC2COrderInfoObj obj = new PrintHILIFEC2COrderInfoObj();
        obj.setAllPayLogisticsID("49066");
        obj.setCVSPaymentNo("76RE05280526");
        return client.printHILIFEC2COrderInfo(obj);
    }

    public static String postPrintOKMARTC2COrderInfo()
    {
        PrintOKMARTC2COrderInfoObj obj = new PrintOKMARTC2COrderInfoObj();
        obj.setAllPayLogisticsID("1682318");
        obj.setCVSPaymentNo("W3383770105");
        return client.printOKMARTC2COrderInfo(obj);
    }

    public static String postCreateTestData()
    {
        CreateTestDataObj obj = new CreateTestDataObj();
        obj.setLogisticsSubType("FAMI");
        return client.createTestData(obj);
    }
}
