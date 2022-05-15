package unit;

import lombok.SneakyThrows;
import org.example.dao.FoodService;
import org.example.entity.FoodEntity;
import org.example.service.FoodServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FoodServiceUT {

    @SneakyThrows
    @Test
    public void checkFindById() {
        Long inputId = 1L;

        FoodService foodDao = Mockito.mock(FoodService.class);
        Mockito.when(foodDao.findById(inputId)).thenReturn(new FoodEntity(1L, "Babana"));
        FoodServiceImpl foodService = new FoodServiceImpl(foodDao);

        FoodEntity expectedFood = new FoodEntity(1L, "Babana");

        FoodEntity actualFood = foodService.findById(inputId);

        Assertions.assertEquals(expectedFood, actualFood);
    }
}
