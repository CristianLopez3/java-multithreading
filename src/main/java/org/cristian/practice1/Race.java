package org.cristian.practice1;


import java.util.ArrayList;
import java.util.List;

public class Race {
    private final List<Runner> runners;
    private final List<Thread> threads;

    public Race(List<Runner> runners) {
        this.runners = runners;
        this.threads = new ArrayList<>();
    }

    public void startRace() throws InterruptedException {

        for (Runner runner : runners) {
            threads.add(new Thread(runner));
        }

        for (Thread thread : threads) {
            thread.start();
        }


        for (Thread thread : threads) {
            thread.join();
        }


        for (Runner runner : runners) {
            if (runner.getProgress() == 100) {
                System.out.println(String.format("Runner %d wins the race!!!", runner.getNumber()));
                break;
            }
        }

    }
}
