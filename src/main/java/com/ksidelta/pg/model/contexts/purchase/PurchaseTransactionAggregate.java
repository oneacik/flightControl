package com.ksidelta.pg.model.contexts.purchase;

import com.ksidelta.pg.model.kernel.flight.FlightId;
import com.ksidelta.pg.model.contexts.purchase.discount.PreDiscountStage;
import com.ksidelta.pg.model.kernel.tenant.TenantId;
import com.ksidelta.pg.model.kernel.price.Price;

public class PurchaseTransactionAggregate {
    private final PurchaseId purchaseId;

    private final FlightId flightId;
    private final TenantId tenantId;

    // NOTE: it is not quite pricesely noted what means to not save the discount information. I assume that it shouldn't be present on the ticket.
    private PurchaseStage currentStage;

    public PurchaseTransactionAggregate(PurchaseId purchaseId, FlightId flightId, TenantId tenantId, PurchaseStage currentStage) {
        this.purchaseId = purchaseId;
        this.flightId = flightId;
        this.tenantId = tenantId;
        this.currentStage = currentStage;
    }

    public void updateStage(PurchaseStage nextStage) {
        this.currentStage = nextStage;
    }

    public PurchaseStage getCurrentStage() {
        return currentStage;
    }

    public PurchaseId getPurchaseId() {
        return purchaseId;
    }

    public FlightId getFlightId() {
        return flightId;
    }

    public TenantId getTenantId() {
        return tenantId;
    }

    public PurchaseTransactionAggregate startPurchaseTransaction(FlightId flightId, TenantId tenantId, Price price) {
        return new PurchaseTransactionAggregate(null, flightId, tenantId, PreDiscountStage.create(price));
    }
}
