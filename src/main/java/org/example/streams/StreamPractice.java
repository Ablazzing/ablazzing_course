package org.example.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Практика по стримам
 */
public class StreamPractice {
    public static void main(String[] args) {
        //Базовая коллекция
        List<Integer> integers = Arrays.asList(1, 5, 3, 4, 2, 2);

        //Промежуточные операции - это такие операции, которые не закрывают стрим,
        // т.е позволяют выполнять последующие команды

        //map - Преобразование данных (возможно изменения типа например из int в String)
        Stream<Integer> mapResult = integers.stream()
                .map(e -> e + 1);

        //filter - Фильтрация данных: если элемент удовлятворяет условию, то он остается в стриме
        Stream<Integer> filterResult = mapResult.filter(e -> e > 2);

        //distinct - Удаление дубликатов
        Stream<Integer> distinctResult = filterResult.distinct();

        //skip - пропуск первых N элементов
        Stream<Integer> skipResult = distinctResult.skip(1);

        //limit - выбор первых N элементов
        Stream<Integer> limitResult = skipResult.limit(5);

        //peek - Преобразование внутреннего состояния элементов (часто используется для дебага)
        List<List<Integer>> lists = Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3, 4));
        Stream<List<Integer>> peekResult = lists.stream().peek(e -> e.add(1));

//        System.out.println(lists);
        //flatMap - Устранение вложенности элементов (для этого нужно внутренние листы преобразовать в стримы)
        List<Integer> flatMapResult = lists.stream().flatMap(e -> e.stream()).collect(Collectors.toList());
//        System.out.println(flatMapResult);

        //Терминальне операции - операции которые закрывают стрим (т.е. последующее обращение к стриму невозможно)
        Stream<Integer> stream = integers.stream();
        //forEach - Исполнение действия над каждым элементом
        stream.forEach(e -> System.out.print(e + " "));
        //Вызов кода ниже, закончится ошибкой, т.к. forEach закрыл стрим
        //stream.forEach(System.out::println);

        //anyMatch - Хотя бы один элемент отвечает условию. Возвращает boolean
        boolean anyMatchResult = integers.stream().anyMatch(e -> e == 3);

        //allMatch - Все элементы отвечают условию. Возвращает boolean
        boolean allMatchResult = integers.stream().allMatch(e -> e == 3);

        //findFirst - выбор первого элемента
        Optional<Integer> first = integers.stream().findFirst();

        //reduce - сложение результатов в одно значение
        Optional<Integer> reduce = stream.reduce((e, k) -> e + k);

        //count - количество элементов стрима
        long count = integers.stream().count();

        //collect - Преобразования стрима в какой то результат (List, Map, среднее значение и тд).
        Set<Integer> integerSet = integers.stream().collect(Collectors.toSet());

        //sorted - Сортировка
        //Stream<Integer> sortedResult = skipResult.sorted();
    }
}
