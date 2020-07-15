package com.demo.hashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Li Luopu
 * @version 1.0
 * @date 2020/7/15 10:45
 */
public class HashMapDemo {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        //通过foreach直接遍历key
        for (String key : map.keySet()) {
            System.out.println(key + map.get(key));
        }
        //通过迭代器遍历
        Iterator key = map.entrySet().iterator();
        while (key.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) key.next();

            System.out.println(entry.getKey() + entry.getValue());
        }
        //容量大时用
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
