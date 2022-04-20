package org.example;

public class Lombok_2_NonNull {
    //@NonNull проверяет аттрибут на null, если присваевается null, вылетает ошибка (пишется над аттрибутами и аргументами функций)
    //private @NonNull String name
    private String name;

    public Lombok_2_NonNull(String name) {
        if (name == null) {
            throw new NullPointerException("name is marked non-null but is null");
        }
        this.name = name;
    }
}
