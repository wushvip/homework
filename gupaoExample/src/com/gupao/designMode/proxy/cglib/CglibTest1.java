package com.gupao.designMode.proxy.cglib;

public class CglibTest1 {

    public static void main(String[] args) {
        try{
            BiyeshengProxy bys = new BiyeshengProxy();
            Biyesheng instance = (Biyesheng)bys.getInstance(Biyesheng.class);
            instance.findJob();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
