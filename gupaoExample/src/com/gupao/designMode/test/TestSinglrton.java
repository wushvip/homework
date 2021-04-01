package com.gupao.designMode.test;

import com.gupao.designMode.singleton.EhanSingleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestSinglrton {

    //CountDownLatch是java.util.concurrent下的类，利用它可以实现类似计数器的功能
    public static void main(String[] args) {
    int n = 100;
    CountDownLatch latch = new CountDownLatch(n);
    for(int i=0;i<n;i++){
        new Thread(){
            @Override
            public void run() {
                try{
//                latch.await(1,TimeUnit.SECONDS);
                    latch.await();
                }catch(Exception e){
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "_" + EhanSingleton.getInstance() + "_"+ Thread.currentThread());
            }
        }.start();
        latch.countDown();
    }


	// write your code here
    }
}
