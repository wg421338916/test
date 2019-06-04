import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-06-04 16:23
 **/
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        IntStream.range(0, 10).forEach(i -> {
            new Thread(() -> {
                try {
                    SeamphoreLock.lock();
                    System.out.println("come on" + i);
                    TimeUnit.SECONDS.sleep(5);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    SeamphoreLock.unLock();
                }
            }).start();
        });

        TimeUnit.SECONDS.sleep(100000);
    }

    static class SeamphoreLock {
        private static final Semaphore semaphore = new Semaphore(2);

        public static void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public static void unLock() {
            semaphore.release();
        }
    }
}
