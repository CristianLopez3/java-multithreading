package org.cristian.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Example2 {

    public static final int MAX_PASSWORD = 9999;

    public static void main(String[] args) {

        List<Thread> threadList = new ArrayList<>();
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));
        threadList.add(new AscendingHacker(vault));
        threadList.add(new DescendingHacker(vault));
        threadList.add(new Police());

        for(Thread thread: threadList) {
            thread.start();
        }

    }

    private record Vault(int password) {

        public boolean isCorrect(int guess){
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {
            }
            return this.password == guess;
        }

    }


    private abstract static class HackerThread extends Thread {
        protected final Vault vault;
        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }
        @Override
        public void start() {
            System.out.println("Starting thread: " + this.getName());
            super.start();
        }
    }

    private static class AscendingHacker extends HackerThread {
        public AscendingHacker(Vault vault) {
            super(vault);
        }
        @Override
        public void run() {for (int i = 0; i < MAX_PASSWORD; i++) {
                if (vault.isCorrect(i)) {
                    System.out.println(this.getName() + " guessed the password: " + i);
                    System.exit(0);
                }
            }
        }
    }

    private static class  DescendingHacker extends HackerThread {

        public DescendingHacker(Vault vault) {
            super(vault);
        }

        @Override
        public void run(){
            for(int i = MAX_PASSWORD; i >= 0; i--) {
                if (vault.isCorrect(i)) {
                    System.out.println(this.getName() + " guessed the password: " + i);
                    System.exit(0);
                }
            }
        }
    }

    private static class Police extends Thread {
        @Override
        public void run(){
            for(int i = 10; i > 0; i--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                System.out.println(i);
            }
            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }



}
