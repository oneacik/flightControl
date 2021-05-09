package com.ksidelta.pg.model.contexts.flight;

import com.ksidelta.pg.model.kernel.flight.FlightId;
import com.ksidelta.pg.model.contexts.flight.itinerary.FlightItinerary;
import com.ksidelta.pg.model.contexts.flight.prices.FlightPrices;

import static com.google.common.base.Preconditions.checkNotNull;

public class FlightAggregate {
    FlightId flightId;
    FlightPrices flightPrices;
    FlightItinerary flightItinerary;

    private FlightAggregate() {
    }

    public FlightPrices getFlightPrices() {
        return flightPrices;
    }

    // TODO: Tests
    public void updateFlightPrices(FlightPrices flightPrices) {
        assertFlightPricesAreBeforeDepartureDate(flightItinerary, flightPrices);

        this.flightPrices = flightPrices;
    }

    public FlightItinerary getFlightItinerary() {
        return flightItinerary;
    }

    // TODO: Tests
    public void updateFlightItinerary(FlightItinerary flightItinerary) {
        assertFlightPricesAreBeforeDepartureDate(flightItinerary, flightPrices);

        this.flightItinerary = flightItinerary;
    }

    public FlightId getFlightId() {
        return flightId;
    }

    //NOTE: FlightId is assumed to be fed by user, however it may appear to be generated after talking with PM
    public static FlightAggregate createNewFlight(FlightId flightId, FlightItinerary flightItinerary, FlightPrices flightPrices) {
        assertFlightPricesAreBeforeDepartureDate(flightItinerary, flightPrices);

        final var flightAggregate = new FlightAggregate();

        flightAggregate.flightId = flightId;
        flightAggregate.flightItinerary = flightItinerary;
        flightAggregate.flightPrices = flightPrices;

        return flightAggregate;
    }

    public static void assertFlightPricesAreBeforeDepartureDate(FlightItinerary flightItinerary, FlightPrices flightPrices) {
        if (flightItinerary.getOrigin().getOffsetDateTime().toInstant().compareTo(flightPrices.endOfBuyingPeriod()) < 0) {
            throw new InvalidPurchasePeriodException();
        }
    }
}
