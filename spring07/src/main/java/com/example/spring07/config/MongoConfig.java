package com.example.spring07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {
	@Bean
	MongoClient mongoClient() {
		return MongoClients.create("mongodb://web:1234@localhost:27017/web?authSource=web&authMechanism=SCRAM-SHA-1");
	}
}
