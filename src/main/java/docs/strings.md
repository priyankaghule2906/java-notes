# String related question


1. What is string?
2. Is string class or data type?
3. What is difference between == and equals()?
4. What is the difference between String, StringBuilder, and StringBuffer?
5. Why are strings immutable in Java?
6. How do you compare two strings in Java?
7. Why string should be created using string literal?
8. How to create immutable class?
9. What is StringJoiner introduced in Java 8?
10. What are new String methods introduced in Java 11?
11. What are Strings new methods introduced in Java 12?
12. what are important string methods

  


## 1. What is string?
In Java, String is sequence of characters. String objects are immutable which means it cannot be modified once created.

## 2. Is string class or data type?
String is class in java.lang package but in Java all classes are also considered as data type hence string are data type as well

## 3. What is difference between == and equals()?
== Compares references of string object while equals() method compares contents of string object.

## 4. What is the difference between String, StringBuilder, and StringBuffer?


# String vs StringBuilder vs StringBuffer

| Feature       | String                                                                                                   | StringBuilder                                                                                   | StringBuffer                                                                                   |
|---------------|----------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------|
| **Definition**| Represents an immutable sequence of characters. Once created, its content cannot be changed.              | A mutable alternative to `String`, allowing efficient modification without creating new objects. | Similar to `StringBuilder`, but thread-safe, making it suitable for multi-threaded applications.|
| **Pros**      | - Thread-safe (immutable) <br> - Predictable memory usage.                                               | - Mutable, efficient for dynamic string manipulation.<br> - Ideal for concatenation in loops.   | - Mutable and thread-safe. <br> - Safe for use in multi-threaded environments.                 |
| **Cons**      | - Inefficient for frequent modifications; creates new objects with each change, leading to overhead.     | - Not thread-safe; requires synchronization in multi-threaded contexts.                         | - Slightly less efficient than `StringBuilder` due to synchronization overhead.                |
| **Usage**     | When immutability is required, and changes to the string are infrequent.                                 | Ideal for dynamic or frequent string modifications in single-threaded contexts.                 | Suitable for scenarios needing both mutability and thread safety.                              |
| **Performance**| Slower for concatenation as new objects are created.                                                    | Faster for concatenation as no new objects are created.                                         | Slightly slower than `StringBuilder` due to thread safety overhead.                            |
| **Example**   | `String str = "Hello";` <br> `str = str + " World"; // Creates a new string object`                      | `StringBuilder sb = new StringBuilder("Hello");` <br> `sb.append(" World"); // Modifies existing object` | `StringBuffer buffer = new StringBuffer("Hello");` <br> `buffer.append(" World"); // Modifies existing object` |

## Which to Choose:
- **String**: Use when you need an immutable sequence of characters, and frequent modifications are not needed.
- **StringBuilder**: Use for dynamic string manipulation in a single-threaded environment; it offers efficient modifications.
- **StringBuffer**: Choose when thread safety is required along with efficient string manipulation.

The choice depends on whether your application prioritizes immutability, efficiency in string manipulation, or thread safety.

## 5. Why are strings immutable in Java?

In Java, strings are immutable for several key reasons related to memory optimization, security, and thread safety:

**Memory Efficiency with String Pooling**: Java uses a "string pool" to store commonly used strings. When a string is created, Java checks if it already exists in the pool, and if so, reuses that object instead of creating a new one. If strings were mutable, any change to a string's content would also affect all other references to it, making pooling impractical and resulting in memory inefficiency.

**Security**: Strings are widely used to store sensitive information, such as file paths, database URLs, and credentials. Immutable strings help prevent accidental or malicious modifications, making Java applications more secure. For example, in network connections, the immutability of strings prevents the modification of hostnames or ports by another part of the program.

**Thread Safety**: Since strings are immutable, they are inherently thread-safe. Multiple threads can access the same string without the need for synchronization, avoiding potential data inconsistencies or the need for complex locking mechanisms, which can lead to better performance in multithreaded environments.

**Performance with Caching**: Immutability allows Java to cache a string's hash code at the time of its creation, improving performance in scenarios where strings are frequently used as keys in hash-based data structures like HashMap. The cached hash code eliminates the need for repeated calculations, speeding up retrieval operations.



## 6. How do you compare two strings in Java?

In Java, you can compare two strings using several approaches:

1. Using .equals() Method: This method compares the content of two strings. It checks if each character in both strings is identical and returns true if they are equal. 
This method is case-sensitive, so "hello".equals("Hello") will return false.

```java
String str1 = "Hello";
String str2 = "Hello";
if (str1.equals(str2)) {
System.out.println("Strings are equal.");
}
```

2. Using .equalsIgnoreCase() Method: If you want to compare strings without considering case differences, .equalsIgnoreCase() is ideal. 
"hello".equalsIgnoreCase("Hello") will return true.

```java
String str1 = "hello";
String str2 = "Hello";
if (str1.equalsIgnoreCase(str2)) {
System.out.println("Strings are equal ignoring case.");
}
```

3. Using compareTo() Method: This method is used to compare strings lexicographically. It returns:

0 if the strings are equal,
A positive integer if the first string is lexicographically greater,
A negative integer if the first string is lexicographically smaller.
```java
String str1 = "apple";
String str2 = "banana";
int result = str1.compareTo(str2);
if (result == 0) {
System.out.println("Strings are equal.");
} else if (result < 0) {
System.out.println("str1 is lexicographically smaller than str2.");
} else {
System.out.println("str1 is lexicographically greater than str2.");
}
```

4. Using compareToIgnoreCase() Method: Similar to compareTo(), but ignores case differences between the strings.
It’s helpful when you need a case-insensitive lexicographical comparison.

```java
String str1 = "Apple";
String str2 = "apple";
if (str1.compareToIgnoreCase(str2) == 0) {
System.out.println("Strings are equal ignoring case and lexicographically.");
}
```

5. Using == Operator: This checks if both string references point to the same object in memory, not the content. It’s not generally used for content comparison but can confirm if two variables refer to the same string instance in memory.

```java
String str1 = "Hello";
String str2 = "Hello";
if (str1 == str2) {
System.out.println("Both references point to the same object.");
}
```

## 7. Why string should be created using string literal?
String object created using string literal is stored in String constant pool
which minimize the redundancy and memory waste caused by storing
String objects with duplicate values on the heap.

## 8. How to create immutable class?
To create an immutable class following steps are followed:
Declare the class as final so it could not be inherited.
Declare all variables as private so that it cannot be directly modified.
Do not allocate setter methods for variables only have getter methods.
Declare all variables as final so that it can be initialized once only.
Initialize all the variables via a constructor.

```java
public final class Person {
    final String name;
    final String address;
    
    public Person (String name, String address) {
        this.name = name;
        this.address = address;
    }
    
    public String getName() {
     return name;
    }
   
    public String getAddress () {
        return address;
    }
}

```
## 9. What is StringJoiner introduced in Java 8?
Java 8 added a new final class StringJoiner in java.util package.
It is used to construct a sequence of characters separated by delimiter.
```java
StringJoiner joinSports = new StringJoiner(“,”);
joinSports.add("baseball");
joinSports.add("football");
System.out.println(joinSports);
// Output: baseball, football
```
## 10. What are new String methods introduced in Java 11?

There are lots of new string methods introduced in Java 11, few of them
are as below:

String.isEmpty():
String.isEmpty() method determines if String is empty or if it contains
only whitespace characters.

String.lines():
String.lines() method returns stream of lines extracted from a given
multiline string, separated by line terminators.

String.repeat():
String.repeat() method returns string which is basically concatenation of
given string repeated n times. If string is empty or count is zero then
empty string is returned


## 11. What are Strings new methods introduced in Java 12?

String.indent():

The indent method helps with changing the indentation of a String. We
can either pass a positive value or a negative value depending on whether
we want to add more white spaces or remove existing white spaces.
e.g.
var result = "Hello\nWorld!".indent(3);
System.out.println(result);
Output: Hello World!

String.transform():

The transform() method takes a String and transforms it into a new String
with the help of a Function.
e.g.
var result = "Hello".transform(s -> s + ", World!");
System.out.println(result); // Hello, World!

## 12. what are important string methods

* length() : Returns the length of the string (number of characters).

```java
String s = "Hello";
int len = s.length(); // Output: 5
```
* charAt(int index) : Returns the character at the specified index.
```java
char ch = s.charAt(1); // Output: 'e'
```
* isEmpty() : Checks if the string is empty (length is 0).
```java
String s = "";
boolean empty = s.isEmpty(); // Output: true
```
* equals(Object obj) : Compares the string to another string for equality.
```java
boolean equal = s.equals("Hello"); // Output: true
```
* equalsIgnoreCase(String anotherString) : Compares two strings for equality, ignoring case differences.
```java
boolean equalIgnoreCase = s.equalsIgnoreCase("hello"); // Output: true
```
* contains(CharSequence s) : Checks if the string contains the specified sequence of characters.
```java
boolean contains = s.contains("ell"); // Output: true
```

* indexOf(String str) : Returns the index of the first occurrence of the specified substring, or -1 if not found.
```java
int index = s.indexOf("l"); // Output: 2
```
* lastIndexOf(String str) : Returns the index of the last occurrence of the specified substring, or -1 if not found.
```java
int lastIndex = s.lastIndexOf("l"); // Output: 3
```
* startsWith(String prefix) : Checks if the string starts with the specified prefix.
```java
boolean starts = s.startsWith("He"); // Output: true
```
* endsWith(String suffix) : Checks if the string ends with the specified suffix.
```java
boolean ends = s.endsWith("lo"); // Output: true
```
* substring(int beginIndex) : Returns a substring starting from the specified index to the end.
```java
String sub = s.substring(1); // Output: "ello"
```
* substring(int beginIndex, int endIndex) : Returns a substring starting from beginIndex and ending just before endIndex.
```java
String sub = s.substring(1, 3); // Output: "el"
```
* toUpperCase() : Converts all characters in the string to uppercase.
```java
String upper = s.toUpperCase(); // Output: "HELLO"
```
* toLowerCase() : Converts all characters in the string to lowercase.
```java
String lower = s.toLowerCase(); // Output: "hello"
```
* trim() : Removes any leading and trailing whitespace from the string.

```java
String trimmed = " Hello ".trim(); // Output: "Hello"
```
* replace(char oldChar, char newChar) : Replaces all occurrences of oldChar with newChar in the string.

```java

String replaced = s.replace('l', 'p'); // Output: "Heppo"
```
* replace(CharSequence target, CharSequence replacement) : Replaces each substring matching the target sequence with the replacement sequence.

```java
String replaced = s.replace("ell", "ipp"); // Output: "Hippo"
```
* replaceAll(String regex, String replacement) :Replaces each substring matching a regular expression with the given replacement.

```java

String result = "abc123".replaceAll("\\d", ""); // Output: "abc"
```
* replaceFirst(String regex, String replacement): Replaces the first substring matching a regular expression with the replacement.

```java
String result = "abc123abc".replaceFirst("\\d", ""); // Output: "abc23abc"
```

* split(String regex): Splits the string based on the specified regular expression.

```java
String[] parts = "apple,banana,cherry".split(","); // Output: ["apple", "banana", "cherry"]
```
* join(CharSequence delimiter, CharSequence... elements): Joins the given elements with the specified delimiter.

```java
String joined = String.join("-", "2024", "11", "03"); // Output: "2024-11-03"
```

* toCharArray():   Converts the string to a character array.

```java
char[] chars = s.toCharArray(); // Output: ['H', 'e', 'l', 'l', 'o']
```

* valueOf(...): Converts different types (e.g., int, char, double) to a string.

```java
String numStr = String.valueOf(123); // Output: "123"
```
* matches(String regex) : Checks if the entire string matches the specified regular expression.
```java
boolean isMatch = s.matches("\\w+"); // Output: true (if s is alphanumeric)
```
* intern() : The method intern() creates an exact copy of a String object in the heap memory and stores it in the String constant pool.

Note that, if another String with the same contents exists in the String constant pool, then a new object won’t be created and the new reference will point to the other String.

```java
@Test
public void whenIntern_thenCorrect() {
    String s1 = "abc";
    String s2 = new String("abc");
    String s3 = new String("foo");
    String s4 = s1.intern();
    String s5 = s2.intern();
    
    assertFalse(s3 == s4);
    assertTrue(s1 == s5);
}
```
*  chars(): Returns an IntStream of the characters in the string.

```java
s.chars().forEach(System.out::println); // Prints ASCII values of each character
```

* codePoints() : Returns an IntStream of Unicode code points in the string.

```java
s.codePoints().forEach(System.out::println);
```

