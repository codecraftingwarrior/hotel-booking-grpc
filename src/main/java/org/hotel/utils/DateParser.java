package org.hotel.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateParser {

    public static Date parse(String date) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate;
        try {
            parsedDate = format.parse(date);
        } catch (ParseException e) {
            throw new Exception("format date invalide!");
        }

        return parsedDate;
    }

    public static LocalDate toLocalDate(Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }
}
