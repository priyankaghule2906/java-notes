# Java Memory Management

## 1. What is the Java Memory Model?
The Java Memory Model (JMM) defines how threads interact through memory and what behaviors are allowed in a multithreaded environment. It specifies the interaction between the main memory and the local memory (CPU cache).

Key Points:
- The JMM ensures visibility of changes to variables across threads.
- It defines the rules for synchronizing access to memory.
- It is important for understanding how to write thread-safe code.

## 2. what are the rules in Java Memory Model
The Java Memory Model (JMM) defines several rules and principles that govern how threads interact through memory. Here are the key rules:

1. Visibility

changes made by one thread to shared variables must be visible to other threads. The JMM provides mechanisms (like volatile variables and synchronization) to ensure visibility.

2. Atomicity

Certain operations are atomic, meaning they are indivisible and cannot be interrupted. For example, reading and writing to volatile variables and certain primitive types (like int and boolean) are atomic.


3. Ordering


The JMM allows compilers and processors to reorder instructions to optimize performance. However, the happens-before relationship ensures that certain actions occur in a specific order.


4. Happens-Before Relationship


The happens-before relationship defines the order of operations in multi-threaded programs. If one action happens-before another, the first action's effects are guaranteed to be visible to the second action.

Key happens-before rules include:

- Program Order Rule: Within a single thread, statements are executed in the order they appear.

- Monitor Lock Rule: An unlock on a monitor happens-before every subsequent lock on the same monitor.

- Volatile Variable Rule: A write to a volatile variable happens-before every subsequent read of that same variable.

- Thread Start Rule: Calling Thread.start() on a thread happens-before any actions in the started thread.

- Thread Join Rule: All actions in a thread happen-before any thread successfully returns from a Thread.join() on that thread.

5. Synchronization

Synchronization mechanisms (like synchronized blocks and java.util.concurrent classes) ensure that only one thread can access a block of code or a variable at a time, providing both mutual exclusion and visibility guarantees.

6. Final Fields

The JMM guarantees that once a constructor has completed, any thread that sees the reference to the constructed object will see the correctly constructed values of its final fields.

7. Thread Safety

To ensure thread safety, developers should avoid sharing mutable data between threads or should use synchronization mechanisms to manage access to shared data.

## 3. What is Java memory management?

Java memory management is the process of managing the allocation and deallocation of memory in a Java application. It involves the automatic allocation and deallocation of objects in the Java heap memory, as well as the management of stack memory for method invocations and local variables.

## 4. How does Java manage memory?

Java manages memory through a combination of automatic memory allocation and garbage collection. When objects are created using the new keyword, memory is allocated for them in the Java heap. The garbage collector periodically identifies and deallocates objects that are no longer referenced, freeing up memory for reuse.

## 5. What is the Java heap memory
Java Heap space is used by java runtime to allocate memory to Objects and JRE classes. Whenever we create an object, it’s always created in the Heap space. Garbage Collection runs on the heap memory to free the memory used by objects that don’t have any reference. Any object created in the heap space has global access and can be referenced from anywhere of the application.

## 5. What is the Java stack memory
Java Stack memory is used for the execution of a thread. They contain method-specific values that are short-lived and references to other objects in the heap that is getting referred from the method. Stack memory is always referenced in LIFO (Last-In-First-Out) order. Whenever a method is invoked, a new block is created in the stack memory for the method to hold local primitive values and reference to other objects in the method. As soon as the method ends, the block becomes unused and becomes available for the next method. Stack memory size is very less compared to Heap memory.

## 6. What is the difference between stack memory and heap memory in Java?

- Stack memory is used for storing local variables and method invocations, while heap memory is used for storing objects.
- Stack memory is organized as a stack data structure and is thread-specific, while heap memory is shared among all threads.
- Stack memory is automatically allocated and deallocated as method invocations happen, while heap memory requires manual memory allocation and deallocation.

## 7. What are the different generations in the Java heap?

The Java heap is divided into different generations to optimize garbage collection performance. The main generations are:

- Young Generation: This is where new objects are allocated. It consists of the Eden space and survivor spaces.
  - Eden Space: Where new objects are allocated.
  - Survivor Spaces (S0 and S1): Objects that survive garbage collection in Eden are moved here.
- Old Generation: This is where long-lived objects are stored. Objects that survive multiple garbage collection cycles in the young generation are promoted to the old generation.
- Permanent Generation (deprecated in Java 8): This was used to store metadata about classes and methods. It has been replaced by the Metaspace in Java 8 and later versions.

## 8. How can you optimize memory usage in Java?

- Use object pooling or caching to reuse objects instead of creating new ones.
- Minimize the use of static variables, as they remain in memory throughout the application's lifecycle.
- Avoid memory leaks by ensuring that objects are properly dereferenced when no longer needed.
- Use appropriate data structures and algorithms to minimize memory usage.
- Tune the garbage collector settings based on the application's memory requirements.

## 9. What are memory leaks in Java?

Memory leaks in Java occur when objects are no longer needed by the application but are still referenced, preventing them from being garbage collected. This leads to the accumulation of unreferenced objects in memory, eventually causing an OutOfMemoryError. Memory leaks can be caused by improper handling of object references, such as not dereferencing objects after use or holding onto references longer than necessary.

## 10. How can you avoid memory leaks in Java?

To avoid memory leaks in Java, follow these best practices:

- Ensure that objects are properly dereferenced when no longer needed.
- Use try-with-resources or finally blocks to release resources.
- Avoid holding onto references longer than necessary.
- Use weak references or soft references when appropriate.
- Use memory profiling tools to identify and fix memory leaks.

## 11.  How do you detect memory leak in Java?

Detecting memory leaks in Java involves identifying objects that are no longer needed but are still referenced, preventing them from being garbage collected. 

You need to monitor Java memory consumption first.

The simplest way to do this is to use jstat utility that comes with every JVM.
```textmate
jstat -gcutil <process_id> <timeout>
```
It will report memory consumption for each generation (Young, Eldery and Old) and garbage collection times (Young and Full).

As soon as you spot that Full Garbage Collection is executed too often and takes too much time, you can assume that application is leaking memory.

Then you need to create a memory dump using jmap utility:

```textmate
jmap -dump:live,format=b,file=heap.bin <process_id>
```
Then you need to analyse heap.bin file with Memory Analyser, Eclipse Memory Analyzer (MAT) for example.
MAT will analyze the memory and provide you suspect information about memory leaks.

## 12. What is garbage collection in Java?
Garbage collection (GC) is the process by which the JVM automatically reclaims memory by deleting objects that are no longer reachable in the application.

- It helps in managing memory efficiently by removing unused objects.
- The main types of GC algorithms are Serial, Parallel, CMS (Concurrent Mark-Sweep), and G1 (Garbage-First).

## 13. Are There Any Disadvantages of Garbage Collection?
Yes. Whenever the garbage collector runs, it has an effect on the application’s performance. This is because all other threads in the application have to be stopped to allow the garbage collector thread to effectively do its work.

Depending on the requirements of the application, this can be a real problem that is unacceptable by the client. However, this problem can be greatly reduced or even eliminated through skillful optimization and garbage collector tuning and using different GC algorithms.

## 14. Explain the concept of “Stop-the-world” in Java garbage collection.
“Stop-the-world” refers to the pause in application execution when the garbage collector runs. During this time, all application threads are stopped to allow the GC to perform its work.

## 15. How does the garbage collector know which objects to collect?

The garbage collector (GC) in Java determines which objects to collect through a process known as reachability analysis. Here’s how it works:

1. Root Set Identification
The GC starts by identifying a set of "root" references. These roots include:

- Active threads and their stack frames.
- Static variables in loaded classes.
- Local variables in methods currently executing.
- JNI (Java Native Interface) references.

2. Reachability Analysis
From the root set, the GC performs a traversal of the object graph:

- It follows references from root objects to other objects, marking them as reachable.
- Any object that can be accessed directly or indirectly from the root set is considered reachable.

3. Unreachable Objects
   Any object that is not reachable from the root set is considered garbage and eligible for collection. This means that there are no live references pointing to these objects.

4. Mark-and-Sweep Algorithm

Many garbage collectors use a mark-and-sweep algorithm:

- Mark Phase: The GC traverses the object graph starting from the root set, marking all reachable objects.
- Sweep Phase: After marking, the GC goes through the heap and collects (deletes) all unmarked objects, reclaiming their memory.

5. Generational Garbage Collection

Java uses a generational garbage collection approach:
- Young Generation: Newly created objects are allocated here. Most objects die young, so this area is collected frequently.
- Old Generation: Objects that survive multiple collections in the young generation are promoted here. This area is collected less frequently.
- The GC can optimize collection based on the age of objects, as older objects are less likely to be garbage.

6. Garbage Collector Algorithms
Different garbage collectors (like G1, CMS, and ZGC) implement various algorithms and strategies for memory management, but they all fundamentally rely on reachability analysis to determine which objects can be collected.

## 16. What are the different types of references in Java?

A reference is information about the location of an object. A reference is stored in the stack memory and the actual object is stored in the Heap memory of the JVM.

1. Strong Reference
By default, when you create an object and directly point it to a variable, then it holds a strong reference to the object. Any object which has a strong reference is not eligible for garbage collection. The object is garbage collected only when the variable points to null or is no more reachable or referenced by any threads.
   
![strong-ref.jpg](..%2Fresources%2Fstrong-ref.jpg)

2. Weak Reference
If an object has a weak reference, it will be swept out of the memory during the garbage collection even if there is enough memory available in the heap.

We can create a weak reference using the classWeakReference

A WeakReference is useful for implementing an in-memory cache-like data structure using a HashMap, with keys of the type WeakReference. Then the entries which are not more referenced will be garbage collected during the next GC cycle.
```java
import java.lang.ref.WeakReference;

//...

WeakReference<Person> weakPerson = new WeakReference<>(new Person("abc", 12));
System.out.println(weakPerson.get());    // com.test.Person@36baf30c

System.gc();
Thread.sleep(5000); // Allow some time for gc

System.out.println(weakPerson.get());    // null. The object is garbage collected!
```
3. Soft Reference

Objects with soft references will be retained in the memory during the garbage collection when there is enough memory available. They will be removed only when the JVM runs out of memory.
We can create a soft reference using the classSoftReference

```java
import java.lang.ref.SoftReference;

//...

SoftReference<Person> softPerson = new SoftReference<>(new Person("abc", 12));
System.out.println(softPerson.get());    // com.test.Person@36baf30c

System.gc();
Thread.sleep(5000); // Allow some time for gc


System.out.println(softPerson.get()); // com.test.Person@36baf30c
// The output depends on available memory in the JVM. If there is enough memory available, 
// then the object will be retained, otherwise the object will be removed during the GC and this will print 'null'
```

4. Phantom Reference
   The last type of reference is the Phantom reference. The objects which are referenced by phantom references are eligible for garbage collection. However, the JVM enqueues them in a queue called ‘reference queue’ before removing them from the memory. They are put in a reference queue after calling finalize() method on them.

There are two main use cases:

a). To determine when an object was removed from the memory which helps to schedule memory-sensitive tasks. For example, we can wait for a large object to be removed before loading another one.

b). To avoid using the finalize method and improve the finalization process.

We can create a phantom reference using the classPhantomReference

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
```java
//...

ReferenceQueue<Person> refQueue = new ReferenceQueue<>();
PhantomReference<Person> phantomPerson = new PhantomReference<>(new Person("abc", 12), refQueue);
System.out.println(phantomPerson.get());    // null. Phantom object is always inaccessible.
System.out.println(refQueue.poll()); // null. No elements in the ref-queue yet.

System.gc();
Thread.sleep(5000); // Allow some time for gc

System.out.println(phantomPerson.get()); // null
System.out.println(refQueue.poll()); // java.lang.ref.PhantomReference@36baf30c - Since the object is gc'd, it's now available in the refQueue.


```


## 17. How Do You Trigger Garbage Collection from Java Code?
You, as Java programmer, can not force garbage collection in Java; it will only trigger if JVM thinks it needs a garbage collection based on Java heap size.
Before removing an object from memory garbage collection thread invokes finalize()method of that object and gives an opportunity to perform any sort of cleanup required. You can also invoke this method of an object code, however, there is no guarantee that garbage collection will occur when you call this method.
Additionally, there are methods like System.gc() and Runtime.gc() which is used to send request of Garbage collection to JVM but it’s not guaranteed that garbage collection will happen.

## 18.  What Happens When There Is Not Enough Heap Space to Accommodate Storage of New Objects?
If there is no memory space for creating a new object in Heap, Java Virtual Machine throws OutOfMemoryError or more specifically java.lang.OutOfMemoryError heap space.

## 19. Suppose We Have a Circular Reference (Two Objects That Reference Each Other). Could Such Pair of Objects Become Eligible for Garbage Collection and Why?
Yes, a pair of objects with a circular reference can become eligible for garbage collection. This is because of how Java’s garbage collector handles circular references. It considers objects live not when they have any reference to them, but when they are reachable by navigating the object graph starting from some garbage collection root (a local variable of a live thread or a static field). If a pair of objects with a circular reference is not reachable from any root, it is considered eligible for garbage collection.




