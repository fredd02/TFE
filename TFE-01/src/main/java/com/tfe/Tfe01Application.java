package com.tfe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.tfe.service.IStorageService;
import com.tfe.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Tfe01Application {

	public static void main(String[] args) {
		SpringApplication.run(Tfe01Application.class, args);
	}
	
	@Bean
	CommandLineRunner init(IStorageService storageService) {
		return (args) -> {
			//storageService.deleteAll();
			storageService.init();
		};
	}
	
//	@Bean
//	public CommonsMultipartResolver filterMultipartResolver() {
//		return new CommonsMultipartResolver();
//	}
}
