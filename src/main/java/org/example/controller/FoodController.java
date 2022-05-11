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
import java.util.Set;

@RestController
public class FoodController implements Controller {

    FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("food/remove_all_duplicates")
    public BaseResponse removeAllDuplicates() {
        try {
            this.foodService.removeAllDuplicates();
            return createSuccessfulResponse("All duplicates are removed", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @PostMapping("food/clear")
    public BaseResponse clearFoodDatabase() {
        try {
            this.foodService.clearDatabase();
            return createSuccessfulResponse("Database is cleared", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @GetMapping("food/find_all_duplicates")
    public BaseResponse findAllDuplicates() {
        try {
            Set<String> data = this.foodService.findAllDuplicates();
            HashMap<String, Object> map = new HashMap<>();
            map.put("duplicates", data);
            return createSuccessfulResponse("OK", map);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @GetMapping("api/food/{id}")
    public BaseResponse getFoodByService(@PathVariable Long id) throws DatabaseUnavailableException {
        try {
            FoodEntity data = this.foodService.findById(id);;
            HashMap<String, Object> map = new HashMap<>();
            map.put("food_entity", data);
            return createSuccessfulResponse("OK", map);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }

    }

    @GetMapping("api/food")
    public BaseResponse getFood(@RequestParam (required = false) String name) {
        if(name != null && !name.isEmpty()) {
            return getFoodByName(name);
        } else {
            return getAllFood();
        }
    }

    private BaseResponse getAllFood() {
        try {
            List<FoodEntity> entities = this.foodService.findAll();
            Map<String, Object> data = new HashMap<>();
            data.put("foodList", entities);
            return createSuccessfulResponse("OK", data);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    private BaseResponse getFoodByName(String name) {
        try {
            List<FoodEntity> foundFoods = this.foodService.findByName(name);
            Map<String, Object> data = new HashMap<>();
            data.put("foods", foundFoods);
            return createSuccessfulResponse("ОК", data);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @PostMapping("api/food")
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

    @PatchMapping("api/food")
    public BaseResponse updateFood(@RequestBody FoodDto foodDto) {
        try {
            this.foodService.update(foodDto);
            return createSuccessfulResponse("Update is successful", null);
        } catch (DatabaseUnavailableException | IncorrectDtoValueException e) {
            return createFailureResponse(e);
        }
    }

    @DeleteMapping("api/food/{id}")
    public BaseResponse deleteFoodById(@PathVariable Long id) {
        try {
            this.foodService.deleteById(id);
            return createSuccessfulResponse("Delete is successful", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @DeleteMapping("api/food")
    public BaseResponse deleteFoodByName(@RequestParam String name) {
        try {
            this.foodService.deleteByName(name);
            return createSuccessfulResponse("Delete is successful", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }
}



























