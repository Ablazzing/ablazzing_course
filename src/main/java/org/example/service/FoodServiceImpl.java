package org.example.service;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.dao.FoodDao;
import org.example.dto.FoodDto;
import org.example.entity.FoodEntity;
import org.example.mapper.FoodDtoMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    }

    @Override
    public Map<String, Integer> findDuplicates() throws DatabaseUnavailableException {
        return null;
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
        FoodEntity food = FoodEntity.builder()
                .id(Long.valueOf(foodDto.getProductId()))
                .name(foodDto.getProductName())
                .build();

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
