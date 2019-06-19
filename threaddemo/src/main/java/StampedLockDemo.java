import java.util.concurrent.locks.StampedLock;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-06-19 14:24
 **/
public class StampedLockDemo {
    private final static StampedLock lock = new StampedLock();

    public static void main(String[] args) {
        long readLockCount = -1;
        try {
            readLockCount = lock.readLock();

        } finally {
            lock.unlock(readLockCount);

        }

        try {
            readLockCount = lock.writeLock();

        } finally {
            lock.unlock(readLockCount);

        }
    }
}
