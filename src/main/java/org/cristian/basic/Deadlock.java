package org.cristian.basic;

import java.util.Random;
import java.util.TreeMap;

public class Deadlock {
    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        Thread trainAThread = new Thread(new TrainA(intersection));
        Thread trainBThread = new Thread(new TrainB(intersection));

        trainAThread.start();
        trainBThread.start();

    }

    private static class TrainA implements Runnable {
        private Intersection intersection;
        private Random random = new Random();

        public TrainA(Intersection intersection) {
            this.intersection = intersection;
        }

        @Override
        public void run() {
            while(true){
                long sleepingTime = random.nextInt(5);
                try{
                    Thread.sleep(sleepingTime);
                } catch(InterruptedException ignored){}

                intersection.takeRoadA();
            }
        }
    }

    private static class TrainB implements Runnable {
        private Intersection intersection;
        private Random random = new Random();

        public TrainB(Intersection intersection) {
            this.intersection = intersection;
        }

        @Override
        public void run() {
            while(true){
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                }catch (InterruptedException ignored){}

                intersection.takeRoadB();
            }
        }
    }

    private static class Intersection {
        Object roadA = new Object();
        Object roadB = new Object();

        public void takeRoadA() {
            synchronized (roadA) {
                System.out.println("Road B is locked by thread: " + Thread.currentThread().getName());
                synchronized (roadB) {
                    System.out.println("Train is passing through road A");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ignored) {
                    }
                }

            }
        }

        public void takeRoadB() {
            synchronized (roadA) {
                System.out.println("Road B is locked by thread: " + Thread.currentThread().getName());
                synchronized (roadB){
                    System.out.println("Train is passing through road B");
                    try{
                        Thread.sleep(1);
                    }catch (InterruptedException ignored){
                    }
                }
            }
        }

    }

}
