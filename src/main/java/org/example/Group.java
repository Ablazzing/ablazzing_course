package org.example;

import lombok.Value;
import org.springframework.stereotype.Component;

@Component
@Value
public class Group {
    private final Student student;
}
