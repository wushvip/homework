package com.gupao.designMode.test;

import com.gupao.designMode.singleton.EhanSingleton;
import com.gupao.designMode.singleton.LazySingleton;

import java.util.concurrent.CountDownLatch;
 /*
    *当第一次加载Singleton类时并不会初始化SINGLRTON,只有第一次调用getInstance方法的时候才会初始化SINGLETON
    *第一次调用getInstance 方法的时候虚拟机才会加载SingletonHoder类,这种方式不仅能够保证线程安全,也能够保证对象的唯一,
    *还延迟了单例的实例化,所有推荐使用这种方式
    * */
public class TestLazy {

    public static void main(String[] args) {
        int n = 100;
        CountDownLatch latch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
//                latch.await(1,TimeUnit.SECONDS);
                        latch.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis() + "_" + LazySingleton.getInstance() + "_" + Thread.currentThread());
                }
            }.start();
            latch.countDown();
        }
    }
}
