# Java 8 Questions and Answers

1. What New Features Were Added in Java 8?
2. Describe Some of the Functional Interfaces in the Standard Library 
3. What is a Lambda Expression, and why is it used?
4. What is a Functional Interface? Can you create your own?
5. What Is a Default Method and When Do We Use It? 
6. What are Method References, and how are they used
7. What Is the Meaning of String::Valueof Expression?
8. What is Optional Class in Java 8?
9. What is Nashorn, and what advantages does it provide?
10. What is the new Date and Time API in Java 8?
11. What is the purpose of the Stream API
12. What is stream pipelining?
13. coding questions on stream


## 1. What New Features Were Added in Java 8?
* Lambda Expressions: Enables functional-style programming by allowing you to treat functionality as a method argument.
* Streams API: Allows for processing sequences of elements (e.g., collections) in a functional style.
* Functional Interfaces: Interfaces with a single abstract method, such as Runnable, Comparator, and Callable.
* Default Methods in Interfaces: Methods in interfaces can now have a default implementation.
* Optional Class: A container object that can hold a value or be empty, helping to handle null values more gracefully.
* New Date and Time API: Introduced java.time package for improved date and time handling.
* Method References and Constructor References: Enables referencing methods and constructors using :: operator.
* Nashorn JavaScript Engine: Integrates JavaScript engine within the JVM.


## 2. Describe Some of the Functional Interfaces in the Standard Library
Java 8 introduced several predefined functional interfaces in the java.util.function package to support functional programming. Each of these interfaces can be used with lambda expressions or method references. Here’s a look at some of the most commonly used functional interfaces with examples:
1. Function : it takes one argument and returns a result
```java
Function<String, Integer> lengthFunction = str -> str.length();
System.out.println(lengthFunction.apply("Hello"));  // Output: 5
```
2. Represents an operation that takes a single argument and returns no result (performs side effects).
```java
Consumer<String> printConsumer = message -> System.out.println(message);
printConsumer.accept("Hello, World!");  // Output: Hello, World!
```
3. Supplier :  it takes no arguments and returns a result

```java
Supplier<Double> randomValueSupplier = () -> Math.random();
System.out.println(randomValueSupplier.get());  // Output: random double value
```
4. Predicate :  it takes one argument and returns a boolean
```java
Predicate<Integer> isEven = num -> num % 2 == 0;
System.out.println(isEven.test(4));  // Output: true
```

5. BiFunction: Takes two arguments and produces a result.
```java
BiFunction<String, String, String> concatFunction = (a, b) -> a + b;
System.out.println(concatFunction.apply("Hello, ", "World!"));  // Output: Hello, World!
```
6. BiConsumer: Represents an operation that takes two arguments and returns no result (typically used for side effects).
```java
BiConsumer<String, Integer> printPerson = (name, age) -> System.out.println(name + " is " + age + " years old");
printPerson.accept("Alice", 30);  // Output: Alice is 30 years old
```
7. BinaryOperator – it is similar to a BiFunction, taking two arguments and returning a result. The two arguments and the result are all of the same types
```java
BinaryOperator<Integer> addOperator = (a, b) -> a + b;
System.out.println(addOperator.apply(5, 3));  // Output: 8
```
8. UnaryOperator – it is similar to a Function, taking a single argument and returning a result of the same type
```java
UnaryOperator<Integer> squareOperator = n -> n * n;
System.out.println(squareOperator.apply(6));  // Output: 36
```
9. BiPredicate: Represents a predicate (boolean-valued function) that takes two arguments
```java
BiPredicate<String, Integer> lengthCheck = (str, len) -> str.length() > len;
System.out.println(lengthCheck.test("Hello", 3));  // Output: true
```

## 3. What is a Lambda Expression, and why is it used?

Lambda Expression is a concise way to represent anonymous functions (or functional interfaces). It reduces boilerplate code and enables functional programming in Java.
Syntax: (parameters) -> expression or (parameters) -> { statements; }
Example:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.forEach(name -> System.out.println(name));
```

## 4. What is a Functional Interface? Can you create your own?

A Functional Interface is an interface with a single abstract method. It can have multiple default or static methods but only one abstract method.
You can create your own using @FunctionalInterface annotation to ensure it has only one abstract method.
Example:
```java
@FunctionalInterface
interface MyFunctionalInterface {
void execute();
}
```

## 5. What Is a Default Method and When Do We Use It?

A default method is a method with an implementation, which can be found in an interface.

We can use a default method to add a new functionality to an interface, while maintaining backward compatibility with classes that are already implementing the interface:

```java
public interface Vehicle {
public void move();
default void hoot() {
System.out.println("peep!");
}
}
```

Usually when we add a new abstract method to an interface, all implementing classes will break until they implement the new abstract method. In Java 8, this problem was solved by using the default method.

For example, the Collection interface does not have a forEach method declaration. Thus adding such a method would simply break the whole collections API.
Java 8 introduced the default method so that the Collection interface can have a default implementation of the forEach method without requiring the classes implementing this interface to implement the same.


## 6. What are Method References, and how are they used

Method References provide a shorthand notation for invoking a method by referring to it with the :: symbol.
Types include static method references (ClassName::method), instance method references (instance::method), and constructor references (ClassName::new).

```java
Integer::parseInt(str) \\ method reference
str -> Integer.ParseInt(str); \\ equivalent lambda

```

## 7. What Is the Meaning of String::Valueof Expression?

It’s a static method reference to the valueOf method of the String class.

## 8. What is Optional Class in Java 8?
In Java 8, Optional Class is a container object.

The Optional class used to represent a value that may be present or may not be.
This class helps in avoiding null pointer exceptions by providing methods to check the presence of a value before accessing it.
This helps null values handling more effectively.

```java
Optional<String> optionalName = Optional.ofNullable("John");

// Check if value is present
if (optionalName.isPresent()) {
    System.out.println("Name is present: " + optionalName.get());
} else {
    System.out.println("Name is not present");
}

int min1 = Arrays.stream(new int[]{1, 2, 3, 4, 5})
        .min()
        .orElse(0);
assertEquals(1, min1);

int min2 = Arrays.stream(new int[]{})
        .min()
        .orElse(0);
assertEquals(0, min2);
```

Some Optional methods are described below.

of: It creates an Optional with a non-null value.
ofNullable: It creates an Optional with a given nullable value.
empty: It creates an empty Optional.
isPresent: This checks whether the Optional contains a non-null value.
get: It gets the value if present, otherwise it throws an exception i.e. NoSuchElementException.
orElse: It returns the value if present, otherwise returns the specified default value.
orElseGet: It returns the value if present, otherwise it returns the result of invoking the supplier function.
orElseThrow: It returns the value if present, otherwise it throws an exception produced by the provided supplier.
map: It applies a function to the value if present and return a new Optional with the result, or return an empty Optional if no value is present.
filter: It applies a predicate to the value if present and return an Optional with the value if it matches the predicate, otherwise return an empty Optional.

## 9. What is Nashorn, and what advantages does it provide?
Nashorn is the new JavaScript processing engine that shipped with Java 8. Previously, the Java platform used Mozilla Rhino. Nashorn offers better compliance with ECMA normalized JavaScript specifications and provides faster run-time performance than its predecessor.


## 10. What is the new Date and Time API in Java 8?
Java 8 introduced the java.time package to address issues with the old Date and Calendar classes.
Key classes:
LocalDate, LocalTime, LocalDateTime: For handling date and time without timezone.
ZonedDateTime: For handling date and time with timezone.
Example:
```java

LocalDate today = LocalDate.now();
LocalDate birthday = LocalDate.of(1990, Month.JANUARY, 1);
System.out.println(today.isAfter(birthday));  // Checks if today is after the birthday
```

## 11. What is the purpose of the Stream API
The Stream API is used to process collections of data in a functional and declarative way, making it easier to work with sequences of elements.
Operations are either intermediate (e.g., filter, map) or terminal (e.g., collect, forEach).

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.stream()
       .filter(n -> n % 2 == 0)
       .forEach(System.out::println);  // Outputs even numbers
```

## 12. What is stream pipelining?
Stream pipelining is the process of chaining different operations together. Pipelining accomplishes this function by dividing stream operations into two categories, intermediate operations, and terminal operations. When each intermediate operation runs, it returns an instance of the stream. Thus, a user can set up an arbitrary number of intermediate operations to process data, thereby forming a processing pipeline.

At the end of the process, there must be a terminal operation to return a final value and terminate the pipeline.

Components of the stream are:

A data source
Set of Intermediate Operations to process the data source
Single Terminal Operation that produces the result

![stream.png](..%2Fresources%2Fstream.png)

## 13. coding questions on stream
### stream on numbers
1. Separate odd and even numbers
```java
// this will return a map where true key will have even number and false key with odd number as condition is i % 2
// partitioningBy returns a map with boolean key and List value of provided type
Map<Boolean, List<Integer>> oddEvenNumbersMap = listOfIntegers.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
```
2. How do you sort the given list of decimals in reverse order?
```java
// Comparator.reverseOrder() sorts the given stream in descending order and naturalOrder is for ascending order
decimalList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

```
3. From the given list of integers, print the numbers which are multiples of 5?

```java
List<Integer> listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
listOfIntegers.stream().filter(i -> i % 5 == 0).forEach(System.out::println);
```

4. Given a list of integers, find maximum and minimum of those numbers?
```java
List<Integer> listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
int max = listOfIntegers.stream().max(Comparator.naturalOrder()).get();
int min = listOfIntegers.stream().min(Comparator.naturalOrder()).get();
```
5. How do you merge two unsorted arrays into single sorted array using Java 8 streams?
```java
 int[] a = new int[] {4, 2, 5, 1};
         
int[] b = new int[] {8, 1, 9, 5};
         
int[] c = IntStream.concat(Arrays.stream(a), Arrays.stream(b)).sorted().distinct().toArray();
         
System.out.println(Arrays.toString(c));
```
6. How do you get three maximum numbers and three minimum numbers from the given list of integers?
```java
List<Integer> listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
 listOfIntegers.stream().sorted().limit(3).forEach(System.out::println);
 listOfIntegers.stream().sorted(Comparator.reverseOrder()).limit(3).forEach(System.out::println);
```

7. Find sum of all digits of a number in Java 8?

```java
int i = 123456;
Integer collect2 = Stream.of(String.valueOf(i).split("")).collect(Collectors.summingInt(Integer::parseInt));
```

8. Find second largest number in an integer array?
```java
List<Integer> listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
Integer secondLargestNumber = listOfIntegers.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
```
9. Given an integer array, find sum and average of all elements?

```java
int[] a = new int[] {45, 12, 56, 15, 24, 75, 31, 89};
IntSummaryStatistics intSummaryStatistics = Arrays.stream(a).summaryStatistics();
int sum = Arrays.stream(a).sum();
OptionalDouble average = Arrays.stream(a).average();
```
10.  How do you find common elements between two arrays?
```java
List<Integer> list1 = Arrays.asList(71, 21, 34, 89, 56, 28);
List<Integer> list2 = Arrays.asList(12, 56, 17, 21, 94, 34);
list1.stream().filter(list2::contains).forEach(System.out::println);
```

11. How do you find sum of first 10 natural numbers?
```java
int sum = IntStream.range(1, 11).sum();
```
12. Reverse an integer array
```java
int[] reversedArray = IntStream.rangeClosed(1, array.length).map(i -> array[array.length - i]).toArray();
```
### stream on strings

1. How do you remove duplicate elements from a list using Java 8 streams?
 ```java
// distinct intermediate operation is used for removing duplicate
List<String> collect = stringList.stream().distinct().collect(Collectors.toList());
```

2. How do you find frequency of each character in a string using Java 8 streams?
```java
// inputString.chars() returns IntStream - ascii values for each character
// Function.identity() return whatever input is passed
Map<Character, Long> collect1 = inputString.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
```
3.  How do you find frequency of each element in an array or a list?
 ```java
Map<String, Long> stationeryCountMap =
stationeryList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

```
4. Given a list of strings, join the strings with ‘[‘ as prefix, ‘]’ as suffix and ‘,’ as delimiter?
```java
String joinedString = listOfStrings.stream().collect(Collectors.joining(", ", "[", "]"));
```
5. Java 8 program to check if two strings are anagrams or not?
```java
String s1 = "RaceCar";
String s2 = "CarRace";
s1 = Stream.of(s1.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
s2 = Stream.of(s2.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
         
(s1.equals(s2))? true : false; 
```
6. Given a list of strings, sort them according to increasing order of their length?
```java
List<String> listOfStrings = Arrays.asList("Java", "Python", "C#", "HTML", "Kotlin", "C++", "COBOL", "C");
listOfStrings.stream().sorted(Comparator.comparing(String::length)).forEach(System.out::println);
   
```
7. Reverse each word of a string using Java 8 streams?
```java
String str = "Java Concept Of The Day";
         
        String reversedStr = Arrays.stream(str.split(" "))
                                    .map(word -> new StringBuffer(word).reverse())
                                    .collect(Collectors.joining(" "));
         
```


### stream on object

### stream on files 

