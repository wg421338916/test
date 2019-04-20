package 观察者模式.generic;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 12:47
 **/
public class MainMehod {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new OneObserver(subject);
        new TwoObserver(subject);

        subject.setArg("test1");
        subject.setArg("test2");
        subject.setArg("test2");

        //no exe
        //subject.notifyObservers("sth");
    }
}
