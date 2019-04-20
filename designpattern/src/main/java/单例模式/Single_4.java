package 单例模式;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: test
 * @description: demo4
 * @author: Mr.WG
 * @create: 2019-04-20 07:31
 **/
public class Single_4 {
    private Single_4() {
        new Thread(() -> {
            name = "new code";
            System.out.println(name);
        }).start();
    }

    private String name = "default";

    private enum Single {
        Instance;

        private Single_4 single_4;

        Single() {
            single_4 = new Single_4();
        }

        public Single_4 getInstance() {

            return single_4;
        }
    }

    public void print(int i) {
        System.out.println("my name=" + name + ",i=" + i);
    }

    public static Single_4 getInstance() {
        return Single.Instance.getInstance();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(10);

        IntStream.range(0, 10).forEach(i -> {
            executorService.execute(() -> {
                Single_4.getInstance().print(i);
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        });

        latch.await();
        System.out.println("exit");
        executorService.shutdown();
        executorService.shutdownNow();
    }
}
