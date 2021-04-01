package com.gupao.factory.fun;

import com.gupao.factory.HuaWei;
import com.gupao.factory.Phone;

public class HuaWeiFactory implements FunFactory{
    @Override
    public Phone getPhone() {
        return new HuaWei();
    }
}
