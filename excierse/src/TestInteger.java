import java.lang.reflect.Field;

/**
 * 值引用、对象引用（举例：交换数据）
 */

public class TestInteger {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //Integer中缓存的问题,java中-128-127默认分配到堆内存中，当我们定义一个改范围内的Integer，
        // 取回的是已经缓存的地址，从缓存的下标取值。
        System.out.println(Integer.parseInt("1"));

        Integer a = -128,b=-128;
        System.out.println(a==b);

        Integer m=1,n=2;//自动装箱
        System.out.println("before swap m:" + m + "   n:" + n);

        swap(m,n);

        System.out.println("after swap m:" + m + "   n:" + n);

    }

    private static void swap(Integer m, Integer n) throws NoSuchFieldException, IllegalAccessException {
        //此处采用这种方法不可行
        //在java中对于这种封装类型的传递都是副本，所以在做改变的时候，改变的都是副本的值，而不是原封装m\n的值。
        // Integer中value是final类型,不可改变的
//        Integer temp = m;
//        m = n;
//        n = temp;
//        System.out.println("m:" + m + "  n:" + n);
//
//        int a = 2;
//        long b = 2l;
//        System.out.println(a==b);

        //解决方法。用反射
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
//        int temp = m.intValue();
        Integer temp = new Integer(m.intValue());

//        field.set(m,n.intValue());
        field.set(m,n);

        field.set(n,temp);


    }


}
