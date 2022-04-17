package org.example.csv_worker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CsvWorkerUtil {
    private final String filePath;

    public CsvWorkerUtil(String filePath) {
        this.filePath = filePath;
    }

    public void writeCsvFile(boolean isAppend, List<String> data) throws IllegalFileExtensionException, IOException {
        if (!this.filePath.endsWith(".csv")) {
            throw new IllegalFileExtensionException("Неправильное расширение файла. Ожидается csv");
        }
        try (CsvBufferedWriter bw = new CsvBufferedWriter(new FileWriter(this.filePath, isAppend))) {
            if (isAppend){
                data.forEach(bw::writeWithNewLine);
            }else{
                data.stream()
                        .limit(1)
                        .forEach(bw::write);
                data.stream()
                        .skip(1l)
                        .forEach(bw::writeWithNewLine);
            }
        }
    }

    public List<String> getTextFromCsvFile(boolean isSkipFirstRow) throws IllegalFileExtensionException, IOException {
        if (!this.filePath.endsWith(".csv")) {
            throw new IllegalFileExtensionException("Неправильное расширение файла. Ожидается csv");
        }
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            return br.lines().skip(isSkipFirstRow ? 1 : 0).collect(Collectors.toList());
        }
    }
}
