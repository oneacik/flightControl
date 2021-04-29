package com.ksidelta.pg.model.flight;

import com.ksidelta.pg.model.flight.id.FlightId;
import com.ksidelta.pg.model.flight.itinerary.FlightItinerary;
import com.ksidelta.pg.model.flight.itinerary.step.LocationInTime;
import com.ksidelta.pg.model.flight.prices.FlightPrices;
import com.ksidelta.pg.model.flight.prices.price.FlightPrice;
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

public class FlightAggregateTest {
    @Test
    public void givenCorrectDataWhenFlightAggregateIsCreatedThenItSucceeds() {
        FlightAggregate.createFlightAggregate(
                createSampleFlightId(),
                createSampleFlightItinerary(),
                createSampleFlightPrices()
        );
    }

    @ParameterizedTest
    @MethodSource("invalidFlightPricesInTime")
    public void givenOverlappingFlightPricesTimesOnFlightTimesWhenFlightAggregateIsCreatedThenItThrowsInvalidPurchasePeriodException(FlightPrices flightPrices) {
        Assertions.assertThrows(InvalidPurchasePeriodException.class, () -> FlightAggregate.createFlightAggregate(
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
