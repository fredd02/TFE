package com.tfe;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.tfe.service.IStorageService;
import com.tfe.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Tfe01Application extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(Tfe01Application.class, args);
	}
	
	@Bean(name="localeResolver")
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.FRENCH);
		return slr;
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
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
