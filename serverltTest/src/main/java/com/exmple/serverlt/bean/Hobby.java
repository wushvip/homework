package com.exmple.serverlt.bean;/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-22 17:12
 * @Description
 * @Since V1.0
 */

import java.util.List;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-22 17:12
 * @Description
 * @Since V1.0
 */
public class Hobby {

    private String bobbyName;

    private List<String> alisas;

    public String getBobbyName() {
        return bobbyName;
    }

    public void setBobbyName(String bobbyName) {
        this.bobbyName = bobbyName;
    }

    public List<String> getAlisas() {
        return alisas;
    }

    public void setAlisas(List<String> alisas) {
        this.alisas = alisas;
    }
}
