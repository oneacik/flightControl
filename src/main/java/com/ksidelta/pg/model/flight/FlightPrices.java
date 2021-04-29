package com.ksidelta.pg.model.flight;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class FlightPrices {
    List<FlightPrice> sortedByTimeFlightPrices;

    FlightPrices(List<FlightPrice> sortedByTimeFlightPrices) {
        this.sortedByTimeFlightPrices = sortedByTimeFlightPrices;
    }

    public Instant endOfBuyingPeriod() {
        return Instant.now();
    }

    public Instant beginningOfBuyingPeriod() {
        return Instant.now();
    }

    public Optional<FlightPrice> priceForGivenTime(Instant givenTime) {
        return Optional.empty();
    }

    public static FlightPrices createFlightPrices(List<FlightPrice> flightPrices) {
        return new FlightPrices(flightPrices);
    }
}
