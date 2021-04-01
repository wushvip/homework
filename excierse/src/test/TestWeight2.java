package test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @Author: wushuai
 * @Date: 2019/9/19 10:33
 * @Description:
 */
public class TestWeight2 {

    private static TreeMap<Integer,Double> treeMap = new TreeMap<>();

    public static void main(String[] args) {
        int[] arr = {19,16,1,2,5,10,13};
        initData(arr);
    }

    private static void initData(int[] arr) {
        if(arr==null || arr.length<=0){
            return;
        }

        double weight = 0;
        for(int i:arr){
            weight += i;
            treeMap.put(i,weight);
        }

        Set<Map.Entry<Integer, Double>> entries = treeMap.entrySet();
        for(Map.Entry<Integer, Double> entry:entries){
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }
    }


}
