package com.ksidelta.pg.model.contexts.flight.itinerary.step;

import java.time.OffsetDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationInTime that = (LocationInTime) o;
        return Objects.equals(country, that.country) && Objects.equals(airport, that.airport) && Objects.equals(offsetDateTime, that.offsetDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, airport, offsetDateTime);
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public static LocationInTime createLocationInTime(String country, String airport, OffsetDateTime offsetDateTime) {
        return new LocationInTime(country, airport, offsetDateTime);
    }
}
