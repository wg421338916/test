import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: test jdk1.7
 * @description: 替換 CountDownLatch CyclicBarrier
 * @author: Mr.WG
 * @create: 2019-06-24 16:21
 * https://blog.csdn.net/qq496013218/article/details/69568263
 * https://blog.csdn.net/chenchaofuck1/article/details/51603600
 **/
public class PhaserDemo {
    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser();
        IntStream.rangeClosed(0, 10).boxed().map(i -> phaser).forEach(Task::new);
        phaser.register();//注册一个
        phaser.arriveAndAwaitAdvance();
        System.out.println("exit");

//------------------

        final Phaser phaser2 = new Phaser(6);
        for (int i = 1; i <= 5; i++) {
            new Task2(phaser2).start();
        }

        phaser2.arriveAndDeregister();//删除一个
        System.out.println("exit2");


//------------------

        final Phaser phaser3 = new Phaser(1);
        // 十名选手
        for (int index = 0; index < 10; index++) {
            phaser3.register();
            System.out.println("num-" + phaser3.getRegisteredParties());
            new Thread(new player(phaser3), "player" + index).start();
        }
        System.out.println("Game Start");
        //注销当前线程,比赛开始
        phaser3.arriveAndDeregister();
        System.out.println("num2-" + phaser3.getRegisteredParties());
        //是否非终止态一直等待
        while (!phaser3.isTerminated()) {
        }
        System.out.println("num3-" + phaser3.getRegisteredParties());
        System.out.println("Game Over");

        //-------------------------
        final Phaser phaser4 = new Phaser(1);
        phaser4.arrive();
        System.out.println(phaser4.getArrivedParties());
        System.out.println(phaser4.getRegisteredParties());

        TimeUnit.SECONDS.sleep(1000);

//        phaser2.bulkRegister();//注册
//        phaser2.register(); //注册
//        phaser2.getRegisteredParties();//获取数量
//        phaser2.getArrivedParties();//获取已经达到的数量
//        phaser2.getUnarrivedParties();//获取没有达到的数量

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

    static class player implements Runnable {
        private final Phaser phaser;

        player(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                // 第一阶段——等待创建好所有线程再开始
                phaser.arriveAndAwaitAdvance();

                // 第二阶段——等待所有选手准备好再开始

                Thread.sleep((long) (Math.random() * 10000));
                System.out.println(Thread.currentThread().getName() + " ready");
                phaser.arriveAndAwaitAdvance();

                // 第三阶段——等待所有选手准备好到达，到达后，该线程从phaser中注销，不在进行下面的阶段。
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println(Thread.currentThread().getName() + " arrived");
                phaser.arriveAndDeregister();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
