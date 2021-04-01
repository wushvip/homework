package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: wushuai
 * @Date: $date $time
 * @Description:
 */
public class TestMap {

    public static void main(String[] args) {
//        Object obj = new HashMap<>();


//        Map<String,Object> map = new HashMap<>(16);

//        map.put("key1","val1");
//        map.put("key2","val2");
//        map.put("key3","val3");
//        map.put("key4","val4");
//        map.put("key5","val4");
//        map.put("key6","val4");
//        map.put("key7","val4");
//        map.put("key8","val8");
//        map.put("key9","val9");
//        map.put("key10","val9");
//        map.put("key11","val9");
//        map.put("key12","val9");
//        map.put("key13","val9");

//        int h ;

//        System.out.println((h="key1".hashCode()));
//        System.out.println(h>>>16);
//        System.out.println((h="key1".hashCode())^(h>>>16));
//        System.out.println(Double.valueOf(map.get("key2").toString()));


//        Set<Map.Entry<String, Object>> entries = map.entrySet();
//        for(Map.Entry<String,Object> entry:entries){
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
//
//        Set<String> keys = map.keySet();
//        for(String key:keys){
//            System.out.println(key);
//        }


        //test order
        Map<Integer,String> map = new HashMap<>(16);

        map.put(Integer.valueOf(2),"two");
        map.put(Integer.valueOf(5),"five");
        map.put(Integer.valueOf(1),"one");
        map.put(Integer.valueOf(3),"three");
        map.put(Integer.valueOf(4),"four");

//        for (String val:map.values())
//            System.out.println(val);
        System.out.println(map.toString());
    }
}
