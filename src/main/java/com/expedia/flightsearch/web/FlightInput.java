package com.expedia.flightsearch.web;

import java.text.ParseException;

import static com.expedia.flightsearch.business.TimeUtil.get24HoursTime;

class FlightInput {
    private String time;

    public FlightInput() {
    }

    public FlightInput(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isValid() {
        if (time == null) return false;
        try {
            get24HoursTime(time);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}