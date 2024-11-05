# MultiThreading and Concurrency

1. What is Multithreading in Java?
2. What is the difference between a process and a thread?
3. How can we create a Thread in Java?
4. What is synchronization, and why is it important?
5. What are the different states of a thread in Java?
6. What is ThreadLocal in java?
7. What is the difference between Thread class and Runnable interface?
8. How can you create a thread-safe singleton in Java?

## MultiThreading Concepts
1. What is Volatile
2. How to stop a thread
3. what is deadlock
4. what is livelock
5. what are atomic variables
6. what is semaphores
7. what is mutex
8. mutex and binary semaphores

## 1. What is Multithreading in Java?
Multithreading in Java is a feature that allows concurrent execution of two or more threads. A thread is a lightweight process, and multithreading enables the CPU to execute multiple threads simultaneously, improving application performance and resource utilization.

## 2. What is the difference between a process and a thread?

* **Process** : A process is an independent program that runs in its own memory space. It has its own resources and is isolated from other processes.

* **Thread**: A thread is a smaller unit of a process that can run independently but shares the same memory space and resources with other threads of the same process. Threads within the same process can communicate with each other more easily than processes.

## 3. How can we create a Thread in Java?
There are two ways to create Thread in Java - first by implementing Runnable interface and then creating a Thread object from it and second is to extend the Thread Class. 

1. Implementing the Runnable interface:

```java
class MyRunnable implements Runnable {
    public void run() {
        // Code to be executed in the thread
    }
}
Thread t = new Thread(new MyRunnable());
t.start();
```

2. Extending the Thread class:

```java
class MyThread extends Thread {
    public void run() {
        // Code to be executed in the thread
    }
}
MyThread t = new MyThread();
t.start();
```

## 4. What is synchronization, and why is it important?
Synchronization is a mechanism that ensures that only one thread can access a resource at a time. It is important to prevent thread interference and memory consistency errors when multiple threads are accessing shared resources. Java provides synchronized methods and blocks to achieve synchronization.

**Synchronized Method**
A synchronized method is a method that is declared with the synchronized keyword. When a thread invokes a synchronized method, it acquires the intrinsic lock (monitor) for the object (or class, if it’s a static method) that the method belongs to. This means that only one thread can execute that method on the same object at any given time.

Characteristics:
**Locking Scope**: The entire method is locked, preventing other threads from executing any synchronized methods of the same object.

**Ease of Use**: It is straightforward to implement, as you just add the synchronized keyword to the method signature.

**Performance**: It can lead to performance issues if the method contains a lot of code or if it is called frequently, as it locks the entire method.
```java
public class Example {
    private final Object lock = new Object();
    // Synchronized method
    public synchronized void syncMethod() {
        // critical section
    }
    // Synchronized block
    public void syncBlock() {
        synchronized (lock) {
            // critical section
        }
    }
}

```


**Synchronized Block**

A synchronized block allows you to specify a block of code that should be synchronized, rather than the entire method. You can specify the object whose lock you want to acquire, providing more granular control over synchronization.

Characteristics:

**Locking Scope**: Only the code within the synchronized block is locked, allowing other threads to execute non-synchronized code or other synchronized blocks.

**Flexibility**: You can synchronize on different objects or even on class-level locks (using ClassName.class), giving you more control over which resources are protected.

**Performance**: It can improve performance by reducing the scope of the lock, allowing other threads to access non-critical sections of code.

## 5. What are the different states of a thread in Java?

During its lifetime, a thread can be in the following states:

- NEW
- RUNNABLE (Ready or Running)
- WAITING
- TIMED WAITING
- BLOCKED
- DEAD or TERMINATED

1. NEW : When you create an instance of the Thread class, the thread is in the NEW state.
2. RUNNABLE :  A thread enters the Runnable state after calling the start() method. It is ready to run and waiting for CPU time. It can be in this state either because it is currently executing or waiting in the queue for CPU resources.
3. WAITING: a thread enters this state if it waits for another thread to perform a particular action. For instance, a thread enters this state upon calling the Object.wait() method on a monitor it holds, or the Thread.join() method on another thread
4. TIMED WAITING :  same as the above, but a thread enters this state after calling timed versions of Thread.sleep(), Object.wait(), Thread.join() and some other methods
5. BLOCKED :  A thread is in the Blocked state when it is trying to acquire a lock but another thread holds that lock. The thread will remain in this state until it can acquire the lock.
6. DEAD OR TERMINATED : A thread is in the Terminated state when it has completed its execution, either normally (after the run() method finishes) or abruptly (due to an exception).

## 5. Difference between Runnable and callable

In Java, both Runnable and Callable are interfaces designed for defining tasks that can be executed by threads, but they have some key differences.

1. Return Type

* Runnable: The run() method does not return a value. It has a void return type.
* Callable: The call() method returns a value. It can return any type of object and can also throw checked exceptions.
```java
public interface Runnable {
    void run();
}

public interface Callable<V> {
    V call() throws Exception;
}
```
2. Exception Handling

* Runnable: The run() method cannot throw checked exceptions. If a checked exception occurs, it must be handled within the run() method.
* Callable: The call() method can throw checked exceptions. This makes it easier to handle exceptions that may occur during the execution of the task.

3. Usage with Executor Framework
* Runnable: Runnable can be submitted to an ExecutorService using the execute() method, but it cannot return a result.
* Callable: Callable can be submitted to an ExecutorService using the submit() method, which returns a Future object that can be used to retrieve the result of the computation.

```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Running in Runnable");
    }
}

ExecutorService executor = Executors.newFixedThreadPool(1);
executor.execute(new MyRunnable());
executor.shutdown();

```

```java
class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 42; // Returning a result
    }
}

ExecutorService executor = Executors.newFixedThreadPool(1);
Future<Integer> future = executor.submit(new MyCallable());
Integer result = future.get(); // Retrieves the result
System.out.println("Result from Callable: " + result);
executor.shutdown();
```

## 6. What is ThreadLocal in java?
ThreadLocal is a class in Java that allows you to create variables that can only be read and written by the same thread. This can be useful in situations where you have multiple threads accessing the same variable, but you want to ensure that each thread has its own isolated copy of the variable
```java
public class ThreadLocalExample {

    // Creating a ThreadLocal variable
    private static final ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            // Set value for thread1
            threadLocal.set(1);
            System.out.println("Thread1 value: " + threadLocal.get());
        });

        Thread thread2 = new Thread(() -> {
            // Set value for thread2
            threadLocal.set(2);
            System.out.println("Thread2 value: " + threadLocal.get());
        });

        thread1.start();
        thread2.start();
    }
}
```
## 7. What is the difference between Thread class and Runnable interface?

* The Thread class represents a thread of execution, while Runnable is a functional interface that defines a task that can be executed by a thread.
* By implementing Runnable, you decouple the task from the actual thread of execution, which allows for better flexibility (e.g., thread pooling via ExecutorService). When extending the Thread class, you lose the ability to extend any other class due to Java's single inheritance.

## 8. How can you create a thread-safe singleton in Java?

A common way to create a thread-safe singleton is using the Bill Pugh Singleton Design with the static inner class. This method is both thread-safe and efficient:

You can also use synchronized blocks or the enum-based singleton approach for thread safety
```java
public class Singleton {
    private Singleton() {}
    private static class SingletonHelper {
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```

## MultiThreading Concepts
### 1. What is Volatile
Volatile is a modifier used with variables including both primitive and object reference. The volatile keyword is used to indicate that a variable's value will be modified by different threads. Declaring a variable as volatile ensures that any read or write to that variable is directly done from the main memory, rather than from a thread's local cache. This guarantees visibility of changes across threads.

```java
private volatile boolean running = true;
```
**Key Points:**

* Visibility: The volatile keyword ensures that changes made by one thread are visible to others.

* No Atomicity: While volatile guarantees visibility, it does not guarantee atomicity. For compound actions (like incrementing a counter), you would still need other synchronization mechanisms.

* Use Cases: volatile is often used for flags, state variables, or any scenario where a variable is shared between threads but does not require complex operations.

![volatile.png](..%2Fresources%2Fvolatile.png)


## 2. How to stop a thread
Stopping a thread in Java can be a bit tricky, as Java does not provide a direct method to stop a thread safely. The Thread.stop() method is deprecated because it can lead to inconsistent states in the application. Instead, a more controlled approach is recommended.

Recommended Approach: Using a Flag

The most common way to stop a thread is by using a flag. Here’s how you can do it:

* Define a volatile boolean flag to indicate whether the thread should continue running.

* Check the flag within the thread's run method, and break the loop or exit when the flag is set to false.

```java
class Worker implements Runnable {
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            // Simulate some work
            System.out.println("Thread is running...");
            try {
                Thread.sleep(500); // Sleep for a while
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                System.out.println("Thread was interrupted.");
            }
        }
        System.out.println("Thread has stopped.");
    }

    public void stop() {
        running = false; // Set the flag to false to stop the thread
    }
}

public class ThreadStopExample {
    public static void main(String[] args) throws InterruptedException {
        Worker stoppableThread = new Worker();
        Thread thread = new Thread(stoppableThread);

        thread.start(); // Start the thread

        // Let the thread run for a few seconds
        Thread.sleep(2000);

        // Stop the thread
        stoppableThread.stop();

        // Wait for the thread to finish
        thread.join();
        System.out.println("Main thread has finished.");
    }
}
```
3. what is deadlock