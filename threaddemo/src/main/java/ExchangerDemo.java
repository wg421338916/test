import java.util.Date;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-06-04 15:52
 **/
public class ExchangerDemo {
    public static void main(String[] args) throws InterruptedException {
        Exchanger exchanger = new Exchanger();
        new Thread(()->{

            try {
                TimeUnit.SECONDS.sleep(2);
                Object a = new Date();
                System.out.println("aaaaaaaaaaaaaa-"+a);


                Object exchange = exchanger.exchange(a);

                System.out.println("re:"+exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();


        new Thread(()->{
            Object b= new Date();
            try {
                System.out.println("bbbbbbbbbbbbb-"+b);

                Object exchange = exchanger.exchange(b);

                System.out.println("re:"+exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();


        TimeUnit.SECONDS.sleep(6);
    }
}
