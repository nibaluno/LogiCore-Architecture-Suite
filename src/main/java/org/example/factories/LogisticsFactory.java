package org.example.factories;

import org.example.domain.document.Waybill;
import org.example.domain.transport.Transport;


public interface LogisticsFactory {


    Transport createTransport(String name, double costPerKm, double speed);


    Waybill createWaybill();
}