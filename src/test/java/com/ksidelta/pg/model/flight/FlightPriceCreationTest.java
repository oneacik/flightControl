package com.ksidelta.pg.model.flight;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlightPriceCreationTest {

    @Test
    public void whenPriceIsZeroThenIncorrectNegativeOrZeroPriceExceptionIsThrown() {
        assertThrows(NegativeOrZeroPriceException.class, () -> FlightPrice.createFlightPrice(BigDecimal.ZERO, Instant.ofEpochSecond(0), Instant.ofEpochSecond(1)));
    }

    @Test
    public void whenPriceIsLessThanZeroThenNegativeOrZeroPriceExceptionIsThrown() {
        assertThrows(NegativeOrZeroPriceException.class, () -> FlightPrice.createFlightPrice(BigDecimal.ONE.negate(), Instant.ofEpochSecond(0), Instant.ofEpochSecond(1)));
    }

    @Test
    public void whenDurationOfOfferIsZeroThenNegativeOrZeroPeriodExceptionIsThrown() {
        assertThrows(
                NegativeOrZeroPeriodException.class,
                () -> FlightPrice.createFlightPrice(BigDecimal.ONE, Instant.ofEpochSecond(0), Instant.ofEpochSecond(0))
        );
    }

    @Test
    public void whenDurationOfOfferIsLessThanZeroThenNegativeOrZeroPeriodExceptionIsThrown() {
        assertThrows(
                NegativeOrZeroPeriodException.class,
                () -> FlightPrice.createFlightPrice(BigDecimal.ONE, Instant.ofEpochSecond(1), Instant.ofEpochSecond(0))
        );
    }

    @Test
    public void whenDurationIsGreaterThanZeroAndPriceIsGreaterThanZeroThenFlightPriceIsCreated(){
        final var flightPrice = FlightPrice.createFlightPrice(BigDecimal.ONE, Instant.ofEpochSecond(0), Instant.ofEpochSecond(1));

        assertThat(flightPrice.getPrice(), equalTo(BigDecimal.ONE));
        assertThat(flightPrice.getStartOfAppliance(), equalTo(Instant.ofEpochSecond(0)));
        assertThat(flightPrice.getEndOfAppliance(), equalTo(Instant.ofEpochSecond(1)));
    }
}
