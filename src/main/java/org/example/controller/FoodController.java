package org.example.controller;

import com.sun.jdi.event.StepEvent;
import org.example.dto.FoodDto;
import org.example.entity.FoodEntity;
import org.example.service.DatabaseUnavailableException;
import org.example.service.FoodService;
import org.example.service.IncorrectDtoValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FoodController implements Controller {
    private FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("food/{id}")
    public BaseResponse getFoodById(@PathVariable Long id) {
        try {
            FoodEntity foodEntity = foodService.findById(id);
            Map<String, Object> data = new HashMap<>();
            data.put("foodEntity", foodEntity);
            return createSuccessfulResponse(null, data);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @GetMapping("food")
    public BaseResponse getAllFood() {
        try {
            List<FoodEntity> foodEntities = foodService.findAll();
            Map<String, Object> data = new HashMap<>();
            data.put("foodList", foodEntities);
            return createSuccessfulResponse("OK", data);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @PostMapping("food")
    public BaseResponse createFood(@RequestBody FoodDto foodDto) {
        try {
            FoodEntity foodEntity = foodService.create(foodDto);
            String message = String.format("The food %s, created with id - %d", foodEntity.getName(),
                    foodEntity.getId());
            return createSuccessfulResponse(message, null);
        } catch (DatabaseUnavailableException | IncorrectDtoValueException e) {
            return createFailureResponse(e);
        }
    }

    @PatchMapping("food")
    public BaseResponse updateFood(@RequestBody FoodDto foodDto) {
        try {
            this.foodService.update(foodDto);
            return createSuccessfulResponse("Update successful", null);
        } catch (DatabaseUnavailableException | IncorrectDtoValueException e) {
            return createFailureResponse(e);
        }
    }

    @DeleteMapping("food/{id}")
    public BaseResponse deleteFoodByName(@PathVariable Long id) {
        try {
            foodService.deleteById(id);
            return createSuccessfulResponse("Delete is OK", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }
}
