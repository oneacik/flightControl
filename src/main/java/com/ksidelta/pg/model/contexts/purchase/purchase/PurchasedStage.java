package com.ksidelta.pg.model.contexts.purchase.purchase;

import com.ksidelta.pg.model.contexts.purchase.discount.Discount;
import com.ksidelta.pg.model.contexts.purchase.PurchaseStage;
import com.ksidelta.pg.model.kernel.price.Price;

import java.util.List;

public class PurchasedStage implements PurchaseStage {
    Price finalPrice;
    List<Discount> appliedDiscounts;

    public PurchasedStage(Price finalPrice, List<Discount> appliedDiscounts) {
        this.finalPrice = finalPrice;
        this.appliedDiscounts = appliedDiscounts;
    }
}
