package com.example.jpa02.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {  // dto와 entity 상호변환
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
