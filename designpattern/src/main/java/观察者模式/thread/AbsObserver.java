package 观察者模式.thread;

import 观察者模式.thread.Subject;

import java.util.Observer;

/**
 * @program: test
 * @description: observer
 * @author: Mr.WG
 * @create: 2019-04-20 12:31
 **/
public abstract class AbsObserver implements Observer {
    protected Subject subject;

    public AbsObserver(Subject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
    }
}
