package com.example.tradeintechniqueapp.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${app.partsList.bucket}")
    private final String bucketPartList = new String();

    @Value("${app.actFiles.bucket}")
    private final String bucketActs = new String();

    @SneakyThrows
    public Path uploadPartList(String partListPath, InputStream content) {
        Path fullPath = Path.of(bucketPartList, partListPath);
        try (content) {
            Files.createDirectories(fullPath.getParent());
            Files.write(fullPath, content.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
        return fullPath;

    }

    public boolean uploadFilesForAct(MultipartFile[] file, String pathFiles) {
        if(file != null){
            String fullPathStr = bucketActs +
                                 pathFiles + "\\";
            for (MultipartFile oneFile : file) {

                Path fullPath = Path.of( fullPathStr,Objects.requireNonNull(oneFile.getOriginalFilename()));

                try (InputStream inputStream = oneFile.getInputStream()) {
                    Files.write(fullPath, inputStream.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                }catch (IOException e){
                    return false;
                }
            }
        }
        return true;
    }
    public void deleteDir(String path){
        File file = new File(bucketActs + path);
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
