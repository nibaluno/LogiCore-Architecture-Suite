package org.example.domain.transport;


public interface Transport {
    String getName();


    double calculateDeliveryCost(double distance);


    double calculateTravelTime(double distance);
}