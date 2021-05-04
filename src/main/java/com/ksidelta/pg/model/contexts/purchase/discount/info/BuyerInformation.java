package com.ksidelta.pg.model.contexts.purchase.discount.info;

import java.time.Instant;

public class BuyerInformation {
    Instant dayOfBirth;

    public BuyerInformation(Instant dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public Instant getDayOfBirth() {
        return dayOfBirth;
    }
}
