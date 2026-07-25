package org.example.domain.dto;

/**
 * Класс-результат (DTO), содержащий итоговую информацию о доставке.
 * Используется для отображения, фильтрации, сортировки и последующего экспорта.
 */
public class DeliveryResult {
    private final String transportName;
    private final double totalCost;
    private final double travelTime;
    private final String waybillText;
    private final double speed;

    public DeliveryResult(String transportName, double totalCost, double travelTime, String waybillText, double speed) {
        this.transportName = transportName;
        this.totalCost = totalCost;
        this.travelTime = travelTime;
        this.waybillText = waybillText;
        this.speed = speed;
    }

    // Геттеры необходимы для реализации сортировки и фильтрации (принцип Information Expert)
    public String getTransportName() {
        return transportName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getTravelTime() {
        return travelTime;
    }

    public String getWaybillText() {
        return waybillText;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return """
               ----------------------------------------------------
               ВАРИАНТ ДОСТАВКИ: %s
               ----------------------------------------------------
               Итоговая стоимость: %.2f у.е.
               Время в пути: %.1f ч. (Скорость: %.1f км/ч)
               
               Текст документа:
               %s
               """.formatted(transportName, totalCost, travelTime, speed, waybillText);
    }
}