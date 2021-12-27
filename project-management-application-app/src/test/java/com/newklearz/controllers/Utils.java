package com.newklearz.controllers;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils
{
    public final static Date LOCAL_DATE = Calendar.getInstance().getTime();
    public final static String COMPLEX_PASSWORD = "admiN123$";
    public final static String EMAIL = "test@test.com";
    public final static String MORE_CHARACTERS_THAN_100 ="sajdhkkahskdjhakjdhakjsdhkajshdkjashdkjashdkjahsdkjsahdkahsdkjahdskjahdskjahsdkhasdkjhasdkj";
    public final static Integer INTEGER_ZERO = 0;
    public final static Integer OUT_OF_RANGE_VALUE = Integer.MAX_VALUE + 1;
    public final static String NO_DIGITS_PASSWORD = "crocodiL$$$$$$";
    public final static String LESS_CHARACTERS_PASSWORD = "cro123$";
    public final static String MORE_CHARACTERS_PASSWORD = "crocodiL123$$$$$$$$$$$$$$";
    public final static String NO_UPPERCASE_PASSWORD = "crocodil123$$$";
    public final static String NO_LOWERCASE_PASSWORD = "CROCODIL123$$$$";
    public final static String NO_SPECIAL_CHARACTERS_PASSWORD = "crocodiL123";
    public final static String FORBIDDEN_CHARACTERS_PASSWORD = "crocodiL123$]";
    public final static String EMPTY_STRING = "";
    public final static String BLANK_SPACE = " ";
    public final static String MORE_CHARACTERS_EMAIL = "test@testajshdkashdkajhdkjahsdkjahsdkjhaskdjhaksdjhakjshdkajhsdkjahsdkjahskdjhaksdjhadajhsd.com";

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
}