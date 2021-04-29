package com.ksidelta.pg.model.flight;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightPrices {
    List<FlightPrice> sortedByTimeFlightPrices;

    FlightPrices(List<FlightPrice> sortedByTimeFlightPrices) {
        this.sortedByTimeFlightPrices = sortedByTimeFlightPrices;
    }

    public Instant endOfBuyingPeriod() {
        return sortedByTimeFlightPrices.get(sortedByTimeFlightPrices.size() - 1).getEndOfAppliance();
    }

    public Instant beginningOfBuyingPeriod() {
        return sortedByTimeFlightPrices.get(0).getStartOfAppliance();
    }

    public Optional<FlightPrice> priceForGivenTime(Instant givenTime) {
        return sortedByTimeFlightPrices.stream()
                .filter(flightPrice ->
                        flightPrice.getStartOfAppliance().compareTo(givenTime) <= 0
                                && flightPrice.getEndOfAppliance().compareTo(givenTime) > 0
                ).findFirst();
    }

    public static FlightPrices createFlightPrices(List<FlightPrice> flightPrices) {
        final var sortedFlightPrices = sortFlightPrices(flightPrices);

        assertNonEmptyFlightPrices(sortedFlightPrices);
        assertNotOverlappingFlightPrices(sortedFlightPrices);
        assertNotDiscontinousFlightPrices(sortedFlightPrices);

        return new FlightPrices(sortedFlightPrices);
    }

    private static List<FlightPrice> sortFlightPrices(List<FlightPrice> flightPrices) {
        return flightPrices.stream()
                .sorted((left, right) -> left.getStartOfAppliance().compareTo(right.getEndOfAppliance()))
                .collect(Collectors.toList());
    }

    private static void assertNonEmptyFlightPrices(List<FlightPrice> flightPrices) {
        if (flightPrices.isEmpty()) {
            throw new EmptyFlightPricesException();
        }
    }

    private static void assertNotOverlappingFlightPrices(List<FlightPrice> sortedFlightPrices) {
        sortedFlightPrices.stream().reduce((left, right) -> {
            if (left.endOfAppliance.isAfter(right.startOfAppliance)) {
                throw new OverlappingPeriodException();
            }
            return right;
        });
    }

    private static void assertNotDiscontinousFlightPrices(List<FlightPrice> sortedFlightPrices) {
        sortedFlightPrices.stream().reduce((left, right) -> {
            if (left.endOfAppliance.isBefore(right.startOfAppliance)) {
                throw new NonContinuousPeriodException();
            }
            return right;
        });
    }
}
