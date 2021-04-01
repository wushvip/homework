package lamda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wushuai
 * @Date: 2019/8/1 14:30
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
//        List<City> cities = new ArrayList<>();
//        cities.add(new City(10000000000L,new BigDecimal(5768787)));
//        cities.add(new City(10000000000L,new BigDecimal(5768787)));
//        cities.add(new City(10000000000L,new BigDecimal(5768787)));
//        cities.add(new City(10000000000L,new BigDecimal(5768787)));
//        cities.add(new City(10000000000L,new BigDecimal(5768787)));
//        cities.add(new City(10000000000L,new BigDecimal(5768787)));
//        cities.add(new City(10000000000L,new BigDecimal(5768787)));
//        List<City> cities1 = null;
//
//        long persons = cities1.stream().mapToLong(item->item.getPersonNum()).sum();
//        BigDecimal gdps = cities1.stream().map(item->item.getGDP()).reduce(BigDecimal.ZERO,BigDecimal::add);
//        System.out.println(persons);
//        System.out.println(gdps);
        int cap = 3;
        int n = cap - 1;
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n);
    }
}
