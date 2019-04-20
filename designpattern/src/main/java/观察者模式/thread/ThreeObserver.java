package 观察者模式.thread;

import java.util.Observable;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 13:18
 **/
public class ThreeObserver extends AbsObserver {
    private volatile Integer data = 0;

    public ThreeObserver(Subject subject) {
        super(subject);
    }

    public void runOtherSth() {
        int currentVal = data;
        System.out.println("run sth " + currentVal);

        while (currentVal < 2) {
            if (currentVal != data) {
                System.out.printf("the value update to [%d]\n", data);
                currentVal = data;
            }

            if (data == 2)
                System.out.println("change data: " + data);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        //异步通知
        data++;
        System.out.println("result:" + arg + ", data:" + data.toString());
    }
}
