package org.example.export;

import org.example.domain.dto.DeliveryResult;
import java.util.List;


public interface ResultExporter {
    void export(List<DeliveryResult> data, String fileName);
    String getExtension(); //"Tell, Don't Ask"
}