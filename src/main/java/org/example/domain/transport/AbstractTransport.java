package org.example.domain.transport;


public abstract class AbstractTransport implements Transport {
    private final String name;
    private final double costPerKm;
    private final double speed;

    public AbstractTransport(String name, double costPerKm, double speed) {
        this.name = name;
        this.costPerKm = costPerKm;
        this.speed = speed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double calculateDeliveryCost(double distance) {
        return distance * costPerKm;
    }

    @Override
    public double calculateTravelTime(double distance) {
        if (speed <= 0) return 0;
        return distance / speed;
    }
}
