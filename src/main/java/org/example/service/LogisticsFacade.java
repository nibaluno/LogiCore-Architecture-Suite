package org.example.service;

import org.example.domain.cargo.CargoItem;
import org.example.domain.dto.DeliveryResult;
import org.example.export.*;
import org.example.factories.LogisticsFactory;
import org.example.filters.*;
import java.util.*;

public class LogisticsFacade {
    private final FactoryRegistry factoryRegistry;
    private final DeliveryService deliveryService;
    private final DeliverySortService sortService;

    public LogisticsFacade(FactoryRegistry factoryRegistry, DeliveryService deliveryService, DeliverySortService sortService) {
        this.factoryRegistry = factoryRegistry;
        this.deliveryService = deliveryService;
        this.sortService = sortService;
    }

    public void process(
            List<CargoItem> batch,
            List<String[]> selectedTransports,
            double distance,
            double minS,
            double maxP,
            int sortType,
            int format,
            boolean encrypt,
            boolean compress
    ) {
        // 1. РАСЧЕТ (Абстрактная фабрика)
        List<DeliveryResult> results = new ArrayList<>();
        for (String[] tData : selectedTransports) {
            String name = tData[1];
            double cost = Double.parseDouble(tData[4].replace(",", "."));
            double speed = Double.parseDouble(tData[5].replace(",", "."));
            String type = name.contains("Воздух") ? "Воздух" : (name.contains("Вода") ? "Вода" : "Земля");

            results.add(deliveryService.calculateDelivery(factoryRegistry.getFactory(type), name, cost, speed, batch, distance));
        }

        // 2. ФИЛЬТРАЦИЯ (Chain of Responsibility)
        DeliveryFilter chain = null;
        if (minS > 0) chain = new MinSpeedFilter(minS);
        if (maxP > 0) {
            if (chain == null) chain = new MaxPriceFilter(maxP);
            else chain.linkWith(new MaxPriceFilter(maxP));
        }
        if (chain != null) {
            DeliveryFilter finalChain = chain;
            results = results.stream().filter(finalChain::handle).toList();
        }

        // 3. СОРТИРОВКА (Strategy)
        List<Comparator<DeliveryResult>> strategies = new ArrayList<>();
        if (sortType == 1 || sortType == 3) strategies.add(SortingProvider.byPrice());
        if (sortType == 2 || sortType == 3) strategies.add(SortingProvider.bySpeed());
        if (!strategies.isEmpty()) {
            results = new ArrayList<>(results);
            sortService.sort(results, strategies);
        }

        // 4. ВЫВОД
        System.out.println("\n=== ИТОГОВЫЕ ВАРИАНТЫ ===");
        if (results.isEmpty()) System.out.println("Ничего не найдено.");
        else results.forEach(System.out::println);

        // 5. ЭКСПОРТ (Decorator)
        ResultExporter exporter = (format == 1) ? new CsvExporter() : new JsonExporter();
        if (encrypt) exporter = new EncryptionDecorator(exporter);
        if (compress) exporter = new ZipDecorator(exporter);

        exporter.export(results, "final_report");
    }
}