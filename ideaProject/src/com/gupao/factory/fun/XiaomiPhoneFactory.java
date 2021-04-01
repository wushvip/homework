package com.gupao.factory.fun;

import com.gupao.factory.Phone;
import com.gupao.factory.XaiomiPhone;

public class XiaomiPhoneFactory implements FunFactory {
    @Override
    public Phone getPhone() {
        return new XaiomiPhone();
    }
}
