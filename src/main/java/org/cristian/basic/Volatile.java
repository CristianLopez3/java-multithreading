package org.cristian.basic;

public class Volatile {

    public static void main(String[] args) {
        SharedClass sharedClass = new SharedClass();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.checkDataRace();
            }
        });

        thread1.start();
        thread2.start();
    }

    public static class SharedClass {
        private volatile int x = 0;
        private volatile int y = 0;

        public void increment() {
            x++;
            y++;
        }

        public void checkDataRace() {
            /**
             *  y is executed after x, always if y is executed before x, this is a data race because y
             *  is going to be greater than x
             *  y never greater than x.
             */
            if (y > x) {
                System.out.println("y > x Data race detected.");
            }
        }

    }
}
