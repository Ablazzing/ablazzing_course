package org.example.dao;

import org.example.entity.FoodEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ArrayListFoodDaoImplementation implements FoodDao {
    private final List<FoodEntity> foodList = new ArrayList<>();




    @Override
    public void create(FoodEntity foodEntity) {
        System.out.println(foodList.size());
        if (foodEntity.getId().equals((long)foodList.size())) {
            System.out.println("ID уже используется! Продукт был добавлен под следующим ID!");
            foodEntity.setId((long) (foodList.size()+1));
        }
        foodList.add(foodEntity);
        foodEntity.setId((long) (foodList.size()));
        System.out.printf("Продукт создан: ID = %s, Имя = %s \n",foodEntity.getId(), foodEntity.getName());

    }

    @Override
    public void deleteById(Long id) {
        try {
            foodList.remove(foodList.stream()
                    .filter(foodEntity -> foodEntity.getId().equals(id))
                    .findFirst().get());
            System.out.printf("Продукт под ID = %s был удалён! \n", id);
        } catch (NoSuchElementException wrongId) {
            System.out.println("Данный ID не существует! введите другой ID!");
        }
    }

    @Override
    public void deleteByName(String name) {
        try {
            foodList.remove(foodList.stream()
                    .filter(foodEntity -> foodEntity.getName().contains(name))
                    .findFirst().get());
            System.out.printf("Продукт под именем %s удалён! \n", name);
        } catch (NoSuchElementException wrongName) {
            System.out.printf("Продукт %s не существует! введите другое имя продукта!\n", name);
        }
    }

    @Override
    public void update(FoodEntity foodEntity) {
        if (foodEntity.getId() > foodList.size() || foodEntity.getId() == 0) {
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
        System.out.println("Список найденных объектов:");
        System.out.println(foodList.stream()
                .filter(foodEntity -> foodEntity.getName().contains(name))
                .collect(Collectors.toList()));
        return foodList.stream()
                .filter(foodEntity -> foodEntity.getName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public void truncate() {
        foodList.clear();
        System.out.println("Список продуктов очищен");
    }
}
