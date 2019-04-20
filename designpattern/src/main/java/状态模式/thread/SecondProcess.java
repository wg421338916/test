package 状态模式.thread;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 19:09
 **/
public class SecondProcess {
    public void exe() {
        System.out.println("run it two tname:" + Thread.currentThread().getName());
        Object one = ActionContext.getInstance().getStatus();
        System.out.println("do sth arg is " + one + " tname " + Thread.currentThread().getName());
        ActionContext.getInstance().setStatus("two tname:" + Thread.currentThread().getName());
    }
}
