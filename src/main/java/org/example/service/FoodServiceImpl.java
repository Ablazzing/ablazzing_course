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

    FoodDao foodDao;

    public FoodServiceImpl(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    @Override
    public void create(FoodDto foodDto) throws DatabaseUnavailableException {
        try {
            FoodEntity foodEntity = FoodDtoMapper.convertFoodDtoToEntity(foodDto);
            foodDao.create(foodEntity);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void removeAllDuplicates() throws DatabaseUnavailableException {

    }

    @Override
    public Map<String, Integer> findAllDuplicates() throws DatabaseUnavailableException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws DatabaseUnavailableException {

    }

    @Override
    public void deleteByName(String name) throws DatabaseUnavailableException {

    }

    @Override
    public void update(FoodDto foodDto) throws DatabaseUnavailableException {

    }

    @Override
    public List<FoodEntity> findAll() throws DatabaseUnavailableException {
        return null;
    }

    @Override
    public FoodEntity findById(Long id) throws DatabaseUnavailableException {
        return null;
    }

    @Override
    public List<FoodEntity> findByName(String name) throws DatabaseUnavailableException {
        return null;
    }

    @Override
    public void clearDatabase() throws DatabaseUnavailableException {

    }

}

