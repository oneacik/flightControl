package com.ksidelta.pg.model.contexts.purchase.discount.list;

import com.ksidelta.pg.model.contexts.purchase.discount.Discount;

public class BirthdayDiscount extends Discount {
    public BirthdayDiscount() {
        super("Birthday Discount", discountDerivableInformation -> {
                    final var offset = discountDerivableInformation.getFlightInformation().getDayOfFlight().getOffset();
                    final var dayOfFlight = discountDerivableInformation.getFlightInformation().getDayOfFlight().getDayOfYear();
                    final var dayOfBuyerBirthday = discountDerivableInformation.getBuyerInformation().getDayOfBirth().atOffset(offset).getDayOfYear();

                    final var isFlightOnBuyersBirthDay = dayOfFlight == dayOfBuyerBirthday;
                    return isFlightOnBuyersBirthDay;
                }
        );
    }
}
