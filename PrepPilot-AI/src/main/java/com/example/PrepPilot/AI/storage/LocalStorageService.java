package com.example.PrepPilot.AI.storage;

import com.example.PrepPilot.AI.entity.enums.DocumentType;
import com.example.PrepPilot.AI.exception.FileHandlingException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;

@Service
public class LocalStorageService implements StorageService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String store(MultipartFile file, DocumentType documentType) {

        try {


            String extension = getExtension(file.getOriginalFilename());
            String storedFileName = UUID.randomUUID() + extension;

            // uploads/resume
            Path directory = Paths.get(uploadDir,
                    documentType.name().toLowerCase());

            // Create folder if not exists/ agar exist krta hai to ignore
            Files.createDirectories(directory);

            // uploads/resume/uuid.pdf
            Path destination = directory.resolve(storedFileName);

            // Save file
            file.transferTo(destination);

            return storedFileName;

        } catch (Exception e) {
            throw new FileHandlingException("Failed to store file");
        }
    }

    @Override
    public Resource load(String storedFileName, DocumentType documentType) {

        try {

            Path filePath = Paths.get(uploadDir,
                    documentType.name().toLowerCase(),
                    storedFileName);

            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            }

            throw new RuntimeException("File not found");

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid file path", e);
        }
    }

    @Override
    public void delete(String storedFileName, DocumentType documentType) {

        try {

            Path filePath = Paths.get(uploadDir,
                    documentType.name().toLowerCase(),
                    storedFileName);

            Files.deleteIfExists(filePath);

        } catch (Exception e) {
            throw new FileHandlingException("Failed to delete file");
        }
    }

    private String getExtension(String fileName) {

        if (fileName == null || !fileName.contains(".")) {
            return "";
        }

        return fileName.substring(fileName.lastIndexOf("."));
    }
}