package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@Builder
@Jacksonized
public class PeopleEntity {

    private Long id;

    @NonNull
    private String name;

    private Integer age;

    private FoodEntity food;
}

