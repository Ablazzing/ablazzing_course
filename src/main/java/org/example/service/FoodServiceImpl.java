package org.example.service;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.dao.FoodDao;
import org.example.dto.FoodDto;
import org.example.entity.FoodEntity;
import org.example.mapper.FoodDtoMapper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FoodServiceImpl implements FoodService {
    private FoodDao foodDao;

    public FoodServiceImpl(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    @Override
    public void create(FoodDto foodDto) throws DatabaseUnavailableException {
        try {
            FoodEntity foodEntity = FoodDtoMapper.convertFoodDtoToFoodEntity(foodDto);
            foodDao.create(foodEntity);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void removeDuplicates() throws DatabaseUnavailableException {
        try {
            Set<String> duplicates = new HashSet<>();
            List<FoodEntity> uniqueEntities = foodDao.findAll().stream()
                    .filter(e -> {
                        if (duplicates.contains(e.getName())) {
                            return false;
                        } else {
                            duplicates.add(e.getName());
                            return true;
                        }
                    })
                    .collect(Collectors.toList());

            foodDao.saveList(uniqueEntities);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public Set<String> findDuplicates() throws DatabaseUnavailableException {
        try {
            Map<String, Integer> nameCounts = new HashMap<>();
            foodDao.findAll().forEach(e -> nameCounts.put(e.getName(), nameCounts.getOrDefault(e.getName(), 0) + 1));

            return nameCounts.entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toSet());
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void deleteById(Long id) throws DatabaseUnavailableException {
        try {
            foodDao.deleteById(id);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void deleteByName(String name) throws DatabaseUnavailableException {
        try {
            foodDao.deleteByName(name);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void update(FoodDto foodDto) throws DatabaseUnavailableException {
        FoodEntity food = FoodDtoMapper.convertFoodDtoToFoodEntity(foodDto);
        try {
            foodDao.update(food);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public List<FoodEntity> findAll() throws DatabaseUnavailableException {
        try {
            return foodDao.findAll();
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public FoodEntity findById(Long id) throws DatabaseUnavailableException {
        try {
            return foodDao.findById(id);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public List<FoodEntity> findByName(String name) throws DatabaseUnavailableException {
        try {
            return foodDao.findByName(name);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void clearDatabase() throws DatabaseUnavailableException {
        try {
            foodDao.truncate();
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }
}
