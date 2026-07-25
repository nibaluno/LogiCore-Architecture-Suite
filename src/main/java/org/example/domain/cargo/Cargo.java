package org.example.domain.cargo;


public class Cargo {
    private final String name;
    private final double weightPerUnit;
    private final double transportCostPerKg;

    public Cargo(String name, double weightPerUnit, double transportCostPerKg) {
        this.name = name;
        this.weightPerUnit = weightPerUnit;
        this.transportCostPerKg = transportCostPerKg;
    }

    public String getName() {
        return name;
    }

    public double getWeightPerUnit() {
        return weightPerUnit;
    }

    public double getTransportCostPerKg() {
        return transportCostPerKg;
    }

    @Override
    public String toString() {
        return name;
    }
}