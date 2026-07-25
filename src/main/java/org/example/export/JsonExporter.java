package org.example.export;

import org.example.domain.dto.DeliveryResult;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

public class JsonExporter implements ResultExporter {
    @Override
    public void export(List<DeliveryResult> data, String fileName) {
        String fullName = fileName + ".json";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fullName))) {
            writer.println("[");

            for (int i = 0; i < data.size(); i++) {
                DeliveryResult result = data.get(i);

                String escapedWaybill = result.getWaybillText()
                        .replace("\\", "\\\\")
                        .replace("\"", "\\\"")
                        .replace("\n", "\\n")
                        .replace("\r", "");

                writer.println("  {");
                writer.printf(Locale.US, "    \"transportName\": \"%s\",%n", result.getTransportName());
                writer.printf(Locale.US, "    \"totalCost\": %.2f,%n", result.getTotalCost());
                writer.printf(Locale.US, "    \"travelTime\": %.1f,%n", result.getTravelTime());
                writer.printf(Locale.US, "    \"speed\": %.1f,%n", result.getSpeed());
                writer.printf(Locale.US, "    \"waybillText\": \"%s\"%n", escapedWaybill);

                if (i < data.size() - 1) {
                    writer.println("  },");
                } else {
                    writer.println("  }");
                }
            }

            writer.println("]");
            System.out.println("[ФОРМАТ] Данные успешно экспортированы в JSON файл: " + fullName);

        } catch (IOException e) {
            System.err.println("[ОШИБКА] Не удалось записать JSON файл: " + e.getMessage());
        }
    }

    @Override
    public String getExtension() {
        return ".json";
    }
}