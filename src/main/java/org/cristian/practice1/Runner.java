package org.cristian.practice1;
/**
 * Diseña un programa que simule una carrera entre varios corredores.
 * Cada corredor debe ser representado por un hilo separado.
 * Cada corredor debe imprimir su progreso en la carrera
 * (por ejemplo, "Corredor 1: 10% completado", "Corredor 2: 20% completado", etc.)
 * cada cierto intervalo de tiempo. El primero en llegar al 100% completado será el ganador.
 * El programa debe imprimir un mensaje indicando el ganador al finalizar la carrera.
 */

public class Runner implements Runnable {
    private int progress;
    private final int number;

    public Runner(int number) {
        this.progress = 0;
        this.number = number;
    }

    @Override
    public void run() {
        while (progress < 100) {
            progress += 10;
            System.out.println(String.format("Runner %d: %d%% complete", number, progress));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getProgress() {
        return progress;
    }

    public int getNumber() {
        return number;
    }
}

