package org.example.dao;

import org.example.csv_worker.CsvWorkerUtil;
import org.example.csv_worker.IllegalFileExtensionException;
import org.example.entity.PeopleEntity;
import org.example.mapper.FoodEntityMapper;
import org.example.mapper.PeopleEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PeopleDaoImpl implements PeopleDao {
    private static String filePath = "C:\\Java_Projects\\ablazzing_course\\src\\main\\resources\\people.csv";
    private static final String DELIMITER = ",";
    private static final String HEADER_FILE = "id,name,age,food";
    private Long currentId;
    private PeopleEntityMapper peopleEntityMapper;

    @Autowired
    public PeopleDaoImpl(PeopleEntityMapper peopleEntityMapper) throws IllegalFileExtensionException,
            IOException {
        this.peopleEntityMapper = peopleEntityMapper;
        this.currentId = initCurrentId();
    }

    private Long initCurrentId() throws IllegalFileExtensionException, IOException {
        List<String> textFromCsvFile = CsvWorkerUtil.getTextFromCsvFile(true, filePath);
        List<Long> idList = textFromCsvFile.stream()
                .map(row -> peopleEntityMapper.convertTextToEntity(row, DELIMITER))
                .map(e -> e.getId())
                .sorted()
                .collect(Collectors.toList());
        if (idList.isEmpty()){
            return 1L;
        }
        return idList.get(idList.size() - 1) + 1;
    }


    @Override
    public PeopleEntity create(PeopleEntity peopleEntity) throws IllegalFileExtensionException, IOException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws IllegalFileExtensionException, IOException {

    }

    @Override
    public void deleteByName(String name) throws IllegalFileExtensionException, IOException {

    }

    @Override
    public void update(PeopleEntity peopleEntity) throws IllegalFileExtensionException, IOException {

    }

    @Override
    public List<PeopleEntity> findAll() throws IllegalFileExtensionException, IOException {
        return null;
    }

    @Override
    public PeopleEntity findById(Long id) throws IllegalFileExtensionException, IOException {
        return null;
    }

    @Override
    public List<PeopleEntity> findByName(String name) throws IllegalFileExtensionException, IOException {
        return null;
    }

    @Override
    public void truncate() throws IllegalFileExtensionException, IOException {

    }

    @Override
    public void saveListEntities(List<PeopleEntity> foodEntities) throws IllegalFileExtensionException, IOException {

    }
}
