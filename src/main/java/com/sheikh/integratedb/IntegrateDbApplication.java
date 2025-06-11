package com.sheikh.integratedb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableMongoAuditing
@EnableTransactionManagement
@SpringBootApplication
public class IntegrateDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrateDbApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager transaction(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);

	}
}
