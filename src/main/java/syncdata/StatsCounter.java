package syncdata;

/**
 * Created by suhail on 2016-12-06.
 */
public class StatsCounter {
    private int successCount;

    public final int getSuccessCount() {
        return successCount;
    }

    public final synchronized void increaseSuccessCount(int delta) {
        successCount += delta;
    }
}
