package com.homecook.homecookstorefront.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaskUtilTest
{
    @Test
    void testMaskString()
    {
        String id = "A123456789";
        String actualMaskString = MaskUtil.maskString(id, 2, 5, '*');
        assertEquals("A1*****789", actualMaskString);
    }

    @Test
    void testMaskEmailAddress()
    {
        String testEmail1 = "a@gmail.com";
        String actualMaskTestEmail1 = MaskUtil.maskEmailAddress(testEmail1);
        assertEquals("*@gmail.com", actualMaskTestEmail1);


        String testEmail2 = "ale@yahoo.com";
        String actualMaskTestEmail2 = MaskUtil.maskEmailAddress(testEmail2);
        assertEquals("***@yahoo.com", actualMaskTestEmail2);

        String testEmail4 = "gzed@gmail.com";
        String actualMaskTestEmail4 = MaskUtil.maskEmailAddress(testEmail4);
        assertEquals("gz**@gmail.com", actualMaskTestEmail4);

        String testEmail3 = "helloworld@gmail.com";
        String actualMaskTestEmail3 = MaskUtil.maskEmailAddress(testEmail3);
        assertEquals("he********@gmail.com", actualMaskTestEmail3);
    }

    @Test
    void testMaskPhoneNumber()
    {
        String testPhoneNumber1 = "0952148426";
        String actualMaskTestPhoneNumber1 = MaskUtil.maskPhoneNumber(testPhoneNumber1);
        assertEquals("*******426", actualMaskTestPhoneNumber1);

        String testPhoneNumber2 = "+886952148426";
        String actualMaskTestPhoneNumber2 = MaskUtil.maskPhoneNumber(testPhoneNumber2);
        assertEquals("*********426", actualMaskTestPhoneNumber2);
    }
}