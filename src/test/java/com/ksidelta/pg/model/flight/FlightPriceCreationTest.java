package com.ksidelta.pg.model.flight;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlightPriceCreationTest {

    @Test
    public void whenPriceIsZeroThenIncorrectPriceExceptionIsThrown() {
        assertThrows(IncorrectPriceException.class, () -> FlightPrice.createFlightPrice(BigDecimal.ZERO, Instant.ofEpochSecond(0), Instant.ofEpochSecond(1)))
    }

    @Test
    public void whenPriceIsLessThanZeroThenIncorrectPriceExceptionIsThrown() {
        assertThrows(IncorrectPriceException.class, () -> FlightPrice.createFlightPrice(BigDecimal.ONE.negate(), Instant.ofEpochSecond(0), Instant.ofEpochSecond(1)))
    }

    @Test
    public void whenDurationOfOfferIsZeroThenInvalidPeriodExceptionIsThrown() {
        assertThrows(
                InvalidPeriodException.class,
                () -> FlightPrice.createFlightPrice(BigDecimal.ONE, Instant.ofEpochSecond(0), Instant.ofEpochSecond(0))
        );
    }

    @Test
    public void whenDurationOfOfferIsLessThanZeroThenInvalidPeriodExceptionIsThrown() {
        assertThrows(
                InvalidPeriodException.class,
                () -> FlightPrice.createFlightPrice(BigDecimal.ONE, Instant.ofEpochSecond(0), Instant.ofEpochSecond(0))
        );
    }

    public void whenDurationIsGreaterThanZeroAndPriceIsGreaterThanZeroThenFlightPriceIsCreated(){
        final var flightPrice = FlightPrice.createFlightPrice(BigDecimal.ONE, Instant.ofEpochSecond(0), Instant.ofEpochSecond(1));

        assertThat(flightPrice.getPrice(), equalTo(BigDecimal.ONE));
        assertThat(flightPrice.getStartOfAppliance(), equalTo(Instant.ofEpochSecond(0)));
        assertThat(flightPrice.getEndOfAppliance(), equalTo(Instant.ofEpochSecond(1)));
    }
}
