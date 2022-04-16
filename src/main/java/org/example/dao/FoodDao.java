package org.example.dao;

import org.example.entity.FoodEntity;

import java.util.List;

public interface FoodDao {
    void create(FoodEntity foodEntity);
    void deleteById(Long id);
    void deleteByName(String name);
    void update(FoodEntity foodEntity);
    List<FoodEntity> findAll();
    FoodEntity findById(Long id);
    List<FoodEntity> findByName(String name);
    void truncate();
}
