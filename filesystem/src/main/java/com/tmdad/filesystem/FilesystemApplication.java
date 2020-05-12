package com.tmdad.filesystem;

import com.tmdad.filesystem.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class FilesystemApplication implements CommandLineRunner {

	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(FilesystemApplication.class, args);
	}

	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}

}
