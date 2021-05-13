package com.chinamobile.rt.ws.bdoc_demo.service.cluster;/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-09 10:46
 * @Description
 * @Since V1.0
 */

import com.chinamobile.rt.ws.bdoc_demo.bean.CommonResponseBean;
import com.chinamobile.rt.ws.bdoc_demo.bean.cluster.ClusterBean;
import com.chinamobile.rt.ws.bdoc_demo.service.Component.Component;
import com.chinamobile.rt.ws.bdoc_demo.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Resource;
import java.util.List;

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

    private List<Component> components;

//    @Autowired
    private ConnectVerifyService connectVerifyService;

    public AbstractCluster(){
        System.out.println("AbstractCluster initing");
    }

    public void setConnectVerifyService(ConnectVerifyService connectVerifyService){
        this.connectVerifyService = connectVerifyService;
    }


    protected abstract boolean checkConnection(String userName,String password);

    //增加集群
    @Override
    public final CommonResponseBean add(ClusterBean cluster) {
        if(null==connectVerifyService){
            connectVerifyService = SpringContextHolder.getBeanByName("connectVerifyServiceImpl");
        }

        this.standardAddCluster(cluster);
        connectVerifyService.testConnect(cluster.getName(),cluster.getPassword());
        return null;
    }

    protected abstract boolean standardAddCluster(ClusterBean cluster);

    //定义模板
    @Override
    public final CommonResponseBean update(ClusterBean cluster) {

        connectVerifyService.testConnect(cluster.getName(),cluster.getPassword());

        //1、校验集群基本参数
        //2、检验集群连接状态
         checkConnection(cluster.getUser(),cluster.getPassword());

        //3、覆盖keytab与krb文件
        //4、拉取组件配额(钩子函数)
        //5、检查组件连接状态

        //6、保存集群信息与组件配置信息

        update(cluster);

        return null;
    }
    @Override
    public boolean isAlived(String url, String params) {
        return false;
    }


    public abstract CommonResponseBean updateClusterInternal();



    protected void saveKeytab (){

    }

    protected List<Component> fetchComponents (int clusterId){
        return null;
    }
}
