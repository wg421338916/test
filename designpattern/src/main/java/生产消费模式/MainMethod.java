package 生产消费模式;

import java.util.stream.IntStream;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-21 09:05
 **/
public class MainMethod {
    public static void main(String[] args) {

        final MessageQueue queue = new MessageQueue(1000);

        IntStream.range(0, 5).forEach(i -> {
            new ProducerThread(queue, i).start();
        });

        IntStream.range(0, 5).forEach(i -> {
            new ConsumerThread(queue, i).start();
        });
    }
}
