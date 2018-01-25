package com.expedia.flightsearch.business;

import com.expedia.flightsearch.domain.DataSource;
import com.expedia.flightsearch.domain.FlightCollection;
import com.expedia.flightsearch.domain.FlightDetails;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.expedia.flightsearch.business.TimeUtil.absoluteDifferenceInTime;
import static com.expedia.flightsearch.business.TimeUtil.get24HoursTime;

public class FlightSearchBusiness {

    private DataSource dataSource = new DataSource();

    public FlightCollection getClosestFlights(LocalTime timeOfDeparture, int hoursAround) throws IOException {
        FlightCollection flights = dataSource.getJSON();
        final int minutesAround = hoursAround * 60;
        List<FlightDetails> flightDetails = flights.getFlights().stream().filter(
                flightDetail -> {
                    try {
                        final long minutesDifference = absoluteDifferenceInTime(timeOfDeparture, get24HoursTime(flightDetail.getDeparture()));
                        return minutesDifference <= minutesAround;
                    } catch (ParseException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
        FlightCollection response = new FlightCollection();
        response.setFlights(flightDetails);
        return response;
    }
    void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}