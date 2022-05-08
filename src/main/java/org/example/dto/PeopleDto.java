package org.example.dto;

import lombok.Value;

@Value
public class PeopleDto {

    Long humanId;

    String name;

    Integer age;

    String foodName;

    String sessionId;
}
