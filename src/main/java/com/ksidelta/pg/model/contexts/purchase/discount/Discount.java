package com.ksidelta.pg.model.contexts.purchase.discount;

import com.ksidelta.pg.model.contexts.purchase.discount.info.DiscountDerivableInformation;

import java.util.function.Predicate;

public class Discount {
    String name;
    Predicate<DiscountDerivableInformation> discountAppliance;

    public Discount(String name, Predicate<DiscountDerivableInformation> discountAppliance) {
        this.name = name;
        this.discountAppliance = discountAppliance;
    }

    public boolean isDiscountApplicable(DiscountDerivableInformation discountDerivableInformation) {
        return discountAppliance.test(discountDerivableInformation);
    }
}
