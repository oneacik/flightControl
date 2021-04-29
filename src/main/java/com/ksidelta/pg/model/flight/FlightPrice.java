package com.ksidelta.pg.model.flight;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.Date;

public class FlightPrice {
    BigDecimal price;
    Instant startOfAppliance;
    Instant endOfAppliance;

    protected FlightPrice(BigDecimal price, Instant startOfAppliance, Instant endOfAppliance) {
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
        return new FlightPrice(price, startOfAppliance, endOfAppliance);
    }
}
