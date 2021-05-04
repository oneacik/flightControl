package com.ksidelta.pg.model.contexts.flight.itinerary;

import com.ksidelta.pg.model.contexts.flight.itinerary.step.LocationInTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.*;
import java.util.Arrays;
import java.util.List;

import static java.time.Instant.ofEpochSecond;
import static java.time.OffsetDateTime.ofInstant;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FlightItineraryTest {

    @ParameterizedTest
    @MethodSource("negativeOrZeroPairs")
    public void givenNegativeOrZeroPeriodBetweenLocationsInTimeWhenItineraryIsCreatedThenTimeTravelExceptionIsThrown(LocationInTimePair locationInTimePair) {
        Assertions.assertThrows(TimeTravelException.class, () -> FlightItinerary.createFlightItinerary(locationInTimePair.origin, locationInTimePair.destination));
    }

    @ParameterizedTest
    @MethodSource("positivePairs")
    public void givenPositivePeriodBetweenLocationsInTimeWhenItineraryIsCreatedThenItSucceeds(LocationInTimePair locationInTimePair) {
        final var flightItinerary = FlightItinerary.createFlightItinerary(locationInTimePair.origin, locationInTimePair.destination);

        assertThat(flightItinerary.getOrigin(), equalTo(locationInTimePair.origin));
        assertThat(flightItinerary.getDestination(), equalTo(locationInTimePair.destination));
    }

    public static List<LocationInTimePair> negativeOrZeroPairs() {
        return Arrays.asList(
                new LocationInTimePair(
                        LocationInTime.createLocationInTime("Poland", "Chopin", ofInstant(ofEpochSecond(3600), ZoneId.of("Europe/Warsaw"))),
                        LocationInTime.createLocationInTime("Spain", "Hola", ofInstant(ofEpochSecond(3600), ZoneId.of("Europe/Madrid")))
                ),
                new LocationInTimePair(
                        LocationInTime.createLocationInTime("Poland", "Chopin", ofInstant(ofEpochSecond(3600), ZoneId.of("Europe/Warsaw"))),
                        LocationInTime.createLocationInTime("Poland", "Modlin", ofInstant(ofEpochSecond(0), ZoneId.of("Europe/Warsaw")))
                )
        );
    }

    public static List<LocationInTimePair> positivePairs() {
        return Arrays.asList(
                new LocationInTimePair(
                        LocationInTime.createLocationInTime("Poland", "Chopin", ofInstant(ofEpochSecond(0), ZoneId.of("Europe/Warsaw"))),
                        LocationInTime.createLocationInTime("Spain", "Hola", ofInstant(ofEpochSecond(3600), ZoneId.of("Europe/Madrid")))
                ),
                new LocationInTimePair(
                        LocationInTime.createLocationInTime("Poland", "Chopin", ofInstant(ofEpochSecond(0), ZoneId.of("Europe/Warsaw"))),
                        LocationInTime.createLocationInTime("Poland", "Modlin", ofInstant(ofEpochSecond(3600), ZoneId.of("Europe/Warsaw")))
                )
        );
    }

    static class LocationInTimePair {
        LocationInTime origin;
        LocationInTime destination;

        public LocationInTimePair(LocationInTime origin, LocationInTime destination) {
            this.origin = origin;
            this.destination = destination;
        }
    }

}
