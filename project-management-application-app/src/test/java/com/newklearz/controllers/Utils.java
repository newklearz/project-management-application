package com.newklearz.controllers;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils
{
    public static String getAlphaNumericString()
    {
        String generatedString = RandomStringUtils.randomAlphabetic(8);
        return generatedString;
    }

    public static String getRandomEmail()
    {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
        String email;
        String temp = RandomStringUtils.random(7, allowedChars);
        email = temp.substring(0,  7) + "@testdata.com";
        return email;
    }

    public static String getRandomDate(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }
}