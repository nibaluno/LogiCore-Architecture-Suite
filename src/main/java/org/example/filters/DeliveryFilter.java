package org.example.filters;

import org.example.domain.dto.DeliveryResult;

public abstract class DeliveryFilter {
    private DeliveryFilter next;


    public DeliveryFilter linkWith(DeliveryFilter nextFilter) {
        this.next = nextFilter;
        return next;
    }


    public boolean check(DeliveryResult result) {
        if (next == null) {
            return true;
        }
        return next.check(result);
    }

    protected abstract boolean doCheck(DeliveryResult result);


    public boolean handle(DeliveryResult result) {
        if (!doCheck(result)) {
            return false;
        }
        return check(result);
    }
}