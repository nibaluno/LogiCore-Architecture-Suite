package org.example.parsers;

import org.example.domain.cargo.Cargo;

import java.util.List;

public interface DataParser {
    List<Cargo> parseCargos(String filePath);
}
