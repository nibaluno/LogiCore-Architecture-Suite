package org.example.domain.document;


public class AirWaybill implements Waybill {

    @Override
    public String generate(String cargoSummary, double totalCost, double distance) {
        return """
               ====================================================
               [✈️ AIR WAYBILL / АВИАНАКЛАДНАЯ]
               ====================================================
               Описание груза: %s
               Дистанция полета: %.1f км
               ----------------------------------------------------
               ИТОГО К ОПЛАТЕ: %.2f у.е.
               ----------------------------------------------------
               * ВНИМАНИЕ: Груз подлежит строгому таможенному 
                 контролю и рентген-досмотру в аэропорту.
               ====================================================
               """.formatted(cargoSummary, distance, totalCost);
    }
}
