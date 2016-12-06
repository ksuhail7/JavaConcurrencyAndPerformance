package syncexec;

import java.util.concurrent.CountDownLatch;

/**
 * Created by suhail on 2016-12-06.
 */
public class SyncExec1 {

    public static class SimpleTaskExecutor implements Runnable {
        private final int threadId;
        private final CountDownLatch latch;

        public SimpleTaskExecutor(int threadId, CountDownLatch latch) {
            this.threadId = threadId;
            this.latch = latch;
        }

        public void run() {
            try {
                System.out.println("Thread " + threadId + " starting");
                Thread.sleep(threadId * 1000L);
                System.out.println("Thread " + threadId + " completed");
                latch.countDown();
            } catch(InterruptedException intEx) {
                System.err.println("thread [" + threadId + "] interrupted, msg: " + intEx.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        int noOfThreads = 5;
        CountDownLatch latch = new CountDownLatch(noOfThreads);
        for (int i = 0; i < noOfThreads; i++) {
            new Thread(new SimpleTaskExecutor(i, latch)).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {

        }
        System.out.println("Started all " + noOfThreads + " threads");
    }
}
