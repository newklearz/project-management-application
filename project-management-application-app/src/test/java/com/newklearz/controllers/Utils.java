package com.newklearz.controllers;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils
{
    private final static Date LOCAL_DATE = Calendar.getInstance().getTime();
    private final static String COMPLEX_PASSWORD = "admiN123$";
    private final static String EMAIL = "test@test.com";

    public static String getAlphaNumericString()
    {
        return RandomStringUtils.randomAlphabetic(8);
    }

    public static String getRandomEmail()
    {
        return RandomStringUtils.randomAlphabetic(7) + "@testdata.com";
    }

    public static String getRandomDate()
    {
        DateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(LOCAL_DATE);
    }

    public static String getPassword()
    {
        return COMPLEX_PASSWORD;
    }

    public static String getEmail()
    {
        return EMAIL;
    }
}