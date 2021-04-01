package com.gupao.factory.simple;

import com.gupao.factory.ApplePhone;
import com.gupao.factory.HuaWei;
import com.gupao.factory.Phone;

public class SimpleFactory {

    public Phone getPhone(String name){
        if(name.equals("apple")){
            return new ApplePhone();
        }else if(name.equals("huaWei")){
            return new HuaWei();
        }else{
            return null;
        }
    }
}
