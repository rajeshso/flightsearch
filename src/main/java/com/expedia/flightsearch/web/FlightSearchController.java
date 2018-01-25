package com.expedia.flightsearch.web;

import com.expedia.flightsearch.business.FlightSearchBusiness;
import com.expedia.flightsearch.domain.FlightCollection;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.expedia.flightsearch.business.TimeUtil.get24HoursTime;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/")
public class FlightSearchController {
    private static final int HOURS_AROUND = 5;
    private final FlightSearchBusiness flightSearchBusiness = new FlightSearchBusiness();

    @RequestMapping(method = POST, value = "flight", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public FlightCollection searchClosestFlights(@RequestBody FlightInput departureTime) {
        FlightCollection result = new FlightCollection();
        if (isInputInvalid(departureTime)) {
            result.setAdditionalProperty("error", "Invaild Input");
        } else {
            try {
                result = flightSearchBusiness.getClosestFlights(get24HoursTime(departureTime.getTime()), HOURS_AROUND);
            } catch (Exception e) {
                result.setAdditionalProperty("error", "Invaild Input");
            }
        }
        return result;
    }

    private boolean isInputInvalid(FlightInput departureTime) {
        return departureTime == null || !departureTime.isValid();
    }
}
