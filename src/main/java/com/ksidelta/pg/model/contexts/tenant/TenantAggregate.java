package com.ksidelta.pg.model.contexts.tenant;

import com.ksidelta.pg.model.kernel.tenant.TenantId;

public class TenantAggregate {
    TenantId tenantId;
    PersonalInformation personalInformation;
    TenantCategory tenantCategory;

    // NOTE: this tenantId should be automatically generated
    TenantAggregate(TenantId tenantId, PersonalInformation personalInformation, TenantCategory tenantCategory) {
        this.tenantId = tenantId;
        this.personalInformation = personalInformation;
        this.tenantCategory = tenantCategory;
    }

    public TenantId getTenantId() {
        return tenantId;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void updatePersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public TenantCategory getTenantCategory() {
        return tenantCategory;
    }

    public void updateTenantCategory(TenantCategory tenantCategory) {
        this.tenantCategory = tenantCategory;
    }

    public TenantAggregate createTenantAggregate(PersonalInformation personalInformation, TenantCategory tenantCategory) {
        return new TenantAggregate(null, personalInformation, tenantCategory);
    }
}
