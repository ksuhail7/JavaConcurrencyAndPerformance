package javaconcurrency;

import java.util.Random;

/**
 * Created by suhail on 2016-12-07.
 */
public class Main {
    static final int TASK_COUNT = 20;

    public static void main(String[] args) {
        Random random = new Random();
        View view = new View();
        Engine engine = new Engine();
        View.println("Starting...");
        for (int i = 0; i < TASK_COUNT; i++) {
            engine.dispatch(new Task(i, random.nextInt(Task.LIMIT), view), random
            .nextInt(Task.LIMIT));
        }
        engine.shutdown();
        view.displayResults();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {


        }
        View.println("Done active threads=" + Thread.activeCount());

    }
}
