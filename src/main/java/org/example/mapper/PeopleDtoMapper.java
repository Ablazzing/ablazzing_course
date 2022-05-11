package org.example.mapper;

import org.example.dao.FoodDao;
import org.example.dto.PeopleDto;
import org.example.entity.FoodEntity;
import org.example.entity.PeopleEntity;
import org.example.service.DatabaseUnavailableException;
import org.example.service.FoodService;
import org.example.service.IncorrectDtoValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeopleDtoMapper {

    private final FoodService foodService;

    @Autowired
    public PeopleDtoMapper(FoodService foodService) {
        this.foodService = foodService;
    }

    public PeopleEntity convertDtoToEntity(PeopleDto peopleDto) throws DatabaseUnavailableException,
            IncorrectDtoValueException {
        List<FoodEntity> foundEntities = foodService.findByName(peopleDto.getFoodName());
        if(foundEntities.size() == 0) {
            throw new IncorrectDtoValueException("Food with name " + peopleDto.getFoodName() + " is not exists");
        }
        FoodEntity foodEntity = foundEntities.get(0);
        return PeopleEntity.builder()
                .id(peopleDto.getHumanId() == null ? 0L : peopleDto.getHumanId())
                .age(peopleDto.getAge())
                .name(peopleDto.getName())
                .food(foodEntity)
                .build();
    }
}
