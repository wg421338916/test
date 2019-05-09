package active;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-02 22:03
 **/
public class MakeStringRequest extends MethodRequest {
    private int count;
    private char fillChar;

    public MakeStringRequest(Servant servant, FutureResult futureResult, int count, char fillChar) {
        super(servant, futureResult);

        this.count = count;
        this.fillChar = fillChar;
    }

    @Override
    public void execute() {
        Result result = this.servant.makeString(this.count, this.fillChar);
        this.futureResult.setResult(result, true);
    }
}
