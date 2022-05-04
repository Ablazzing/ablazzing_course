package integration;

import lombok.SneakyThrows;
import org.example.csv_worker.CsvWorkerUtil;
import org.example.dao.FoodDao;
import org.example.dao.FoodDaoImpl;
import org.example.entity.FoodEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class FoodDaoImplIT {
    private FoodDao foodDao;
    private static final String FILE_PATH = "C:\\codingSinko\\ablazzing_course_spring\\src\\test\\resources\\food.csv";
    private List<String> initialData = Arrays.asList("id,name", "1,tomato");

    @SneakyThrows
    @BeforeEach
    public void initFoodDao() {
        this.foodDao = new FoodDaoImpl();
        CsvWorkerUtil.writeCsvFile(false, initialData, FILE_PATH);
    }

    @Test
    public void checkCreate() {
        FoodEntity foodEntity = FoodEntity.builder().name("potato").build();
        Assertions.assertDoesNotThrow(() -> foodDao.create(foodEntity));
    }

    @SneakyThrows
    @Test
    public void checkDeleteById() {
        Long inputId = 1l;
        foodDao.deleteById(inputId);
        Assertions.assertNull(foodDao.findById(inputId));
    }

    @SneakyThrows
    @Test
    public void checkFindById() {
        Long inputId = 1l;
        //Expected
        FoodEntity expectedFoodEntity = new FoodEntity(1l, "tomato");

        //Actual
        FoodEntity actualFoodEntity = foodDao.findById(inputId);

        Assertions.assertEquals(expectedFoodEntity, actualFoodEntity);
    }

    @SneakyThrows
    @Test
    public void checkUpdate() {
        Long inputId = 1l;

        //Expected
        FoodEntity inputFoodEntity = new FoodEntity(inputId, "potato");
        foodDao.update(inputFoodEntity);

        //Actual
        FoodEntity actualFoodEntity = foodDao.findById(inputId);
        Assertions.assertEquals(inputFoodEntity, actualFoodEntity);
    }
}