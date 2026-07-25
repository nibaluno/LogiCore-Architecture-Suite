package org.example.export;

import org.example.domain.dto.DeliveryResult;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipOutputStream;


public class ZipDecorator extends ExporterDecorator {
    public ZipDecorator(ResultExporter wrappedExporter) {
        super(wrappedExporter);
    }

    @Override
    public void export(List<DeliveryResult> data, String fileName) {
        super.export(data, fileName);


        String sourceFileName = fileName + getExtension();
        String zipName = fileName + ".zip";


        try (FileOutputStream fos = new FileOutputStream(zipName);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(sourceFileName)) {

            System.out.println("[СЖАТИЕ] Файл " + sourceFileName + " упакован в " + zipName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
