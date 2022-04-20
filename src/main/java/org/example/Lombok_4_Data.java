package org.example;

import lombok.NonNull;

import java.util.Objects;

//@Data = @RequiredArgsConstructor + @Getter + @Setter + @ToString + @EqualsAndHashCode
public class Lombok_4_Data {
    private String name;
    private final int size;
    private @NonNull String address;

    public Lombok_4_Data(int size, @NonNull String address) {
        this.size = size;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lombok_4_Data that = (Lombok_4_Data) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }

    @Override
    public String toString() {
        return "LombokData{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
