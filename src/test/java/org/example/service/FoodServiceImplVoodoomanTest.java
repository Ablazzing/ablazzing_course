package org.example.service;

import lombok.SneakyThrows;
import org.example.dao.FoodDaoImpl;
import org.example.dto.FoodDto;

public class FoodServiceImplVoodoomanTest {
    @SneakyThrows
    public static void main(String[] args) {
        FoodDaoImpl foodDaoImpl = new FoodDaoImpl(
                "C:\\Users\\Voodo\\Desktop\\GitLocalRepository\\ablazzing_course\\src\\test\\resources\\food.csv"
        );
        FoodServiceImplVoodooman test = new FoodServiceImplVoodooman(foodDaoImpl);
//        test.clearDatabase();
//        test.create(new FoodDto(1,"tomato"));
//        test.create(new FoodDto(1,"potato"));
//        test.create(new FoodDto(1,"strawberry"));
//        test.create(new FoodDto(1,"banana"));
//        test.create(new FoodDto(1,"banana"));
//        test.create(new FoodDto(1,"banana"));
//        test.create(new FoodDto(1,"banana"));
//        test.create(new FoodDto(1,"banana"));
//        test.create(new FoodDto(1,"banana"));
//        test.create(new FoodDto(1,"banana"));
//        test.create(new FoodDto(1,"banana"));
//        test.create(new FoodDto(1,"banana"));
//        test.create(new FoodDto(1,"peach"));
//        test.create(new FoodDto(1,"cucumber"));

//        test.findAll();
        test.findByName("strawberry");
        test.deleteById(2L);
        test.deleteById(3L);
//        test.update(new FoodDto(18, "banana"));
//        test.update(new FoodDto(45, "banana"));
//        test.findAllDuplicates();
//        test.removeAllDuplicates();
    }
}
