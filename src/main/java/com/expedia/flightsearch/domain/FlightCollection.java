package com.expedia.flightsearch.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Used an online auto converter from json to java objects
public class FlightCollection {

    private List<FlightDetails> flights = null;
    private Map<String, Object> additionalProperties = new HashMap();

    public List<FlightDetails> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightDetails> flights) {
        this.flights = flights;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}