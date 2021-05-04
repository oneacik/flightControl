package com.ksidelta.pg.model.contexts.purchase.discount.list;

import com.ksidelta.pg.model.contexts.purchase.discount.Discount;

import java.time.DayOfWeek;

public class AfricanThursdayDiscount extends Discount {
    public AfricanThursdayDiscount() {
        super("African Thursday Discount", discountDerivableInformation -> {
                    final var dayOfWeekOfDeparture = discountDerivableInformation.getFlightInformation().getDayOfFlight().getDayOfWeek();
                    final var countryOfDeparture = discountDerivableInformation.getFlightInformation().getDestinationCountry();

                    final var isDepartureOnThursday = dayOfWeekOfDeparture.compareTo(DayOfWeek.THURSDAY) == 0;
                    final var isDestinationCountryAfrica = countryOfDeparture.equals("Africa");

                    return isDepartureOnThursday && isDestinationCountryAfrica;
                }
        );
    }
}
