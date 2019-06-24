import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: test jdk1.7
 * @description: 替換 CountDownLatch CyclicBarrier
 * @author: Mr.WG
 * @create: 2019-06-24 16:21
 **/
public class PhaserDemo {
    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser();
        IntStream.rangeClosed(0, 10).boxed().map(i -> phaser).forEach(Task::new);
        phaser.register();
        phaser.arriveAndAwaitAdvance();
        System.out.println("exit");


        final Phaser phaser2 = new Phaser(6);
        for (int i = 1; i <= 5; i++) {
            new Task2(phaser2).start();
        }

        phaser2.arriveAndDeregister();
        System.out.println("exit2");

        TimeUnit.SECONDS.sleep(1000);

    }

    static class Task extends Thread {
        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
            this.phaser.register();
            this.start();
        }


        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("ok" + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                phaser.arriveAndAwaitAdvance();
                System.out.println("ok1" + System.currentTimeMillis());
            }
        }
    }

    static class Task2 extends Thread {
        private final Phaser phaser;

        public Task2(Phaser phaser) {
            this.phaser = phaser;
        }


        @Override
        public void run() {
            try {
                System.out.println("wait one..");
                phaser.arriveAndAwaitAdvance();

                System.out.println("wait two..");
                phaser.arriveAndAwaitAdvance();

                System.out.println("wait three..");
                phaser.arriveAndAwaitAdvance();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
}
