package com.gupao.factory.fun;

import com.gupao.factory.GIONEEPhone;
import com.gupao.factory.Phone;

public class GIONEEPhoneFactory implements FunFactory {
    @Override
    public Phone getPhone() {
        return new GIONEEPhone();
    }
}
