import java.util.concurrent.atomic.AtomicInteger;
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

    }
}
