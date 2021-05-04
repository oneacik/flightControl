package com.ksidelta.pg.model.translation.purchase.discount.info;

import com.ksidelta.pg.model.contexts.flight.itinerary.FlightItinerary;
import com.ksidelta.pg.model.contexts.purchase.discount.info.BuyerInformation;
import com.ksidelta.pg.model.contexts.purchase.discount.info.DiscountDerivableInformation;
import com.ksidelta.pg.model.contexts.purchase.discount.info.FlightInformation;
import com.ksidelta.pg.model.contexts.tenant.PersonalInformation;

public class DiscountDerivableInformationTranslator {
    public DiscountDerivableInformation translate(FlightItinerary flightItinerary, PersonalInformation personalInformation) {
        return new DiscountDerivableInformation(
                new BuyerInformation(personalInformation.getDateOfBirth()),
                new FlightInformation(flightItinerary.getOrigin().getOffsetDateTime(), flightItinerary.getDestination().getCountry())
        );
    }
}
