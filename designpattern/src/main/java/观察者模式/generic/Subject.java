package 观察者模式.generic;

import java.util.Observable;

/**
 * @program: test
 * @description: subject
 * @author: Mr.WG
 * @create: 2019-04-20 12:31
 **/
public class Subject extends Observable {

    private Object arg;

    public Subject() {
        this.setChanged();
    }

    public void setArg(Object arg) {
        if (this.arg == arg)
            return;

        this.setChanged();
        this.arg = arg;
        this.notifyObservers(arg);
    }
}
