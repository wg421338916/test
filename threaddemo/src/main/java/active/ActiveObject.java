package active;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-02 21:41
 **/
public interface ActiveObject {

    Result  makeString(int count,char fillChar) throws InterruptedException;
    void displayString(String text) throws InterruptedException;
}
