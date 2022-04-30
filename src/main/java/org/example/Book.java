package org.example;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("book")
@Primary
public class Book implements Text {
    private static int count = 0;
    private int index = 0;
    private String name;

    public Book(String name) {
        this.name = name;
        count++;
        index = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getIndex() {
        return index;
    }
}
