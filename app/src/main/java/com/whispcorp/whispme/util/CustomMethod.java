package com.whispcorp.whispme.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomMethod {

    public static Date StringToDate(String dateString){

        Date date = null;
        DateFormat format = new SimpleDateFormat(Constants.DateFormat.TRENDFORMAT);
        try {
            date = format.parse(dateString);

        } catch (ParseException e) {

            e.printStackTrace();
        }

        return date;
    }

}
