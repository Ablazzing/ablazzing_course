package org.example;

import java.util.Objects;

//@Getter - позволяет сгенерировать геттеры на все поля (пишется над классом)
//@Setter - позволяет сгенерировать сеттеры на все поля (пишется над классом)
//@ToString - переопределяет метод ToString у класса (пишется над классом)
//@EqualsAndHashCode - переопределяет методы equals и hashcode (пишется над классом)
public class Lombok_1_Basic {
    private String name;

    //@Getter
    public String getName() {
        return name;
    }

    //@Setter
    public void setName() {
        this.name = name;
    }

    //@ToString
    @Override
    public String toString() {
        return "LombokBasic{" +
                "name='" + name + '\'' +
                '}';
    }

    //@EqualsAndHashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lombok_1_Basic that = (Lombok_1_Basic) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
