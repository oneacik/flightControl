package com.ksidelta.pg.model.contexts.purchase.purchase;

import com.ksidelta.pg.model.contexts.purchase.discount.PostDiscountStage;

public interface PurchaseStrategy {
    PurchasedStage purchase(PostDiscountStage postDiscountStage);
}
