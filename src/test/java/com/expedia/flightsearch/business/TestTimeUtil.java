package com.expedia.flightsearch.business;

import org.junit.Test;

import java.text.ParseException;
import java.time.LocalTime;
import static org.assertj.core.api.Assertions.*;

public class TestTimeUtil {

    @Test
    public void testConversionOf06AMTO06Hours() throws ParseException {
        String input = "06:00AM";
        String expected = "06:00";
        LocalTime localTime = TimeUtil.get24HoursTime(input);
        assertThat(localTime.toString()).isEqualTo(expected);
    }

    @Test
    public void testConversionOf12AMTO12Hours() throws ParseException {
        String input = "12:00PM";
        String expected = "12:00";
        LocalTime localTime = TimeUtil.get24HoursTime(input);
        assertThat(localTime.toString()).isEqualTo(expected);
    }
    @Test
    public void testConversionOf12AMTO15Hours() throws ParseException {
        String input = "03:00PM";
        String expected = "15:00";
        LocalTime localTime = TimeUtil.get24HoursTime(input);
        assertThat(localTime.toString()).isEqualTo(expected);
    }

    @Test
    public void testDifferenceInTimeBetween06And07HoursShouldBe60() throws ParseException {
        LocalTime from = TimeUtil.get24HoursTime("06:00AM");
        LocalTime to = TimeUtil.get24HoursTime("07:00AM");
        long actual = TimeUtil.absoluteDifferenceInTime(from, to);
        long expected = 60;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDifferenceInTimeBetween0630And07HoursShouldBe30() throws ParseException {
        LocalTime from = TimeUtil.get24HoursTime("06:30AM");
        LocalTime to = TimeUtil.get24HoursTime("07:00AM");
        long actual = TimeUtil.absoluteDifferenceInTime(from, to);
        long expected = 30;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDifferenceInTimeBetween0630AMAnd0330AMShouldBe180() throws ParseException {
        LocalTime from = TimeUtil.get24HoursTime("06:30AM");
        LocalTime to = TimeUtil.get24HoursTime("03:30AM");
        long actual = TimeUtil.absoluteDifferenceInTime(from, to);
        long expected = 180;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDifferenceInTimeBetween0330AMAnd0630AMShouldBe180() throws ParseException {
        LocalTime from = TimeUtil.get24HoursTime("03:30AM");
        LocalTime to = TimeUtil.get24HoursTime("06:30AM");
        long actual = TimeUtil.absoluteDifferenceInTime(from, to);
        long expected = 180;
        assertThat(actual).isEqualTo(expected);
    }
}
