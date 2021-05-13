package com.chinamobile.rt.ws.bdoc_demo.utils;

import com.chinamobile.rt.ws.bdoc_demo.bean.ClusterImplEnum;
import com.chinamobile.rt.ws.bdoc_demo.service.cluster.Cluster;

/**
 * @Title 集群管理者静态工厂
 * @Author Administrator
 * @Date 2021-04-09 14:21
 * @Description
 * @Since V1.0
 */
public class ClusterManagerFactory {

    public static Cluster buildeClusterManager(String type){
        if(null==type){
            return null;
        }
        return ClusterImplEnum.getConstruct(type).get();
    }


    public static void main(String[] args) {
        System.out.println(buildeClusterManager("cmh_hadoop"));
    }
}
