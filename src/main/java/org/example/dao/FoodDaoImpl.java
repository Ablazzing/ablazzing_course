package org.example.dao;

import org.example.csv_worker.CsvWorkerUtil;
import org.example.csv_worker.IllegalFileExtensionException;
import org.example.entity.FoodEntity;
import org.example.mapper.FoodEntityMapper;

import java.io.IOException;
import java.util.List;

public class FoodDaoImpl implements FoodDao{
    private static String filePath = "D:\\Новая папка\\ablazzing_course\\src\\main\\resources\\food.csv";
    private static final String DELIMITER = ",";
    private CsvWorkerUtil  csvWorkerUtil;
    private Long currentId;
    public FoodDaoImpl() {
        this.csvWorkerUtil = new CsvWorkerUtil(filePath);
        this.currentId = currentId;
    }

    private Long initCurrentId() throws IllegalFileExtensionException, IOException {
        List<String> textFromCsvFile = csvWorkerUtil.getTextFromCsvFile(true);
        textFromCsvFile.stream()
                .map(row -> FoodEntityMapper.convertTextToEntity(row, DELIMITER));


    }

    @Override
    public void create(FoodEntity foodEntity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void update(FoodEntity foodEntity) {

    }

    @Override
    public List<FoodEntity> findAll() {
        return null;
    }

    @Override
    public FoodEntity findById(Long id) {
        return null;
    }

    @Override
    public List<FoodEntity> findByName(String name) {
        return null;
    }

    @Override
    public void truncate() {

    }
}
