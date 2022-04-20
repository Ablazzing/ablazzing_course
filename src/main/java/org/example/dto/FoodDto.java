package org.example.dto;

import lombok.Value;

@Value
public class FoodDto {

    Integer productId;
    String productName;
    Integer session;
}
