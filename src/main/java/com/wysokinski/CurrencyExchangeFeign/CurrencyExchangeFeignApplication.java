package com.wysokinski.CurrencyExchangeFeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyExchangeFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeFeignApplication.class, args);
	}

}
