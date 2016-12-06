package syncexec;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by suhail on 2016-12-06.
 */
public class SyncExecCycBarrier {

    public static class SimpleTaskExecutor implements Runnable {
        private int threadId;
        private CyclicBarrier cyclicBarrier;

        public SimpleTaskExecutor(int id, CyclicBarrier barrier, CountDownLatch latch) {
            this.threadId = id;
            this.cyclicBarrier = barrier;
        }
        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                Thread.sleep((threadId + 1) * 1000L);
            } catch (InterruptedException intEx) {
                intEx.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        int noOfThreads = 5;
        Runnable callback = () -> System.out.println("barrier reached");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(noOfThreads, callback);
    }
}
