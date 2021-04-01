package com.gupao.designMode.adapt;

import com.gupao.designMode.adapt.passport.SiginForThirdService;

/**
 * Created by Tom on 2018/3/14.
 */
public class SiginForThirdServiceTest {

    public static void main(String[] args) {

        SiginForThirdService service = new SiginForThirdService();

        //不改变原来的代码，也要能够兼容新的需求
        //还可以再加一层策略模式
        ResultMsg result = service.loginForQQ("sdfgdgfwresdf9123sdf");
        System.out.println(result.getCode());

    }

}
