package com.example.thymelife;

import com.example.thymelife.control.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootThymelifeApplication implements CommandLineRunner{

	@Autowired
	UploadFileService uploadFileService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootThymelifeApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		uploadFileService.deleteAll();
		uploadFileService.init();
	}
}
