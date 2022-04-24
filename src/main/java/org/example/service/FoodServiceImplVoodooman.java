package org.example.service;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.dao.FoodDaoImpl;
import org.example.dto.FoodDto;
import org.example.entity.FoodEntity;
import org.example.mapper.FoodDtoMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FoodServiceImplVoodooman implements FoodService {

    FoodDaoImpl foodDaoImpl;

    public FoodServiceImplVoodooman(FoodDaoImpl foodDaoImpl) throws IllegalFileExtensionException, IOException {
        this.foodDaoImpl = foodDaoImpl;
    }


    @Override
    public void create(FoodDto foodDto) throws DatabaseUnavailableException {
        try {
            FoodEntity foodEntity = FoodDtoMapper.convertFoodDtoToEntity(foodDto);
            foodDaoImpl.create(foodEntity);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void removeAllDuplicates() throws DatabaseUnavailableException {

        List<String> tempList = new ArrayList<>();

        Map<String, Integer> allDuplicates = findAllDuplicates();

        allDuplicates.forEach((key, value) -> {
            if (value > 1) {
                tempList.add(key);
            }
        });

        tempList.forEach(name -> {
            try {
                foodDaoImpl.deleteByName(name);
                foodDaoImpl.create(FoodEntity.builder().name(name).build());
            } catch (IOException | IllegalFileExtensionException e) {
                e.printStackTrace();
            }
        });

        System.out.println(tempList);
    }

    @Override
    public Map<String, Integer> findAllDuplicates() throws DatabaseUnavailableException {
        try {
        List<FoodEntity> foodEntityList = foodDaoImpl.findAll();

        Map<String, Integer> duplicates = new TreeMap<>();
            if (!foodEntityList.isEmpty()) {
                foodEntityList.forEach(FoodEntity -> {
                    if (duplicates.containsKey(FoodEntity.getName())) {
                        duplicates.put(FoodEntity.getName(), duplicates.get(FoodEntity.getName()) + 1);
                    } else {
                        duplicates.put(FoodEntity.getName(), 1);
                    }
                });
                System.out.println(duplicates);
            } else {
                System.out.println("Невозможно найти дубликаты! База данных пуста!");
            }
            return duplicates;
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void deleteById(Long id) throws DatabaseUnavailableException {
        try {
            if (foodDaoImpl.findById(id) != null) {
                foodDaoImpl.deleteById(id);
                System.out.printf("Объекты по запросу '%s' удалены!\n", id);
            } else {
                System.out.printf("Нечего удалять! Объекты по запросу '%s' не найдены!\n", id);
            }
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void deleteByName(String name) throws DatabaseUnavailableException {
        try {
            foodDaoImpl.deleteByName(name);
            System.out.printf("Объекты по запросу '%s' удалены!\n", name);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void update(FoodDto foodDto) throws DatabaseUnavailableException {
        try {
            FoodEntity foodEntity = FoodDtoMapper.convertFoodDtoToEntity(foodDto);
            foodDaoImpl.update(foodEntity);
            System.out.printf(
                    "Продукт под номером %s обновлён! теперь это '%s' \n", foodEntity.getId(), foodEntity.getName()
            );
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public List<FoodEntity> findAll() throws DatabaseUnavailableException {
        try {
            List<FoodEntity> tempList = foodDaoImpl.findAll();
            System.out.printf("Результаты по запросу! \n%s\n", tempList);
            return tempList;
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public FoodEntity findById(Long id) throws DatabaseUnavailableException {
        try {
            FoodEntity tempFoodEntity = foodDaoImpl.findById(id);
            System.out.printf("Результаты по запросу! \n%s\n", tempFoodEntity);
            return tempFoodEntity;

        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public List<FoodEntity> findByName(String name) throws DatabaseUnavailableException {
        try {
            List<FoodEntity> tempList = foodDaoImpl.findByName(name);
            System.out.printf("Результаты по запросу! \n%s\n", tempList);
            return tempList;
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void clearDatabase() throws DatabaseUnavailableException {
        try {
            foodDaoImpl.truncate();
            System.out.println("База данных очищена!");
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

}

