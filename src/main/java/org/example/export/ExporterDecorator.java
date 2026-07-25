package org.example.export;

import org.example.domain.dto.DeliveryResult;
import java.util.List;




public abstract class ExporterDecorator implements ResultExporter {
    protected ResultExporter wrappedExporter;

    public ExporterDecorator(ResultExporter wrappedExporter) {
        this.wrappedExporter = wrappedExporter;
    }

    @Override
    public void export(List<DeliveryResult> data, String fileName) {
        wrappedExporter.export(data, fileName);
    }
    @Override
    public String getExtension() {
        return wrappedExporter.getExtension();
    }
}