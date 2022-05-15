package org.example.mapper;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.dao.FoodService;
import org.example.dto.PeopleDto;
import org.example.entity.FoodEntity;
import org.example.entity.PeopleEntity;
import org.example.service.IncorrectDtoValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class PeopleDtoMapper {
    private final FoodService service;

    @Autowired
    public PeopleDtoMapper(FoodService service) {
        this.service = service;
    }

    public PeopleEntity convertDtoToEntity(PeopleDto peopleDto) throws IncorrectDtoValueException,
            IllegalFileExtensionException, IOException {
        List<FoodEntity> foodEntities = service.findByName(peopleDto.getFoodName());
        if (foodEntities.isEmpty()) {
            throw new IncorrectDtoValueException("Food with name: " + peopleDto.getFoodName() + " is not exist");
        }
        FoodEntity food = foodEntities.get(0);
        return PeopleEntity.builder()
                .age(peopleDto.getAge())
                .name(peopleDto.getName())
                .id(peopleDto.getHumanId() == null ? 0L : peopleDto.getHumanId())
                .food(food)
                .build();
    }
}
