package com.ksidelta.pg.model.flight;

import java.math.BigDecimal;
import java.time.Instant;

public final class FlightPrice {
    BigDecimal price;
    Instant startOfAppliance;
    Instant endOfAppliance;

    FlightPrice(BigDecimal price, Instant startOfAppliance, Instant endOfAppliance) {
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
