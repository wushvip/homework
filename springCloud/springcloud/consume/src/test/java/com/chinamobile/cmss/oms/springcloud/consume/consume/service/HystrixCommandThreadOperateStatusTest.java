package com.chinamobile.cmss.oms.springcloud.consume.consume.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author LIAOJIANYA
 * @date 2020/9/28
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class HystrixCommandThreadOperateStatusTest {

//    @Test
//    public void testHystrixCommandThreadOperateStatus () {
//
//        for(int i = 0; i < 12; i++) {
//            System.out.println("i=" + i);
//            new TestThread(i).start();
//        }
//
//    }

    public static void main(String[] args) throws Exception{
        for(int i = 0; i < 12; i++) {
            System.out.println("i=" + i);
            new TestThread(i).start();
        }
    }

    private static class TestThread extends Thread{
        private int index;

        public TestThread (int index) {
            this.index = index;
        }

        @Override
        public void run() {
            HystrixCommandThreadOperateStatus hystrixCommandThreadOperateStatus
                    = new HystrixCommandThreadOperateStatus("success");

            System.out.println("The status of " + index + " is: " +hystrixCommandThreadOperateStatus.execute());
        }
    }
}
