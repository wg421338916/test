import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        /***
         * 等待所有就绪继续
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        IntStream.range(0, 2).forEach(i -> {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println("ok" + Thread.currentThread().getName());
            }).start();
        });

        TimeUnit.SECONDS.sleep(2);
    }
}
