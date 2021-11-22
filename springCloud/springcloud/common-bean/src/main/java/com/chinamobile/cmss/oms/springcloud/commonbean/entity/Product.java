package com.chinamobile.cmss.oms.springcloud.commonbean.entity;

/**
 * @author wangyao
 * @Title
 * @create 2020-09-21 19:12
 * @Description test bean
 * @since 1.0.0
 */

public class Product {

    private String name;
    private int age;
    private String add;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Product() {
        this.name = "BDPaaS";
        this.age = 5;
        this.add = "苏研";
        this.email = "oms@cmss.chinamobile.com";
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", add='" + add + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
