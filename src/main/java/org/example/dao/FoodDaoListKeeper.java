package org.example.dao;

import org.example.entity.FoodEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FoodDaoListKeeper implements FoodDao {
    private final List<FoodEntity> foodEntityKeeper = new ArrayList<>();

    @Override
    public void create(FoodEntity foodEntity) {
        if (foodEntityKeeper.stream()
                .anyMatch(e -> e.getId().equals(foodEntity.getId())))
            System.out.println("Id already used, please use the function 'update'");
        else foodEntityKeeper.add(foodEntity);
    }

    @Override
    public void deleteById(Long id) {
        foodEntityKeeper.removeIf(e -> e.getId().equals(id));

    }

    @Override
    public void deleteByName(String name) {
        foodEntityKeeper.removeIf(e -> e.getName().equals(name));

    }

    @Override
    public void update(FoodEntity foodEntity) {
        if (foodEntityKeeper.stream()
                .anyMatch(e -> e.getId().equals(foodEntity.getId()))) {
            foodEntityKeeper.removeIf(e -> e.getId().equals(foodEntity.getId()));
            foodEntityKeeper.add(foodEntity);
        }

    }

    @Override
    public List<FoodEntity> findAll() {
        return new ArrayList<>(foodEntityKeeper);
    }

    @Override
    public FoodEntity findById(Long id) {
        FoodEntity temp = null;
        if (foodEntityKeeper.stream().anyMatch(e -> e.getId().equals(id))){
            temp =  foodEntityKeeper.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .get();
        }
        return temp;
    }

    @Override
    public List<FoodEntity> findByName(String name) {
        return foodEntityKeeper.stream()
                .filter(e -> e.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void truncate() {
        foodEntityKeeper.clear();
    }
}
