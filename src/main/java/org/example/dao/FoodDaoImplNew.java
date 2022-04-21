package org.example.dao;

import org.example.entity.FoodEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class FoodDaoImplNew implements FoodDao {
    private final List<FoodEntity> foodList = new ArrayList<>();
    private Long currentId;

    public FoodDaoImplNew() {
        currentId = initCurrentId();
    }

    private Long initCurrentId() {
        List<Long> idList = foodList.stream().map(value -> value.getId()).sorted().collect(Collectors.toList());
        if (idList.isEmpty()) {
            return 1L;
        }
        return (long) idList.size();
    }

    @Override
    public void create(FoodEntity foodEntity) {
        foodEntity.setId(currentId);
        foodList.add(foodEntity);
        System.out.printf("Продукт создан: ID = %s, Имя = %s \n", foodEntity.getId(), foodEntity.getName());
        currentId++;
    }

    @Override
    public void deleteById(Long id) {
        try {
            foodList.remove(foodList.stream()
                    .filter(foodEntity -> foodEntity.getId().equals(id))
                    .findFirst().get());
            System.out.printf("Продукт под ID = %s был удалён! \n", id);
        } catch (NoSuchElementException wrongId) {
            System.out.println("Данный ID не существует! Невозможно удалить!");
        }
    }

    @Override
    public void deleteByName(String name) {
        try {
            foodList.removeAll(foodList.stream()
                    .filter(foodEntity -> foodEntity.getName().contains(name))
                    .collect(Collectors.toList()));
            System.out.printf("Продукты под именем %s удалён! \n", name);
        } catch (NoSuchElementException wrongName) {
            System.out.printf("Продукт %s не существует! Невозможно удалить!\n", name);
        }
    }

    @Override
    public void update(FoodEntity foodEntity) {
        if (foodList.stream().noneMatch(foodEntityInList -> foodEntityInList.getId().equals(foodEntity.getId()))) {
            System.out.println("Неправильный ID! Введите существующий ID!");
        } else {
            foodList.stream()
                    .forEach(foodEntityInList -> {
                        if (foodEntityInList.getId().equals(foodEntity.getId())) {
                            foodEntityInList.setName(foodEntity.getName());
                            System.out.printf("Продукт под номером %s обновлён! теперь это '%s' \n", foodEntity.getId(), foodEntity.getName());
                        }
                    });
        }

    }

    @Override
    public List<FoodEntity> findAll() {
        System.out.println(foodList); //сложна, непонятна, очень сложна
        return foodList;
    }

    @Override
    public FoodEntity findById(Long id) {
        try {
            FoodEntity foodEntityInList = foodList.stream()
                    .filter(foodEntity -> foodEntity.getId().equals(id))
                    .findFirst().get();
            System.out.printf("Продукт найден по ID = %s, это: %s \n", foodEntityInList.getId(), foodEntityInList.getName());
            return foodEntityInList;
        } catch (NoSuchElementException wrongId) {
            System.out.println("Данный ID не существует! введите другой ID!");
        }
        return null;
    }


    @Override
    public List<FoodEntity> findByName(String name) {
        List<FoodEntity> tempList = foodList.stream()
                .filter(foodEntity -> foodEntity.getName().contains(name))
                .collect(Collectors.toList());
        System.out.println("Список найденных объектов:");
        System.out.println(tempList);
        return tempList;
    }

    @Override
    public void truncate() {
        foodList.clear();
        System.out.println("Список продуктов очищен");
    }
}
