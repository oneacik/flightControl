package com.ksidelta.pg.model.contexts.purchase.discount;

import com.ksidelta.pg.model.contexts.purchase.PurchaseStage;
import com.ksidelta.pg.model.kernel.price.Price;

import java.util.List;

public class PostDiscountStage implements PurchaseStage {
    Price finalPrice;
    List<Discount> appliedDiscounts;

    public PostDiscountStage(Price finalPrice, List<Discount> appliedDiscounts) {
        this.finalPrice = finalPrice;
        this.appliedDiscounts = appliedDiscounts;
    }

    public Price getFinalPrice() {
        return finalPrice;
    }

    public List<Discount> getAppliedDiscounts() {
        return appliedDiscounts;
    }

    public PostDiscountStage purgeAppliedDiscounts() {
        return new PostDiscountStage(finalPrice, appliedDiscounts);
    }
}
