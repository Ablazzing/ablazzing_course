package org.example;

import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Lombok_7_Cleanup {
    public static void main(String[] args) throws FileNotFoundException {
        withCleanUp();
    }

    //@Cleanup - вызывает close у переменной
    public static void withCleanUp() throws FileNotFoundException {
        @Cleanup BufferedReader in = new BufferedReader(new FileReader("/food.csv"));
        List<String> text = in.lines().collect(Collectors.toList());
        System.out.println(text);
    }

    public static void withoutCleanUp() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("/food.csv"));
        try {
            List<String> text = in.lines().collect(Collectors.toList());
            System.out.println(text);
        } finally {
            in.close();
        }

    }
}
