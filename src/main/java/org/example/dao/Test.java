package org.example.dao;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.entity.FoodEntity;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IllegalFileExtensionException, IOException {
        FoodDaoListKeeper foodDaoListKeeper = new FoodDaoListKeeper();


        foodDaoListKeeper.create(new FoodEntity(1L, "banana"));
        foodDaoListKeeper.create(new FoodEntity(2L, "anana"));
        foodDaoListKeeper.create(new FoodEntity(3L, "ana"));
        foodDaoListKeeper.create(new FoodEntity(4L, "apple"));
        foodDaoListKeeper.create(new FoodEntity(4L, "ape"));

        System.out.println(foodDaoListKeeper.findById(9L));
        System.out.println(foodDaoListKeeper.findByName("ana"));

        foodDaoListKeeper.update(new FoodEntity(4L, "pineapple"));
        foodDaoListKeeper.deleteById(8L);
        foodDaoListKeeper.deleteByName("anaweqwe");
        System.out.println(foodDaoListKeeper.findAll().toString());
        foodDaoListKeeper.truncate();
        System.out.println(foodDaoListKeeper.findAll().toString());
    }
}
