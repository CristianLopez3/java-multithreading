package org.cristian.basic;

import java.math.BigInteger;

public class Interrupt {
    public static void main(String[] args) {
        NewThread thread = new NewThread();
        thread.start();
        try {
            Thread.sleep(1000); // Espera un poco antes de interrumpir el hilo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt(); // Interrumpe el hilo
        System.out.println(thread.bow(new BigInteger("200000"), new BigInteger("200000")));
    }

    private static class NewThread extends Thread {
        @Override
        public void run(){
            System.out.println("Running thread...");
        }

        public BigInteger bow(BigInteger base, BigInteger expo){
            BigInteger result = BigInteger.ONE;
            for(int i = 1; i <= expo.intValue(); i++){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Thread interrupted");
                    return BigInteger.ZERO;
                }
                result =  result.multiply(base);
            }
            return result;
        }

    }

}