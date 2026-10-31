package com.portfolio.my_porfolio_backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * FileStorageService
 */
@Service
public class FileStorageService {

    @Value("${file.upload.dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Empty file");
        }

        String fileName = file.getOriginalFilename();
        String extension = "";
        if (fileName != null) {
            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex > 0) {
                extension = fileName.substring(dotIndex);
            }
        }

        String newFileName = UUID.randomUUID().toString() + extension;
        Path filePath = Paths.get(uploadDir, newFileName)
                .normalize();

        Files.copy(file.getInputStream(), filePath);

        return "/img/projects/" + newFileName;
    }

}
