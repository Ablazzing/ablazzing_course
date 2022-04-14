package org.example.csv_worker;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvWorkerUtil {
	 private static final String FILEPATH = "C:\\Users\\kia\\IdeaProjects\\JavaProjectTutor\\src\\main\\resources\\food.csv";

	 public static void main (String[] args) throws Exception {
			writeCsvFile (FILEPATH, true, Arrays.asList ("3,tomato"));

			getTextFromCsvFile (FILEPATH, true)
							.forEach (System.out::println);
	 }

	 public static void writeCsvFile (String path, boolean isAppend, List<String> data) throws IOException {
			try (BufferedWriter bw = new BufferedWriter (new FileWriter (path, isAppend))) {
				 for (String s : data) {
						bw.newLine ();
						bw.write (s);
				 }
			}
	 }

	 public static List<String> getTextFromCsvFile (String file, boolean isSkipFirstRow) throws IOException, IllegalFileExtensionException {
			if (!file.endsWith (".csv")) {
				 throw new IllegalFileExtensionException ("Неправльное расширение файла. Ожидается CSV");
			}
			try (BufferedReader reader = new BufferedReader (new FileReader (file))) {
				 return reader.lines ().skip (isSkipFirstRow ? 1 : 0)
								 .collect (Collectors.toList ());
			}
	 }
}
