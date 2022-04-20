package org.example.mapper;

import lombok.experimental.UtilityClass;
import org.example.entity.FoodEntity;

@UtilityClass
public class FoodEntityMapper {
    public FoodEntity convertTextToEntity(String row, String delimiter){
        String[] array = row.split(delimiter);
        return new FoodEntity(Long.valueOf(array[0]), array[1]);
    }

    public String convertEntityToText(FoodEntity foodEntity, String delimiter){
        return String.format("%d%s%s", foodEntity.getId(), delimiter, foodEntity.getName());
    }
}
