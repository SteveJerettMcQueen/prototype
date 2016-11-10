/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Steve
 */
public class TimeUtils {

    public static String defaultFormat(Date date) {
        return new SimpleDateFormat("MM/dd/yyyy").format(date);
    }

    public static LocalDate getFirstDate() {
        return LocalDate.now()
                .withMonth(Month.JANUARY.getValue())
                .withYear(LocalDate.now().getYear())
                .withDayOfMonth(1);

    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now()
                .withMonth(LocalDate.now().getMonthValue())
                .withYear(LocalDate.now().getYear())
                .withDayOfMonth(LocalDate.now().getDayOfMonth());
    }

    /*
    Format for the database
     */
    public static Date parse(LocalDate ld) {
        try {
            String format = "yyyy-MM-dd";
            DateFormat df = new SimpleDateFormat(format);
            return df.parse(ld.format(DateTimeFormatter.ofPattern(format)));
        } catch (ParseException ex) {
            return null;
        }
    }

}
