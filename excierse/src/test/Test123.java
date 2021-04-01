package test;

import java.util.*;

/**
 * @Author: wushuai
 * @Date: 2019/9/17 19:14
 * @Description:
 */
public class Test123 {

    private static int totalWeight;

    private static List<NumDecorate> nds = new ArrayList<>();

    public static void main(String[] args){
        int[] arr = {1,2,5,10,13};
        initData(arr);
        Random random = new Random();

        long beginTime = System.currentTimeMillis();
        int[][] arrs = new int[10000][3];
        Set<Integer> set = null;
        int totalTimes = 0;
        int manyTimes = 0;
        for(int i=0;i<10000;i++){
            set = new HashSet<>();
            int j=0;
            while (j<3){
                int weight = Math.abs(random.nextInt(totalWeight+1));
                int num = getIndex(weight);
                while (!set.add(num)){
                    weight = Math.abs(random.nextInt(totalWeight+1));
                    num = getIndex(weight);
                    manyTimes++;
                };
                j++;
                totalTimes++;
            }

//            for(int j=0;j<3;){
//                int weight = Math.abs(random.nextInt(totalWeight+1));
//                int num = getIndex(weight);
//                set.add(num);
//                j = set.size();
////                arrs[i][j] = arr[index];
//
//                //统计各元素出现的次数
////                if(timesMap.get(num)==null){
////                    timesMap.put(num,0);
////                }
////                timesMap.put(num,timesMap.get(num)+1);
//                totalTimes++;
//            }
            System.out.println(set.toString());
        }
        long endTime = System.currentTimeMillis();


//        Set<Map.Entry<Integer, Integer>> entries = timesMap.entrySet();
//        for(Map.Entry<Integer,Integer> entry:entries){
//            System.out.println(entry.getKey() + "\r total appear times: " + entry.getValue());
//        }
        System.out.println("多余查找次数：" + manyTimes);
        System.out.println("共消耗：" + (endTime - beginTime) + "ms" + "循环次数：" +totalTimes);

    }

    private static void initData(int[] arr){
        for(int i:arr){
            totalWeight += i;
            NumDecorate nd = new NumDecorate(i,totalWeight);
            nds.add(nd);
        }
    }

    private static int getIndex(int weight){
        if(nds==null || nds.size()==0){
            throw new RuntimeException("当前节点列表不能为空！");
        }
        int result = -1;
        for(NumDecorate nd:nds){
            if(weight>nd.getWeight()){
                continue;
            }
            result = nd.getNum();
            break;
        }
//        nds.forEach((item)->{
//        });
        return result;
    }


    static class NumDecorate implements Comparable<NumDecorate>{
        private int num;

        private int weight;


        public NumDecorate(int num,int weight){
            this.num = num;
            this.weight = weight;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public int compareTo(NumDecorate obj) {
            if(obj.getNum() > this.num){
                return 1;
            }else if(obj.getNum() < this.num){
                return -1;
            }else{
                return 0;
            }
        }
    }

}
