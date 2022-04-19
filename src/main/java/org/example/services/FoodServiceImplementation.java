package org.example.services;

import org.example.dao.FoodDao;
import org.example.entity.FoodEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FoodServiceImplementation implements FoodDao {
    private final List<FoodEntity> foodList = new ArrayList<>();


    @Override
    public void create(FoodEntity foodEntity) {
        foodList.add(foodEntity);
        System.out.printf("Продукт создан: %s \n", foodEntity.getName());

    }

    @Override
    public void deleteById(Long id) {
        boolean flag = false;
        for (FoodEntity foodEntity : foodList) {
            if (foodEntity.getId() == id) {
                foodList.remove(foodEntity);
                System.out.println("Элемент удалён!");
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("Продукт не найден!");
        }

    }

    @Override
    public void deleteByName(String name) {
        try {
            foodList.remove(foodList.stream().filter(foodEntity -> foodEntity.getName().equals(name)).findFirst().get());
        } catch (NoSuchElementException cantFindName) {
            System.out.printf("Не найден элемент для удаления! %s нет в списке! \n", name);
        }
    }

    @Override
    public void update(FoodEntity foodEntity) {
        boolean flag = false;
        for (FoodEntity foodEntityInList : foodList) {
            if (foodEntityInList.getId() == foodEntity.getId()) {
                foodEntityInList.setName(foodEntity.getName());
                System.out.printf("Продукт обновлён: %s %s \n", foodEntity.getId(), foodEntity.getName());
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("Продукт не найден!");
        }
    }

    @Override
    public List<FoodEntity> findAll() {
        System.out.println(foodList);
        return foodList;
    }

    @Override
    public FoodEntity findById(Long id) {
        FoodEntity foodEntity;
        for (FoodEntity food : foodList) {
            if (food.getId() == id) {
                foodEntity = food;
                System.out.println(food);
                return foodEntity;
            }
        }
        System.out.println("Продукт не найден");
        return null;
    }


    @Override
    public List<FoodEntity> findByName(String name) {
        List<FoodEntity> smallList = new ArrayList<>();
        for (FoodEntity food : foodList) {
            if (food.getName().equals(name)) {
                smallList.add(food);
            }
        }
        if (smallList.isEmpty()) {
            System.out.println("Продукты с таким именем не найдены");
            return null;
        }
        System.out.println(smallList);
        return smallList;
    }

    @Override
    public String truncate() {
        foodList.clear();
        return "Список продуктов очищен";
    }
}
