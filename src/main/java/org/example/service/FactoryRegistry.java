package org.example.service;

import org.example.factories.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Реестр логистических фабрик.
 * Инкапсулирует логику хранения и поиска доступных способов доставки.
 * если пользователь... не определил тип транспорта, то необходимо отдавать
 * в качестве результата список со всеми возможными вариантами (для каждого вида транспорта)
 */
public class FactoryRegistry {
    private final Map<String, LogisticsFactory> factories = new HashMap<>();

    public FactoryRegistry() {
        factories.put("Воздух", new AirLogisticsFactory());
        factories.put("Земля", new LandLogisticsFactory());
        factories.put("Вода", new WaterLogisticsFactory());
    }

    public LogisticsFactory getFactory(String type) {
        return factories.get(type);
    }

    public Set<String> getAvailableTypes() {
        return factories.keySet();
    }

    public Map<String, LogisticsFactory> getAll() {
        return factories;
    }
}