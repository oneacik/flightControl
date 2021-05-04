package com.ksidelta.pg.model.translation.purchase.purchase.strategy;

import com.ksidelta.pg.model.contexts.purchase.purchase.PurchaseStrategy;
import com.ksidelta.pg.model.contexts.purchase.purchase.strategy.PreserveDiscountsStrategy;
import com.ksidelta.pg.model.contexts.purchase.purchase.strategy.PurgeDiscountsStrategy;
import com.ksidelta.pg.model.contexts.tenant.TenantCategory;

public class PurchaseStrategyTranslator {
    public PurchaseStrategy translate(TenantCategory tenantCategory) {
        switch (tenantCategory) {
            case A:
                return new PreserveDiscountsStrategy();
            case B:
                return new PurgeDiscountsStrategy();
            default:
                throw new IllegalStateException("Unexpected value: " + tenantCategory);
        }
    }
}

