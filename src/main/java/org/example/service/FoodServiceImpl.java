package org.example.service;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.dao.FoodService;
import org.example.dto.FoodDto;
import org.example.entity.FoodEntity;
import org.example.mapper.FoodDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements org.example.service.FoodService {
    private FoodService foodDao;

    @Autowired
    public FoodServiceImpl(FoodService foodDao) {
        this.foodDao = foodDao;
    }

    @Override
    public FoodEntity create(FoodDto foodDto) throws DatabaseUnavailableException, IncorrectDtoValueException {
        try {
            FoodEntity foodEntity = FoodDtoMapper.convertFoodDtoToFoodEntity(foodDto);
            return foodDao.create(foodEntity);
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
    public void update(FoodDto foodDto) throws DatabaseUnavailableException, IncorrectDtoValueException {
        FoodEntity food = FoodDtoMapper.convertFoodDtoToFoodEntity(foodDto);
        if (food.getId() == 0L) {
            throw new IncorrectDtoValueException("Food id is null or 0");
        }
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
