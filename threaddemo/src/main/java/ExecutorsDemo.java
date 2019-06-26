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
        //demo.timerStudy();
        //demo.testAbortPolicy();
        //demo.testDiscardOldestPolicy();
        //demo.testCallerRunsPolicy();
        //demo.testKeeplive();
        demo.testprestartAllCoreThreads();

//        TimeUnit.SECONDS.sleep(20);
//        //--------- -------------- ---------- --
//        ScheduledExecutorService scheduledExecutorService =
//                Executors.newSingleThreadScheduledExecutor();


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

    private void testAbortPolicy() {
        ThreadPoolExecutor service = new ThreadPoolExecutor(
                1,//初始化线程数量
                2,//当队列满了就会开辟新线程
                30l,//30s后回收空闲线程
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                },
                new ThreadPoolExecutor.AbortPolicy()//超过队列容量则拒绝
        );

        for (int i = 0; i < 10; i++) {
            service.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void testDiscardOldestPolicy() {
        ThreadPoolExecutor service = new ThreadPoolExecutor(
                1,//初始化线程数量
                2,//当队列满了就会开辟新线程
                30l,//30s后回收空闲线程
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                },
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        for (int i = 0; i < 10; i++) {
            final int j = i;
            service.submit(() -> {
                try {
                    System.out.println("ok:" + String.valueOf(j));
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("oknone:" + String.valueOf(j));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void testCallerRunsPolicy() {
        //1. CallerRunsPolicy ：这个策略重试添加当前的任务，他会自动重复调用 execute() 方法，直到成功。
        //2. AbortPolicy ：对拒绝任务抛弃处理，并且抛出异常。
        //3. DiscardPolicy ：对拒绝任务直接无声抛弃，没有异常信息。
        //4. DiscardOldestPolicy ：对拒绝任务不抛弃，而是抛弃队列里面等待最久的一个线程，然后把拒绝任务加到队列。
        ThreadPoolExecutor service = new ThreadPoolExecutor(
                1,//初始化线程数量
                2,//当队列满了就会开辟新线程
                30l,//30s后回收空闲线程
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                },
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < 10; i++) {
            final int j = i;
            service.submit(() -> {
                try {
                    System.out.println("ok:" + String.valueOf(j));
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("oknone:" + String.valueOf(j));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void testKeeplive() throws InterruptedException {
        ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        service.setKeepAliveTime(10, TimeUnit.SECONDS);
        service.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 2; i++) {
            final int j = i;
            service.execute(() -> {
                try {
                    System.out.println("ok:" + String.valueOf(j));
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("oknone:" + String.valueOf(j));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(service.getActiveCount());
        TimeUnit.SECONDS.sleep(20);
        System.out.println(service.getActiveCount());

        Runnable t = () -> {
            System.out.println("run");
        };
        service.execute(t);

        System.out.println(service.getActiveCount());

        TimeUnit.SECONDS.sleep(10);

        service.remove(t);//从队列中移除
    }


    private void testprestartAllCoreThreads() throws InterruptedException {
        ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        System.out.println(service.getActiveCount());

        System.out.println(service.prestartCoreThread());
        System.out.println(service.getActiveCount());

        System.out.println(service.prestartAllCoreThreads());
        System.out.println(service.getActiveCount());

        System.out.println(service.prestartAllCoreThreads());
        System.out.println(service.getActiveCount());

        System.out.println(service.prestartCoreThread());
        System.out.println(service.getActiveCount());
    }
}
