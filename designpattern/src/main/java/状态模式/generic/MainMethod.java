package 状态模式.generic;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 19:10
 **/
public class MainMethod {
    public static void main(String[] args) {
        Context context = new Context();

        FirstProcess first = new FirstProcess();
        first.exe(context);

        SecondProcess two = new SecondProcess();
        two.exe(context);
    }

}
