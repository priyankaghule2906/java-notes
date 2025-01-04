package org.example.threads;

public class VolatileVsSynchronized {

    private volatile int volatileCounter = 0;
    private int synchronizedCounter = 0;

    public void incrementVolatileCounter() {
        volatileCounter++; // Not atomic, can lead to race conditions
    }

    public synchronized void incrementSynchronizedCounter() {
        synchronizedCounter++; // Atomic, protected by a lock
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileVsSynchronized example = new VolatileVsSynchronized();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                example.incrementVolatileCounter();
                example.incrementSynchronizedCounter();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                example.incrementVolatileCounter();
                example.incrementSynchronizedCounter();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Volatile Counter: " + example.volatileCounter); // Likely less than 20000 due to race conditions
        System.out.println("Synchronized Counter: " + example.synchronizedCounter); // Always 20000
    }
}