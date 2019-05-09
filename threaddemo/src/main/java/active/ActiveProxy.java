package active;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-02 22:28
 **/
public class ActiveProxy implements ActiveObject {

    private Servant servant;
    private SchedulerThread schedulerThread;

    public ActiveProxy(Servant servant, SchedulerThread schedulerThread) {
        this.servant = servant;
        this.schedulerThread = schedulerThread;
    }

    @Override
    public Result makeString(int count, char fillChar) throws InterruptedException {
        FutureResult result = new FutureResult();
        this.schedulerThread.invoke(new MakeStringRequest(servant, result, count, fillChar));
        return result;
    }

    @Override
    public void displayString(String text) throws InterruptedException {
        this.schedulerThread.invoke(new DisplayStringRequest(servant, text));
    }
}
