package com.ksidelta.pg.model.flight.id;

import java.util.Objects;
import java.util.regex.Pattern;

public class FlightId {
    static Pattern flightIdPattern = Pattern.compile("[A-Z]{3}[\\d]{5}[A-Z]{3}");

    private final String flightId;

    private FlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getFlightId() {
        return flightId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightId flightId1 = (FlightId) o;
        return Objects.equals(flightId, flightId1.flightId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId);
    }

    public static FlightId createFlightId(String flightId) {
        assertFlightIdPattern(flightId);

        return new FlightId(flightId);
    }

    static void assertFlightIdPattern(String flightId) {
        if (!flightIdPattern.matcher(flightId).matches()) {
            throw new InvalidFlightIdException();
        }
    }
}
