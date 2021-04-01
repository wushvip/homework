package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: wushuai
 * @Date: 2019/9/19 10:43
 * @Description:
 */
public class OrderTest {

    public static void main(String[] args) {
        int[] arr = {1,5,3,8,10,16,76,34,56,89,100,2};

        //冒泡排序
        //将第一个元素与后面所有的元素进行一一比较，先找到最小的元素，排在第一位，依次类推第二位、第三位
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }


        for(int a:arr){
            System.out.print(a + "\t");
        }
    }
}
