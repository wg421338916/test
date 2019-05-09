package com.wanggang.common.jdk;

import java.sql.DriverManager;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @program: test
 * @description: clinit javap -v ClassLoaderDemo
 * root-ext-app 一级一级往上找，直到根root
 * @author: Mr.WG
 * @create: 2019-05-08 11:07
 **/
public class ClassLoaderDemo {
    private static int i = 10;

    static {
        System.out.println(i);

        i = i + 10;

        //y = y + 100;
    }

    public static int y = 2;

    public static void main(String[] args) throws ClassNotFoundException {

//        Class.forName("com.mysql.jdbc.Driver")
//        DriverManager.getConnection()

        //System.out.println(ClassLoaderDemo.class);

        //System.out.println(subClass.class);

        System.out.println(new subClass());

//        new Thread(() -> new subClass2()).start();
//        new Thread(() -> new subClass2()).start();

        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));

        Class<?> aClass = Class.forName("com.wanggang.common.jdk.subClass");
        System.out.println(aClass.getClassLoader());
        System.out.println(aClass.getClassLoader().getParent());
        System.out.println(aClass.getClassLoader().getParent().getParent());

//        MyClassLoader myClassLoader=new MyClassLoader();
//        Class<?> xxxxxxxxxx = myClassLoader.loadClass("xxxxxxxxxx");
//        Class<?>[] interfaces = xxxxxxxxxx.getInterfaces();
//        String
    }
}

class subClass extends ClassLoaderDemo {
    static {
        System.out.println(subClass.y);
        System.out.println(test.tt);
    }
}

class subClass2 extends ClassLoaderDemo {
    static AtomicBoolean init = new AtomicBoolean(true);

    static {
        System.out.println("init");
        while (init.get()) {
        }
    }
}

interface test {
    int tt = 100;
}