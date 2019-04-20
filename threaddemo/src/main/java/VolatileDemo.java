import java.util.concurrent.TimeUnit;

/**
 * @program: test
 * @description: sth
 * @author: Mr.WG
 * @create: 2019-04-20 08:29
 * <p>
 * i=i+1  getmaincache-> cpu cache i->i+1->set cpu cache 2->set main cache
 * <p>
 * 俩种解决办法:
 * 1.lock
 * 2.volatile
 * <p>
 * 总线加锁：数据总线 地址总线 控制总线
 * cpu高速缓冲一致性协议: intel MESI
 * 我在操作写maincache，发现是共享则通知其他操作无效（重来）
 **/
public class VolatileDemo {
    private volatile static Integer INIT_VALUE = 0;
//    private  static Integer INIT_VALUE = 0;

    private static Integer MAX_VALUE = 5;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int currentVal = INIT_VALUE;
            while (currentVal < MAX_VALUE) {
                if (currentVal != INIT_VALUE) {
                    System.out.printf("the value update to [%d]\n", INIT_VALUE);
                    currentVal = INIT_VALUE;
                }

            }
        }, "READER").start();

        new Thread(() -> {
            int currentVal = INIT_VALUE;
            while (currentVal < MAX_VALUE) {
                System.out.printf("update the value  to [%d]\n", ++currentVal);
                INIT_VALUE = currentVal;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATER").start();

        TimeUnit.MICROSECONDS.sleep(100);

        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(INIT_VALUE);

        }
        TimeUnit.SECONDS.sleep(10);
    }
}
