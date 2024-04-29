package org.cristian.basic;

public class Example1 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Hello from: " + Thread.currentThread().getName());
        });

        System.out.println("Before running threads, in thread: " + Thread.currentThread().getName());
        thread.start();

        Thread.sleep(1000);
    }

}
