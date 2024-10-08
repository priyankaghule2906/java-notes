# MultiThreading and Concurrency

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

## 6. 
