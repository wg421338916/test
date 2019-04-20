package 状态模式.generic;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 19:06
 **/
public class FirstProcess implements IProcess {

    public void exe(Context context) {
        System.out.println("run it");
        context.setStatus("first");
    }
}
