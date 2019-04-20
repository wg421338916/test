package 状态模式.thread;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 19:06
 **/
public class FirstProcess implements IProcess {

    public void exe() {
        System.out.println("run it tname:" + Thread.currentThread().getName());
        ActionContext.getInstance().setStatus("first");
    }
}
