package active;

import java.io.PipedReader;
import java.lang.invoke.MethodHandle;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-02 22:19
 **/
public class SchedulerThread extends Thread {

    private ActivationQueue queue;

    public SchedulerThread(ActivationQueue queue) {
        this.queue = queue;
    }

    public void invoke(MethodRequest request) throws InterruptedException {
        this.queue.put(request);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.queue.take().execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
