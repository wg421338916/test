package active;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-02 21:43
 **/
public class Servant implements ActiveObject {
    @Override
    public Result makeString(int count, char fillChar) {
        char[] buffer = new char[count];
        IntStream.range(0, count).forEach(i -> {
            buffer[i] = fillChar;

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception ex) {

            }
        });

        return new RealResult(new String(buffer));
    }

    @Override
    public void displayString(String text) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("do " + text);
    }
}
