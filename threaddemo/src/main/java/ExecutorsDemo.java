import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-06-25 10:04
 **/
public class ExecutorsDemo {
    public static void main(String[] args) throws InterruptedException {
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

        IntStream.rangeClosed(0,16).boxed().forEach(t->{
            service.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("ok"+t);
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

        service.awaitTermination(5,TimeUnit.SECONDS);

        System.out.println(runnables.size());
    }
}
