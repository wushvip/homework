package com.webService.client.axis;

import org.apache.axis.message.RPCElement;
import org.apache.axis.message.SOAPBodyElement;

/**
 * @autor ws
 * @description
 * @date 2020/8/31 18:03
 **/
public class Test {

    public static void main(String[] args) {
        Object[] arrs = new Object[]{new RPCElement("123"),RPCElement.class,1,"ewewf"};

        int i;
        for(i = 0; arrs != null && i < arrs.length && arrs[i] instanceof SOAPBodyElement; ++i) {
            System.out.println("-------:");
            ;
        }
        System.out.println(i);
    }
}
