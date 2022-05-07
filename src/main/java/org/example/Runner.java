package org.example;

import org.apache.catalina.core.ApplicationContext;
import org.example.dao.PeopleDao;
import org.example.dao.PeopleDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootApplication
public class Runner {
    public static void main(String[] args) {
//        SpringApplication.run(Runner.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Runner.class);
        PeopleDao peopleDao = context.getBean(PeopleDao.class);

    }
}
