package com.gupao.designMode.protoType.deep;

/**
 * 不仅对成员变量的进行值的复制，而且对内部引用类型的成员变量的值也进行复制
 */
public class TestDeep {
    public static void main(String[] args) {
        JiliCar jili = new JiliCar(new CarProduct(),"sae");
        try{
            JiliCar clone = (JiliCar)jili.clone();
            System.out.println(jili==clone);
            System.out.println(jili.getEngin()==clone.getEngin());
            System.out.println(jili.shangbiao == clone.shangbiao);
            System.out.println(jili.product == clone.product);
        }catch(Exception e){
            e.printStackTrace();
        }



        ;
    }
}
