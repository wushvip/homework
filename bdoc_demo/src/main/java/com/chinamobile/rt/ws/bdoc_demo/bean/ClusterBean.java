package com.chinamobile.rt.ws.bdoc_demo.bean;/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-06 16:57
 * @Description
 * @Since V1.0
 */

import io.swagger.annotations.ApiModel;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-06 16:57
 * @Description
 * @Since V1.0
 */
@ApiModel(value = "ClusterBean",description = "集群配置bean")
public class ClusterBean {

    private String userName;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ClusterBean{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
