package collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: wushuai
 * @Date: 2020/4/17 14:23
 * @Description:
 */
public class TestWhile {

        public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println("nezt is:  :" + next);
            if("5".equals(next)){
                System.out.println("find ..... :" + next);
                break;
            }
        }


        System.out.println(" while end!");

    }
}
