package org.example.java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Code {


    public static void main(String[] args) {
        List<Integer> listOfIntegers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56, 87);

        Map<Boolean, List<Integer>> oddEvenNumbersMap = listOfIntegers.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));

        List<String> stringList = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");

        List<String> collect = stringList.stream().distinct().collect(Collectors.toList());

        String inputString = "Java Concept Of The Day";
        Map<Character, Long> collect1 = inputString.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<String> stationeryList = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler", "Note Book", "Pencil");

        stationeryList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Double> decimalList = Arrays.asList(12.45, 23.58, 17.13, 42.89, 33.78, 71.85, 56.98, 21.12);

        List<String> listOfStrings = Arrays.asList("Facebook", "Twitter", "YouTube", "WhatsApp", "LinkedIn");

        System.out.println(listOfStrings.stream().collect(Collectors.joining(", ", "[", "]")));

        List<Integer> listOfIntegers1 = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
        int max = listOfIntegers1.stream().max(Comparator.naturalOrder()).get();
        int min = listOfIntegers1.stream().min(Comparator.naturalOrder()).get();

        System.out.println("Max "+max + "; Min"+min);

        int i = 123456;

        Integer collect2 = Stream.of(String.valueOf(i).split("")).collect(Collectors.summingInt(Integer::parseInt));
        int[] a = new int[] {45, 12, 56, 15, 24, 75, 31, 89};

        IntSummaryStatistics intSummaryStatistics = Arrays.stream(a).summaryStatistics();
        int sum = Arrays.stream(a).sum();
        OptionalDouble average = Arrays.stream(a).average();

        System.out.println(intSummaryStatistics);
        System.out.println(sum);
        System.out.println(average.getAsDouble());

        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        // Convert ArrayList to int array
        int[] array = arrayList.stream().mapToInt(Integer::intValue).toArray();

        // Print the array elements
        for (int element : array) {
            System.out.println(element);
        }


    }
}


