package active;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-02 21:57
 **/
public class FutureResult implements Result {
    private Result result;
    private Boolean ready = false;

    public synchronized void setResult(Result result, Boolean ready) {
        this.result = result;
        this.ready = ready;
        this.notifyAll();
    }

    @Override
    public synchronized Object getResultValue() throws InterruptedException {
        while (!ready) {
            this.wait();
        }

        return this.result.getResultValue();
    }
}
