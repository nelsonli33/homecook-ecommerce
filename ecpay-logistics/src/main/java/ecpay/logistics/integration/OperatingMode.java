package ecpay.logistics.integration;

import java.util.TreeMap;

public enum OperatingMode
{
    TEST,

    PRODUCTION;

    private static TreeMap<String, OperatingMode> valueMap = new TreeMap<>();
    private String value;

    static
    {
        TEST.value = "Test";
        PRODUCTION.value = "Production";

        valueMap.put("Test", OperatingMode.TEST);
        valueMap.put("Production", OperatingMode.PRODUCTION);
    }


    public static OperatingMode fromString(String mode)
    {
        return valueMap.get(mode);
    }

    public String value()
    {
        return value;
    }

}
