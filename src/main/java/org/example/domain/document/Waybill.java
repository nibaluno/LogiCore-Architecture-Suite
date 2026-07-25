package org.example.domain.document;


public interface Waybill {

    String generate(String cargoSummary, double totalCost, double distance);
}