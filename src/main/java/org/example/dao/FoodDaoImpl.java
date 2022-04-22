package org.example.dao;

import lombok.SneakyThrows;
import org.example.csv_worker.CsvWorkerUtil;
import org.example.csv_worker.IllegalFileExtensionException;
import org.example.entity.FoodEntity;
import org.example.mapper.FoodEntityMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FoodDaoImpl implements FoodDao {
    private static String filePath = "C:\\Users\\kia\\IdeaProjects\\JavaProjectTutor\\src\\main\\resources\\food.csv";
    private static final String DELIMITER = ",";
    private static final String HEADER_FILE = "id,name";
    private Long currentId;

    public FoodDaoImpl() throws IllegalFileExtensionException, IOException {
        this.currentId = initCurrentId();
    }

    @SneakyThrows
    public static void main(String[] args) throws IllegalFileExtensionException, IOException {

    }

    private Long initCurrentId() throws IllegalFileExtensionException, IOException {
        List<String> textFromCsvFile = CsvWorkerUtil.getTextFromCsvFile(true, filePath);
        List<Long> idList = textFromCsvFile.stream()
                .map(row -> FoodEntityMapper.convertTextToEntity(row, DELIMITER))
                .map(e -> e.getId())
                .sorted()
                .collect(Collectors.toList());
        if (idList.isEmpty()) {
            return 1L;
        }
        return idList.get(idList.size() - 1) + 1;
    }

    @Override
    public void create(FoodEntity foodEntity) throws IllegalFileExtensionException, IOException {
        foodEntity.setId(this.currentId);
        List<String> foodEntityRows = Arrays.asList(foodEntity).stream()
                .map(e -> FoodEntityMapper.convertEntityToText(e, DELIMITER))
                .collect(Collectors.toList());
        CsvWorkerUtil.writeCsvFile(true, foodEntityRows, filePath);
        this.currentId++;
    }

    @Override
    public void deleteById(Long id) throws IllegalFileExtensionException, IOException {
        List<String> foodEntityRows = CsvWorkerUtil.getTextFromCsvFile(true, filePath)
                .stream()
                .map(e -> FoodEntityMapper.convertTextToEntity(e, DELIMITER))
                .filter(e -> !e.getId().equals(id))
                .map(e -> FoodEntityMapper.convertEntityToText(e, DELIMITER))
                .collect(Collectors.toList());
        foodEntityRows.add(0, HEADER_FILE);
        CsvWorkerUtil.writeCsvFile(false, foodEntityRows, filePath);
    }

    @Override
    public void deleteByName(String name) throws IllegalFileExtensionException, IOException {
        List<String> foodEntityRows = CsvWorkerUtil.getTextFromCsvFile(true, filePath)
                .stream()
                .map(e -> FoodEntityMapper.convertTextToEntity(e, DELIMITER))
                .filter(e -> !e.getName().equals(name))
                .map(e -> FoodEntityMapper.convertEntityToText(e, DELIMITER))
                .collect(Collectors.toList());
        foodEntityRows.add(0, HEADER_FILE);
        CsvWorkerUtil.writeCsvFile(false, foodEntityRows, filePath);
    }

    @Override
    public void update(FoodEntity foodEntity) throws IllegalFileExtensionException, IOException {
        List<String> foodEntityRows = CsvWorkerUtil.getTextFromCsvFile(true, filePath)
                .stream()
                .map(e -> FoodEntityMapper.convertTextToEntity(e, DELIMITER))
                .map(e -> e.getId().equals(foodEntity.getId()) ? foodEntity : e)
                .map(e -> FoodEntityMapper.convertEntityToText(e, DELIMITER))
                .collect(Collectors.toList());
        foodEntityRows.add(0, HEADER_FILE);
        CsvWorkerUtil.writeCsvFile(false, foodEntityRows, filePath);
    }

    @Override
    public List<FoodEntity> findAll() throws IllegalFileExtensionException, IOException {
        return CsvWorkerUtil.getTextFromCsvFile(true, filePath)
                .stream()
                .map(e -> FoodEntityMapper.convertTextToEntity(e, DELIMITER))
                .collect(Collectors.toList());
    }

    @Override
    public FoodEntity findById(Long id) throws IllegalFileExtensionException, IOException {
        return CsvWorkerUtil.getTextFromCsvFile(true, filePath)
                .stream()
                .map(e -> FoodEntityMapper.convertTextToEntity(e, DELIMITER))
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public List<FoodEntity> findByName(String name) throws IllegalFileExtensionException, IOException {
        return CsvWorkerUtil.getTextFromCsvFile(true, filePath)
                .stream()
                .map(e -> FoodEntityMapper.convertTextToEntity(e, DELIMITER))
                .filter(e -> e.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void truncate() throws IllegalFileExtensionException, IOException {
        CsvWorkerUtil.writeCsvFile(false, Arrays.asList(HEADER_FILE), filePath);
    }
}
