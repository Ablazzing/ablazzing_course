package org.example;

import java.util.Objects;

//@Value = @AllArgsConstructor + все поля final + @Getter + @ToString + @EqualsAndHashCode
public final class Lombok_5_Value {
    private String name; // станет final
    private int size; // станет final

    public Lombok_5_Value(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Lombok_5_Value{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lombok_5_Value that = (Lombok_5_Value) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
