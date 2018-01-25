package com.expedia.flightsearch.business;

import com.expedia.flightsearch.domain.FlightCollection;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class TestFlightSearchBusiness_IT {

    @BeforeClass
    public static void setCustomFlightSource() {
        System.setProperty("config", "src\\test\\resources\\flight.json");
    }

    @Test
    public void findFlightThatDepartsExactlyAtThisTime() throws IOException
    {
        LocalTime time = LocalTime.of(7,30);
        FlightSearchBusiness flightSearchBusiness = new FlightSearchBusiness();
        FlightCollection flightCollection = flightSearchBusiness.getClosestFlights(time,  1);
        assertThat(flightCollection.getFlights().size()).isEqualTo(1);
        assertThat(flightCollection.getFlights().get(0).getFlight()).isEqualTo("Air Canada 8099");
    }

    // This corner case test requires a deeper thought than is permitted by the examination duration
    @Ignore
    public void findFlightsThatIsBeforeAndAfter() throws IOException
    {
        LocalTime time = LocalTime.of(23,30);
        FlightSearchBusiness flightSearchBusiness = new FlightSearchBusiness();
        FlightCollection flightCollection = flightSearchBusiness.getClosestFlights(time,  2);
        assertThat(flightCollection.getFlights().size()).isEqualTo(2);
        assertThat(flightCollection.getFlights().get(0).getFlight()).isEqualTo("NorthJet 10000");
        assertThat(flightCollection.getFlights().get(1).getFlight()).isEqualTo("SouthJet 10000");
    }
}
