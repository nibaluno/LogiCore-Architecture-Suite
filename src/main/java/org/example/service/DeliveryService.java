package org.example.service;

import org.example.domain.cargo.CargoItem;
import org.example.domain.dto.DeliveryResult;
import org.example.domain.transport.Transport;
import org.example.domain.document.Waybill;
import org.example.factories.LogisticsFactory;
import java.util.List;

public class DeliveryService {
/// ////////
    /**
     * Основной метод-контроллер процесса доставки.
     * Координирует работу фабрик, экспертов по грузам и транспорта.
     */
    public DeliveryResult calculateDelivery(
            LogisticsFactory factory,
            String transportName,
            double costPerKm,
            double speed,
            List<CargoItem> batch,
            double distance
    ) {
        Transport transport = factory.createTransport(transportName, costPerKm, speed);
        Waybill waybill = factory.createWaybill();


        double cargoCost = CargoItem.calculateTotalBatchCost(batch);


        double transportCost = transport.calculateDeliveryCost(distance);
        double travelTime = transport.calculateTravelTime(distance);


        String summary = CargoItem.formatBatchSummary(batch);

        double totalFinalPrice = cargoCost + transportCost;
        String waybillText = waybill.generate(summary, totalFinalPrice, distance);

        return new DeliveryResult(
                transportName,
                totalFinalPrice,
                travelTime,
                waybillText,
                speed
        );
    }
}