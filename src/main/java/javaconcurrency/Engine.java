package javaconcurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by suhail on 2016-12-07.
 */
public class Engine {
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    void dispatch(Task task, int param) {
        executorService.execute(() -> {
            task.execute(param);
        });
    }

    public void shutdown() {
        try {
            executorService.shutdown();
            boolean status = executorService.awaitTermination(50, TimeUnit.SECONDS);
            System.out.println("Executor service " + (status ? "shutdown successful" : "could not be shutdown"));
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
