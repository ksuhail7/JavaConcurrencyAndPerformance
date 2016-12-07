package syncdata;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by suhail on 2016-12-06.
 */
public class StatsCounter {
    private int successCount;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public final int getSuccessCount() {
        readLock.lock();
        try {
            return successCount;
        } finally {
            readLock.unlock();
        }
    }

//    public final synchronized void increaseSuccessCount(int delta) {
//        successCount += delta;
//    }

    public final void increaseSuccessCount(int delta) {
        writeLock.lock();
        try {
            successCount += delta;
        } finally {
            writeLock.unlock();
        }
    }
}
