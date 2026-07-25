package org.example.filters;

import org.example.domain.dto.DeliveryResult;

public class TransportTypeFilter extends DeliveryFilter {
    private final String typeSubstring;

    public TransportTypeFilter(String typeSubstring) {
        this.typeSubstring = typeSubstring;
    }

    @Override
    protected boolean doCheck(DeliveryResult result) {
        return result.getTransportName().contains(typeSubstring);
    }
}
