package org.example.csv_worker;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvWorkerUtil {
    private static final String FILE_PATH = "C:\\Users\\King Kazma\\IdeaProjects\\Java_project\\src\\main\\resources\\food.csv";

    public static void main(String[] args) throws Exception {
        writeCsvFile(FILE_PATH, true, Arrays.asList("3,Lemon"));
        getTextFromCsvFile(FILE_PATH, true).stream()
                .forEach(System.out::println);
    }

    public static void writeCsvFile(String path, boolean isAppend, List<String> data) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, isAppend))) {
            for (String line : data) {
                bw.newLine();
                bw.write(line);
            }
        }
    }

    public static List<String> getTextFromCsvFile(String path, boolean isSkipFirstRow) throws IOException, IllegalFileExtensionException {
        if (!path.endsWith(".csv")) {
            throw new IllegalFileExtensionException("Неправильное расширение файла. Ожидается csv");
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.lines().skip(isSkipFirstRow ? 1 : 0).collect(Collectors.toList());
        }
    }
}
