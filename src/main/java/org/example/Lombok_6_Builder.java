package org.example;

//@Builder - генерирует класс, который отвечает паттерну строитель
public class Lombok_6_Builder {
    private String name;
    private int size;

    //Как использовать
    public static void main(String[] args) {
        Lombok_6_Builder instance = Lombok_6_Builder.builder()
                .name("Hi")
                .size(5)
                .build();
    }

    private Lombok_6_Builder() {
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public static class Builder {
        private Lombok_6_Builder ourClass;

        public Builder() {
            this.ourClass = new Lombok_6_Builder();
        }

        public Builder name(String name) {
            ourClass.setName(name);
            return this;
        }

        public Builder size(int size) {
            ourClass.setSize(size);
            return this;
        }

        public Lombok_6_Builder build() {
            return ourClass;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
