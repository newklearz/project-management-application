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
    private final static String MORE_CHARACTERS_THAN_100 ="sajdhkkahskdjhakjdhakjsdhkajshdkjashdkjashdkjahsdkjsahdkahsdkjahdskjahdskjahsdkhasdkjhasdkj";
    private final static Integer INTEGER_ZERO = 0;
    private final static Integer OUT_OF_RANGE_VALUE = Integer.MAX_VALUE + 1;
    private final static String NO_DIGITS_PASSWORD = "crocodiL$$$$$$";
    private final static String LESS_CHARACTERS_PASSWORD = "cro123$";
    private final static String MORE_CHARACTERS_PASSWORD = "crocodiL123$$$$$$$$$$$$$$";
    private final static String NO_UPPERCASE_PASSWORD = "crocodil123$$$";
    private final static String NO_LOWERCASE_PASSWORD = "CROCODIL123$$$$";
    private final static String NO_SPECIAL_CHARACTERS_PASSWORD = "crocodiL123";
    private final static String FORBIDDEN_CHARACTERS_PASSWORD = "crocodiL123$]";
    private final static String EMPTY_STRING = "";
    private final static String BLANK_SPACE = " ";

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

    public static String getMoreChars()
    {
        return MORE_CHARACTERS_THAN_100;
    }

    public static Integer getIntegerZero()
    {
        return INTEGER_ZERO;
    }

    public static Integer getOutOfRangeValue()
    {
        return OUT_OF_RANGE_VALUE;
    }

    public static String getNoDigitsPassword()
    {
        return NO_DIGITS_PASSWORD;
    }

    public static String getLessCharactersPassword()
    {
        return LESS_CHARACTERS_PASSWORD;
    }

    public static String getMoreCharactersPassword()
    {
        return MORE_CHARACTERS_PASSWORD;
    }

    public static String getNoUppercasePassword()
    {
        return NO_UPPERCASE_PASSWORD;
    }

    public static String getNoLowercasePassword()
    {
        return NO_LOWERCASE_PASSWORD;
    }

    public static String getNoSpecialCharactersPassword()
    {
        return NO_SPECIAL_CHARACTERS_PASSWORD;
    }

    public static String getForbiddenCharactersPassword()
    {
        return FORBIDDEN_CHARACTERS_PASSWORD;
    }

    public static String getEmptyString()
    {
     return EMPTY_STRING;
    }

    public static String getBlankSpace()
    {
        return BLANK_SPACE;
    }
}