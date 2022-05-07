package org.example.mapper;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.dao.FoodDao;
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
    FoodDao foodDao;

    @Autowired
    public PeopleEntityMapper(FoodDao foodDao) {
        this.foodDao = foodDao;
    }


    public PeopleEntity convertTextToEntity(String row, String delimiter) throws CastEntityException {
        String[] array = row.split(delimiter);

        try {
            FoodEntity foodEntity = foodDao.findById(Long.valueOf(array[3]));
            if (foodEntity == null) {
                throw new CastEntityException("Can not create PeopleEntity: FoodEntity with id "
                        + array[3]
                        + " is not found");
            }
            return new PeopleEntity(Long.valueOf(array[0]),
                    array[1],
                    Integer.valueOf(array[2]),
                    foodEntity);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new CastEntityException(e);
        }
    }

    public String convertEntityToText(PeopleEntity peopleEntity, String delimiter) {
        List<? extends Serializable> list = Arrays.asList(peopleEntity.getId(),
                peopleEntity.getName(),
                peopleEntity.getAge(),
                peopleEntity.getFood().getId());
        return list.stream().map(e -> e.toString()).collect(Collectors.joining(delimiter));
    }
}
