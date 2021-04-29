package com.ksidelta.pg.model.flight.itinerary.step;

import java.time.OffsetDateTime;

public class LocationInTime {
    private final String country;
    private final String airport;

    private final OffsetDateTime offsetDateTime;

    private LocationInTime(String country, String airport, OffsetDateTime offsetDateTime) {
        this.country = country;
        this.airport = airport;
        this.offsetDateTime = offsetDateTime;
    }

    public String getCountry() {
        return country;
    }

    public String getAirport() {
        return airport;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public static LocationInTime createLocationInTime(String country, String airport, OffsetDateTime offsetDateTime) {
        return new LocationInTime(country, airport, offsetDateTime);
    }
}
