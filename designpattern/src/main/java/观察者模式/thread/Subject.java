package 观察者模式.thread;

import java.util.Observable;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 13:16
 **/
public abstract class Subject extends Observable implements Runnable {
    public Subject(){
        setChanged();
    }

    @Override
    protected synchronized void clearChanged() {

    }
}
