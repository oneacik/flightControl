package com.ksidelta.pg.model.contexts.purchase.discount;

import com.ksidelta.pg.model.contexts.purchase.discount.list.AfricanThursdayDiscount;
import com.ksidelta.pg.model.contexts.purchase.discount.list.BirthdayDiscount;
import com.ksidelta.pg.model.contexts.purchase.discount.info.DiscountDerivableInformation;

import java.util.Arrays;
import java.util.List;

public class DiscountService {

    List<Discount> discounts;

    public DiscountService(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public PostDiscountStage applyDiscountsToPreDiscountStage(PreDiscountStage preDiscountStage, DiscountDerivableInformation discountDerivableInformation) {
        var currentDiscountStage = preDiscountStage;

        for (final var discount : discounts) {
            if (discount.isDiscountApplicable(discountDerivableInformation)) {
                preDiscountStage = preDiscountStage.applyDiscount(discount);
            }
        }

        return currentDiscountStage.finishApplyingDiscounts();
    }

    public static DiscountService createDiscountService() {
        return new DiscountService(Arrays.asList(new AfricanThursdayDiscount(), new BirthdayDiscount()));
    }
}
