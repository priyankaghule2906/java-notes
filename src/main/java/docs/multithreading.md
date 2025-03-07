# MultiThreading and Concurrency

1. What is Multithreading in Java?
2. What is the difference between a process and a thread?
3. How can we create a Thread in Java?
4. What is synchronization, and why is it important?
5. What are the different states of a thread in Java?
6. What is the difference between synchronized and volatile keywords in Java?
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
6. TERMINATED : A thread is in the Terminated state when it has completed its execution, either normally (after the run() method finishes) or abruptly (due to an exception).

Programmatically identifying thread states:

You can programmatically identify the state of a thread using the getState() method of the Thread class. This method returns a Thread.State enum constant representing the current state of the thread.

```java
public class ThreadStateExample {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(() -> {
            try {
                Thread.sleep(5000); // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        System.out.println("Thread state before start: " + myThread.getState()); // Output: NEW

        myThread.start();
        System.out.println("Thread state after start: " + myThread.getState()); // Output: RUNNABLE

        Thread.sleep(1000);
        System.out.println("Thread state after sleeping for a while: " + myThread.getState()); // Output: TIMED_WAITING

        myThread.join(); // Wait for the thread to finish
        System.out.println("Thread state after join: " + myThread.getState()); // Output: TERMINATED
    }
}

// op

/*
Thread state before start: NEW
Thread state after start: RUNNABLE
Thread state after sleeping for a while: TIMED_WAITING
Thread state after join: TERMINATED

Process finished with exit code 0
 * 
 * */
```
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
## What is the difference between synchronized and volatile keywords in Java?

synchronized:

Purpose: Primarily used for controlling access to shared resources and preventing race conditions. It ensures that only one thread can execute a synchronized block or method at a time, providing mutual exclusion.  

Mechanism: Uses intrinsic locks (monitors) associated with objects. When a thread enters a synchronized block or method, it acquires the lock. Other threads attempting to acquire the same lock will be blocked until the first thread releases it.  

Visibility: Ensures visibility of changes to shared variables across threads. When a thread exits a synchronized block, it flushes its changes to main memory. When a thread enters a synchronized block, it reads the latest values from main memory. This is a side effect of locking, not its primary purpose.

Atomicity: Provides atomicity for the synchronized block or method. Operations within the synchronized block are treated as a single, indivisible unit.

Performance: Can have a performance overhead due to lock contention and context switching.  

Usage: Can be applied to methods (instance and static) and blocks of code.   

volatile:

Purpose: Primarily used to ensure visibility of changes to a shared variable across threads. It tells the JVM that the variable may be modified by multiple threads, and therefore, it should not cache the variable's value in thread-local memory.  

Mechanism: Works by instructing the JVM to read the variable's value directly from main memory and write changes directly back to main memory, bypassing the CPU cache. This ensures that all threads see the most up-to-date value.  

Visibility: Guarantees visibility of changes to the volatile variable. Any write to a volatile variable by one thread is immediately visible to all other threads.  

Atomicity: Does not provide atomicity for compound operations (e.g., incrementing a variable: count++ is not atomic). It only guarantees atomicity for simple reads and writes of the variable itself.

Performance: Generally has lower overhead than synchronized because it avoids locking.

Usage: Can only be applied to instance and static variables.

When to use which:

Use synchronized when you need to protect a block of code or a method that performs multiple operations on shared data and requires atomicity. It is also necessary when you need to prevent race conditions involving complex state changes.
Use volatile when you need to ensure visibility of a single shared variable across threads, and you don't need atomicity for compound operations. It is useful for simple flags or status indicators that are frequently read by multiple threads.

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
Volatile is a modifier used with variables including both primitive and object reference. The volatile keyword is used to indicate that a variable's value will be modified by different threads.
Declaring a variable as volatile ensures that any read or write to that variable is directly done from the main memory, rather than from a thread's local cache.
This guarantees visibility of changes across threads.

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

# What is a thread pool, and why is it used?
A thread pool is a collection of pre-created, reusable threads that can execute tasks concurrently. Instead of creating a new thread every time a task needs to run, the thread pool assigns one of its existing threads to handle the task. Once the task is completed, the thread is returned to the pool, ready for reuse.

In Java, thread pools are part of the java.util.concurrent package and are managed by an ExecutorService, which provides methods to create and manage thread pools.

Why is a Thread Pool Used?
a. Reduces Thread Creation Overhead
Creating a thread is resource-intensive. A thread pool avoids the cost of creating and destroying threads repeatedly by reusing existing threads.

b. Controls Concurrency
Thread pools allow you to limit the number of threads running concurrently, which helps prevent excessive resource consumption (like CPU or memory) and avoids problems like thread starvation or out-of-memory errors.

c. Improves Performance
Tasks are executed faster because threads are pre-created and ready for use, reducing the latency associated with thread creation.

d. Simplifies Thread Management
Developers don’t need to manage thread lifecycles (e.g., starting or stopping threads) manually. The thread pool abstracts these complexities, allowing you to focus on task execution.

# Four types of thread pool in java

1. Fixed Thread Pool : A pool with a fixed number of threads.
   Suitable when the number of concurrent tasks is predictable.
2. Cached Thread Pool : A pool with an unlimited number of threads, which dynamically grows or shrinks based on the workload.
   Suitable for short-lived, lightweight tasks.
3. Scheduled Thread Pool : A pool for scheduling tasks to run after a delay or at fixed intervals.
4. Single Thread Executor : A pool with a single thread that executes tasks sequentially.
   Useful when tasks must be executed in order.

```java
ExecutorService executor = Executors.newFixedThreadPool(5);

ExecutorService executor = Executors.newCachedThreadPool();

ExecutorService executor = Executors.newSingleThreadExecutor();

ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
executor.schedule(() -> {
        System.out.println("Task executed after delay");
}, 2, TimeUnit.SECONDS);
executor.shutdown();

```

difference between  ReentrantLock and synchronized

1) difference between ReentrantLock and the synchronized keyword is fairness. The synchronized keyword doesn't support fairness. Any thread can acquire lock once released, no preference can be specified, on the other hand, you can make ReentrantLock fair by specifying fairness property while creating an instance of ReentrantLock. Fairness property provides a lock to the longest waiting thread, in case of contention.

2) The second difference between synchronized and Reentrant lock is tryLock() method. ReentrantLock provides a convenient tryLock() method, which acquires lock only if its available or not held by any other thread. This reduces the blocking of thread waiting for lock-in Java applications.

3) One more worth noting the difference between ReentrantLock and synchronized keyword in Java is, the ability to interrupt Thread while waiting for Lock.

In case of a synchronized keyword, a thread can be blocked waiting for a lock, for an indefinite period of time and there was no way to control that.

ReentrantLock provides a method called lockInterruptibly(), which can be used to interrupt thread when it is waiting for lock. Similarly, tryLock() with timeout can be used to timeout if the lock is not available in certain time period.

4) ReentrantLock also provides convenient method to get List of all threads waiting for lock.




3. what is deadlock

A deadlock is a situation in which two or more threads are blocked forever, waiting for each other to release resources. 
This happens when multiple threads hold some resources and wait for others, leading to a circular wait condition.

## Example of Deadlock in Java
Here’s a simple Java example that demonstrates a deadlock:
```java
class Resource {
    private final String name;

    public Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class DeadlockExample {
    public static void main(String[] args) {
        Resource resource1 = new Resource("Resource1");
        Resource resource2 = new Resource("Resource2");

        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1 locked " + resource1.getName());

                // Simulating some work
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (resource2) {
                    System.out.println("Thread 1 locked " + resource2.getName());
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2 locked " + resource2.getName());

                // Simulating some work
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (resource1) {
                    System.out.println("Thread 2 locked " + resource1.getName());
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

```
```text
Thread 1 locks resource1 and then tries to lock resource2, but resource2 is already held by Thread 2.
Thread 2 locks resource2 and then tries to lock resource1, but resource1 is already held by Thread 1.
Both threads keep waiting for each other to release resources, causing a deadlock.
```
## How to Prevent Deadlocks?
1. Lock Ordering: Always acquire locks in the same order
```java
synchronized (resource1) {
    synchronized (resource2) {
        // Work
    }
}

```
2. Try-Lock Mechanism (Using ReentrantLock)

```java
boolean locked = lock1.tryLock();
if (locked) {
    try {
        if (lock2.tryLock()) {
            try {
                // Work
            } finally {
                lock2.unlock();
            }
        }
    } finally {
        lock1.unlock();
    }
}

```
3. Timeouts in Locking: Use tryLock(timeout, TimeUnit.SECONDS) instead of lock().

# What is a Race Condition?
A race condition occurs when multiple threads access and modify a shared resource concurrently, leading to unexpected or inconsistent results. This happens when the execution order of threads affects the outcome.

Example of Race Condition in Java
Let’s look at a scenario where two threads increment a shared counter:

```java
class SharedCounter {
    private int count = 0;

    public void increment() {
        count++;  // Not thread-safe!
    }

    public int getCount() {
        return count;
    }
}

public class RaceConditionExample {
    public static void main(String[] args) {
        SharedCounter counter = new SharedCounter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Counter Value: " + counter.getCount());
    }
}

```

```text
Expected vs. Actual Behavior
Expected: The final counter value should be 2000.
Actual: Due to a race condition, the output is often less than 2000 because both threads might read and update the count at the same time, overwriting each other’s changes.

Why Does This Happen?
The count++ operation is not atomic. It consists of three steps:

Read the value of count.
Increment the value.
Store the incremented value back.
If two threads execute these steps simultaneously, they might read the same old value before updating, leading to lost updates.
```

## How to Prevent Race Conditions?
1. Use synchronized Keyword 
2. Use AtomicInteger : AtomicInteger ensures atomic updates without requiring explicit synchronization.
3. Use ReentrantLock

# What is a CountDownLatch in Java?
A CountDownLatch is a synchronization aid in Java’s java.util.concurrent package that blocks a set of threads until another set of operations is completed.

It maintains a count, and once the count reaches zero, all waiting threads are released.
Used in concurrent programming when you need one or multiple threads to wait for a certain number of tasks to complete before proceeding.

## Example Scenario
Imagine a race where multiple runners must wait for the starter to give the signal. The starter counts down from 3 (CountDownLatch), and when the count reaches zero, all runners start running.

## Example of CountDownLatch
```java
import java.util.concurrent.CountDownLatch;

class Worker extends Thread {
    private CountDownLatch latch;

    public Worker(CountDownLatch latch, String name) {
        super(name);
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(getName() + " is working...");
            Thread.sleep(2000); // Simulate some work
            System.out.println(getName() + " finished work.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown(); // Decrease count
        }
    }
}

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int numberOfWorkers = 3;
        CountDownLatch latch = new CountDownLatch(numberOfWorkers);

        // Create and start worker threads
        for (int i = 1; i <= numberOfWorkers; i++) {
            new Worker(latch, "Worker-" + i).start();
        }

        // Main thread waits for all workers to finish
        latch.await();
        System.out.println("All workers finished. Main thread proceeds.");
    }
}

/*
 * Worker-1 is working...
Worker-2 is working...
Worker-3 is working...
Worker-1 finished work.
Worker-2 finished work.
Worker-3 finished work.
All workers finished. Main thread proceeds.

 * */
```

How Does CountDownLatch Work?
Initialize CountDownLatch with a count (e.g., new CountDownLatch(3)).
Worker threads call countDown() when they complete their task.
Main thread calls await() to block execution until the count reaches zero.
Once the count reaches zero, all waiting threads are released.

# What is a Semaphore in Java?
A Semaphore is a concurrency control mechanism in Java’s java.util.concurrent package that restricts the number of threads that can access a resource at the same time.

- It is often used to limit access to a critical section or resource (like a database connection pool or a file).
- It maintains a permit count that determines how many threads can acquire access simultaneously.

## Example Scenario
Imagine a parking lot with 3 parking spots. Only 3 cars (threads) can park at a time. When one car leaves, another can enter.

```java
import java.util.concurrent.Semaphore;

class ParkingLot {
    private final Semaphore semaphore;

    public ParkingLot(int slots) {
        this.semaphore = new Semaphore(slots); // Allows only `slots` threads at a time
    }

    public void parkCar(String carName) {
        try {
            System.out.println(carName + " is trying to park.");
            semaphore.acquire(); // Request a permit
            System.out.println(carName + " has parked.");
            Thread.sleep(2000); // Simulating parking time
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(carName + " is leaving.");
            semaphore.release(); // Release permit
        }
    }
}

public class SemaphoreExample {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(3); // Only 3 cars can park at a time

        Runnable car = () -> lot.parkCar(Thread.currentThread().getName());

        for (int i = 1; i <= 6; i++) { // 6 cars trying to park
            new Thread(car, "Car-" + i).start();
        }
    }
}

/*
 * 
 * Car-1 is trying to park.
Car-2 is trying to park.
Car-3 is trying to park.
Car-1 has parked.
Car-2 has parked.
Car-3 has parked.
Car-4 is trying to park.
Car-5 is trying to park.
Car-6 is trying to park.
Car-1 is leaving.
Car-4 has parked.
Car-2 is leaving.
Car-5 has parked.
Car-3 is leaving.
Car-6 has parked.
Car-4 is leaving.
Car-5 is leaving.
Car-6 is leaving.
* 
* Here, only 3 cars can park at a time, while the rest wait.
*/
```