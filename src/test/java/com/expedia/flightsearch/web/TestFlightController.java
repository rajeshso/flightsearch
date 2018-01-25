package com.expedia.flightsearch.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightSearchController.class)
public class TestFlightController {
    @Autowired
    protected MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void givenAsixOClockFlightReturnBoth0730And1030Flights() throws Exception {
        FlightInput flightInput = new FlightInput();
        flightInput.setTime("06:00AM");
        this.mvc.perform(post("/api/flight").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(flightInput)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json("{\"flights\":[{\"flight\":\"Air Canada 8099\",\"departure\":\"7:30AM\"},{\"flight\":\"United Airline 6115\",\"departure\":\"10:30AM\"}],\"additionalProperties\":{}}"));

    }

    @Test
    public void givenAErrorInputReturnError() throws Exception {
        FlightInput flightInput = new FlightInput();
        this.mvc.perform(post("/api/flight").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(flightInput)).accept(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"flights\":null,\"additionalProperties\":{\"error\":\"Invaild Input\"}}"));
    }
}
