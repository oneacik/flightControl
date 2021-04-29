package com.ksidelta.pg.model.flight;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.time.Instant.ofEpochSecond;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlightPricesTest {

    @Test
    public void givenFlightPricePeriodsAreEmptyThenEmptyPeriodsExceptionIsThrown() {
        assertThrows(EmptyFlightPricesException.class, () -> FlightPrices.createFlightPrices(Lists.emptyList()));
    }

    @ParameterizedTest
    @MethodSource("overlappingFlightPrices")
    public void givenFlightPricePeriodsAreOverlappingThenOverlappingPeriodExceptionIsThrown(List<FlightPrice> overlappingFlightPrices) {
        assertThrows(OverlappingPeriodException.class, () -> FlightPrices.createFlightPrices(overlappingFlightPrices));
    }

    @ParameterizedTest
    @MethodSource("discontinuousFlightPrices")
    public void givenFlightPricePeriodsAreDiscontinuousThenOverlappingPeriodExceptionIsThrown(List<FlightPrice> discontinuousFlightPrices) {
        assertThrows(NonContinuousPeriodException.class, () -> FlightPrices.createFlightPrices(discontinuousFlightPrices));

    }

    @ParameterizedTest
    @MethodSource("continuousFlightPrices")
    public void givenFlightPricePeriodsAreContinuousThenObjectIsCreated(List<FlightPrice> continuousFlightPrices) {
        final var flightPricesObject = FlightPrices.createFlightPrices(continuousFlightPrices);

        assertThat(flightPricesObject, notNullValue());
    }

    @Test
    public void whenBegginingOfBuyingPeriodIsGotThenLowestApplianceStartingDateIsReturned() {
        final var flightPrices = FlightPrices.createFlightPrices(asList(
                FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(1)),
                FlightPrice.createFlightPrice(ONE, ofEpochSecond(1), ofEpochSecond(2))
        ));

        final var beginningOfBuyingPeriod = flightPrices.beginningOfBuyingPeriod();

        assertThat(beginningOfBuyingPeriod, equalTo(ofEpochSecond(0)));
    }

    @Test
    public void whenEndOfBuyingPeriodIsGotThenHighestApplianceEndingDateDateIsReturned() {
        final var flightPrices = FlightPrices.createFlightPrices(asList(
                FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(1)),
                FlightPrice.createFlightPrice(ONE, ofEpochSecond(1), ofEpochSecond(2))
        ));

        final var endOfBuyingPeriod = flightPrices.endOfBuyingPeriod();

        assertThat(endOfBuyingPeriod, equalTo(ofEpochSecond(2)));
    }

    @ParameterizedTest
    @MethodSource("gotPriceCases")
    public void givenOverlappingTimeWhenPriceIsGotThenItMatchesFlightPrice(GotPriceCase gotPriceCases) {
        final var flightPrices = FlightPrices.createFlightPrices(gotPriceCases.flightPrices);

        final var currentPrice = flightPrices.priceForGivenTime(gotPriceCases.time);

        assertThat(currentPrice, equalTo(gotPriceCases.flightPrice));
    }

    public static List<List<FlightPrice>> overlappingFlightPrices() {
        return asList(
                asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(2)),
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(1), ofEpochSecond(3))
                ),
                asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(1), ofEpochSecond(3)),
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(2))
                ),
                asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(2)),
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(1), ofEpochSecond(2))
                ),
                asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(2)),
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(1))
                )
        );
    }

    public static List<List<FlightPrice>> discontinuousFlightPrices() {
        return asList(
                asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(2)),
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(3), ofEpochSecond(4))
                ),
                asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(3), ofEpochSecond(4)),
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(2))
                )
        );
    }

    public static List<List<FlightPrice>> continuousFlightPrices() {
        return asList(
                asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(1)),
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(1), ofEpochSecond(2))
                ),
                asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(1), ofEpochSecond(2)),
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(1))
                )
        );
    }

    public static List<GotPriceCase> gotPriceCases() {
        return Arrays.asList(
                new GotPriceCase(asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(2)),
                        FlightPrice.createFlightPrice(TEN, ofEpochSecond(2), ofEpochSecond(4))
                ), ofEpochSecond(0), Optional.of(FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(2)))),
                new GotPriceCase(asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(2)),
                        FlightPrice.createFlightPrice(TEN, ofEpochSecond(2), ofEpochSecond(4))
                ), ofEpochSecond(1), Optional.of(FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(2)))),
                new GotPriceCase(asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(2)),
                        FlightPrice.createFlightPrice(TEN, ofEpochSecond(2), ofEpochSecond(4))
                ), ofEpochSecond(2), Optional.of(FlightPrice.createFlightPrice(TEN, ofEpochSecond(2), ofEpochSecond(4)))),

                new GotPriceCase(asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(1), ofEpochSecond(2)),
                        FlightPrice.createFlightPrice(TEN, ofEpochSecond(2), ofEpochSecond(3))
                ), ofEpochSecond(0), Optional.empty()),
                new GotPriceCase(asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(2)),
                        FlightPrice.createFlightPrice(TEN, ofEpochSecond(2), ofEpochSecond(4))
                ), ofEpochSecond(4), Optional.empty()),
                new GotPriceCase(asList(
                        FlightPrice.createFlightPrice(ONE, ofEpochSecond(0), ofEpochSecond(2)),
                        FlightPrice.createFlightPrice(TEN, ofEpochSecond(2), ofEpochSecond(4))
                ), ofEpochSecond(5), Optional.empty())
        );
    }
}

class GotPriceCase {
    List<FlightPrice> flightPrices;
    Instant time;
    Optional<FlightPrice> flightPrice;

    public GotPriceCase(List<FlightPrice> flightPrices, Instant time, Optional<FlightPrice> flightPrice) {
        this.flightPrices = flightPrices;
        this.time = time;
        this.flightPrice = flightPrice;
    }

    public List<FlightPrice> getFlightPrices() {
        return flightPrices;
    }

    public Instant getTime() {
        return time;
    }

    public Optional<FlightPrice> getFlightPrice() {
        return flightPrice;
    }
}
