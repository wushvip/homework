package com.chinamobile.rt.ws.bdoc_demo.bean;

import com.chinamobile.rt.ws.bdoc_demo.service.cluster.Cluster;
import com.chinamobile.rt.ws.bdoc_demo.service.cluster.CmhCluster;
import com.chinamobile.rt.ws.bdoc_demo.service.cluster.ContainerCluster;

import java.util.function.Supplier;

/**
 * @Title 集群类型
 * @Author Administrator
 * @Date 2021-04-09 13:57
 * @Description
 * @Since V1.0
 */
public enum ClusterImplEnum {

    CMHCLUSTER("cmh_hadoop",CmhCluster::new),

    CONTAINERCLUSTER("container",ContainerCluster::new);

    private Supplier< Cluster > supplier;

    private String type;

//    ClusterImplEnum(String type){
//        ClusterType(type,);
//  }

    ClusterImplEnum(String type, Supplier< Cluster > supplier){
        this.type = type;
        this.supplier = supplier;
    }

    public static Supplier< Cluster > getConstruct(String type){
        for (ClusterImplEnum clusterType : ClusterImplEnum.values()) {
            if(clusterType.type.equals(type)){
                return clusterType.supplier;
            }
        }
        return null;
    }
}
