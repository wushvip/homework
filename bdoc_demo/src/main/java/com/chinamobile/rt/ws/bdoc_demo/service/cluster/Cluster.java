package com.chinamobile.rt.ws.bdoc_demo.service.cluster;

import com.chinamobile.rt.ws.bdoc_demo.bean.CommonResponseBean;
import com.chinamobile.rt.ws.bdoc_demo.bean.cluster.ClusterBean;

/**
 * @Title cluster interface
 * @Author wushuai
 * @Date 2021-04-09 10:38
 * @Description
 * @Since V1.0
 */
public interface Cluster {

    /***
    * @Titile add cluster
    * @Author Administrator
    * @Date 2021/4/9 10:44
    * @Description TODO
    * @Param [cluster]
    * @Return com.chinamobile.rt.ws.bdoc_demo.bean.CommonResponseBean
    * @Since V1.0
    */
    public CommonResponseBean add(ClusterBean cluster);


    /***
    * @Titile update
    * @Author wushuai
    * @Date 2021/4/9 10:45
    * @Description TODO
    * @Param [cluster]
    * @Return com.chinamobile.rt.ws.bdoc_demo.bean.CommonResponseBean
    * @Since V1.0
    */
    public CommonResponseBean update(ClusterBean cluster);


    /***
    * @Titile delete
    * @Author Administrator
    * @Date 2021/4/9 10:45
    * @Description TODO
    * @Param [cluster]
    * @Return com.chinamobile.rt.ws.bdoc_demo.bean.CommonResponseBean
    * @Since V1.0
    */
    public CommonResponseBean delete(ClusterBean cluster);
}
