package com.homecook.homecookstorefront.util;

public class MaskUtil
{
    /**
     * @param text       original string
     * @param start      start index of mask string
     * @param length     length of mask string
     * @param maskSymbol mask symbol
     * @return masked string
     */
    public static String maskString(String text, int start, int length, char maskSymbol)
    {
        if (text == null || text.isEmpty())
        {
            return "";
        }

        if (start < 0)
        {
            start = 0;
        }

        if (length < 1)
        {
            return text;
        }

        if (length > text.length())
        {
            length = text.length();
        }

        StringBuilder sb = new StringBuilder();
        char[] cc = text.toCharArray();
        for (int i = 0; i < cc.length; i++)
        {
            if (i >= start && i < (start + length))
            {
                sb.append(maskSymbol);
            }
            else
            {
                sb.append(cc[i]);
            }
        }
        return sb.toString();
    }

    public static String maskEmailAddress(String email)
    {
        if (email == null || email.isEmpty())
        {
            return "";
        }

        final String[] parts = email.split("@");

        // mask first part
        String account = "";
        if (parts[0].length() < 4)
        {
            account = maskString(parts[0], 0, parts[0].length(), '*');
        }
        else
        {
            account = maskString(parts[0], 2, parts[0].length(), '*');
        }

        return account + "@" + parts[1];
    }

    public static String maskPhoneNumber(String phone)
    {
        if (phone == null || phone.isEmpty())
        {
            return "";
        }

        String pattern = "\\d(?=\\d{3})";
        return phone.replaceAll("[^0-9]", "").replaceAll(pattern, "*");
    }
}
