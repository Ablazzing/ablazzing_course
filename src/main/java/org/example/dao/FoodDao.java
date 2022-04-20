package org.example.dao;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.entity.FoodEntity;

import java.io.IOException;
import java.util.List;

public interface FoodDao {
    void create(FoodEntity foodEntity) throws IllegalFileExtensionException, IOException;
    void deleteById(Long id) throws IllegalFileExtensionException, IOException;
    void deleteByName(String name) throws IllegalFileExtensionException, IOException;
    void update(FoodEntity foodEntity) throws IllegalFileExtensionException, IOException;
    List<FoodEntity> findAll() throws IllegalFileExtensionException, IOException;
    FoodEntity findById(Long id) throws IllegalFileExtensionException, IOException;
    List<FoodEntity> findByName(String name) throws IllegalFileExtensionException, IOException;
    void truncate() throws IllegalFileExtensionException, IOException;
}
