package org.example.filters;

import org.example.domain.dto.DeliveryResult;

public class MinSpeedFilter extends DeliveryFilter {
    private final double minSpeed;

    public MinSpeedFilter(double minSpeed) {
        this.minSpeed = minSpeed;
    }

    @Override
    protected boolean doCheck(DeliveryResult result) {
        return result.getSpeed() >= minSpeed;
    }
}