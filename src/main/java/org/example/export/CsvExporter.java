package org.example.export;

import org.example.domain.dto.DeliveryResult;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvExporter implements ResultExporter {
    @Override
    public void export(List<DeliveryResult> data, String fileName) {
        String fullName = fileName + ".csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fullName))) {


            writer.println("Transport;TotalCost;TravelTime;Speed;WaybillText");

            for (DeliveryResult result : data) {
                String cleanWaybill = result.getWaybillText()
                        .replace("\n", " ")
                        .replace("\r", "")
                        .replace(";", ",");

                writer.printf("%s;%.2f;%.1f;%.1f;%s%n",
                        result.getTransportName(),
                        result.getTotalCost(),
                        result.getTravelTime(),
                        result.getSpeed(),
                        cleanWaybill
                );
            }

            System.out.println("[ФОРМАТ] Данные успешно экспортированы в файл: " + fullName);

        } catch (IOException e) {
            System.err.println("[ОШИБКА] Не удалось записать CSV файл: " + e.getMessage());
        }

    }
    @Override
    public String getExtension() {
        return ".csv";
    }
}