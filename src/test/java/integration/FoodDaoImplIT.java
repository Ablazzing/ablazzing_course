//package integration;
//
//import lombok.SneakyThrows;
//import org.example.csv_worker.CsvWorkerUtil;
//import org.example.dao.FoodDao;
//import org.example.dao.FoodDaoImpl;
//import org.example.entity.FoodEntity;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class FoodDaoImplIT {
//    private FoodDao foodDao;
//    private List<String> initialData = Arrays.asList("id,name", "1,Babana", "2,Apple", "3,Meat");
//
//    @SneakyThrows
//    @BeforeEach
//    public void initFoodDao(@Value("${food_test}") String filePath) {
//        this.foodDao = new FoodDaoImpl(filePath);
//        CsvWorkerUtil.writeCsvFile(false, initialData, filePath);
//    }
//
//    @Test
//    public void checkCreate() {
//        FoodEntity foodEntity = FoodEntity.builder().name("Lemon").build();
//        Assertions.assertDoesNotThrow(() -> foodDao.create(foodEntity));
//    }
//
//    @SneakyThrows
//    @Test
//    public void checkDeleteById() {
//        Long inputId = 1l;
//        foodDao.deleteById(inputId);
//        Assertions.assertNull(foodDao.findById(inputId));
//    }
//
//    @SneakyThrows
//    @Test
//    public void checkFindById() {
//        Long inputId = 1l;
//        FoodEntity expectedEntity = new FoodEntity(inputId, "Babana");
//        Assertions.assertEquals(expectedEntity, foodDao.findById(inputId));
//    }
//
//    @SneakyThrows
//    @Test
//    public void checkUpdate() {
//        Long inputId = 1l;
//        FoodEntity inputFood = new FoodEntity(inputId, "Tomato");
//        foodDao.update(inputFood);
//        FoodEntity actualFood = foodDao.findById(inputId);
//        Assertions.assertEquals(inputFood, actualFood);
//    }
//}
