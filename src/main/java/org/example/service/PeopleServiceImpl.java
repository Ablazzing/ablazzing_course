package org.example.service;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.dao.PeopleDao;
import org.example.dto.PeopleDto;
import org.example.entity.PeopleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PeopleServiceImpl implements PeopleService {
    private final PeopleDao peopleDao;

    @Autowired
    public PeopleServiceImpl(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @Override
    public void removeAllDuplicates() throws DatabaseUnavailableException {
        HashSet<String> uniqueNames = new HashSet<>();
        try {
            List<PeopleEntity> entitiesWithoutDuplicates = peopleDao.findAll()
                    .stream()
                    .filter(e -> {
                        String uniqueName = e.getName() + e.getAge();
                        if (!uniqueNames.contains(uniqueName)) {
                            uniqueNames.add(uniqueName);
                            return true;
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
            peopleDao.saveListEntities(entitiesWithoutDuplicates);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }


    }

    @Override
    public Set<String> findAllDuplicates() throws DatabaseUnavailableException {
        Map<String, Integer> peopleMap = new HashMap<>();
        List<PeopleEntity> peopleEntities = null;
        try {
            peopleEntities = peopleDao.findAll();
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
        peopleEntities.forEach(e -> {
            String uniqueName = e.getName() + e.getAge();
            peopleMap.put(uniqueName, peopleMap.getOrDefault(uniqueName, 0) + 1);
        });
        return peopleMap.entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .map(e -> e.getKey())
                .collect(Collectors.toSet());
    }

    @Override
    public PeopleEntity create(PeopleDto peopleDto) throws DatabaseUnavailableException, IncorrectDtoValueException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws DatabaseUnavailableException {

    }

    @Override
    public void deleteByName(String name) throws DatabaseUnavailableException {

    }

    @Override
    public void update(PeopleDto peopleDto) throws DatabaseUnavailableException, IncorrectDtoValueException {

    }

    @Override
    public List<PeopleEntity> findAll() throws DatabaseUnavailableException {
        return null;
    }

    @Override
    public PeopleEntity findById(Long id) throws DatabaseUnavailableException {
        return null;
    }

    @Override
    public List<PeopleEntity> findByName(String name) throws DatabaseUnavailableException {
        return null;
    }

    @Override
    public void clearDatabase() throws DatabaseUnavailableException {

    }
}
