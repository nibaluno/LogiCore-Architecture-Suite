package org.example.parsers;

import org.example.domain.cargo.Cargo;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonParser implements DataParser {

    @Override
    public List<Cargo> parseCargos(String filePath) {
        List<Cargo> cargos = new ArrayList<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
        if (is == null) return cargos;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("\"type\": \"cargo\"") || line.contains("\"type\":\"cargo\"")) {
                    String name = extractString(line, "name");
                    double weight = extractDouble(line, "weight");
                    double cost = extractDouble(line, "cost");
                    cargos.add(new Cargo(name, weight, cost));
                }
            }
        } catch (Exception e) {
            System.err.println("[ОШИБКА JSON] Не удалось прочитать грузы: " + e.getMessage());
        }
        return cargos;
    }

    public List<String[]> parseTransports(String filePath) {
        List<String[]> transports = new ArrayList<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
        if (is == null) return transports;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("\"type\": \"transport\"") || line.contains("\"type\":\"transport\"")) {
                    String name = extractString(line, "name");
                    String cons = extractString(line, "consumption"); // Расход
                    String speed = extractString(line, "speed");       // Скорость

                    transports.add(new String[]{"transport", name, "0", "0", cons, speed});
                }
            }
        } catch (Exception e) {
            System.err.println("[ОШИБКА JSON] Не удалось прочитать транспорт: " + e.getMessage());
        }
        return transports;
    }

    /**
     * Извлекает строковое значение и убирает лишние кавычки/пробелы
     */
    private String extractString(String line, String key) {
        String k = "\"" + key + "\":";
        if (!line.contains(k)) return "";
        int start = line.indexOf(k) + k.length();
        int end = line.indexOf(",", start);
        if (end == -1) end = line.indexOf("}", start);
        if (end == -1) end = line.length();

        return line.substring(start, end)
                .replace("\"", "")
                .replace(":", "")
                .replace("}", "")
                .replace("]", "")
                .trim();
    }

    /**
     * Извлекает числовое значение, удаляя ВСЕ лишние символы кроме цифр, точек и минусов
     */
    private double extractDouble(String line, String key) {
        String raw = extractString(line, key);
        if (raw.isEmpty()) return 0.0;
        try {
            String clean = raw.replaceAll("[^0-9.\\-]", "");
            return Double.parseDouble(clean);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}