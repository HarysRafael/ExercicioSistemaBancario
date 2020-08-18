package com.br.sistemabancariospringboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SistemaBancarioSpringApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();

	}

	public static void main(String[] args) {
		SpringApplication.run(SistemaBancarioSpringApplication.class, args);
	}

}
