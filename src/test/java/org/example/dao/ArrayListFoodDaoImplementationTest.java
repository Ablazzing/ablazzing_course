package org.example.dao;

import org.example.entity.FoodEntity;

public class ArrayListFoodDaoImplementationTest {
    public static void main(String[] args) {
        ArrayListFoodDaoImplementation foodServiceImplementationObject = new ArrayListFoodDaoImplementation();
        foodServiceImplementationObject.create(new FoodEntity(1L, "banana1"));
        foodServiceImplementationObject.create(new FoodEntity(2L, "potato2"));
        foodServiceImplementationObject.create(new FoodEntity(3L, "strawberry3"));
        foodServiceImplementationObject.create(new FoodEntity(4L, "banana4"));
        foodServiceImplementationObject.create(new FoodEntity(5L, "banana5"));
        foodServiceImplementationObject.create(new FoodEntity(5L, "banana6"));
        foodServiceImplementationObject.create(new FoodEntity(5L, "banana7"));
        foodServiceImplementationObject.create(new FoodEntity(5L, "banana8"));
        foodServiceImplementationObject.create(new FoodEntity(5L, "banana9"));
        foodServiceImplementationObject.create(new FoodEntity(5L, "banana10"));
        foodServiceImplementationObject.create(new FoodEntity(5L, "banana11"));
        foodServiceImplementationObject.create(new FoodEntity(5L, "banana12"));
        foodServiceImplementationObject.create(new FoodEntity(5L, "banana13"));
        foodServiceImplementationObject.create(new FoodEntity(13L, "banana14"));
        foodServiceImplementationObject.create(new FoodEntity(13L, "banana15"));
        foodServiceImplementationObject.create(new FoodEntity(5L, "banana16"));
        foodServiceImplementationObject.create(new FoodEntity(15L, "banana17"));
        foodServiceImplementationObject.create(new FoodEntity(13L, "banana18"));
//        foodServiceImplementationObject.create(new FoodEntity(7L, "banana"));
//        foodServiceImplementationObject.create(new FoodEntity(8L, "banana"));
//        foodServiceImplementationObject.create(new FoodEntity(9L, "banana"));
//        foodServiceImplementationObject.create(new FoodEntity(10L, "banana"));
//        foodServiceImplementationObject.truncate();
//        foodServiceImplementationObject.findAll();
//        foodServiceImplementationObject.findById(0L);
//        foodServiceImplementationObject.findById(4L);
        foodServiceImplementationObject.findByName("banana");
//        foodServiceImplementationObject.findByName("banan");
//        foodServiceImplementationObject.update(new FoodEntity(0L, "carrot"));
//        foodServiceImplementationObject.update(new FoodEntity(5L, "carrot"));
//        foodServiceImplementationObject.deleteByName("strawberry");
//        foodServiceImplementationObject.deleteByName("strawberr");
//        foodServiceImplementationObject.deleteByName("strawberry");
//        foodServiceImplementationObject.deleteByName("banana");
//        foodServiceImplementationObject.deleteById(2L);
//        foodServiceImplementationObject.deleteById(2L);
//        foodServiceImplementationObject.findAll();
//        foodServiceImplementationObject.findById(2L);
//        foodServiceImplementationObject.findAll();



    }

}
