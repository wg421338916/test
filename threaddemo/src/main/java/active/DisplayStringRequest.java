package active;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-02 22:06
 **/
public class DisplayStringRequest extends MethodRequest {
    private String text;

    public DisplayStringRequest(Servant servant, String text) {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute() {
        this.servant.displayString(this.text);
    }
}
