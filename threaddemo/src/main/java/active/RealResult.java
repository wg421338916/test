package active;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-02 21:46
 **/
public class RealResult implements Result {
    private final Object result;

    public RealResult(Object result) {
        this.result = result;
    }

    @Override
    public Object getResultValue() {
        return result;
    }
}
