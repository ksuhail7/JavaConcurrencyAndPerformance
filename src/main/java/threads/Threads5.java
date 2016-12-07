package threads;

/**
 * Write a short program where three threads increment the same int value and prints out the new value.
 * What do you observe?
 * Created by suhail on 2016-12-06.
 */

public class Threads5 {
    public static class SimpleCounter implements Runnable {
        private int counter;
        public SimpleCounter() {
            this.counter = 0;
        }
        public void increment() {
            counter = counter + 1;
        }

        public int getCounter() {
            return this.counter;
        }
        @Override
        public void run() {
            increment();
        }
    }

    public static void main(String[] args) {
        int noOfThreads = 3000;
        Thread[] threads = new Thread[noOfThreads];
        SimpleCounter counter = new SimpleCounter();
        for (int i = 0; i < noOfThreads; i++) {
            threads[i] = new Thread(counter);
        }

        for (int i = 0; i < noOfThreads; i++) {
            threads[i].start();
        }

        for (int i = 0; i < noOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Value of counter: " + counter.getCounter());
    }
}
