package org.example.parsers;

import org.example.domain.cargo.Cargo;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvParser implements DataParser {

    @Override
    public List<Cargo> parseCargos(String filePath) {
        List<Cargo> cargos = new ArrayList<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
        if (is == null) return cargos;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(";");
                if (cols.length >= 4 && cols[0].equalsIgnoreCase("cargo")) {
                    cargos.add(new Cargo(cols[1],
                            Double.parseDouble(cols[2].replace(",", ".")),
                            Double.parseDouble(cols[3].replace(",", "."))));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return cargos;
    }

    public List<String[]> parseTransports(String filePath) {
        List<String[]> transports = new ArrayList<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
        if (is == null) return transports;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(";");
                if (cols.length >= 6 && cols[0].equalsIgnoreCase("transport")) {
                    transports.add(cols);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return transports;
    }
}