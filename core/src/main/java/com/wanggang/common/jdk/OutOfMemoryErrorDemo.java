package com.wanggang.common.jdk;

import com.google.common.collect.Lists;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-07-17 13:47
 **/
public class OutOfMemoryErrorDemo {
    /***
     * java 堆溢出
     * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        List<OutOfMemoryErrorDemo> demos = Lists.newArrayList();

        while (true) {
            //TimeUnit.MILLISECONDS.sleep(1);
            demos.add(new OutOfMemoryErrorDemo());
        }
    }

    /*** 虚拟机栈 本地方法栈
     * -Xss128k
     * @param args
     */
    public static void main2(String[] args) {
        OutOfMemoryErrorDemo demo = new OutOfMemoryErrorDemo();
        demo.check();
    }


    /*** 方法区 jdk6
     * -XX:PermSize=10M -XX:MaxPermSize=10M
     * @param args
     */
    public static void main3(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;

        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

    /*** 方法区 jdk7
     * -XX:PermSize=10M -XX:MaxPermSize=10M
     * @param args
     */
    public static void main4(String[] args) {
//        while (true) {
//            Enhancer enhancer = new Enhancer();
//            enhancer.setSuperclass(OutOfMemoryErrorDemo.class);
//            enhancer.setUseCache(false);
//            enhancer.setCallback(new MethodInterceptor() {
//                @Override
//                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                    return methodProxy.invokeSuper(o, objects);
//                }
//            });
//            enhancer.create();
//        }
    }

    public void check() {
        check();
    }
}
