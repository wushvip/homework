package com.gupao.designMode.singleton;

/**
 * 第一次加载Singleton类时并不会初始化SINGLRTON,只有第一次调用getInstance方法的时候才会初始化SINGLETON
 *     *第一次调用getInstance 方法的时候虚拟机才会加载SingletonHoder类,这种方式不仅能够保证线程安全,也能够保证对象的唯一,
 *     *还延迟了单例的实例化,所有推荐使用这种方式
 * ---------------------
 * 作者：Haomeng_
 * 来源：CSDN
 * 原文：https://blog.csdn.net/username987654/article/details/79067583
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 */
public class Singleton4 {
    private Singleton4 (){
    }
    public static Singleton4 getInstance(){
        return SingletonHodler.INSTANCE;
    }

    //默认不加载
    public static class SingletonHodler{
        private static final Singleton4 INSTANCE = new Singleton4();
    }
}
