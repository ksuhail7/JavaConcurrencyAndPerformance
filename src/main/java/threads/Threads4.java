package threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Study the example of code in the course notes for handling uncaught exceptions.
 * Write a program, that starts 5 threads running the same code, and name the threads 1, 2,3 ,4,5.
 * Within the run method of the object have the fifth thread throw an unchecked exception immediately, and have the
 * other threadd sleep for 1 sec and then terminate cleanly. In the uncaught exception handler, have the thread sleep for 10 sec
 * and then terminate clenaly. Your main method should loop and call Thread.join on each thread in order.
 * You should see four threads join quickly and a short delay before the 5th one terminates.
 * Created by suhail on 2016-12-06.
 */
public class Threads4 {

    private static final Logger logger = LoggerFactory.getLogger(Threads4.class);

    public static class SpecialTaskRunner implements Runnable {

        private int threadId;

        public SpecialTaskRunner(int threadId) {
            this.threadId = threadId;
        }
        @Override
        public void run() {
            try {
                if(this.threadId == 5) {
                    logger.info("thread {} throwing unchecked exception", this.threadId);
                    throw new UncheckedIOException("some unchecked io exception", new IOException("some io error"));
                }
                logger.info("thread {} starting", this.threadId);
                Thread.sleep(threadId * 1000L);
                logger.info("thread {} completed", this.threadId);
            } catch (InterruptedException ioe) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        int noOfThreads = 5;
        Thread[] threads = new Thread[noOfThreads];
        for (int i = 0; i < noOfThreads; i++) {
            threads[i] = new Thread(new SpecialTaskRunner(i+1), Integer.toString(i + 1));
            threads[i].setUncaughtExceptionHandler((thread, throwable) -> {
                logger.info("exception thread {} caught", thread.getName());
                try {
                    logger.info("doing some cleanup for thread {}", thread.getName());
                    Thread.sleep(5*1000L);
                    logger.info("cleanup done");
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            });
        }

        for (int i = 0; i < noOfThreads; i++) {
            threads[i].start();
        }

        try {
            for (int i = 0; i < noOfThreads; i++) {
                threads[i].join();
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("process ended");
    }
}
