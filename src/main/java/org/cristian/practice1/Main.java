package org.cristian.practice1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Runner runner1 = new Runner(1);
        Runner runner2 = new Runner(2);
        Runner runner3 = new Runner(3);
        Runner runner4 = new Runner(4);

        List<Runner> runners = List.of(runner1, runner2, runner3, runner4);

        Race race = new Race(runners);
        race.startRace();
    }


}
