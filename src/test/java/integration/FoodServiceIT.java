//package integration;
//
//import lombok.SneakyThrows;
//import org.example.dao.FoodDao;
//import org.example.dao.FoodDaoImpl;
//import org.example.entity.FoodEntity;
//import org.example.service.FoodServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
//public class FoodServiceIT {
//
//    @SneakyThrows
//    @Test
//    @Autowired
//    public void checkFindById(@Value("${food_test}") String filePath) {
//        Long inputId = 1L;
//        FoodDao foodDao = new FoodDaoImpl(filePath);
//        FoodServiceImpl foodService = new FoodServiceImpl(foodDao);
//
//        FoodEntity expectedFood = new FoodEntity(1L, "Babana");
//
//        FoodEntity actual = foodService.findById(1L);
//
//        Assertions.assertEquals(expectedFood, actual);
//    }
//
//    @Test
//    public void checkCreate() {
//
//    }
//}
