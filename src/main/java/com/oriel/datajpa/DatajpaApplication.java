package com.oriel.datajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DatajpaApplication {

	private static final Logger log = LoggerFactory.getLogger(DatajpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DatajpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository){
		return (args) -> {// Save few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			//fetch all customers

			log.info("Customer found with findAll():");
			log.info("--------------------------------");

			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}

			//fetch customer by id

			log.info("Customer found with id = 1");
			log.info("--------------------------------");
			log.info(repository.findById(1L).toString());

			//fetch customer by lastname
			log.info("Customer found with lastname ('Bauer'):");
			log.info("--------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");
		};
	}
}
