package com.gupao.factory.abstractTest;

import com.gupao.factory.ApplePhone;
import com.gupao.factory.HuaWei;
import com.gupao.factory.Phone;
import com.gupao.factory.XaiomiPhone;

public class PhoFactory extends AbstactPhone {
    @Override
    public Phone getHuaWei() {
        return new HuaWei();
    }

    @Override
    Phone getApple() {
        return new ApplePhone();
    }

    @Override
    Phone getXiaoMi() {
        return new XaiomiPhone();
    }
}
