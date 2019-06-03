import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * @program: test
 * @description: CyclicBarrier
 * @author: Mr.WG
 * @create: 2019-04-18 15:41
 *
 * 等待所有人准备好在开始
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException, TimeoutException {
        int count = 10;
        /***
         * 等待所有就绪继续,10次
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        CyclicBarrierDemo.await(count, cyclicBarrier);

        /***
         * 在等待所有就绪继续,10次
         */
        TimeUnit.SECONDS.sleep(5);
        CyclicBarrierDemo.await(count, cyclicBarrier);

        TimeUnit.SECONDS.sleep(5);
        System.out.println("exit-1");

        cyclicBarrier.await(5, TimeUnit.SECONDS);

        System.out.println("exit-2");
    }

    public static void await(int count, CyclicBarrier cyclicBarrier) {

        IntStream.range(0, count).forEach(i -> {
            new Thread(() -> {
                try {
                    System.out.println("wait:" + i);
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
//                System.out.println("ok" + Thread.currentThread().getName());
            }).start();
        });
    }
}
