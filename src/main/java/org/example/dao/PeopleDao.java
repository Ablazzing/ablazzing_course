package org.example.dao;

import org.example.csv_worker.IllegalFileExtensionException;
import org.example.entity.PeopleEntity;

import java.io.IOException;
import java.util.List;

public interface PeopleDao {
    PeopleEntity create(PeopleEntity peopleEntity) throws IllegalFileExtensionException, IOException;
    void deleteById(Long id) throws IllegalFileExtensionException, IOException;
    void deleteByName(String name) throws IllegalFileExtensionException, IOException;
    void update(PeopleEntity peopleEntity) throws IllegalFileExtensionException, IOException;
    List<PeopleEntity> findAll() throws IllegalFileExtensionException, IOException;
    PeopleEntity findById(Long id) throws IllegalFileExtensionException, IOException;
    List<PeopleEntity> findByName(String name) throws IllegalFileExtensionException, IOException;
    void truncate() throws IllegalFileExtensionException, IOException;
    void saveListEntities(List<PeopleEntity> foodEntities) throws IllegalFileExtensionException, IOException;
}
