package syncdata;

import java.util.logging.Logger;

/**
 * Created by suhail on 2016-12-06.
 */
public class Program {
    private static final Logger logger = Logger.getLogger("Program");
    public static final int threadCount = 100;
    public static final int iterationCount = 1000000;

    public static void main(String[] args) {
        final StatsCounter c = new StatsCounter();

        Thread[] threads = new Thread[threadCount];

        for (int tnum = 0; tnum < threadCount; tnum++) {
            threads[tnum]= new Thread(() -> {
                for (int d = 0; d < iterationCount; d++) {
                    c.increaseSuccessCount(d);
                    c.increaseSuccessCount(-d);
                }
            });
        }

        long startTime = System.currentTimeMillis();
        for(Thread t : threads) {
            t.start();
        }

        for(Thread t: threads) {
            try {
                t.join();
            } catch (InterruptedException intEx) {
                logger.severe("caught exception: " + intEx.getMessage());
            }
        }
        long endTime = System.currentTimeMillis();
        logger.info(String.format("Result was %d and it completed in %f seconds",
                c.getSuccessCount(),
                (endTime - startTime)/1000.0));
    }
}
