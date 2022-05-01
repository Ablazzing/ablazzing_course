package integration;

import lombok.SneakyThrows;
import org.example.dao.FoodDao;
import org.example.dao.FoodDaoImpl;
import org.example.dto.FoodDto;
import org.example.entity.FoodEntity;
import org.example.service.FoodServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FoodServiceIT {
    static final String FILE_PATH = "C:\\codingSinko\\ablazzing_course_spring\\src\\test\\resources\\food.csv";

    @Test
    @SneakyThrows
    public void checkFindById() {
        System.out.println(".checkFindById запустился");
        Long inputId = 1l;
        FoodDao foodDao = new FoodDaoImpl();
        FoodServiceImpl foodService = new FoodServiceImpl(foodDao);

        FoodEntity expectedFoodEntity = new FoodEntity(1l, "tomato");

        FoodEntity actualFoodEntity = foodService.findById(inputId);

        Assertions.assertEquals(expectedFoodEntity, actualFoodEntity);
    }
}
