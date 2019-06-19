import java.util.concurrent.TimeUnit;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-21 09:26
 **/
public class TerminatedDemo extends Thread {
    private volatile boolean isTerminated = false;

    @Override
    public void run() {
        while (!isTerminated) {
            System.out.println("run");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("exit");
    }

    public void clear() {
        this.isTerminated = true;
    }

    public static void main(String[] args) throws InterruptedException {
        TerminatedDemo terminatedDemo = new TerminatedDemo();
        terminatedDemo.start();
        TimeUnit.SECONDS.sleep(10);
        terminatedDemo.clear();
    }
}
