package org.example.service;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.dto.PeopleDto;
import org.example.entity.PeopleEntity;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface PeopleService {
    void removeAllDuplicates() throws DatabaseUnavailableException;

    Set<String> findAllDuplicates() throws DatabaseUnavailableException;

    PeopleEntity create(PeopleDto peopleDto) throws DatabaseUnavailableException, IncorrectDtoValueException;

    void deleteById(Long id) throws DatabaseUnavailableException;

    void deleteByName(String name) throws DatabaseUnavailableException;

    void update(PeopleDto peopleDto) throws DatabaseUnavailableException, IncorrectDtoValueException;

    List<PeopleEntity> findAll() throws DatabaseUnavailableException;

    PeopleEntity findById(Long id) throws DatabaseUnavailableException;

    List<PeopleEntity> findByName(String name) throws DatabaseUnavailableException;

    void clearDatabase() throws DatabaseUnavailableException;
}
