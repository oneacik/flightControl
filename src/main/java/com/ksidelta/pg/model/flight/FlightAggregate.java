package com.ksidelta.pg.model.flight;

import com.ksidelta.pg.model.flight.id.FlightId;
import com.ksidelta.pg.model.flight.itinerary.FlightItinerary;
import com.ksidelta.pg.model.flight.prices.FlightPrices;

public class FlightAggregate {
    FlightId flightId;
    FlightPrices flightPrices;
    FlightItinerary flightItenary;
}
