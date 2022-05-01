package org.example.controller;

import org.example.entity.FoodEntity;
import org.example.service.DatabaseUnavailableException;
import org.example.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodController {

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
}



























