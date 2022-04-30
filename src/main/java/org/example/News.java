package org.example;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

@Component("news")
@Value
@AllArgsConstructor
public class News implements Text {
    private String name;

    public String getName() {
        return name;
    }
}
