package active;

import java.util.LinkedList;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-02 22:11
 **/
public class ActivationQueue {
    private static final int MAX_MEHTOD_RQUEST_SIZE = 10;
    private final LinkedList<MethodRequest> methodReque;

    public ActivationQueue(LinkedList<MethodRequest> methodReque) {
        this.methodReque = methodReque;
    }

    public ActivationQueue() {
        this.methodReque = new LinkedList<>();
    }

    public synchronized void put(MethodRequest request) throws InterruptedException {
        while (methodReque.size() >= MAX_MEHTOD_RQUEST_SIZE) {
            this.wait();
        }

        this.methodReque.addLast(request);
        this.notifyAll();
    }

    public synchronized MethodRequest take() throws InterruptedException {
        while (this.methodReque.isEmpty()) {
            this.wait();
        }


        MethodRequest methodRequest = this.methodReque.removeFirst();
        this.notifyAll();
        return methodRequest;
    }
}
