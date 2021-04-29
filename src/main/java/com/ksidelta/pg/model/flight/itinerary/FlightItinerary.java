package com.ksidelta.pg.model.flight.itinerary;

import com.ksidelta.pg.model.flight.itinerary.step.LocationInTime;

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

    public static FlightItinerary createFlightItinerary(LocationInTime origin, LocationInTime destination) {
        return new FlightItinerary(origin, destination);
    }
}
