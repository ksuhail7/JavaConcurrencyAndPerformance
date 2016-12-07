package completablefutures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by suhail on 2016-12-07.
 */
public class Main {
    public static class IntegerGenerator {
        private static final Random random = new Random();

        public int getInt(int scale) {
            int delay = 1 + random.nextInt(5);
            try {
                Thread.sleep(delay * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return random.nextInt(scale);
        }
    }

    public static void main(String[] args) {
        IntegerGenerator generator = new IntegerGenerator();

        List<Integer> evenIntegers = new ArrayList<>();
        List<Integer> oddIntegers = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> generator.getInt(100), executorService);
            future.thenAccept(result -> {
                if(result % 2 == 0)
                    evenIntegers.add(result);
                else
                    oddIntegers.add(result);
                System.out.println("Even list -" + evenIntegers);
                System.out.println("Odd list - " + oddIntegers);
            });

            //wait for future to complete
        }
        try {
            executorService.shutdown();
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
