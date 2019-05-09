package active;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-02 22:40
 **/
public class Client {
    public static void main(String[] args) throws InterruptedException {
        ActiveProxy activeObject = ActiveObjectFactory.createActiveObject();
        Result a = activeObject.makeString(10, 'a');

        activeObject.displayString("some time");

        System.out.println(a.getResultValue());
    }
}
