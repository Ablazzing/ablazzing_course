package org.example;

public class SingletonEx {
    private static SingletonEx instance = null;
    String name;

    private SingletonEx(String name) {
        this.name = name;
    }

    public static SingletonEx getInstance(String name) {
        if (instance == null) {
            instance = new SingletonEx(name);
            return instance;
        }
        return instance;
    }

    @Override
    public String toString() {
        return "SingletonEx{" +
                "name='" + name + '\'' +
                '}';
    }
}
