package org.example;

import java.util.Locale;

//@UtilityClass - делает класс final, приватный конструктор, все методы статические
public final class Lombok_9_UtilityClass {

    private Lombok_9_UtilityClass() {
    }

    public static String changeText(String text) {
        return text.toUpperCase(Locale.ROOT);
    }
}
