package active;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-02 21:43
 **/
public abstract class MethodRequest {
    protected final Servant servant;
    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();
}
