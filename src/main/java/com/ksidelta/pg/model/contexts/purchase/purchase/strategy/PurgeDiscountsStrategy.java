package com.ksidelta.pg.model.contexts.purchase.purchase.strategy;

import com.ksidelta.pg.model.contexts.purchase.discount.PostDiscountStage;
import com.ksidelta.pg.model.contexts.purchase.purchase.PurchaseStrategy;
import com.ksidelta.pg.model.contexts.purchase.purchase.PurchasedStage;

public class PurgeDiscountsStrategy implements PurchaseStrategy {
    @Override
    public PurchasedStage purchase(PostDiscountStage postDiscountStage) {
        return new PurchasedStage(postDiscountStage.getFinalPrice(), postDiscountStage.purgeAppliedDiscounts().getAppliedDiscounts());
    }
}
