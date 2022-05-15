package org.example.mapper;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.dao.FoodService;
import org.example.entity.FoodEntity;
import org.example.entity.PeopleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PeopleEntityMapper {
    private FoodService foodDao;

    @Autowired
    public PeopleEntityMapper(FoodService foodDao) {
        this.foodDao = foodDao;
    }

    public PeopleEntity convertTextToEntity(String row, String delimiter) throws CastEntityExceptionError {
        String[] array = row.split(delimiter);
        try {
            FoodEntity food = foodDao.findById(Long.valueOf(array[2]));
            if (food == null) {
                throw new CastEntityExceptionError("Cannot create PeopleEntity: FoodEntity with id "
                        + array[2] + " is not exist");
            }
            return new PeopleEntity(Long.valueOf(array[0]), array[1], Integer.valueOf(array[2]), food);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new CastEntityExceptionError(e);
        }
    }

    public String convertEntityToText(PeopleEntity peopleEntity, String delimiter) {
        List<? extends Serializable> list = Arrays.asList(peopleEntity.getId(),
                peopleEntity.getName(),
                peopleEntity.getFood().getId());
        return list.stream().map(Object::toString)
                .collect(Collectors.joining(delimiter));
    }
}
