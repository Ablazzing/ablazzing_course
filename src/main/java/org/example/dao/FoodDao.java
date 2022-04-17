package org.example.dao;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.entity.FoodEntity;

import java.io.IOException;
import java.util.List;

public interface FoodDao {
    void create(FoodEntity foodEntity) throws IllegalFileExtensionException, IOException;
    void deleteById(Long id);
    void deleteByName(String name);
    void update(FoodEntity foodEntity);
    List<FoodEntity> findAll();
    FoodEntity findById(Long id);
    List<FoodEntity> findByName(String name);
    void truncate();
}
