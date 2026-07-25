package org.example.factories;



import org.example.domain.document.LandWaybill;
import org.example.domain.document.Waybill;
import org.example.domain.transport.LandTransport;
import org.example.domain.transport.Transport;


public class LandLogisticsFactory implements LogisticsFactory {

    @Override
    public Transport createTransport(String name, double costPerKm, double speed) {
        return new LandTransport(name, costPerKm, speed);
    }

    @Override
    public Waybill createWaybill() {
        return new LandWaybill();
    }
}
