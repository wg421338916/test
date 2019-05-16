package com.wanggang.common.jdk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @program: test
 * @description: 文件处理
 * @author: Mr.WG
 * @create: 2019-05-09 17:41
 **/
public class ResPas {
    public static void main(String[] args) throws IOException {

        //String.class.getDeclaredFields()[0].getAnnotatedType();
        
//        FileInputStream f = new FileInputStream("F:\\tmpdata\\sxypuser.txt");
//
//        StringReader r = new StringReader()
        // StringWriter writer = new StringWriter(f);

        FileReader f = new FileReader("F:\\tmpdata\\sxypuser.txt");

        BufferedReader bf = new BufferedReader(f);

        StringBuilder sb = new StringBuilder();
        bf.lines().forEach(l -> {
                    String[] split = l.split("&");
                    Arrays.stream(split).forEach(c ->
                            {
                                // System.out.println(c);
                                if (c.contains("password=")) {
                                    sb.append("p:");
                                    sb.append(c.split("=")[1]);
                                    sb.append("   ");
                                } else if (c.contains("mobile=")) {
                                    sb.append("m:");
                                    sb.append(c.split("=")[1]);
                                    sb.append("\r\n");
                                }
                            }
                    );
                }
        );

        System.out.println(sb);

        FileWriter fff = new FileWriter("F:\\tmpdata\\sxypuser.txt" + System.currentTimeMillis());
        fff.write(sb.toString());
        fff.flush();
        fff.close();

        f.close();
        bf.close();

    }
}
