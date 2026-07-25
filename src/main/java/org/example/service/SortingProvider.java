package org.example.service;

import org.example.domain.dto.DeliveryResult;
import java.util.Comparator;

/**
 * Провайдер стратегий сортировки.
 * Каждая стратегия — это реализация интерфейса Comparator (Pattern: Strategy).
 * SortingProvider реализует паттерн Стратегия.
 * Он предоставляет набор взаимозаменяемых компараторов для объекта DeliveryResult
 * Класс спроектирован так, чтобы обеспечить выполнение требований ТЗ по выбору критериев сортировки
 * пользователем и их последующему комбинированию через DeliverySortService.
 */
public class SortingProvider {

    public static Comparator<DeliveryResult> byName() {
        return Comparator.comparing(DeliveryResult::getTransportName);
    }

    public static Comparator<DeliveryResult> byPrice() {
        return Comparator.comparingDouble(DeliveryResult::getTotalCost);
    }

    public static Comparator<DeliveryResult> bySpeed() {
        return Comparator.comparingDouble(DeliveryResult::getSpeed).reversed();
    }
}