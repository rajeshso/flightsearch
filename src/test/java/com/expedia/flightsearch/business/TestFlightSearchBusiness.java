package com.expedia.flightsearch.business;

import com.expedia.flightsearch.domain.DataSource;
import com.expedia.flightsearch.domain.FlightCollection;
import com.expedia.flightsearch.domain.FlightDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestFlightSearchBusiness {

    private DataSource dataSource = mock(DataSource.class);

    @Test
    public void givenAsixOClockFlightThenGetThatFlight() throws IOException
    {
        LocalTime time = LocalTime.of(6,0);
        FlightSearchBusiness flightSearchBusiness = new FlightSearchBusiness();
        flightSearchBusiness.setDataSource(dataSource);
        when(dataSource.getJSON()).thenReturn(this.getMockFlightCollection());
        FlightCollection flightCollection = flightSearchBusiness.getClosestFlights(time,  1);
        assertThat(flightCollection.getFlights().size()).isEqualTo(1);
        assertThat(flightCollection.getFlights().get(0).getFlight()).isEqualTo("A");
    }
    @Test
    public void givenAsixOClockAnd10AClockFlightFlightThenBothFlightsFor5Hours() throws IOException
    {
        LocalTime time = LocalTime.of(6,0);
        FlightSearchBusiness flightSearchBusiness = new FlightSearchBusiness();
        flightSearchBusiness.setDataSource(dataSource);
        when(dataSource.getJSON()).thenReturn(this.getMockFlightCollection());
        FlightCollection flightCollection = flightSearchBusiness.getClosestFlights(time,  5);
        assertThat(flightCollection.getFlights().size()).isEqualTo(2);
        assertThat(flightCollection.getFlights().get(0).getFlight()).isEqualTo("A");
        assertThat(flightCollection.getFlights().get(1).getFlight()).isEqualTo("B");
    }
    @Test
    public void getBeforeAndAfterFlights() throws IOException
    {
        LocalTime time = LocalTime.of(8,0);
        FlightSearchBusiness flightSearchBusiness = new FlightSearchBusiness();
        flightSearchBusiness.setDataSource(dataSource);
        when(dataSource.getJSON()).thenReturn(this.getMockFlightCollection());
        FlightCollection flightCollection = flightSearchBusiness.getClosestFlights(time,  5);
        assertThat(flightCollection.getFlights().size()).isEqualTo(2);
        assertThat(flightCollection.getFlights().get(0).getFlight()).isEqualTo("A");
        assertThat(flightCollection.getFlights().get(1).getFlight()).isEqualTo("B");
    }
    @Test
    public void getNoFlights() throws IOException
    {
        LocalTime time = LocalTime.of(18,0);
        FlightSearchBusiness flightSearchBusiness = new FlightSearchBusiness();
        flightSearchBusiness.setDataSource(dataSource);
        when(dataSource.getJSON()).thenReturn(this.getMockFlightCollection());
        FlightCollection flightCollection = flightSearchBusiness.getClosestFlights(time,  5);
        assertThat(flightCollection.getFlights().size()).isEqualTo(0);
    }
    private FlightCollection getMockFlightCollection() {
        FlightCollection flightCollection = new FlightCollection();
        FlightDetails flightDetails1 = new FlightDetails();
        flightDetails1.setFlight("A");
        flightDetails1.setDeparture("06:00AM");
        FlightDetails flightDetails2 = new FlightDetails();
        flightDetails2.setFlight("B");
        flightDetails2.setDeparture("10:00AM");
        List<FlightDetails> flights = new ArrayList<>(2);
        flights.add(flightDetails1);
        flights.add(flightDetails2);
        flightCollection.setFlights(flights);
        return flightCollection;
    }
}
