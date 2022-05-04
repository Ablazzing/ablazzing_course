package org.example.mapper;

import lombok.experimental.UtilityClass;
import org.example.dto.FoodDto;
import org.example.entity.FoodEntity;
import org.example.service.IncorrectDtoValueException;

@UtilityClass
public class FoodDtoMapper {

    public FoodEntity convertFoodDtoToEntity(FoodDto foodDto) throws IncorrectDtoValueException {

        return FoodEntity.builder()
                .id(foodDto.getProductId() == null ? 0L : Long.valueOf(foodDto.getProductId()))
                .name(checkName(foodDto.getProductName()))
                .build();
    }

    private String checkName(String name) throws IncorrectDtoValueException {
        if(name == null || name.isEmpty()) {
            throw new IncorrectDtoValueException("Name cannot be null or empty");
        }
        return name;
    }
}
