package com.gupao.designMode.singleton;

public class DCLModel {
    private static DCLModel dcl;

    public static void getInstance(){
        if(dcl == null){
            synchronized (DCLModel.class){
                if(dcl == null){
                    dcl = new DCLModel();
                }
            }
        }
    }

}
