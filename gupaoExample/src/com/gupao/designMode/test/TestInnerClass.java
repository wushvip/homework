package com.gupao.designMode.test;

import com.gupao.designMode.singleton.Singleton4;

import java.util.concurrent.CountDownLatch;

public class TestInnerClass {
    public static void main(String[] args) {
        int n = 200;
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
                    System.out.println(System.currentTimeMillis() + "_" + Singleton4.getInstance() + "_"+ Thread.currentThread());
                }
            }.start();
            latch.countDown();
        }
    }
}
