package 状态模式.thread;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 19:10
 **/
public class MainMethod {
    public static void main(String[] args) throws InterruptedException {
        IntStream.range(0,5).forEach(i->{
            new Thread(()->{
                FirstProcess first = new FirstProcess();
                first.exe();

                SecondProcess two = new SecondProcess();
                two.exe();
            }).start();
        });

        TimeUnit.SECONDS.sleep(1);

    }

}
