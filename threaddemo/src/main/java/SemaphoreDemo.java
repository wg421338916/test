import jdk.nashorn.internal.objects.annotations.Function;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-06-04 16:23
 **/
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        int s = 0;
        Semaphore semaphore = new Semaphore(2);
        while (true) {
            List<String> list = new ArrayList<>();
            list.add("1");
            semaphore.acquire();

            new Thread(() -> {
                try {
                    list.forEach(t -> {
                        System.out.println(t);
                    });
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }).start();

            s++;
            if (s > 10)
                break;
        }

//        semaphore.acquire();
//        semaphore.release();
//
//        semaphore.acquire();
//        semaphore.release();
//
//        semaphore.acquire();
//        semaphore.release();
//
//        IntStream.range(0, 10).forEach(i -> {
//            new Thread(() -> {
//                try {
//                    //System.out.println("w1"+new Date());
//                    semaphore.acquire();
//                    System.out.println("w1" + new Date());
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//
//                } finally {
//                    semaphore.release();
//                    System.out.println("r" + new Date());
//                }
//            }).start();
//        });
        TimeUnit.SECONDS.sleep(1000000);

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

        public static void lockAll() throws InterruptedException {
            semaphore.acquire(2);
//            semaphore.drainPermits();
        }

        public static void unLockAll() throws InterruptedException {
            semaphore.release(2);
        }

        public static void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public static void unLock() {
            semaphore.release();
        }

        public void trySth() throws InterruptedException {
            semaphore.tryAcquire(1, TimeUnit.SECONDS);
        }
    }
}
