package 状态模式.generic;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 19:09
 **/
public class SecondProcess {
    public void exe(Context context) {
        System.out.println("run it two");
        Object one = context.getStatus();
        System.out.println("do sth arg is " + one);
        context.setStatus("two");
    }
}
