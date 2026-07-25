package org.example.domain.cargo;

import java.util.List;
import java.util.stream.Collectors;

public class CargoItem {
    private final Cargo cargo;
    private final int quantity;

    public CargoItem(Cargo cargo, int quantity) {
        this.cargo = cargo;
        this.quantity = quantity;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public int getQuantity() {
        return quantity;
    }

    // Расчет массы одной позиции (вес ед. * кол-во)
    public double getTotalMass() {
        return cargo.getWeightPerUnit() * quantity;
    }

    // Расчет стоимости перевозки конкретно этой позиции
    public double calculateCost() {
        return getTotalMass() * cargo.getTransportCostPerKg();
    }

    // --- Методы Информационного Эксперта для работы со списком (партией) ---

    /**
     * Считает общую стоимость всех товаров в партии.
     * Мы используем static, чтобы можно было вызвать расчет, просто передав список.
     */
    public static double calculateTotalBatchCost(List<CargoItem> batch) {
        return batch.stream()
                .mapToDouble(CargoItem::calculateCost)
                .sum();
    }

    /**
     * Формирует текстовую строку-описание для всей партии.
     * Пример: "Электроника (x10), Одежда (x50)"
     */
    public static String formatBatchSummary(List<CargoItem> batch) {
        return batch.stream()
                .map(i -> i.getCargo().getName() + " (x" + i.getQuantity() + ")")
                .collect(Collectors.joining(", "));
    }
}