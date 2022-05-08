package org.example.dao;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.entity.FoodEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FoodDaoListImpl implements FoodDao {
    private List<FoodEntity> foodList = new ArrayList<>();
    private long currentId = 1L;

    @Override
    public FoodEntity create(FoodEntity foodEntity) throws IllegalFileExtensionException, IOException {
        foodEntity.setId(currentId);
        foodList.add(foodEntity);
        currentId++;
        return foodEntity;
    }

    @Override
    public void deleteById(Long id) throws IllegalFileExtensionException, IOException {
        foodList = foodList.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByName(String name) throws IllegalFileExtensionException, IOException {
        foodList = foodList.stream()
                .filter(e -> !e.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void update(FoodEntity foodEntity) throws IllegalFileExtensionException, IOException {
        if (foodEntity.getId() != null) {
            foodList.stream()
                    .filter(e -> e.getId().equals(foodEntity.getId()))
                    .forEach(e -> e.setName(foodEntity.getName()));
        }
    }

    @Override
    public List<FoodEntity> findAll() throws IllegalFileExtensionException, IOException {
        return foodList;
    }

    @Override
    public FoodEntity findById(Long id) throws IllegalFileExtensionException, IOException {
        return foodList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<FoodEntity> findByName(String name) throws IllegalFileExtensionException, IOException {
        return foodList.stream()
                .filter(e -> e.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void truncate() throws IllegalFileExtensionException, IOException {
        foodList.clear();
    }

    @Override
    public void saveList(List<FoodEntity> foodList) throws IllegalFileExtensionException, IOException {
    }

    @Override
    public String toString() {
        return "FoodDaoListImpl{" +
                "foodList=" + foodList +
                '}';
    }
}
