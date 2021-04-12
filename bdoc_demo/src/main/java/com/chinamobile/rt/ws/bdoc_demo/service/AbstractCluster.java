package com.chinamobile.rt.ws.bdoc_demo.service;/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-09 10:46
 * @Description
 * @Since V1.0
 */

import com.chinamobile.rt.ws.bdoc_demo.bean.CommonResponseBean;
import com.chinamobile.rt.ws.bdoc_demo.bean.cluster.ClusterBean;
import com.chinamobile.rt.ws.bdoc_demo.service.cluster.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-09 10:46
 * @Description
 * @Since V1.0
 */
public abstract class AbstractCluster implements Cluster {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCluster.class);

    private String clusterName;

    protected abstract boolean checkConnection(String userName,String password);


    public CommonResponseBean update(ClusterBean cluster) {


        checkConnection(cluster.getUser(),cluster.getPassword());

        update(cluster);

        return null;
    }


    public abstract CommonResponseBean updateClusterInternal();



    protected void saveKeytab (){

    }

//    protected
}
