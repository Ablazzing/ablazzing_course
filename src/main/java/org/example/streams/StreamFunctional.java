package org.example.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Рассмотрим виды функциональных интерфейсов
 */
public class StreamFunctional {
    public static void main(String[] args) {
        //Базовая коллекция
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //Виды функциональных интерфесов
        //Function - Принимает значение, возвращает значение (может другого типа)
        //Пример функции по старинке
        List<String> resultString = new ArrayList<>();
        StringFunction stringFunctionImpl = new StringFunction() {

            @Override
            public String convertValue(Integer e) {
                return e + "a";
            }
        };

        for (Integer number : integers) {
            resultString.add(stringFunctionImpl.convertValue(number));
        }

        //Тоже самое, через стримы
        //Операция map - ожидает на вход функцию и вернет всегда такое же количество элементов, что и было
        StringFunction stringFunctionLambda = e -> e + "a";
        List<String> strings = integers.stream()
                .map(stringFunctionLambda::convertValue)
                .collect(Collectors.toList());

        //Следующий вид
        //Consumer(потребитель) - принимает значение, и ничего не возвращает
        //Пример потребителя по старинке
        MyConsumer myConsumer = new MyConsumer() {
            @Override
            public void accept(Integer e) {
                System.out.println("Потребил число " + e);
                System.out.println("Очень вкусно!");
            }
        };

        for (Integer number : integers) {
            myConsumer.accept(number);
        }

        //Тоже самое, через стримы
        //Операция forEach - ожидает на вход аргумент, и ничего не вернет в конце. Эта операция закроет стрим.
        MyConsumer myConsumerLambda = e -> {
            System.out.println("Потребил число " + e);
            System.out.println("Очень вкусно!");
        };
        integers.stream().forEach(myConsumerLambda::accept);
        System.out.println("_____________________________________________");

        System.out.println("Supplier");
        //Следующий вид
        //Supplier(Поставщик) - не ожидает на вход аргумент, возвращает значение.
        //Задача: Сгенерировать 100 чисел, от 0 до 99
        //Пример по старинке:

        List<Integer> resultIntegers = new ArrayList<Integer>();

        for (int i = 0; i < 100; i++) {
            resultIntegers.add(i);
        }

        //Тоже самое, через стримы
        //Стримы предполагают, что ваша переменная не будет перезаписываться во время исполнения стрима
        //Поэтому для изменяемых данных используются ссылочные типы данных, и изменения делаются внутри них
        AtomicInteger atomicInteger = new AtomicInteger();
        MySupplier mySupplier = () -> atomicInteger.incrementAndGet();
        List<Integer> supplierResult = Stream.generate(mySupplier::get)
                .limit(100)
                .collect(Collectors.toList());
        System.out.println(supplierResult);
        System.out.println("_____________________________________________");

        //Следующий вид
        //Predicate - принимает аргумент(ы), возвращает значения типа boolean
        //Задача: Если есть хотя бы одно число в списке, которое делится без остатка на 3, то вернуть true, иначе false
        //Пример по старинке:
        boolean hasZeroRemainderOfDivision = false;
        for (Integer number : integers) {
            if (number % 3 == 0) {
                hasZeroRemainderOfDivision = true;
            }
        }

        //Через стримы
        MyPredicate myPredicate = e -> e % 3 == 0;
        boolean hasZeroRemainderOfDivisionLambda = integers.stream().anyMatch(myPredicate::test);

    }
}
