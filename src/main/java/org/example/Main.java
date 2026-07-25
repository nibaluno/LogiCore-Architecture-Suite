package org.example;

import org.example.domain.cargo.Cargo;
import org.example.domain.cargo.CargoItem;
import org.example.parsers.CsvParser;
import org.example.parsers.DataLoader;
import org.example.parsers.JsonParser;
import org.example.parsers.XmlParser;
import org.example.service.DeliveryService;
import org.example.service.DeliverySortService;
import org.example.service.FactoryRegistry;
import org.example.service.LogisticsFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {

    void main() {
        Scanner scanner = new Scanner(System.in);

        DataLoader dataLoader = new DataLoader();
        CsvParser csvParser = new CsvParser();
        JsonParser jsonParser = new JsonParser();
        XmlParser xmlParser = new XmlParser();

        dataLoader.registerParser("csv", csvParser);
        dataLoader.registerParser("json", jsonParser);
        dataLoader.registerParser("xml", xmlParser);

        LogisticsFacade facade = new LogisticsFacade(
                new FactoryRegistry(),
                new DeliveryService(),
                new DeliverySortService()
        );



        System.out.print("Введите имя файла (logistic.csv / .json / .xml): ");
        String filePath = scanner.nextLine();

        List<Cargo> allCargos;
        List<String[]> allTransportsRaw;

        try {
            allCargos = dataLoader.load(filePath);
            if (filePath.endsWith(".csv")) allTransportsRaw = csvParser.parseTransports(filePath);
            else if (filePath.endsWith(".json")) allTransportsRaw = jsonParser.parseTransports(filePath);
            else allTransportsRaw = xmlParser.parseTransports(filePath);
        } catch (Exception e) {
            System.err.println("ошибка загрузки: " + e.getMessage());
            return;
        }

        System.out.println("\nдоступные грузы:");
        for (int i = 0; i < allCargos.size(); i++) {
            System.out.println(i + ". " + allCargos.get(i).getName());
        }
        System.out.print("выберите номера через запятую (напр. 0,2): ");
        String[] choices = scanner.nextLine().split(",");
        List<CargoItem> batch = new ArrayList<>();
        for (String choice : choices) {
            int idx = Integer.parseInt(choice.trim());
            Cargo cargo = allCargos.get(idx);
            System.out.print("Кол-во для '" + cargo.getName() + "': ");
            int qty = Integer.parseInt(scanner.nextLine());
            batch.add(new CargoItem(cargo, qty));
        }

        System.out.println("\nДоступный транспорт:");
        System.out.println("-1. РАССЧИТАТЬ ВСЕ ВАРИАНТЫ ");
        for (int i = 0; i < allTransportsRaw.size(); i++) {
            System.out.println(i + ". " + allTransportsRaw.get(i)[1]);
        }
        System.out.print("Ваш выбор: ");
        int tChoice = Integer.parseInt(scanner.nextLine());
        List<String[]> selectedTransports = (tChoice == -1)
                ? allTransportsRaw
                : Collections.singletonList(allTransportsRaw.get(tChoice));

        System.out.print("\nВведите дистанцию (км): ");
        double dist = Double.parseDouble(scanner.nextLine());

        System.out.print("Минимальная скорость (0 - пропустить): ");
        double minS = Double.parseDouble(scanner.nextLine());

        System.out.print("Максимальная цена (0 - пропустить): ");
        double maxP = Double.parseDouble(scanner.nextLine());

        System.out.print("Тип сортировки (1-Цена, 2-Скорость, 3-Обе, 0-Нет): ");
        int sortType = Integer.parseInt(scanner.nextLine());

        System.out.print("\nФормат экспорта (1-CSV, 2-JSON): ");
        int format = Integer.parseInt(scanner.nextLine());

        System.out.print("Применить шифрование AES? (1-Да, 0-Нет): ");
        boolean encrypt = scanner.nextLine().equals("1");

        System.out.print("Сжать в ZIP-архив? (1-Да, 0-Нет): ");
        boolean compress = scanner.nextLine().equals("1");

        try {
            facade.process(
                    batch,
                    selectedTransports,
                    dist,
                    minS,
                    maxP,
                    sortType,
                    format,
                    encrypt,
                    compress
            );
            System.out.println("\n=== ПРОЦЕСС ЗАВЕРШЕН УСПЕШНО ===");
        } catch (Exception e) {
            System.err.println("Ошибка выполнения: " + e.getMessage());
        }
    }
}