package com.compass.avaliacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ApiPedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPedidoApplication.class, args);
	}

}
