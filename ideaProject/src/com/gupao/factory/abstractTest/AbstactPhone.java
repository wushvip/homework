package com.gupao.factory.abstractTest;

import com.gupao.factory.Phone;

public abstract class AbstactPhone {

    /**
     * 获取华为手机的
     * @return
     */
    abstract Phone getHuaWei();

    /**
     *
     * @return
     */
    abstract Phone getApple();


    abstract Phone getXiaoMi();
}
