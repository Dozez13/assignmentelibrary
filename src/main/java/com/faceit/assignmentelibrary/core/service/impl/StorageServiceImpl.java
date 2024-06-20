package com.faceit.assignmentelibrary.core.service.impl;

import com.faceit.assignmentelibrary.core.exception.StorageException;
import com.faceit.assignmentelibrary.core.service.StorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {
    private final Path root = Paths.get("bookPreviews");

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String store(MultipartFile file) {
        Path storedFilePath = this.root.resolve(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), storedFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
        return storedFilePath.toAbsolutePath().toString();
    }
}
