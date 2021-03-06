package com.ksidelta.pg.model.contexts.flight;

import com.ksidelta.pg.model.kernel.flight.FlightId;
import com.ksidelta.pg.model.contexts.flight.itinerary.FlightItinerary;
import com.ksidelta.pg.model.contexts.flight.itinerary.step.LocationInTime;
import com.ksidelta.pg.model.contexts.flight.prices.FlightPrices;
import com.ksidelta.pg.model.contexts.flight.prices.price.FlightPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.time.Instant.ofEpochSecond;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FlightAggregateTest {
    @Test
    public void givenCorrectDataWhenFlightAggregateIsCreatedThenItSucceeds() {
        final var flightAggregate = FlightAggregate.createNewFlight(
                createSampleFlightId(),
                createSampleFlightItinerary(),
                createSampleFlightPrices()
        );

        assertThat(flightAggregate.getFlightId(), equalTo(createSampleFlightId()));
        assertThat(flightAggregate.getFlightItinerary(), equalTo(createSampleFlightItinerary()));
        assertThat(flightAggregate.getFlightPrices(), equalTo(createSampleFlightPrices()));
    }

    @ParameterizedTest
    @MethodSource("invalidFlightPricesInTime")
    public void givenOverlappingFlightPricesTimesOnFlightTimesWhenFlightAggregateIsCreatedThenItThrowsInvalidPurchasePeriodException(FlightPrices flightPrices) {
        Assertions.assertThrows(InvalidPurchasePeriodException.class, () -> FlightAggregate.createNewFlight(
                createSampleFlightId(),
                createSampleFlightItinerary(),
                flightPrices
        ));
    }

    private static List<FlightPrices> invalidFlightPricesInTime() {
        return Arrays.asList(
                FlightPrices.createFlightPrices(Collections.singletonList(
                        FlightPrice.createFlightPrice(BigDecimal.ONE, ofEpochSecond(0), ofEpochSecond(15))
                )),
                FlightPrices.createFlightPrices(Collections.singletonList(
                        FlightPrice.createFlightPrice(BigDecimal.ONE, ofEpochSecond(15), ofEpochSecond(30))
                )),
                FlightPrices.createFlightPrices(Collections.singletonList(
                        FlightPrice.createFlightPrice(BigDecimal.ONE, ofEpochSecond(0), ofEpochSecond(30))
                ))
        );
    }

    private FlightId createSampleFlightId() {
        return FlightId.createFlightId("KLM12345ABC");
    }

    private FlightItinerary createSampleFlightItinerary() {
        return FlightItinerary.createFlightItinerary(
                LocationInTime.createLocationInTime("Poland", "Chopin", OffsetDateTime.ofInstant(ofEpochSecond(10), ZoneId.of("Europe/Warsaw"))),
                LocationInTime.createLocationInTime("Poland", "Modlin", OffsetDateTime.ofInstant(ofEpochSecond(20), ZoneId.of("Europe/Warsaw")))
        );
    }

    private FlightPrices createSampleFlightPrices() {
        return FlightPrices.createFlightPrices(Arrays.asList(
                FlightPrice.createFlightPrice(BigDecimal.ONE, ofEpochSecond(1), ofEpochSecond(2)),
                FlightPrice.createFlightPrice(BigDecimal.TEN, ofEpochSecond(2), ofEpochSecond(3))
        ));
    }
}
