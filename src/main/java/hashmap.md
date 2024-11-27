# Hashmap interview questions

1. Write a java code to iterate hash map provide
2. How Hashmap is stored in memory — Memory Structure ?
3. What is Hash Collision during hashmap.put() operation ?
4. What is the significance of equals() and hashcode() in Hashmap ?
5. Why the searching time-complexity of HashMap is O(1) ?
6. What is Hashmap treefy threshold and why it is introduced in Java 8 ?
7. Internal working of hashmap
8. What is time complexity of get() and put() in HashMap?
9. What is difference between HashMap and Hashtable in Java?
10. What is difference between HashMap and ConcurrentHashMap in Java?
11. What is difference between HashMap and HashSet in Java?
12. What is difference between HashMap and TreeMap in Java?
13. How many entries you can put into an HashMap? 
14. How ConcurrentHashMap works? 
15. What is difference between HashMap and LinkedHashMap in Java?
16. Can you name some new methods added into HashMap in Java 8
17. What is difference between HashMap and WeakHashMap in Java?
18. Difference between Comparator and Comparable?


## 1. Write a java code to iterate hash map provide
```java
 HashMap<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);

        // 1. Using entrySet() and for-each loop
        System.out.println("Using entrySet() and for-each loop:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // 2. Using keySet() and get() method
        System.out.println("\nUsing keySet() and get() method:");
        for (String key : map.keySet()) {
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }

        // 3. Using values() to iterate through values only
        System.out.println("\nUsing values() to iterate through values:");
        for (Integer value : map.values()) {
            System.out.println("Value: " + value);
        }

        // 4. Using Iterator on entrySet()
        System.out.println("\nUsing Iterator on entrySet:");
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // 5. Using Iterator on keySet() and get() method
        System.out.println("\nUsing Iterator on keySet and get() method:");
        Iterator<String> keyIterator = map.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }

        // 6. Using Java 8 forEach and lambda
        System.out.println("\nUsing Java 8 forEach and lambda:");
        map.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        // 7. Using Stream API with forEach (Java 8+)
        System.out.println("\nUsing Stream API with forEach:");
        map.entrySet().stream()
            .forEach(entry -> System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()));

        // 8. Using Stream API to filter and iterate (Java 8+)
        System.out.println("\nUsing Stream API to filter and iterate (Values > 1):");
        map.entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .forEach(entry -> System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()));
```


## 2. How Hashmap is stored in memory — Memory Structure ?
A Hashmap is typically implemented as an array of linked lists or as an array of balanced binary search trees (from Java 8 onwards, when the threshold is crossed). The array provides the buckets to store key-value pairs. Each bucket can hold multiple entries (either in a linked list or a tree) to handle hash collisions. The index of the array is calculated using the hash code of the key.
## 3. What is Hash Collision during hashmap.put() operation ?
Hash collision occurs when two different keys have the same hash code. In a Hashmap, when a collision occurs during the put() operation, the new key-value pair is added to the existing bucket. If the bucket uses a linked list, the new entry is appended to the list. If the bucket uses a tree (after reaching the treefy threshold), the tree structure is used to handle collisions.
## 4. What is the significance of equals() and hashcode() in Hashmap ?
The equals() and hashCode() methods are crucial for proper functioning of a Hashmap. The hashCode() method is used to calculate the hash code of a key, which determines the index of the bucket where the key-value pair is stored. The equals() method is used to compare keys for equality when there is a hash collision. It helps identify if two keys are the same or different, enabling proper retrieval of values from the Hashmap.
## 5. Why the searching time-complexity of HashMap is O(1) ?
The searching time-complexity of a Hashmap is O(1) on average, which means it has constant-time performance for most operations. This is possible because Hashmap uses a hashing technique to calculate the index where a key-value pair is stored. With a properly distributed hash function, the time complexity to retrieve a value is independent of the number of elements stored in the Hashmap.
## 6. What is Hashmap treefy threshold and why it is introduced in Java 8 ?
The “treefy threshold” is the number of entries a bucket can hold before it is converted from a linked list to a balanced binary search tree. In Java 8, an optimization was introduced to convert a bucket to a tree structure when the number of entries in that bucket exceeds a certain threshold (usually 8). This was done to improve the worst-case performance of the Hashmap, as a long linked list can lead to degraded search performance. By using a tree, the time complexity for search operations in a bucket is reduced from O(n) to O(log n), resulting in more predictable and improved performance.

## 7. Internal working of hashmap
![internal-working-of-hashmap.png](..%2Fresources%2Finternal-working-of-hashmap.png)

There are four things we should know about before going into the internals of how does HashMap work in Java -
1. HashMap works on the principal of hashing.
2. Map.Entry interface - This interface gives a map entry (key-value pair). HashMap in Java stores both key and value object, in bucket, as an object of Entry class which implements this nested interface Map.Entry.
3. hashCode() -HashMap provides put(key, value) for storing and get(key) method for retrieving Values from HashMap. When put() method is used to store (Key, Value) pair, HashMap implementation calls hashcode on Key object to calculate a hash that is used to find a bucket where Entry object will be stored. When get() method is used to retrieve value, again key object is used to calculate a hash which is used then to find a bucket where that particular key is stored.
4. equals() - equals() method is used to compare objects for equality. In case of HashMap key object is used for comparison, also using equals() method Map knows how to handlehashing collision (hashing collision means more than one key having the same hash value, thus assigned to the same bucket. In that case objects are stored in a linked list, refer figure for more clarity.

Where hashCode method helps in finding the bucket where that key is stored, equals method helps in finding the right key as there may be more than one key-value pair stored in a single bucket.
** Bucket term used here is actually an index of array, that array is called table in HashMap implementation. Thus table[0] is referred as bucket0, table[1] as bucket1 and so on.

HashMap changes in Java 8
Though HashMap implementation provides constant time performance O(1) for get() and put() method but that is in the ideal case when the Hash function distributes the objects evenly among the buckets.
But the performance may worsen in the case hashCode() used is not proper and there are lots of hash collisions. As we know now that in case of hash collision entry objects are stored as a node in a linked-list and equals() method is used to compare keys. That comparison to find the correct key with in a linked-list is a linear operation so in a worst case scenario the complexity becomes O(n).
To address this issue in Java 8 hash elements use balanced trees instead of linked lists after a certain threshold is reached. Which means HashMap starts with storing Entry objects in linked list but after the number of items in a hash becomes larger than a certain threshold, the hash will change from using a linked list to a balanced tree, this will improve the worst case performance from O(n) to O(log n).

## 8. What is time complexity of get() and put() in HashMap?
Since HashMap is based upon hash table data structure complexity of get() and put() is O(1) but due to collision, all object can store into same location then it become linked list or binary tree, depending upon Java implementation store it and time complexity reduced to O(N) or O(LogN) depending upon entries are stored in Linked List or Binary tree.

## 9. What is difference between HashMap and Hashtable in Java?  

# HashMap vs. Hashtable in Java

`HashMap` and `Hashtable` are both implementations of the `Map` interface in Java, used to store key-value pairs. However, they differ in several key ways.

| Feature                          | HashMap                              | Hashtable                            |
|----------------------------------|--------------------------------------|--------------------------------------|
| **Thread Safety**                | Not thread-safe. Multiple threads can access a `HashMap` simultaneously, so synchronization must be done manually if needed. | Thread-safe. It synchronizes every method, making it slower but suitable for concurrent access. |
| **Performance**                  | Generally faster as it’s unsynchronized. | Slower due to synchronization overhead. |
| **Null Keys and Values**         | Allows one `null` key and multiple `null` values. | Does not allow any `null` keys or values. |
| **Introduced In**                | Java 1.2                             | Java 1.0                             |
| **Data Structure**               | Uses an array of nodes with linked lists or binary trees for collision handling. | Similar structure, but lacks tree nodes (does not convert to red-black trees). |
| **Iteration Order**              | Does not guarantee the order of entries. | Does not guarantee the order of entries. |
| **Fail-Fast Iterator**           | Yes. Throws a `ConcurrentModificationException` if modified after creation of the iterator. | No. Does not fail fast, but may throw exceptions in concurrent environments due to unexpected states. |
| **Use Case**                     | Suitable for non-threaded applications or where manual synchronization is preferred. | Suitable for legacy systems needing synchronized maps or single-threaded applications. |
| **Alternatives in Java**         | `ConcurrentHashMap` for thread-safe maps without full synchronization. | `ConcurrentHashMap` is generally preferred over `Hashtable` in modern code. |

### Example Code

#### HashMap
```java

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", null); // Allows null values

        System.out.println("HashMap: " + map);
    }
}


public class HashtableExample {
    public static void main(String[] args) {
        Hashtable<String, Integer> table = new Hashtable<>();
        table.put("Apple", 1);
        table.put("Banana", 2);
        // table.put("Cherry", null); // Throws NullPointerException
        System.out.println("Hashtable: " + table);
    }
}
```

## 10. What is difference between HashMap and ConcurrentHashMap in Java?
# HashMap vs. ConcurrentHashMap in Java

`HashMap` and `ConcurrentHashMap` are both implementations of the `Map` interface in Java, used to store key-value pairs. They differ mainly in thread safety, performance, and intended use cases.

| Feature                          | HashMap                              | ConcurrentHashMap                    |
|----------------------------------|--------------------------------------|--------------------------------------|
| **Thread Safety**                | Not thread-safe. Requires manual synchronization if used in a concurrent environment. | Thread-safe. Designed for concurrent access by multiple threads. Uses segmented locking for better performance. |
| **Performance**                  | Fast in single-threaded environments as no synchronization is involved. | Designed for high-performance in multi-threaded environments by allowing concurrent reads and segment-based writes. |
| **Null Keys and Values**         | Allows one `null` key and multiple `null` values. | Does not allow `null` keys or values, as this could lead to ambiguity in concurrent contexts. |
| **Internal Locking Mechanism**   | No internal locking mechanism; requires external synchronization for thread safety. | Uses finer-grained locking (lock per bucket segment) to allow concurrent read/write operations. |
| **Fail-Fast Iterator**           | Yes. Throws `ConcurrentModificationException` if modified while iterating. | Weakly consistent iterator; does not throw `ConcurrentModificationException` and reflects some, but not necessarily all, changes during iteration. |
| **Use Case**                     | Suitable for single-threaded or low-concurrency scenarios where synchronization is not needed. | Suitable for highly concurrent scenarios where high read/write throughput is needed. |
| **Introduced In**                | Java 1.2                             | Java 5 (as part of `java.util.concurrent` package) |

### Key Differences in Detail

1. **Thread Safety**:
    - `HashMap` is **not thread-safe**, meaning it should not be accessed by multiple threads without external synchronization.
    - `ConcurrentHashMap` is **thread-safe** and optimized for concurrent access, using segmented locking, which allows multiple threads to read and write without locking the entire map.

2. **Performance and Locking**:
    - `HashMap` is faster in single-threaded environments because it has no overhead from synchronization.
    - `ConcurrentHashMap` allows concurrent reads and segmented writes, making it much more efficient for multi-threaded applications by minimizing lock contention.

3. **Null Handling**:
    - `HashMap` allows one `null` key and multiple `null` values.
    - `ConcurrentHashMap` does **not allow any `null` keys or values**. This design choice avoids ambiguity and potential issues in concurrent scenarios.

4. **Iteration Behavior**:
    - `HashMap` has a **fail-fast iterator**, meaning it will throw a `ConcurrentModificationException` if modified while iterating.
    - `ConcurrentHashMap` has a **weakly consistent iterator** that doesn’t throw an exception if the map is modified while iterating. It may or may not reflect recent updates, but it guarantees consistency at a specific point in time.

5. **Use Cases**:
    - Use `HashMap` for single-threaded or read-heavy applications where thread safety is not a concern.
    - Use `ConcurrentHashMap` for concurrent applications needing frequent reads and writes.

### Example Code

#### HashMap (Single-threaded Example)
```java

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", null); // Allows null values

        System.out.println("HashMap: " + map);
    }
}

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        // map.put("Cherry", null); // Throws NullPointerException

        System.out.println("ConcurrentHashMap: " + map);
    }
}
```

## 11. What is difference between HashMap and HashSet in Java?


`HashMap` and `HashSet` are both part of Java's Collection framework and are backed by hash-based storage. However, they serve different purposes and have distinct characteristics.

| Feature                          | HashMap                              | HashSet                              |
|----------------------------------|--------------------------------------|--------------------------------------|
| **Purpose**                      | Stores key-value pairs. Each key is unique, and each key is mapped to a specific value. | Stores unique elements only. Primarily used to represent a set of elements with no duplicates. |
| **Underlying Data Structure**    | Backed by an array of buckets, where each bucket is a linked list or binary tree. | Internally backed by a `HashMap`, where each element is stored as a key with a constant `dummy` value. |
| **Data Type Stored**             | Stores `Map.Entry` objects with key-value pairs. | Stores only the keys as elements without associated values. |
| **Null Handling**                | Allows one `null` key and multiple `null` values. | Allows one `null` element only. |
| **Iteration Order**              | Does not guarantee order of entries. | Does not guarantee order of elements. |
| **Performance**                  | Provides `O(1)` time complexity for get and put operations, assuming a good hash function. | Provides `O(1)` time complexity for add, remove, and contains operations. |
| **Use Case**                     | Used when there is a need to associate keys with values, like in dictionaries or maps. | Used when only unique values need to be stored without duplicates, like in sets. |
| **Fail-Fast Iterator**           | Yes, the iterator is fail-fast and throws `ConcurrentModificationException` if modified during iteration. | Yes, the iterator is fail-fast and throws `ConcurrentModificationException` if modified during iteration. |

### Key Differences in Detail

1. **Purpose**:
    - `HashMap` is used for mapping keys to values, making it suitable for storing key-value pairs.
    - `HashSet` is used for storing unique elements without any duplicate values.

2. **Data Structure**:
    - `HashMap` internally manages entries as `Map.Entry` objects, containing both a key and a value.
    - `HashSet` is implemented using a `HashMap`, where each element in the set is stored as a key in the `HashMap`, and the associated value is a constant placeholder object.

3. **Null Handling**:
    - `HashMap` allows one `null` key and multiple `null` values.
    - `HashSet` allows a single `null` element, as it only stores unique items.

4. **Performance**:
    - Both `HashMap` and `HashSet` offer constant time complexity for basic operations (`put`, `get`, `contains`) in ideal conditions (O(1) with a good hash function).

5. **Use Cases**:
    - Use `HashMap` when you need a collection that associates each unique key with a specific value, as in dictionaries.
    - Use `HashSet` when you only need a collection of unique elements, such as finding unique items in a list.

## 12. What is difference between HashMap and TreeMap in Java?
# HashMap vs. TreeMap in Java

Both `HashMap` and `TreeMap` are implementations of the `Map` interface in Java, used to store key-value pairs. However, they differ in ordering, performance, and underlying data structures.

| Feature                          | HashMap                              | TreeMap                              |
|----------------------------------|--------------------------------------|--------------------------------------|
| **Ordering**                     | Does not maintain any order of keys. | Maintains a sorted order based on natural ordering of keys or a custom comparator. |
| **Underlying Data Structure**    | Uses an array of linked lists or binary trees (depending on Java version) for storing entries. | Uses a Red-Black Tree (a self-balancing binary search tree) to maintain sorted order. |
| **Time Complexity**              | `O(1)` average time for `get` and `put` operations. | `O(log n)` time complexity for `get`, `put`, and other operations due to tree traversal. |
| **Null Handling**                | Allows one `null` key and multiple `null` values. | Does not allow `null` keys (throws `NullPointerException`) but allows `null` values. |
| **Iteration Order**              | Entries are not ordered; iteration order may vary. | Entries are sorted by key order (ascending or based on custom comparator). |
| **Performance**                  | Faster than `TreeMap` for insertions, deletions, and lookups in an average-case scenario. | Slower than `HashMap` for most operations due to the tree structure, but provides sorted data. |
| **Use Case**                     | Suitable for general-purpose mappings where order does not matter. | Suitable when a sorted map is required, such as for range-based queries or ordered navigation. |
| **Fail-Fast Iterator**           | Yes, it throws `ConcurrentModificationException` if modified while iterating. | Yes, it throws `ConcurrentModificationException` if modified while iterating. |

### Key Differences in Detail

1. **Ordering**:
    - `HashMap` does not maintain any order for keys and values; elements are accessed based on their hash codes.
    - `TreeMap` maintains elements in ascending key order by default (or based on a custom comparator), making it suitable for cases where sorted order is needed.

2. **Underlying Data Structure**:
    - `HashMap` uses an array of linked lists or tree nodes (in case of high collision rates in Java 8 and above).
    - `TreeMap` uses a **Red-Black Tree**, which is a self-balancing binary search tree that ensures sorted order and balanced height for efficient `O(log n)` operations.

3. **Time Complexity**:
    - `HashMap` provides `O(1)` time complexity for insertions and lookups in the average case due to hash-based indexing.
    - `TreeMap` provides `O(log n)` time complexity due to the Red-Black Tree structure, which requires tree traversal for operations.

4. **Null Handling**:
    - `HashMap` allows one `null` key and multiple `null` values.
    - `TreeMap` does **not allow `null` keys** because it sorts entries and cannot compare `null` to other keys.

5. **Iteration Order**:
    - `HashMap` iteration order is unpredictable and does not follow insertion or sorting order.
    - `TreeMap` guarantees sorted order of keys, which is useful for ordered data traversal.

6. **Use Cases**:
    - `HashMap` is suitable for general-purpose maps where fast access without any particular order is needed.
    - `TreeMap` is used when the sorted order of entries is important, such as in range-based queries, finding the smallest or largest entry, or accessing entries in natural or custom order.

## 13. How many entries you can put into an HashMap? (answer)
You can put as many as you want, until you run out of memory but once you cross the threasold of Integer.MAX_VALUE you will start facing problems because the size method also return an int, which means if you store more than Intger.MAX_VALUE, the size method will return incorrect value.

## 14. How ConcurrentHashMap works?

A ConcurrentHashMap is a Java data structure that implements the java.util.Map interface. Its purpose is to enable multiple threads to access and modify the map concurrently, making it a suitable choice for scenarios where synchronization between threads is required while working with key-value pairs.

Unlike a regular HashMap, a ConcurrentHashMap is internally organized into segments or buckets. Each segment is guarded by its own lock, allowing different threads to access different segments simultaneously. This design minimizes contention and enhances the map's performance in situations with high concurrency.

Operations like adding, retrieving, removing, and checking the existence of key-value pairs can be performed concurrently on a ConcurrentHashMap without requiring external synchronization. However, more complex operations might still necessitate additional synchronization.

It’s important to note that iterators provided by ConcurrentHashMap offer weakly consistent behavior. This means that they reflect the state of the map at the time of iterator creation and may not include the latest updates.

While ConcurrentHashMap provides a level of thread safety, it's crucial to consider the specific requirements of your application and assess whether a concurrent data structure is the appropriate choice. Each concurrency scenario has its own considerations and trade-offs.

* The underlined data structure for ConcurrentHashMap is Hashtable.
* ConcurrentHashMap class is thread-safe i.e. multiple threads can operate on a single object without any complications.
* At a time any number of threads are applicable for a read operation without locking the ConcurrentHashMap object which is not there in HashMap.
* In ConcurrentHashMap, the Object is divided into a number of segments according to the concurrency level.
* The default concurrency-level of ConcurrentHashMap is 16.
* In ConcurrentHashMap, at a time any number of threads can perform retrieval operation but for updated in the object, the thread must lock the particular segment in which the thread wants to operate. This type of locking mechanism is known as Segment locking or bucket locking. Hence at a time, 16 update operations can be performed by threads.
* Inserting null objects is not possible in ConcurrentHashMap as a key or value.

## 15. What is difference between HashMap and LinkedHashMap in Java?
1. Ordering of Elements
   HashMap: Does not guarantee any order of its entries. The order of elements may change when entries are added or removed.
   LinkedHashMap: Maintains the insertion order of its elements, meaning the elements are iterated in the order they were added to the map. It can also be configured to maintain access order, where the order is updated whenever an element is accessed.
2. Implementation
   HashMap: Backed by an array of buckets, where each bucket is a linked list or tree (if hash collisions are frequent). It doesn’t track the order of insertion.
   LinkedHashMap: Extends HashMap and adds a linked list to each bucket, which maintains the order of elements either by insertion or access order.
3. Performance
   HashMap: Faster for most operations because it does not maintain any order. The average time complexity for get and put is O(1).
   LinkedHashMap: Slightly slower than HashMap due to the overhead of maintaining the order, but it provides predictable iteration order. The time complexity for get and put is still O(1), but it may have additional overhead from maintaining the linked list.
4. Use Case
   HashMap: Best used when the order of entries is not important, and faster access is needed.
   LinkedHashMap: Ideal when the insertion or access order needs to be preserved, such as for caching or when you need to process elements in the order they were added or accessed.

## 16. Can you name some new methods added into HashMap in Java 8

In Java 8, several new methods were added to HashMap to improve its functionality and enhance support for functional programming. Some of the notable new methods include:

1. forEach(BiConsumer<? super K, ? super V> action)
   Allows performing an action for each entry in the map. It is a default method that accepts a BiConsumer and iterates over all the entries in the map.
   ```java
    map.forEach((key, value) -> System.out.println(key + ": " + value));
    ```
   
2. compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
   This method computes a value for a given key using a BiFunction. If the key already exists, the existing value is passed to the function; otherwise, it computes a new value.
   ```java
    map.compute("key1", (key, val) -> val == null ? 1 : val + 1);
    ```
   
3. computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
   If the specified key is not present in the map, this method computes the value using the provided Function and adds it to the map.
   ```java
    map.computeIfAbsent("key1", key -> "new value");
    ```
   
4. computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
   If the key is present, this method computes a new value based on the old value using the given BiFunction.
   ```java
   map.computeIfPresent("key1", (key, val) -> val + 1);
   ```           

5. merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
   This method either adds a new value for a key or merges an existing value using a BiFunction. If the key is already present, the BiFunction combines the old value with the new value.
   ```java
   map.merge("key1", 5, (oldVal, newVal) -> oldVal + newVal);
   ```
6. getOrDefault(Object key, V defaultValue)
   This method returns the value associated with the given key, or a default value if the key does not exist.
```java
map.getOrDefault("key1", "default value"); 
```


## 17. What is difference between HashMap and WeakHashMap in Java?
The main difference between HashMap and WeakHashMap is the fact that keys in WeakHashMap are wrapped inside WeakReference object, which means if an object is not referred anywhere else other than the Map itself, then it is subject to garbage collection.


## 18. Difference between Comparator and Comparable?
![comparableAndComparator.png](..%2Fresources%2FcomparableAndComparator.png)

# Comparable vs Comparator in Java

| Comparable                            | Comparator                              |
|---------------------------------------|-----------------------------------------|
| Comparable is an interface in Java.   | Comparator is a functional interface in Java. |
| Comparable provides `compareTo()` method to sort objects. | Comparator provides `compare()` method to sort objects. |
| Comparable is a part of the `java.lang` package. | Comparator is a part of the `java.util` package. |
| Comparable can be used for natural or default ordering. | Comparator can be used for custom ordering. |
| Comparable provides a single sorting sequence. Ex: Sort either by id or name. | Comparator provides multiple sorting sequences. Ex: Sort by both id and name. |
| Comparable modifies the class that implements it. | Comparator doesn't modify any class. |

Comparable interface can be used to provide single way of sorting whereas Comparator interface is used to provide different ways of sorting.
For using Comparable, Class needs to implement it whereas for using Comparator we don’t need to make any change in the class.
Comparable interface is in java.lang package whereas Comparator interface is present in java.util package.
We don’t need to make any code changes at client side for using Comparable, Arrays.sort() or Collection.sort() methods automatically uses the compareTo() method of the class. For Comparator, client needs to provide the Comparator class to use in compare() method.

```java
class Book implements Comparable<Book> {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Overriding compareTo method to compare books by title
    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", author='" + author + '\'' + '}';
    }
}
// example usage

List<Book> books = Arrays.asList(
        new Book("Java Programming", "John Doe"),
        new Book("Algorithms", "Jane Smith"),
        new Book("Data Structures", "Tom Brown")
);

Collections.sort(books);
System.out.println(books);

```

```java
class TitleComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getTitle().compareTo(b2.getTitle());
    }
}

class AuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getAuthor().compareTo(b2.getAuthor());
    }
}
// example usage

List<Book> books = Arrays.asList(
        new Book("Java Programming", "John Doe"),
        new Book("Algorithms", "Jane Smith"),
        new Book("Data Structures", "Tom Brown")
);

// Sort by title
Collections.sort(books, new TitleComparator());
        System.out.println("Sorted by title: " + books);

// Sort by author
Collections.sort(books, new AuthorComparator());
        System.out.println("Sorted by author: " + books);

```