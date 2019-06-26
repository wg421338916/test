import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-06-25 10:04
 * https://blog.csdn.net/qq_38428623/article/details/85868695
 **/
public class ExecutorsDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorsDemo demo = new ExecutorsDemo();

        //demo.ThreadPoolExecutorStudy();
        //demo.threadPoolExecutorStudyV2();
        demo.timerStudy();

        //--------- -------------- ---------- --
        ScheduledExecutorService scheduledExecutorService =
                Executors.newSingleThreadScheduledExecutor();


    }

    private void timerStudy() {
        Timer timeer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("当前时间:" + System.currentTimeMillis());
            }
        };

        timeer.schedule(task, 1000, 1000);
    }

    private void threadPoolExecutorStudy() {
        ThreadPoolExecutor service = new ThreadPoolExecutor(
                10,//初始化线程数量
                20,//当队列满了就会开辟新线程
                30l,//30s后回收空闲线程
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                },
                new ThreadPoolExecutor.AbortPolicy()//超过队列容量则拒绝
        );

//        service.getActiveCount();
//        service.getCorePoolSize();
//        service.getQueue().size();
//        service.getPoolSize();

        IntStream.rangeClosed(0, 16).boxed().forEach(t -> {
            service.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("ok" + t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        List<Runnable> runnables = new ArrayList<>();
        try {
//            service.shutdown();//等待执行,打断空闲,阻塞
            runnables = service.shutdownNow();//返回队列数据，打断所有
        } catch (Exception e) {
            //e.printStackTrace();
        }
//
//        service.awaitTermination(5, TimeUnit.SECONDS);
//
        System.out.println(runnables.size());
    }

    private void threadPoolExecutorStudyV2() throws InterruptedException {
        //会有很多线程 60分钟空闲线程自动销毁 ThreadPoolExecutor
        //ExecutorService service1 = Executors.newCachedThreadPool();
//        ExecutorService service1 = Executors.newCachedThreadPool();
//        TimeUnit.SECONDS.sleep(61);
//        service1.execute(() -> {
//            System.out.println("ssssssssssss");
//        });
//        TimeUnit.SECONDS.sleep(61);

        //固定线程大小 队列大小int.maxsize,联释放线程，队列无上限
        //Executors.newFixedThreadPool(10);

        //相当于 Executors.newFixedThreadPool(1);，一个线程
//        ExecutorService service1 = Executors.newSingleThreadExecutor();


        //所以适合使用在很耗时 ForkJoinPool
        //https://blog.csdn.net/qq_38428623/article/details/86689800
        List<Callable<String>> list = IntStream.range(0, 20).boxed().map(i ->
                (Callable<String>) () -> {
                    System.out.println(Thread.currentThread().getName() + "-" + i);
                    TimeUnit.SECONDS.sleep(2);
                    return String.valueOf(i);
                }
        ).collect(toList());

        ExecutorService service1 = Executors.newWorkStealingPool();
        service1.invokeAll(list).stream().forEach(t -> {
            try {
                System.out.println("task over:" + t.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
