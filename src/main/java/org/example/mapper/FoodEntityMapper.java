package org.example.mapper;

import org.example.entity.FoodEntity;

public class FoodEntityMapper {
    public static FoodEntity convertTextToEntity(String row, String delimiter){
        String[] array = row.split(delimiter);
        return new FoodEntity(Long.valueOf(array[0]), array[1]);
    }
}
