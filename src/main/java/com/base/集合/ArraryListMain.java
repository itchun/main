package com.base.集合;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ArraryListMain {

    public static void main(String[] args) {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        list.add(null);
        list.remove(2);
        list.trimToSize();
        list.toArray();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", i);
            map.put("name", i);
            list.add(map);
        }
        speed1(list);
        speed2(list);
        speed3(list);

        CopyOnWriteArrayList sy_list = new CopyOnWriteArrayList();
        sy_list.remove(1);

        list.stream().map(map -> {
            map.put("id", map.get("id") + "aa");
            return map;
        }).collect(Collectors.toList());
        list.stream().forEach(map -> System.out.println(map.get("id")));
        list.stream().forEach(System.out::println);
        List<String> l = list.stream().filter(o -> o.get("id").equals("1aa")).map(map -> map.get("name").toString()).collect(Collectors.toList());
        System.out.println("end");
    }

    public static void speed1(List<Map<String, Object>> list) {
        Long start = new Date().getTime();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            if (map.get("id").equals("10000")) System.out.println(i);
        }
        Long end = new Date().getTime();
        System.out.println("time1:{}:" + (end - start));
    }

    public static void speed2(List<Map<String, Object>> list) {
        Long start = new Date().getTime();
        list.stream().forEach(map -> {
            if (map.get("id").equals("10000")) System.out.println(map.get("id"));
        });
        Long end = new Date().getTime();
        System.out.println("time2:{}:" + (end - start));
    }

    public static void speed3(List<Map<String, Object>> list) {
        Long start = new Date().getTime();
        for (Map<String, Object> map : list) {
            if (map.get("id").equals("10000")) System.out.println(map.get("id"));
        }
        Long end = new Date().getTime();
        System.out.println("time3:{}:" + (end - start));
    }

}
