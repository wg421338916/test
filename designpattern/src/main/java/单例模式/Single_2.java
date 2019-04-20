package 单例模式;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @program: test
 * @description: demo 懒汉模式
 * @author: Mr.WG
 * @create: 2019-04-20 07:12
 **/
public class Single_2 {
    private volatile static Single_2 instance;
    private String name = "default";

    private Single_2() {
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Thread(() -> name = "new code").start();
        //1.run it
        //2.run it
        //3.run it
    }

    public static Single_2 getInstance() {
        if (instance == null) {
            synchronized (Single_2.class) {
                if (instance == null)
                    instance = new Single_2();
            }
        }

        return instance;
    }

    public void print(int i) {
        System.out.println("my name=" + name + ",i=" + i);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(10);

        IntStream.range(0, 10).forEach(i -> {
            executorService.execute(() -> {
                Single_2.getInstance().print(i);
                latch.countDown();
            });
        });

        latch.await();
        System.out.println("exit");
        executorService.shutdown();
        executorService.shutdownNow();

    }
}
