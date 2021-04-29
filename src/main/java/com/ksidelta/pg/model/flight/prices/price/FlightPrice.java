package com.ksidelta.pg.model.flight.prices.price;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public final class FlightPrice {
    private final BigDecimal price;
    private final Instant startOfAppliance;
    private final Instant endOfAppliance;

    private FlightPrice(BigDecimal price, Instant startOfAppliance, Instant endOfAppliance) {
        this.price = price;
        this.startOfAppliance = startOfAppliance;
        this.endOfAppliance = endOfAppliance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Instant getStartOfAppliance() {
        return startOfAppliance;
    }

    public Instant getEndOfAppliance() {
        return endOfAppliance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightPrice that = (FlightPrice) o;
        return Objects.equals(price, that.price) && Objects.equals(startOfAppliance, that.startOfAppliance) && Objects.equals(endOfAppliance, that.endOfAppliance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, startOfAppliance, endOfAppliance);
    }

    public static FlightPrice createFlightPrice(BigDecimal price, Instant startOfAppliance, Instant endOfAppliance) {
        assertPositivePrice(price);
        assertPositiveDuration(startOfAppliance, endOfAppliance);

        return new FlightPrice(price, startOfAppliance, endOfAppliance);
    }

    private static void assertPositivePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegativeOrZeroPriceException();
        }
    }

    private static void assertPositiveDuration(Instant startOfAppliance, Instant endOfAppliance) {
        if (!endOfAppliance.isAfter(startOfAppliance)) {
            throw new NegativeOrZeroPeriodException();
        }
    }
}
