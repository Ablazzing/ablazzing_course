package org.example.mapper;

import lombok.experimental.UtilityClass;
import org.example.dto.FoodDto;
import org.example.entity.FoodEntity;

@UtilityClass
public class FoodDtoMapper {
    public FoodEntity convertFoodDtoToFoodEntity(FoodDto foodDto) {
        return FoodEntity.builder()
                .id(Long.valueOf(foodDto.getProductId()))
                .name(foodDto.getProductName())
                .build();
    }
}
