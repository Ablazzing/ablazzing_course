package org.example.dao;

import org.example.csv_worker.CsvWorkerUtil;
import org.example.csv_worker.IllegalFileExtensionException;
import org.example.entity.FoodEntity;
import org.example.mapper.FoodEntityMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FoodDaoImpl implements FoodDao {
    private static String filePath = "C:\\Users\\Voodo\\Desktop\\GitLocalRepository\\ablazzing_course\\src\\main\\resources\\food.csv";
    private static final String DELIMITER = ",";
    private static final String HEADER_FILE = "id,name";
    private CsvWorkerUtil csvWorkerUtil;
    private Long currentId;

    public FoodDaoImpl() throws IllegalFileExtensionException, IOException {
        this.csvWorkerUtil = new CsvWorkerUtil(filePath);
        this.currentId = initCurrentId();
    }

    public static void main(String[] args) throws IllegalFileExtensionException, IOException {
        FoodDaoImpl foodDao = new FoodDaoImpl();
        FoodEntity food = new FoodEntity(1L, "banana");
//        foodDao.create(food);
//        foodDao.update(food);
//        foodDao.deleteById();
//        foodDao.deleteByName();
//        foodDao.findAll();
//        foodDao.findById(2l);
//        foodDao.initCurrentId();


    }

    private Long initCurrentId() throws IllegalFileExtensionException, IOException {
        List<String> textFromCsvFile = csvWorkerUtil.getTextFromCsvFile(true);
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
        csvWorkerUtil.writeCsvFile(true, foodEntityRows);
        this.currentId++;
        System.out.println("Продукт создан");
    }

    @Override
    public void deleteById(Long id) throws IllegalFileExtensionException, IOException {
        List<String> foodEntityRows = csvWorkerUtil.getTextFromCsvFile(true)
                .stream()
                .map(e -> FoodEntityMapper.convertTextToEntity(e, DELIMITER))
                .filter(e -> e.getId() != id)
                .map(e -> FoodEntityMapper.convertEntityToText(e, DELIMITER))
                .collect(Collectors.toList());
        foodEntityRows.add(0, HEADER_FILE);

        csvWorkerUtil.writeCsvFile(false, foodEntityRows);
        System.out.println("Продукт удалён по ID");
    }

    @Override
    public void deleteByName(String name) throws IllegalFileExtensionException, IOException {
        List<String> foodEntityRows = csvWorkerUtil.getTextFromCsvFile(true)
                .stream()
                .map(e -> FoodEntityMapper.convertTextToEntity(e, DELIMITER))
                .filter(e -> !e.getName().equals(name))
                .map(e -> FoodEntityMapper.convertEntityToText(e, DELIMITER))
                .collect(Collectors.toList());
        foodEntityRows.add(0, HEADER_FILE);

        csvWorkerUtil.writeCsvFile(false, foodEntityRows);
        System.out.println("Продукт удалён по имени");
    }

    @Override
    public void update(FoodEntity foodEntity) throws IllegalFileExtensionException, IOException {
        List<String> foodEntityRows = csvWorkerUtil.getTextFromCsvFile(true)
                .stream()
                .map(e -> FoodEntityMapper.convertTextToEntity(e, DELIMITER))
                .map(e -> e.getId().equals(foodEntity.getId()) ? foodEntity : e)
                .map(e -> FoodEntityMapper.convertEntityToText(e, DELIMITER))
                .collect(Collectors.toList());

        foodEntityRows.add(0, HEADER_FILE);
        csvWorkerUtil.writeCsvFile(false, foodEntityRows);
        System.out.println("Продукт обновлён");
    }

    @Override
    public List<FoodEntity> findAll() throws IllegalFileExtensionException, IOException {
        return csvWorkerUtil.getTextFromCsvFile(true)
                .stream()
                .map(e -> FoodEntityMapper.convertTextToEntity(e, DELIMITER))
                .collect(Collectors.toList());
    }

    @Override
    public FoodEntity findById(Long id) throws IllegalFileExtensionException, IOException {
        System.out.println("Поиск по ID завершен: ");
        return csvWorkerUtil.getTextFromCsvFile(true)
                .stream()
                .map(e -> FoodEntityMapper.convertTextToEntity(e, DELIMITER))
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public List<FoodEntity> findByName(String name) throws IllegalFileExtensionException, IOException {
        System.out.println("Поиск по имени завершен: ");
        return csvWorkerUtil.getTextFromCsvFile(true)
                .stream()
                .map(e -> FoodEntityMapper.convertTextToEntity(e, DELIMITER))
                .filter(e -> e.getName().equals(name))
                .collect(Collectors.toList());

    }

    @Override
    public String truncate() throws IllegalFileExtensionException, IOException {
        csvWorkerUtil.writeCsvFile(false, Arrays.asList(HEADER_FILE));
        return "Список продуктов очищен!";
    }
}
