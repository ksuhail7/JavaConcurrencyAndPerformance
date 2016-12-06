package threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * Now re-implement the program in question 1, build the counter class as an implementation of the Runnable interface
 * Created by suhail on 2016-12-06.
 */
public class Threads2 {

    private static final Logger logger = Logger.getLogger("Threads2");

    public static class Counter implements Runnable {

        private  int counter = 0;
        @Override
        public void run() {
            while(true) {
                try {
                    counter++;
                    logger.info("Counter: " + counter);
                    Thread.sleep(1000);
                } catch (InterruptedException intEx) {
                    break;

                }
            }
            logger.info("thread going to terminate");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Counter());
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
