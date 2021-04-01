package com.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test3 {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("title","I am title");
        System.out.println(map.entrySet());
        Set set = map.entrySet();
        for(Iterator iter = set.iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry)iter.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
