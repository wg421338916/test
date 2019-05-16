import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-16 09:36
 **/
public class UnSafeDemo {

    interface Counter {
        void increment();

        Long getCounter();
    }

    public static class StupidCounter implements Counter {

        private Long num = 0l;

        @Override
        public void increment() {
            num++;
        }

        @Override
        public Long getCounter() {
            return num;
        }
    }

    public static class SyncCounter implements Counter {

        private Long num = 0l;

        @Override
        public synchronized void increment() {
            num++;
        }

        @Override
        public Long getCounter() {
            return num;
        }
    }

    public static class LockCounter implements Counter {

        private Long num = 0l;
        private Lock lock = new ReentrantLock();

        @Override
        public void increment() {
            try {
                lock.lock();
                num++;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public Long getCounter() {
            return num;
        }
    }

    public static class AtoCounter implements Counter {

        private AtomicLong num = new AtomicLong();

        @Override
        public void increment() {
            num.incrementAndGet();
        }

        @Override
        public Long getCounter() {
            return num.get();
        }
    }

    public static class CasCounter implements Counter {

        private volatile long num = 0l;
        private Unsafe unsafe;
        private Long offset;

        public CasCounter() {
            try {
                unsafe = getUnsafeLocal();
                offset = unsafe.objectFieldOffset(CasCounter.class.getDeclaredField("num"));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void increment() {
            long current = num;
            while (!unsafe.compareAndSwapLong(this, offset, current, current + 1)) {
                current = num;
            }
        }

        @Override
        public Long getCounter() {
            return num;
        }
    }

    public static class CounterRunable implements Runnable {
        private Counter counter;
        private Integer num;

        public CounterRunable(Counter counter, Integer num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i = 0; i < num; i++)
                this.counter.increment();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        Unsafe unsafe = getUnsafeLocal();
//
//        System.out.println(unsafe);

//        Counter counter = new AtoCounter();//耗时:2387,val:100000000
//        Counter counter = new StupidCounter();//耗时:2432,val:8936357
//        Counter counter = new SyncCounter();//耗时:5419,val:100000000
//        Counter counter = new LockCounter();//耗时:5430,val:100000000
        Counter counter = new CasCounter();//耗时:7463,val:100000000

        Long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(2000);
        for (int i = 0; i < 1000; i++)
            executorService.submit(new CounterRunable(counter, 100000));

        executorService.shutdownNow();
        executorService.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("耗时:" + (System.currentTimeMillis() - start) + ",val:" + counter.getCounter());
    }

    private static Unsafe getUnsafeLocal() throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        return (Unsafe) theUnsafe.get(null);
    }
}
