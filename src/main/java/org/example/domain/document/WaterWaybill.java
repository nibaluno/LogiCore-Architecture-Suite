package org.example.domain.document;


public class WaterWaybill implements Waybill {

    @Override
    public String generate(String cargoSummary, double totalCost, double distance) {
        return """
               ====================================================
               [🚢 BILL OF LADING / МОРСКОЙ КОНОСАМЕНТ]
               ====================================================
               Контейнер содержит: %s
               Морские мили (эквивалент в км): %.1f
               ----------------------------------------------------
               ИТОГО К ОПЛАТЕ ФРАХТА: %.2f у.е.
               ----------------------------------------------------
               * Отметка порта: Проверить герметичность контейнеров
                 перед погрузкой на судно.
               ====================================================
               """.formatted(cargoSummary, distance, totalCost);
    }
}