package 观察者模式.generic;

import java.util.Observer;

/**
 * @program: test
 * @description: observer
 * @author: Mr.WG
 * @create: 2019-04-20 12:31
 **/
public abstract class AbsObserver implements Observer {
    private Subject subject;

    public AbsObserver(Subject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
    }
}
