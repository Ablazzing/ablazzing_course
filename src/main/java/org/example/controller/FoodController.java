package org.example.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
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

    FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService){
        this.foodService = foodService;
    }

    @GetMapping("test")
    public String test(){
        return "welcome";
    }

    @GetMapping("food/{id}")
    public FoodEntity getFoodByService(@PathVariable Long id) throws DatabaseUnavailableException {
        return this.foodService.findById(id);
    }

    @GetMapping("food")
    public BaseResponse getAllFood() {
        try {
            List<FoodEntity> entities =  this.foodService.findAll();
            Map<String, Object> data = new HashMap<>();
            data.put("foodList", entities);
            return createSuccessfulResponse("OK", data);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @PostMapping("food")
    public BaseResponse createFood(@RequestBody FoodDto foodDto) {
        try {
            FoodEntity createdFood = this.foodService.create(foodDto);
            String message = String.format("The food %s, created with id - %d", createdFood.getName(),
                    createdFood.getId());
            return createSuccessfulResponse(message, null);
        } catch (DatabaseUnavailableException | IncorrectDtoValueException e) {
            return createFailureResponse(e);
        }
    }

    @PatchMapping("food")
    public BaseResponse updateFood(@RequestBody FoodDto foodDto) {
        try {
            this.foodService.update(foodDto);
            return createSuccessfulResponse("Update is successful", null);
        } catch (DatabaseUnavailableException | IncorrectDtoValueException e) {
            return createFailureResponse(e);
        }
    }

    @DeleteMapping("food/{id}")
    public BaseResponse deleteFood(@PathVariable Long id) {
        try {
            this.foodService.deleteById(id);
            return createSuccessfulResponse("Delete is successful", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }
}



























