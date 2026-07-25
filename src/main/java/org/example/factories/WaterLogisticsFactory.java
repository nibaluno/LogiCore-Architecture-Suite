package org.example.factories;


import org.example.domain.document.WaterWaybill;
import org.example.domain.document.Waybill;
import org.example.domain.transport.Transport;
import org.example.domain.transport.WaterTransport;



public class WaterLogisticsFactory implements LogisticsFactory {

    @Override
    public Transport createTransport(String name, double costPerKm, double speed) {
        return new WaterTransport(name, costPerKm, speed);
    }

    @Override
    public Waybill createWaybill() {
        return new WaterWaybill();
    }
}