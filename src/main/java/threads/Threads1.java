package threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * Write  a program that increments a counter once per second, and <i>start</i> this program on a thread.
 * In the main thread read a single line of data from STDIN and then interrupt the counter thread so that it terminates.
 * Add println statements so that you can verify the behavior of the program.
 *
 * For the initial version, using a named sub class of <i>Thread</i>
 *
 * Created by suhail on 2016-12-06.
 */
public class Threads1 {
    private static final Logger logger = Logger.getLogger("Threads1");
    public static class CounterThread extends Thread {
        private int counter = 0;
        @Override
        public void run() {
            while(true) {
                try {
                    counter++;
                    logger.info("Counter: " + counter);
                    Thread.sleep(1000);
                } catch (InterruptedException intEx) {
                   // Thread.currentThread().interrupt();
                    break;

                }
            }
            logger.info("thread going to terminate");
        }
    }

    public static void main(String[] args) {
        CounterThread thread = new CounterThread();
        thread.start();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            logger.info("Enter 'q' to quit:");
            String input = br.readLine();
            if ("q".equalsIgnoreCase(input.trim())) {

                System.exit(1);
            }
        } catch(IOException ioe) {
            logger.info("caught io exception: " + ioe.getMessage());
            System.exit(1);
        }
            finally
        {
            if(br!= null) {
                try {
                    br.close();
                } catch(IOException ioe) {
                    logger.info("caught io exception: " + ioe.getMessage());
                    System.exit(1);
                }
            }
        }
    }
}
