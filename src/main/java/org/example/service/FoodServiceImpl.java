package org.example.service;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.dao.FoodDao;
import org.example.dto.FoodDto;
import org.example.entity.FoodEntity;
import org.example.mapper.FoodDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {
    private FoodDao foodDao;

    @Autowired
    public FoodServiceImpl(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    @Override
    public FoodEntity create(FoodDto foodDto) throws DatabaseUnavailableException, IncorrectDtoValueException {
        try {
            FoodEntity foodEntity = FoodDtoMapper.convertFoodDtoToEntity(foodDto);
            return foodDao.create(foodEntity);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void removeAllDuplicates() throws DatabaseUnavailableException {
        try {
            Set<String> duplicates = new HashSet<>();
            List<FoodEntity> foodEntities = foodDao.findAll();
            List<FoodEntity> uniqueEntities = foodEntities.stream()
                    .filter(e -> {
                        if (duplicates.contains(e.getName())) {
                            return false;
                        } else {
                            duplicates.add(e.getName());
                            return true;
                        }})
                    .collect(Collectors.toList());
            foodDao.saveListEntities(uniqueEntities);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public Set<String> findAllDuplicates() throws DatabaseUnavailableException {
        try {
            Map<String, Integer> nameCounts = new HashMap<>();
            foodDao.findAll()
                    .forEach(e -> nameCounts.put(e.getName(), nameCounts.getOrDefault(e.getName(), 0) + 1));
            return nameCounts.entrySet().stream()
                    .filter(e -> e.getValue() > 1)
                    .map(e -> e.getKey())
                    .collect(Collectors.toSet());
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
        try {
            FoodEntity foodEntity = FoodDtoMapper.convertFoodDtoToEntity(foodDto);
            if(foodEntity.getId() == 0L) {
                throw new IncorrectDtoValueException("Food id is null or 0");
            }
            foodDao.update(foodEntity);
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

