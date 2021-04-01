package main.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class common {
    public static void main(String[] args) {
        Map<String,Object> result = new HashMap<>();
        result.put("succeed",null);
        List<Map<String,Object>> rl = new ArrayList<>();
        Map<String,Object> rMap = new HashMap<>();
        rMap.put("id","eeeaadfffrrt");
        rMap.put("volumeName","test09251");
        rl.add(rMap);
        result.put("storages",rl);
        try {
            System.out.println("try do something");
        }catch (Exception e){
            System.out.println("this is catch exception!s");
            e.printStackTrace();
        }finally {
            System.out.println(Boolean.valueOf(result.get("succeed").toString()));
            System.out.println((Boolean)result.get("succeed"));

        }
        System.out.println("this is for last");
    }
}
