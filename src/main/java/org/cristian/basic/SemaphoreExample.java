package org.cristian.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new FileWritingTask("Hello from thread 1"));
        Thread thread2 = new Thread(new FileWritingTask("Hello from thread 2"));

        thread1.start();
        thread2.start();

    }

    static class FileWritingTask implements Runnable {

        private Semaphore semaphore = new Semaphore(1);
        private String message;

        public FileWritingTask(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            try{
                semaphore.acquire();
                writeToFile();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch(IOException e){
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }

        private void writeToFile() throws IOException {
            try (PrintWriter out = new PrintWriter(new FileWriter("resources/output.txt", true))){
                out.println(message);
            }
        }

    }

}
