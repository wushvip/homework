package com.chinamobile.rt.ws.bdoc_demo.service.cluster;/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-09 13:59
 * @Description
 * @Since V1.0
 */

import com.chinamobile.rt.ws.bdoc_demo.bean.CommonResponseBean;
import com.chinamobile.rt.ws.bdoc_demo.bean.cluster.ClusterBean;
import org.springframework.stereotype.Service;

/**
 * @Title
 * @Author wushuai
 * @Date 2021-04-09 13:59
 * @Description
 * @Since V1.0
 */
@Service
public class ContainerCluster extends AbstractCluster {
    @Override
    protected boolean checkConnection(String userName, String password) {
        return false;
    }

    @Override
    protected boolean standardAddCluster(ClusterBean cluster) {
        return false;
    }

    @Override
    public CommonResponseBean updateClusterInternal() {
        return null;
    }

//    @Override
//    public CommonResponseBean add(ClusterBean cluster) {
//        return null;
//    }

    @Override
    public CommonResponseBean delete(ClusterBean cluster) {
        return null;
    }
}
