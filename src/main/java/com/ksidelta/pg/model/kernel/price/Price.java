package com.ksidelta.pg.model.kernel.price;

import java.math.BigDecimal;

public class Price {
    BigDecimal price;

    public Price(BigDecimal price) {
        this.price = price;
    }

    public Price discount(BigDecimal discountAmount) {
        return new Price(price.subtract(discountAmount));
    }

    public BigDecimal getPrice() {
        return price;
    }
}
