package org.example;

import org.example.service.DatabaseUnavailableException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
//@ComponentScan("org.example")
public class Runner {
    public static void main(String[] args) throws DatabaseUnavailableException {
        SpringApplication.run(Runner.class, args);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Runner.class);
//        PeopleServiceImpl peopleService = context.getBean(PeopleServiceImpl.class);
//        peopleService.removeAllDuplicates();
    }
}


