package 生产消费模式;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-21 08:57
 **/
public class ProducerThread extends Thread {
    private final MessageQueue queue;
    private final Random random = new Random(System.currentTimeMillis());
    private final static AtomicInteger counter = new AtomicInteger(0);

    public ProducerThread(MessageQueue queue, int seq) {
        super("PRODUCER" + seq);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.queue.put(new Message());

                int count = counter.incrementAndGet();

                System.out.println("threadName:" + Thread.currentThread().getName() + ",count:" + count);

                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
