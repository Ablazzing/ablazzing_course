package org.example.dao;

import org.example.csv_worker.CsvWorkerUtil;
import org.example.csv_worker.IllegalFileExtensionException;
import org.example.entity.FoodEntity;
import org.example.mapper.FoodEntityMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FoodDaoImpl implements FoodDao{
    private static String filePath = "D:\\Новая папка\\ablazzing_course\\src\\main\\resources\\food.csv";
    private static final String DELIMITER = ",";
    private CsvWorkerUtil  csvWorkerUtil;
    private Long currentId;

    public FoodDaoImpl() throws IllegalFileExtensionException, IOException {
        this.csvWorkerUtil = new CsvWorkerUtil(filePath);
        this.currentId = initCurrentId();
    }

    public static void main(String[] args) throws IllegalFileExtensionException, IOException {
        FoodDaoImpl foodDao = new FoodDaoImpl();
        FoodEntity foodEntity = new FoodEntity("potato");
        foodDao.create(foodEntity);
        foodDao.create(foodEntity);
        foodDao.create(foodEntity);
        foodDao.create(foodEntity);
        foodDao.create(foodEntity);
    }
    private Long initCurrentId() throws IllegalFileExtensionException, IOException {
        List<String> textFromCsvFile = csvWorkerUtil.getTextFromCsvFile(true);
        List<Long> idList = textFromCsvFile.stream()
                .map(row -> FoodEntityMapper.convertTextToEntity(row, DELIMITER))
                .map(e -> e.getId())
                .sorted()
                .collect(Collectors.toList());
        if (idList.isEmpty()){
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
        csvWorkerUtil.writeCsvFile(true, foodEntityRows);
        this.currentId++;
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
