package threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Uisng your solution to ques 2, add a getter method to the counter class so that you can retrieve the value
 * of the counter. Update your main thread so that after it interrupts the counter it also joins withthe counter thread.
 * After the join completes, use the get method to obtain the coutner value and print out the vlaue.
 * Note that you use join to ensure the child thread has completed before you can fetch the counter.
 * Created by suhail on 2016-12-06.
 */
public class Threads3 {

    private static final Logger logger = LoggerFactory.getLogger(Threads3.class);
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

        public int getCounter() {
            return this.counter;
        }
    }

    public static void main(String[] args) {
        Counter c = new Counter();
        Thread thread = new Thread(c);
        thread.start();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            logger.info("Enter 'q' to quit:");
            String input = br.readLine();
            if ("q".equalsIgnoreCase(input.trim())) {
                thread.interrupt();
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

        try {
            thread.join();
            System.out.println("Value of counter: " + c.getCounter());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
