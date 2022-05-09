package org.example.dao;

import org.example.csv_worker.CsvWorkerUtil;
import org.example.csv_worker.IllegalFileExtensionException;
import org.example.entity.PeopleEntity;
import org.example.mapper.PeopleEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PeopleDaoImpl implements PeopleDao {
    private String filePath;
    private static final String DELIMITER = ",";
    private static final String HEADER_FILE = "id,name,age,food";
    private Long currentId;
    private PeopleEntityMapper peopleEntityMapper;

    @Autowired
    public PeopleDaoImpl(PeopleEntityMapper peopleEntityMapper, @Value("${people_file}") String filePath)
            throws IllegalFileExtensionException, IOException {
        this.filePath = filePath;
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
        if (idList.isEmpty()) {
            return 1L;
        }
        return idList.get(idList.size() - 1) + 1;
    }

    @Override
    public PeopleEntity create(PeopleEntity peopleEntity) throws IllegalFileExtensionException, IOException {
        peopleEntity.setId(this.currentId);
        List<String> peopleEntityRows = Arrays.asList(peopleEntity).stream()
                .map(e -> peopleEntityMapper.convertEntityToText(e, DELIMITER))
                .collect(Collectors.toList());
        CsvWorkerUtil.writeCsvFile(true, peopleEntityRows, filePath);
        this.currentId++;
        return peopleEntity;
    }

    @Override
    public void deleteById(Long id) throws IllegalFileExtensionException, IOException {
        List<String> peopleEntityRows = CsvWorkerUtil.getTextFromCsvFile(true, filePath)
                .stream()
                .map(e -> peopleEntityMapper.convertTextToEntity(e, DELIMITER))
                .filter(e -> !e.getId().equals(id))
                .map(e -> peopleEntityMapper.convertEntityToText(e, DELIMITER))
                .collect(Collectors.toList());
        peopleEntityRows.add(0, HEADER_FILE);
        CsvWorkerUtil.writeCsvFile(false, peopleEntityRows, filePath);
    }

    @Override
    public void deleteByName(String name) throws IllegalFileExtensionException, IOException {
        List<String> peopleEntityRows = CsvWorkerUtil.getTextFromCsvFile(true, filePath)
                .stream()
                .map(e -> peopleEntityMapper.convertTextToEntity(e, DELIMITER))
                .filter(e -> !e.getName().equals(name))
                .map(e -> peopleEntityMapper.convertEntityToText(e, DELIMITER))
                .collect(Collectors.toList());
        peopleEntityRows.add(0, HEADER_FILE);
        CsvWorkerUtil.writeCsvFile(false, peopleEntityRows, filePath);
    }

    @Override
    public void update(PeopleEntity peopleEntity) throws IllegalFileExtensionException, IOException {
        List<String> peopleEntityRows = CsvWorkerUtil.getTextFromCsvFile(true, filePath)
                .stream()
                .map(e -> peopleEntityMapper.convertTextToEntity(e, DELIMITER))
                .map(e -> e.getId().equals(peopleEntity.getId()) ? peopleEntity : e)
                .map(e -> peopleEntityMapper.convertEntityToText(e, DELIMITER))
                .collect(Collectors.toList());
        peopleEntityRows.add(0, HEADER_FILE);
        CsvWorkerUtil.writeCsvFile(false, peopleEntityRows, filePath);
    }

    @Override
    public List<PeopleEntity> findAll() throws IllegalFileExtensionException, IOException {
        return CsvWorkerUtil.getTextFromCsvFile(true, filePath)
                .stream()
                .map(e -> peopleEntityMapper.convertTextToEntity(e, DELIMITER))
                .collect(Collectors.toList());
    }

    @Override
    public PeopleEntity findById(Long id) throws IllegalFileExtensionException, IOException {
        return CsvWorkerUtil.getTextFromCsvFile(true, filePath)
                .stream()
                .map(e -> peopleEntityMapper.convertTextToEntity(e, DELIMITER))
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<PeopleEntity> findByName(String name) throws IllegalFileExtensionException, IOException {
        return CsvWorkerUtil.getTextFromCsvFile(true, filePath)
                .stream()
                .map(e -> peopleEntityMapper.convertTextToEntity(e, DELIMITER))
                .filter(e -> e.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void truncate() throws IllegalFileExtensionException, IOException {
        CsvWorkerUtil.writeCsvFile(false, Arrays.asList(HEADER_FILE), filePath);
    }

    @Override
    public void saveList(List<PeopleEntity> peopleList) throws IllegalFileExtensionException, IOException {
        List<String> rows = new ArrayList<>(List.of(HEADER_FILE));
        peopleList.stream()
                .map(e -> peopleEntityMapper.convertEntityToText(e, DELIMITER))
                .forEach(rows::add);
        CsvWorkerUtil.writeCsvFile(false, rows, filePath);
    }
}
