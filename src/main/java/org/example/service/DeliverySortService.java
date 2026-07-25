package org.example.service;

import org.example.domain.dto.DeliveryResult;
import java.util.Comparator;
import java.util.List;

public class DeliverySortService {


    public void sort(List<DeliveryResult> results, List<Comparator<DeliveryResult>> strategies) {
        if (strategies == null || strategies.isEmpty()) {
            return;
        }

        Comparator<DeliveryResult> compositeStrategy = strategies.get(0);

        for (int i = 1; i < strategies.size(); i++) {
            compositeStrategy = compositeStrategy.thenComparing(strategies.get(i));
        }

        results.sort(compositeStrategy);
    }
}