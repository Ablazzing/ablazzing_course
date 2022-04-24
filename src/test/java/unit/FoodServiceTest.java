package unit;

import lombok.SneakyThrows;
import org.example.dao.FoodDao;
import org.example.entity.FoodEntity;
import org.example.service.FoodServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FoodServiceTest {

    @Test
    @SneakyThrows
    public void checkFindById() {
        Long inputId = 1l;

        FoodDao foodDao = Mockito.mock(FoodDao.class);
        Mockito.when(foodDao.findById(inputId)).thenReturn(new FoodEntity(1l, "tomato"));
        FoodServiceImpl foodService = new FoodServiceImpl(foodDao);


        FoodEntity expectedFoodEntity = new FoodEntity(1l, "tomato");

        FoodEntity actualFoodEntity = foodService.findById(inputId);

        Assertions.assertEquals(expectedFoodEntity, actualFoodEntity);
    }
}
