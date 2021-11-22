package com.chinamobile.cmss.oms.springcloud.commonbean.entity;

/**
 * @author LIAOJIANYA
 * @date 2020/9/24
 */
public class HystrixServiceBean {

    String name;
    String description;
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HystrixServiceBean() {
        this.name = "BDOC";
        this.description = "BDOC-SpringCloud-Hystrix Research";
        this.email = "hystrix@cmss.chinamobile.com";
    }

    @Override
    public String toString() {
        return "HystrixServiceBean{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
