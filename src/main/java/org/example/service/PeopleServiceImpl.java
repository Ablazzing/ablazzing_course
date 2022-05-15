package org.example.service;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.dao.PeopleDao;
import org.example.dto.PeopleDto;
import org.example.entity.PeopleEntity;
import org.example.mapper.PeopleDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PeopleServiceImpl implements PeopleService {
    private final PeopleDao peopleDao;
    private final PeopleDtoMapper peopleDtoMapper;

    @Autowired
    public PeopleServiceImpl(PeopleDao peopleDao, PeopleDtoMapper peopleDtoMapper) {
        this.peopleDao = peopleDao;
        this.peopleDtoMapper = peopleDtoMapper;
    }

    @Override
    public void removeDuplicates() throws DatabaseUnavailableException {
        HashSet<String> uniqueNames = new HashSet<>();
        try {
            List<PeopleEntity> entitiesWithoutDuplicates = peopleDao.findAll().stream().filter(e -> {
                String name = e.getName() + e.getAge();
                if (!uniqueNames.contains(name)) {
                    uniqueNames.add(name);
                    return true;
                } else {
                    return false;
                }
            }).collect(Collectors.toList());
            peopleDao.saveList(entitiesWithoutDuplicates);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public Set<String> findDuplicates() throws DatabaseUnavailableException {
        Map<String, Integer> peopleMap = new HashMap<>();
        try {
            List<PeopleEntity> allPeople = peopleDao.findAll();
            allPeople.forEach(e -> {
                String uniqueName = e.getName() + e.getAge();
                peopleMap.put(uniqueName, peopleMap.getOrDefault(uniqueName, 0) + 1);
            });
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
        return peopleMap.entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    @Override
    public PeopleEntity create(PeopleDto peopleDto) throws DatabaseUnavailableException, IncorrectDtoValueException {
        try {
            PeopleEntity peopleEntity = peopleDtoMapper.convertDtoToEntity(peopleDto);
            return peopleDao.create(peopleEntity);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void deleteById(Long id) throws DatabaseUnavailableException {
        try {
            peopleDao.deleteById(id);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void deleteByName(String name) throws DatabaseUnavailableException {
        try {
            peopleDao.deleteByName(name);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void update(PeopleDto peopleDto) throws DatabaseUnavailableException, IncorrectDtoValueException {
        try {
            PeopleEntity peopleEntity = peopleDtoMapper.convertDtoToEntity(peopleDto);
            peopleDao.update(peopleEntity);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public List<PeopleEntity> findAll() throws DatabaseUnavailableException {
        try {
            return peopleDao.findAll();
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public PeopleEntity findById(Long id) throws DatabaseUnavailableException {
        try {
            return peopleDao.findById(id);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public List<PeopleEntity> findByName(String name) throws DatabaseUnavailableException {
        try {
            return peopleDao.findByName(name);
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }

    @Override
    public void clearDatabase() throws DatabaseUnavailableException {
        try {
            peopleDao.truncate();
        } catch (IllegalFileExtensionException | IOException e) {
            throw new DatabaseUnavailableException(e);
        }
    }
}
