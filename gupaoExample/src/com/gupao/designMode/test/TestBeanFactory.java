package com.gupao.designMode.test;

import com.gupao.designMode.singleton.BeanFactory;
import com.gupao.designMode.singleton.Singleton5;

import java.util.concurrent.CountDownLatch;

public class TestBeanFactory {

    public static void main(String[] args) {
        int count = 200;

        //发令枪，我就能想到运动员
        CountDownLatch latch = new CountDownLatch(count);

        long start = System.currentTimeMillis();
        for (int i = 0; i < count;i ++) {
            new Thread(){
                @Override
                public void run() {
                    try{

                        try {
                            // 阻塞
                            // count = 0 就会释放所有的共享锁
                            // 万箭齐发
                            latch.await();
                        }catch(Exception e){
                            e.printStackTrace();
                        }

                        //必然会调用，可能会有很多线程同时去访问getInstance()
                        Object obj = BeanFactory.getBean("com.gupao.designMode.test.POJO");;
                        System.out.println(System.currentTimeMillis() + ":" + obj);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start(); //每循环一次，就启动一个线程,具有一定的随机性

            //每次启动一个线程，count --
            latch.countDown();

        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start));

//        int m = 100;
//        CountDownLatch latch = new CountDownLatch(m);
//
//        for(int i=0;i<m;i++){
//            new Thread(){
//                @Override
//                public void run() {
//                    try{
//                        latch.await();
//                    }catch(InterruptedException e){
//                        e.printStackTrace();
//                    }
//                    System.out.println(System.currentTimeMillis() + "_" + BeanFactory.getBean("com.gupao.designMode.test.POJO"));
//                }
//            }.start();
//            latch.countDown();
//        }
    }
}
