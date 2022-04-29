package integration;

import lombok.SneakyThrows;
import org.example.dao.FoodDao;
import org.example.dao.FoodDaoImpl;
import org.example.entity.FoodEntity;
import org.example.service.FoodServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FoodServiceIT {
    private static final String FILE_PATH = "C:\\Users\\kia\\IdeaProjects\\JavaProjectTutor\\src\\test\\resources\\food.csv";

    @SneakyThrows
    @Test
    public void checkFindById() {
        Long inputId = 1L;
        FoodDao foodDao = new FoodDaoImpl(FILE_PATH);
        FoodServiceImpl foodService = new FoodServiceImpl(foodDao);

        FoodEntity expectedFood = new FoodEntity(1L, "Babana");

        FoodEntity actual = foodService.findById(1L);

        Assertions.assertEquals(expectedFood, actual);
    }

    @Test
    public void checkCreate() {

    }
}
