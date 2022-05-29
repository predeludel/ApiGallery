package com.example.demo.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;



    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadPath));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder");
        }
    }

    public String saveFile(byte[] bytes, String fileName) {
        try {
            String path = generatePath(fileName);
            writeBytesToFile(path, bytes);
            return path;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Resource loadFile(String fileName) {
        try {
            Path file = Paths.get(uploadPath)
                    .resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    private String generatePath(String fileName) {
        return uploadPath + "/" + fileName;
    }

    private static void writeBytesToFile(String path, byte[] bytes) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(bytes);
        }
    }
}
