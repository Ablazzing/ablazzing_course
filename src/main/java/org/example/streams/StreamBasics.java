package org.example.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Теория по стримам
 */
public class StreamBasics {
    public static void main(String[] args) {
        //Базовая коллекция
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //Stream - это преобразование данных в поток и совершение операций над каждым элементом потока.
        //Рассмотрим простейший пример использования стрима
        //Как было раньше
        for (Integer number : integers) {
            System.out.println(number);
        }

        //С помощью Stream
        integers.stream().forEach(e -> System.out.println(e));
        //Со ссылкой на метод
        integers.forEach(System.out::println);

        //Функциональный интерфейс - это интерфейс, который содержит в себе только один абстрактный метод
        //Лямбда выражение - конкретная реализация функционального интерфейса через специальный синтаксис
        //Если тело метода помещается в одну строку, тогда используется следующий синтаксис
        MyFunctionalInterface lambda = e -> e * 2;
        //Если тело метода содержит много строк
        MyFunctionalInterface lambda2 = e -> {
            System.out.println("hi hi");
            return e * 2;
        };

        //Рассмотрим пример с использованием функциональных интерфейсов
        ArrayList<Integer> result = new ArrayList<>();
        MyFunctionalInterface myInterfaceImpl = new MyFunctionalInterface() {
            @Override
            public Integer myMap(Integer e) {
                return e * 2;
            }
        };

        for (Integer number : integers) {
            result.add(myInterfaceImpl.myMap(number));
        }

        //После появления функциональных интерфейсов и стримов
        List<Integer> result2 = integers.stream()
                .map(number -> number * 2)
                .collect(Collectors.toList());
    }
}
