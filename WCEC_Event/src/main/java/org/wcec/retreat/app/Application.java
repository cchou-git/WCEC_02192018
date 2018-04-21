package org.wcec.retreat.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
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
import org.wcec.retreat.entity.EventTbl;
import org.wcec.retreat.entity.EventTypeTbl;
import org.wcec.retreat.model.MealPlan;
import org.wcec.retreat.model.MealTemplate;
import org.wcec.retreat.repo.AccessLevelRepo;
import org.wcec.retreat.repo.EventTblRepo;
import org.wcec.retreat.repo.EventTypeTblRepo;

import com.vaadin.server.VaadinSession;

@SpringBootApplication
@EntityScan(basePackages="org.wcec.retreat.entity")
@EnableJpaRepositories("org.wcec.retreat.repo")
public class Application {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
	
	@Bean
	MealTemplate createMealTemplate() {
		MealTemplate appTemplate = new MealTemplate();
		return appTemplate;
	}
	
	@Bean
	MealPlan createMealPlan() {
		MealPlan appMealPlan = new MealPlan();
		return appMealPlan;
	}
	
	@Autowired
	EventTblRepo repo;

	@Autowired
	static List<DateTime> activeEventDateList;

	static EventTbl TheActiveEvent;

	
	
	@Bean
	public CommandLineRunner demo2(AccessLevelRepo repository) {
		//System.out.println("Entity Manager persistence unit name = " + entityManager.getProperties());
		
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
