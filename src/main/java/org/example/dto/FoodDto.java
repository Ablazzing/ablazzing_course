package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class FoodDto {

    Integer productId;
    @JsonProperty("product_name")
    String productName;
    Integer session;
}
