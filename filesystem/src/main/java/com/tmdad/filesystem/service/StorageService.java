package com.tmdad.filesystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class StorageService {

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class.getName());
    private final Path rootLocation = Paths.get("../files");

    public void store(MultipartFile file) {
        logger.info("Call store with file name: {}", file.getName());
        try {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            File f = new File(this.rootLocation.resolve(file.getOriginalFilename()).toString());
            if(f.exists() && !f.isDirectory()) {
                System.out.println("EXIST FILE................");
            }else {
                System.out.println("USING COPY ...............");
                Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println("STORAGE");
            System.out.println("Message ");
            System.out.println(e.getMessage());
            System.out.println("Couse ");
            System.out.println(e.getCause());
            throw new RuntimeException("FAIL!", e.getCause());
        }
    }

    public Resource loadFile(String filename) {
        logger.info("Call loadFile: {}", filename);
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteAll() {
        logger.info("Call deleteAll all files in bucket");
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            logger.info("Init container to files");
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}