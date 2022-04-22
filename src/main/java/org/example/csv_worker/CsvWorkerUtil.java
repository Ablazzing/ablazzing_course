package org.example.csv_worker;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CsvWorkerUtil {

    public void writeCsvFile(boolean isAppend, List<String> data, String filePath)
            throws IllegalFileExtensionException, IOException {
        if (!filePath.endsWith(".csv")) {
            throw new IllegalFileExtensionException("Неправильное расширение файла. Ожидается csv");
        }
        try (CsvBufferedWriter bw = new CsvBufferedWriter(new FileWriter(filePath, isAppend))) {
            if (isAppend) {
                data.forEach(bw::writeWithNewLine);
            } else {
                data.stream()
                        .limit(1)
                        .forEach(bw::write);
                data.stream()
                        .skip(1l)
                        .forEach(bw::writeWithNewLine);
            }
        }
    }

    public List<String> getTextFromCsvFile(boolean isSkipFirstRow, String filePath)
            throws IllegalFileExtensionException, IOException {
        if (!filePath.endsWith(".csv")) {
            throw new IllegalFileExtensionException("Неправильное расширение файла. Ожидается csv");
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.lines().skip(isSkipFirstRow ? 1 : 0).collect(Collectors.toList());
        }
    }
}
