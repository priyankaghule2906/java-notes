package org.example.threads;

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
