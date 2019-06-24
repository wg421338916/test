package com.wanggang.common.guava;

import com.google.common.collect.LinkedListMultimap;

import java.util.List;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-06-21 09:47
 **/
public class LinkedListMultimapDemo {
    public static void main(String[] args) {
        LinkedListMultimap<String, String> multipleMap = LinkedListMultimap.create();

        multipleMap.put("1","1");
        multipleMap.put("1","1");
        multipleMap.put("1","2");
        multipleMap.put("1","3");

        List<String> strings = multipleMap.get("2");
        System.out.println(strings.toArray());
       strings = multipleMap.get("1");
        System.out.println(strings);
    }
}
