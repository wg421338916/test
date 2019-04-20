package Single;

/**
 * @program: test
 * @description: 单例,饿汉模式
 * @author: Mr.WG
 * @create: 2019-04-20 07:05
 **/
public class Single_1 {


    private final static Single_1 instance = new Single_1();

    private Single_1(){}

    public Single_1 getInstance(){
        return instance;
    }

}
