import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(0);
        countDownLatch.await();

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
