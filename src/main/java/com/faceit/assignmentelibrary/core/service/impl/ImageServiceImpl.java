package com.faceit.assignmentelibrary.core.service.impl;

import com.faceit.assignmentelibrary.core.exception.ImageProcessingException;
import com.faceit.assignmentelibrary.core.service.ImageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Base64;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String base64ImageFromFilePath(String path) {
        return bytesToBase64(imageToByte(path));
    }

    public byte[] imageToByte(String pathToFile) {
        byte[] bytes;
        try (RandomAccessFile f = new RandomAccessFile(pathToFile, "r")) {
            bytes = new byte[(int) f.length()];
            f.readFully(bytes);
        } catch (IOException e) {
            throw new ImageProcessingException("Failed to process image", e);
        }
        return bytes;
    }

    public String bytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
