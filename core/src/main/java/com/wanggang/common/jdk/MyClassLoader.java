package com.wanggang.common.jdk;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * @program: test https://www.cnblogs.com/doit8791/p/5820037.html
 * @description: 父子加载器只是包装关系，由同一个classload加载的hashcode一样
 * @author: Mr.WG
 * @create: 2019-05-08 16:28
 **/
public class MyClassLoader extends ClassLoader {


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] datas = getClassBytes();
        return this.defineClass(name, datas, 0, datas.length);

    }

    public byte[] getClassBytes() {
        try (FileInputStream inputStream = new FileInputStream("");
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int position = 0;
            while ((inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, position);
            }
            out.flush();
            return out.toByteArray();
        } catch (Exception ex) {
            return null;
        }
    }

//    @Override
//    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
//        return super.loadClass(name, resolve);
//    }
}
