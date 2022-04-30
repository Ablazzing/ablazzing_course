package org.example;

import lombok.Value;
import org.springframework.stereotype.Component;

@Component
@Value
public class University {
    private Group group;
}
