package com.gupao.designMode.protoType.deepExample;

public class TestDeepExample {
    public static void main(String[] args) {
        QiTianDaSheng ds = new QiTianDaSheng();
        try {
            QiTianDaSheng clone = (QiTianDaSheng)ds.clone();
            System.out.println(clone == ds);
            System.out.println(clone.jinGuBang == ds.jinGuBang);
            System.out.println(clone.height == ds.height);

        }catch(Exception e){
            e.printStackTrace();
        }


        ;
    }
}
