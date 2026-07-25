package org.example.parsers;

import org.example.domain.cargo.Cargo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataLoader {
    private final Map<String, DataParser> parsersRegistry = new HashMap<>();

    public void registerParser(String extension, DataParser parser) {
        parsersRegistry.put(extension.toLowerCase(), parser);
    }

    public List<Cargo> load(String filePath) {
        String extension = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
        DataParser parser = parsersRegistry.get(extension);

        if (parser == null) {
            throw new IllegalArgumentException("неподдерживаемый формат файла: " + extension);
        }

        return parser.parseCargos(filePath);
    }
}