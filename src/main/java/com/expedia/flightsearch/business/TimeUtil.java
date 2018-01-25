package com.expedia.flightsearch.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import static java.lang.Math.abs;
import static java.time.temporal.ChronoUnit.MINUTES;

public class TimeUtil {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("hh:mma"); //Referred java doc for the supported formats

    public static LocalTime get24HoursTime(String timeIn12Hours) throws ParseException {
        Date date = SIMPLE_DATE_FORMAT.parse(timeIn12Hours);
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
    }

    public static long absoluteDifferenceInTime(LocalTime from, LocalTime to) {
        return abs(from.until(to, MINUTES)); //referred java doc
    }
}
