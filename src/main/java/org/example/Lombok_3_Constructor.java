package org.example;

import lombok.NonNull;

public class Lombok_3_Constructor {
    private String name;
    private int size;

    //@NoArgsConstructor - пустой конструктор
    public Lombok_3_Constructor() {
    }

    //@AllArgsConstructor - конструктор на все аттрибуты
    public Lombok_3_Constructor(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public static class LombokConstructorNested {
        private String name;
        private final int size;
        private @NonNull String address;

        //@RequiredArgsConstructor - конструктор на аттрибуты, которые final и @NonNull
        public LombokConstructorNested(int size, @NonNull String address) {
            this.size = size;
            this.address = address;
        }
    }
}
