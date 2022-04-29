package org.example.service;

import org.example.dto.FoodDto;
import org.example.entity.FoodEntity;

import java.util.List;
import java.util.Set;

public interface FoodService {
    void removeDuplicates() throws DatabaseUnavailableException;

    Set<String> findDuplicates() throws DatabaseUnavailableException;

    void create(FoodDto foodDto) throws DatabaseUnavailableException;

    void deleteById(Long id) throws DatabaseUnavailableException;

    void deleteByName(String name) throws DatabaseUnavailableException;

    void update(FoodDto foodDto) throws DatabaseUnavailableException;

    List<FoodEntity> findAll() throws DatabaseUnavailableException;

    FoodEntity findById(Long id) throws DatabaseUnavailableException;

    List<FoodEntity> findByName(String name) throws DatabaseUnavailableException;

    void clearDatabase() throws DatabaseUnavailableException;
}
