package com.spring.jdbc;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.jdbc.data.PersonDao;
import com.spring.jdbc.entities.Person;

@SpringBootApplication
public class SpringJdbcApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PersonDao personDao;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		int insertedRows = personDao.insertPerson(new Person(10004L, "Safaa", "Riyadh", new Date()));
		logger.info("inserted rows count: {}", insertedRows);
		
		int updatedRows = personDao.updatePerson(new Person(10004L, "Safaa Mohamed", "Riyadh KSA", new Date()));
		logger.info("updated rows count: {}", updatedRows);
		
		int deletedRows = personDao.deletePerson(10004L);
		logger.info("deleted rows count: {}", deletedRows);
		
		Person person = personDao.findById(10003L);
		logger.info("Person: {}", person);
		
		logger.info("\nall persons {}", personDao.findAll());
	}

}
