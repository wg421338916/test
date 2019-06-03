import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.stream.IntStream;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-09 15:17
 **/
public class AtomicIntegerDemo {
    public static void main(String[] args) {
        AtomicInteger num = new AtomicInteger();

        num.lazySet(1);
        num.lazySet(0);

        num.compareAndSet(0, 2);

        int andAdd = num.getAndAdd(1);
        System.out.println("-----------" + andAdd + "---------");

        andAdd = num.getAndSet(2);
        System.out.println("-----------" + andAdd + "---------");

        int i1 = num.incrementAndGet();
        System.out.println(i1);

        IntStream.range(0, 100).forEach(j -> {
            new Thread(() -> {
                for (int i = 0; i < 5; i++)
                    System.out.println(num.incrementAndGet());
            }).start();
        });

        AtomicIntergerLock lock = new AtomicIntergerLock();
        lock.tryLock();

        System.out.println("aaaaaaaaaaaaaaaa");

        lock.unLock();

    }
}


class AtomicIntergerLock {
    AtomicInteger atomicInteger = new AtomicInteger();
    Thread current;

    public void tryLock() {
        boolean b = atomicInteger.compareAndSet(0, 1);
        if (!b)
            throw new RuntimeException("");
        else
            current = Thread.currentThread();
    }

    public void unLock() {
        if (current == Thread.currentThread())
            atomicInteger.compareAndSet(1, 0);
    }
}

/**
 * @program: test
 * @description: aba的问题，加版本号
 * @author: Mr.WG
 * @create: 2019-05-15 10:02
 **/
class AtomicStampedReferenceDemo {
    static AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            reference.compareAndSet(100, 101,
                    reference.getStamp(), reference.getReference() + 1);

            System.out.println("1:" + reference.getReference());

            reference.compareAndSet(101, 100,
                    reference.getStamp(), reference.getReference() + 1);

            System.out.println("2:" + reference.getReference());
        });

        Thread t2 = new Thread(() -> {
            int stamp = reference.getStamp();

            System.out.println("version:" + stamp);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            reference.compareAndSet(100, 101,
                    stamp, stamp + 1);

            System.out.println("3:" + reference.getReference());

            reference.compareAndSet(100, 101,
                    reference.getStamp(), reference.getReference() + 1);

            System.out.println("4:" + reference.getReference());

        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

class AtomicIntegerFieldUpdaterDemo {

    public static void main(String[] args) {
        final AtomicIntegerFieldUpdater<TestMe> ato = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        // final AtomicReferenceFieldUpdater<TestMe, Integer> ato = AtomicReferenceFieldUpdater.newUpdater(TestMe.class, Integer.class, "i");
        final TestMe me = new TestMe();

        IntStream.range(0, 2).forEach(t -> {
            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    //ato.compareAndSet(me,i-1,i);
                    ato.incrementAndGet(me);
                    System.out.println(me.i);
                }
            }).start();
        });

        //System.out.println(me.i);
    }

    static class TestMe {
        volatile int i;
    }
}

