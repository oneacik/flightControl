package com.ksidelta.pg.model.flight.id;

import java.util.regex.Pattern;

public class FlightId {
    static Pattern flightIdPattern = Pattern.compile("[A-Z]{3}[\\d]{5}[A-Z]{3}");

    String flightId;

    FlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getFlightId() {
        return flightId;
    }

    public static FlightId createFlightId(String flightId) {
        assertFlightIdPattern(flightId);

        return new FlightId(flightId);
    }

    public static void assertFlightIdPattern(String flightId) {
        if (!flightIdPattern.matcher(flightId).matches()) {
            throw new InvalidFlightId();
        }
    }
}
