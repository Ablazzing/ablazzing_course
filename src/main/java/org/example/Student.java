package org.example;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Student {
    private Text text;

    public void readBook() {
        System.out.println("Student read book: " + text.getName());
    }
}
