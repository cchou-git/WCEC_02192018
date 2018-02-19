package org.wcec.retreat.app;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.wcec.retreat.entity.AccessLevel;
import org.wcec.retreat.entity.EventTypeTbl;
import org.wcec.retreat.repo.AccessLevelRepo;
import org.wcec.retreat.repo.EventTypeTblRepo;

@SpringBootApplication
@EntityScan(basePackages="org.wcec.retreat.entity")
@EnableJpaRepositories("org.wcec.retreat.repo")
public class Application {
	@Autowired
    private EntityManager entityManager;
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
	
	@Bean
	public CommandLineRunner demo2(AccessLevelRepo repository) {
		return (args) -> {
			// save a couple of customers
			List<AccessLevel> theList = repository.findAll();
			

			// fetch all customers
			log.info("AccessLevel found with findAll():");
			log.info("-------------------------------");
			for (AccessLevel rec : theList) {
				log.info(rec.getDescription());
			} 
			 
			log.info("");
		};
	}
	
	@Bean
	public CommandLineRunner demo3(EventTypeTblRepo repository) {
		return (args) -> {
			// save a couple of customers
			List<EventTypeTbl> theList = repository.findAll();
			

			// fetch all customers
			log.info("EventTypeTbl found with findAll():");
			log.info("-------------------------------");
			for (EventTypeTbl rec : theList) {
				log.info(rec.getDescription());
			} 
			 
			log.info("");
		};
	} 

}
