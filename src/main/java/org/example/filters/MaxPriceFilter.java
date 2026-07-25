package org.example.filters;

import org.example.domain.dto.DeliveryResult;

public class MaxPriceFilter extends DeliveryFilter {
    private final double maxPrice;

    public MaxPriceFilter(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    protected boolean doCheck(DeliveryResult result) {
        return result.getTotalCost() <= maxPrice;
    }
}