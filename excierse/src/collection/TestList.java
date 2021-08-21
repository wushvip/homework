package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * list是可重复集合有序的，底层通过数组保存
 * @Author: wushuai
 * @Date: 2019/11/20 14:25
 * @Description:
 */
public class TestList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("13224");
        list.add("1656757");
        list.add("21217");
        list.add("21217");
        list.add("545666");
        list.add("4");
        list.add("3");
        list.add("2");


        System.out.println(list.toString());
    }
}
