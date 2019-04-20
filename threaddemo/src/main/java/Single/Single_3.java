package Single;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: test
 * @description: demo 懒汉模式
 * @author: Mr.WG
 * @create: 2019-04-20 07:12
 **/
public class Single_3 {

    private  String name = "default";

    private Single_3() {
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Thread(()->name="new code").start();

        try {
            TimeUnit.MICROSECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Single {
        private static final Single_3 single3= new Single_3();
    }

    public static Single_3 getInstance() {
        return Single.single3;
    }

    public void print(int i) {
        System.out.println("my name=" + name + ",i=" + i);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(10);

        IntStream.range(0, 10).forEach(i -> {
            executorService.execute(() -> {
                Single_3.getInstance().print(i);
                latch.countDown();
            });
        });

        latch.await();
        System.out.println("exit");
        executorService.shutdown();
        executorService.shutdownNow();

    }
}
