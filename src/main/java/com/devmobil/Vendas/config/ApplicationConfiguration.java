package com.devmobil.Vendas.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
	
	@Bean("applicationName")
	public String applicationName() {
		return "Aplicação de Vendas";
	}
}
