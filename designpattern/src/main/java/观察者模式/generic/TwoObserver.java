package 观察者模式.generic;

import java.util.Observable;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 12:46
 **/
public class TwoObserver extends AbsObserver {
    public TwoObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("two:"+arg);
    }
}
