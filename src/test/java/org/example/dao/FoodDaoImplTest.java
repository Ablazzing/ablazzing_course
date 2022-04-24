package org.example.dao;

import lombok.SneakyThrows;
import org.example.csv_worker.CsvWorkerUtil;
import org.example.entity.FoodEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class FoodDaoImplTest {

    private FoodDao foodDao;
    private static final String FILE_PATH =
            "src/test/resources/food.csv";
    private List<String> initialData = Arrays.asList("id,name", "1,tomato");

    @SneakyThrows
    @BeforeEach
    private void initFoodDao() {
        this.foodDao = new FoodDaoImpl(FILE_PATH);
        CsvWorkerUtil.writeCsvFile(false, initialData, FILE_PATH);
    }

    @Test
    public void createTest() {
        FoodEntity foodEntity = FoodEntity.builder().name("tomato").build();
        Assertions.assertDoesNotThrow(() -> foodDao.create(foodEntity));
    }

    @SneakyThrows
    @Test
    public void checkDeleteById() {
        Long inputId = 1L;
        foodDao.deleteById(inputId);
        Assertions.assertNull(foodDao.findById(inputId));


    }

    @SneakyThrows
    @Test
    public void checkFindById() {
        Long inputId = 1L;
        //expected
        FoodEntity expectedFoodEntity = new FoodEntity(1L, "tomato");

        //actual
        FoodEntity actualFoodEntity = foodDao.findById(inputId);

        Assertions.assertEquals(expectedFoodEntity, actualFoodEntity);
    }

    @SneakyThrows
    @Test
    public void checkUpdate() {
        Long inputId = 1L;
        FoodEntity inputFoodEntity = new FoodEntity(inputId, "potato");
        foodDao.update(inputFoodEntity);
        //Actual
        FoodEntity actualFoodEntity = foodDao.findById(inputId);

        Assertions.assertEquals(actualFoodEntity, inputFoodEntity);

    }
}
