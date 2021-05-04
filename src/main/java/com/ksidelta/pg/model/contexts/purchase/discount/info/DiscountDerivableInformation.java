package com.ksidelta.pg.model.contexts.purchase.discount.info;

public class DiscountDerivableInformation {
    BuyerInformation buyerInformation;
    FlightInformation flightInformation;

    public DiscountDerivableInformation(BuyerInformation buyerInformation, FlightInformation flightInformation) {
        this.buyerInformation = buyerInformation;
        this.flightInformation = flightInformation;
    }

    public BuyerInformation getBuyerInformation() {
        return buyerInformation;
    }

    public FlightInformation getFlightInformation() {
        return flightInformation;
    }
}
