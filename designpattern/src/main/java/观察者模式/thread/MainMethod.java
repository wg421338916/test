package 观察者模式.thread;

import java.util.concurrent.TimeUnit;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 13:28
 **/
public class MainMethod {
    public static void main(String[] args) throws InterruptedException {
        Subject subject = new Subject() {
            @Override
            public void run() {
                this.notifyObservers("success");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.notifyObservers("fail");
            }
        };

        ThreeObserver threeObserver = new ThreeObserver(subject);
        new Thread(subject).start();

        TimeUnit.SECONDS.sleep(1);

        threeObserver.runOtherSth();

        TimeUnit.SECONDS.sleep(1);
    }
}
