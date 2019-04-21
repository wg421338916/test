package 生产消费模式;

import java.util.LinkedList;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-21 08:47
 **/
public class MessageQueue {
    private final static int DEFAULT_MAX_LIMIT = 1000;
    private final int limit;
    private LinkedList<Message> queue;

    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(final int limit) {
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    public void put(Message message) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() >= limit)
                queue.wait();

            queue.addLast(message);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (queue){
            while (queue.isEmpty())
                queue.wait();

            Message message = queue.removeFirst();
            queue.notifyAll();

            return message;
        }
    }

    public int getLimit() {
        return limit;
    }

    public int getQueueSize(){
        synchronized (queue){
            return queue.size();
        }
    }
}
