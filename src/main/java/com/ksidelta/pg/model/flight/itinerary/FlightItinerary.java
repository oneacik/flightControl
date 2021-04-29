package com.ksidelta.pg.model.flight.itinerary;

import com.ksidelta.pg.model.flight.itinerary.step.LocationInTime;

import java.util.Objects;

public class FlightItinerary {
    LocationInTime origin;
    LocationInTime destination;

    public FlightItinerary(LocationInTime origin, LocationInTime destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public LocationInTime getOrigin() {
        return origin;
    }

    public LocationInTime getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightItinerary that = (FlightItinerary) o;
        return Objects.equals(origin, that.origin) && Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination);
    }

    public static FlightItinerary createFlightItinerary(LocationInTime origin, LocationInTime destination) {
        assertPositiveTimeDifference(origin, destination);

        return new FlightItinerary(origin, destination);
    }

    static void assertPositiveTimeDifference(LocationInTime origin, LocationInTime destination) {
        if (destination.getOffsetDateTime().compareTo(origin.getOffsetDateTime()) <= 0) {
            throw new TimeTravelException();
        }
    }
}
