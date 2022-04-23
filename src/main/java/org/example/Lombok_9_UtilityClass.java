package org.example;

import lombok.experimental.UtilityClass;

import java.util.Locale;

//@UtilityClass - делает класс final, приватный конструктор, все методы статические
@UtilityClass
public class Lombok_9_UtilityClass {

    public String changeText(String text) {
        return text.toUpperCase(Locale.ROOT);
    }
}