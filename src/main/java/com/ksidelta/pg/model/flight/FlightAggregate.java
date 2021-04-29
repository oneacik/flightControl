package com.ksidelta.pg.model.flight;

import com.ksidelta.pg.model.flight.id.FlightId;
import com.ksidelta.pg.model.flight.itinerary.FlightItinerary;
import com.ksidelta.pg.model.flight.prices.FlightPrices;

public class FlightAggregate {
    FlightId flightId;
    FlightPrices flightPrices;
    FlightItinerary flightItinerary;

    private FlightAggregate() {
    }

    public FlightId getFlightId() {
        return flightId;
    }

    public void setFlightId(FlightId flightId) {
        this.flightId = flightId;
    }

    public FlightPrices getFlightPrices() {
        return flightPrices;
    }

    public void setFlightPrices(FlightPrices flightPrices) {
        assertFlightPricesAreBeforeDepartureDate(flightItinerary, flightPrices);

        this.flightPrices = flightPrices;
    }

    public FlightItinerary getFlightItinerary() {
        return flightItinerary;
    }

    public void setFlightItinerary(FlightItinerary flightItinerary) {
        this.flightItinerary = flightItinerary;
    }

    public static FlightAggregate createFlightAggregate(FlightId flightId, FlightItinerary flightItinerary, FlightPrices flightPrices) {
        final var flightAggregate = new FlightAggregate();

        flightAggregate.setFlightId(flightId);
        flightAggregate.setFlightItinerary(flightItinerary);
        flightAggregate.setFlightPrices(flightPrices);

        return flightAggregate;
    }

    public void assertFlightPricesAreBeforeDepartureDate(FlightItinerary flightItinerary, FlightPrices flightPrices) {
        if (flightItinerary.getOrigin().getOffsetDateTime().toInstant().compareTo(flightPrices.endOfBuyingPeriod()) < 0) {
            throw new InvalidPurchasePeriodException();
        }
    }
}
