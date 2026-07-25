package org.example.domain.document;


public class LandWaybill implements Waybill {

    @Override
    public String generate(String cargoSummary, double totalCost, double distance) {
        return """
               ====================================================[
               🚛 CMR / ТОВАРНО-ТРАНСПОРТНАЯ НАКЛАДНАЯ]
               ====================================================
               Содержимое кузова/вагона: %s
               Протяженность маршрута: %.1f км
               ----------------------------------------------------
               ИТОГО К ОПЛАТЕ: %.2f у.е.
               ----------------------------------------------------
               * Отметка: Допускается досмотр на весовых станциях.
               ====================================================
               """.formatted(cargoSummary, distance, totalCost);
    }
}
