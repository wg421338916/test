import java.sql.Time;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-02 20:19
 **/
public class FutureDemo {

    //主控制函数
    public static void main(String[] args) throws Exception {
        String queryStr = "query";
        //构造FutureTask，并且传入需要真正进行业务逻辑处理的类,该类一定是实现了Callable接口的类
        FutureTask<String> future = new FutureTask<String>(new UseFuture(queryStr));

        FutureTask<String> future2 = new FutureTask<String>(new UseFuture(queryStr));
        //创建一个固定线程的线程池且线程数为1,
        ExecutorService executor = Executors.newFixedThreadPool(2);
        //这里提交任务future,则开启线程执行RealData的call()方法执行
        //submit和execute的区别： 第一点是submit可以传入实现Callable接口的实例对象， 第二点是submit方法有返回值

        Future f1 = executor.submit(future);        //单独启动一个线程去执行的
        Future f2 = executor.submit(future2);
        System.out.println("请求完毕");


        Future f3 = executor.submit(() -> {
            System.out.println("ok");
            try {
                TimeUnit.SECONDS.sleep(5);
                return "aaaaaaaa";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "aaaaaaaaaaaa";
        });

        try {
            //这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
            System.out.println("处理实际的业务逻辑...");
//            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(f1.get());//wait return null
        System.out.println("a--------------a");
        //调用获取数据方法,如果call()方法没有执行完成,则依然会进行等待
        System.out.println("数据：" + future.get());
        System.out.println("数据：" + future2.get());

        System.out.println("over");
        System.out.println("数据：" + f3.get());

        executor.shutdown();


        FutureTask<String> task = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(1);
            return "111111111";
        });

        new Thread(task).start();

        System.out.println("wait 1 seconds");
        System.out.println(task.get());
        System.out.println("wait 1 over");

    }
}


class UseFuture implements Callable<String> {
    public UseFuture(String queryStr) {
    }

    Random random = new Random(System.currentTimeMillis());

    @Override
    public String call() throws Exception {

        int i = random.nextInt(5);
        System.out.println("等待:" + i);
        TimeUnit.SECONDS.sleep(i);
        return i + "";
    }
}
