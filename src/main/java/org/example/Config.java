package org.example;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"org.example"})
public class Config {

    @Bean(name = "book")
    @Scope("prototype")
    @Primary
    public Text getBook() {
        return new Book("Сказки");
    }

    @Bean(name = "news")
    public Text getNews() {
        return new News("Breaking news!");
    }
}
