package com.expedia.flightsearch.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataSource {

    private final static String availableFlightsPath = System.getProperty("config", "src\\main\\resources\\flight.json");

    public FlightCollection getJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(availableFlightsPath), FlightCollection.class);
    }
}
