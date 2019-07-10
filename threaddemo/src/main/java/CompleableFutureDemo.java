import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-07-10 11:45
 **/
public class CompleableFutureDemo {
    public static void main(String[] args) throws InterruptedException {
        supplyAsync();
        runAsync();

        TimeUnit.SECONDS.sleep(10);
//        Thread.currentThread().join();
    }

    private static void runAsync() {
        CompletableFuture.runAsync(() -> {
            System.out.println("run");
        }).whenComplete((v, t) -> {
            System.out.println("over");
        });
    }

    private static void supplyAsync() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("run");
            return 1;
        }).thenAccept(t -> {
            System.out.println("ove2" + t);
        }).whenComplete((v, t) -> {
            System.out.println("over");
        });
    }
}
