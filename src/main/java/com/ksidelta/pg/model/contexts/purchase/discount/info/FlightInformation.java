package com.ksidelta.pg.model.contexts.purchase.discount.info;

import java.time.OffsetDateTime;

public class FlightInformation {

    OffsetDateTime dayOfFlight;
    String destinationCountry;

    public FlightInformation(OffsetDateTime dayOfFlight, String destinationCountry) {
        this.dayOfFlight = dayOfFlight;
        this.destinationCountry = destinationCountry;
    }

    public OffsetDateTime getDayOfFlight() {
        return dayOfFlight;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }
}
