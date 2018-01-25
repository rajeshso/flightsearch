package com.expedia.flightsearch.domain;

// Used an online converter of json to pojos to create this class

public class FlightDetails {

    private String flight;
    private String departure;

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

}
