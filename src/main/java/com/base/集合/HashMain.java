package com.base.集合;

import java.util.Collections;
import java.util.HashMap;

public class HashMain {
    public static void main(String[] args) {
        Collections.synchronizedMap(new HashMap<>());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(null, null);
        // 3 11 <<2 1100=1*8+1*4
        System.out.println(3 << 2);
    }
}
