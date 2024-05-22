package com.example.tradeintechniqueapp.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
@RequiredArgsConstructor
public class PartsListService {
    @Value("${app.partsList.bucket}")
    private final String bucket = new String();

    private final String bucket2 = new String();

    @SneakyThrows
    public Path upload(String partListPath, InputStream content) {
        Path fullPath = Path.of(bucket, partListPath);
        try (content) {
            Files.createDirectories(fullPath.getParent());
            Files.write(fullPath, content.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
        return fullPath;

    }
}
