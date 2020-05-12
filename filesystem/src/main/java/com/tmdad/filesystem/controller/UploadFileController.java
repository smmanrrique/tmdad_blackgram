package com.tmdad.filesystem.controller;


import com.tmdad.filesystem.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class UploadFileController {
    private static final Logger logger = LoggerFactory.getLogger(UploadFileController.class.getName());
    public final String CROSS_ORIGIN = "http://localhost:4200";
    List<String> files = new ArrayList<String>();
//    private Map<String, List<String>> userFiles = new HashMap<String, List<String>>();

    @Autowired
    StorageService storageService;

    @CrossOrigin(origins =CROSS_ORIGIN)
    @PostMapping("/uploadFile")
    public ResponseEntity<String> UploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("user") String userName) {

        logger.info("Call UploadFile file name: {}", file.getName());
        String message = "";
        try {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            storageService.store(file);

//            ArrayList<String> fileList= new List<String>();
//            userFiles.put(userName, fileList);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            logger.error("FAIL to upload : {}", e.getCause());

            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @CrossOrigin(origins = CROSS_ORIGIN)
    @GetMapping("/uploadFile")
    public ResponseEntity<List<String>> getListFiles(Model model) {
        List<String> fileNames = files
                .stream().map(fileName -> MvcUriComponentsBuilder
                        .fromMethodName(UploadFileController.class, "getFile", fileName).build().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fileNames);
    }

    @CrossOrigin(origins = CROSS_ORIGIN)
    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}