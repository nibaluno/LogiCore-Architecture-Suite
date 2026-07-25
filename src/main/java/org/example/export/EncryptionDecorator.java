package org.example.export;

import org.example.domain.dto.DeliveryResult;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class EncryptionDecorator extends ExporterDecorator {

    private static final String SECRET_KEY = "MyLogisticsKey12";

    public EncryptionDecorator(ResultExporter wrappedExporter) {
        super(wrappedExporter);
    }

    @Override
    public void export(List<DeliveryResult> data, String fileName) {
        super.export(data, fileName);

        String targetPath = fileName + getExtension();
        File file = new File(targetPath);

        if (!file.exists()) {
            System.err.println("[ОШИБКА ШИФРОВАНИЯ] Файл не найден: " + targetPath);
            return;
        }

        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());

            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] encryptedBytes = cipher.doFinal(fileContent);

            Files.write(file.toPath(), encryptedBytes);

            System.out.println("[ШИФРОВАНИЕ] Содержимое файла " + targetPath + " зашифровано алгоритмом AES.");

        } catch (Exception e) {
            System.err.println("[ОШИБКА ШИФРОВАНИЯ] Ошибка при обработке файла: " + e.getMessage());
        }
    }
}