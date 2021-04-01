package com.gupao.designMode.test;

import com.gupao.designMode.singleton.Singleton5;

import java.util.concurrent.CountDownLatch;

public class TestSingleton5 {

    public static void main(String[] args) {
        int m = 200;
        CountDownLatch latch = new CountDownLatch(m);

        for(int i=0;i<m;i++){
            new Thread(){
                @Override
                public void run() {
                     try{
                        latch.await();
                     }catch(InterruptedException e){
                        e.printStackTrace();
                     }
                     System.out.println(System.currentTimeMillis() + "_" + Singleton5.getInstance("single"));
                }
            }.start();
            latch.countDown();
        }
    }
}
