package 观察者模式.generic;

import java.util.Observable;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 12:44
 **/
public class OneObserver extends AbsObserver {
    public OneObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("one:" + arg);
    }
}
