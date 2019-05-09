import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(0);
        countDownLatch.await();

        System.out.println(System.currentTimeMillis());

        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        countDownLatch2.countDown();
        countDownLatch2.await(10, TimeUnit.SECONDS);


        CountDownLatch countDownLatch3 = new CountDownLatch(1);
        countDownLatch3.await(10, TimeUnit.SECONDS);

        System.out.println(System.currentTimeMillis());

        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("-s");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            System.out.println("-e");
        }).start();
        System.out.println("wait");
        latch.await();
        System.out.println("exit");
    }
}

