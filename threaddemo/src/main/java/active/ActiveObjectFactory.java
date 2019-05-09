package active;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-02 22:33
 **/
public final class ActiveObjectFactory {
    private ActiveObjectFactory() {
    }

    public static ActiveProxy createActiveObject() {
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();
        SchedulerThread thread = new SchedulerThread(queue);
        thread.start();

        ActiveProxy proxy = new ActiveProxy(servant, thread);

        return proxy;
    }
}
