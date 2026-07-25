package org.example.factories;


import org.example.domain.document.AirWaybill;
import org.example.domain.document.Waybill;
import org.example.domain.transport.AirTransport;
import org.example.domain.transport.Transport;


public class AirLogisticsFactory implements LogisticsFactory {

    @Override
    public Transport createTransport(String name, double costPerKm, double speed) {
        return new AirTransport(name, costPerKm, speed);
    }

    @Override
    public Waybill createWaybill() {
        return new AirWaybill();
    }
}