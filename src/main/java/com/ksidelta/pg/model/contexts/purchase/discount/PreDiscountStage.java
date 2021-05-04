package com.ksidelta.pg.model.contexts.purchase.discount;

import com.ksidelta.pg.model.contexts.purchase.PurchaseStage;
import com.ksidelta.pg.model.kernel.price.Price;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class PreDiscountStage implements PurchaseStage {
    static private final BigDecimal DISCOUNT_AMOUNT = BigDecimal.valueOf(5);
    static private final BigDecimal MINIMAL_PRICE = BigDecimal.valueOf(20);

    Price price;
    List<Discount> appliedDiscounts;

    public PreDiscountStage(Price price, List<Discount> appliedDiscounts) {
        this.price = price;
        this.appliedDiscounts = appliedDiscounts;
    }

    public Price getPrice() {
        return price;
    }

    public List<Discount> getAppliedDiscounts() {
        return appliedDiscounts;
    }

    public PreDiscountStage applyDiscount(Discount discount) {
        final var afterDiscount = price.discount(DISCOUNT_AMOUNT);
        final boolean isDiscountedPriceUnderMinimal = afterDiscount.getPrice().compareTo(MINIMAL_PRICE) < 0;

        if (isDiscountedPriceUnderMinimal) {
            return new PreDiscountStage(price, appliedDiscounts);
        } else {
            return new PreDiscountStage(afterDiscount, Stream.concat(appliedDiscounts.stream(), Stream.of(discount)).collect(toList()));
        }
    }

    public PostDiscountStage finishApplyingDiscounts() {
        return new PostDiscountStage(price, appliedDiscounts);
    }

    public static PreDiscountStage create(Price price) {
        return new PreDiscountStage(price, emptyList());
    }
}
