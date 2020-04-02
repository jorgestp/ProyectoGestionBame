package com.bame.es.gestion.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bame.es.gestion.app.models.service.impl.IUploadFileService;

@SpringBootApplication
public class GestionBameApplication implements CommandLineRunner {

	@Autowired
	private IUploadFileService uploadService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionBameApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		uploadService.deleteAll();
		uploadService.init();
	}

}
