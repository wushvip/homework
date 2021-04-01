//package com.gupao.designMode.proxy.cglib.cglibExample;
//
//import net.sf.cglib.core.DebuggingClassWriter;
//
//public class CglibTest {
//    public static void main(String[] args) {
//        /**
//         * 通过创建一个extends被代理类的子类,
//         */
//        try {
//            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"code");
//            //创建一个代理对象
//            ZhangSan obj = (ZhangSan)new CglibMeipo().getInstance(ZhangSan.class);
//            obj.findLove();
//            System.out.println("--------------------------------");
//            // System.out.println(obj.getClass());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//}
