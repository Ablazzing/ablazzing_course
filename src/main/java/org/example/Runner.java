package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Book book = context.getBean("book", Book.class);
        Book book2 = context.getBean("book", Book.class);

    }
}
