package com.chinamobile.rt.ws.bdoc_demo.service.cluster;/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-09 11:06
 * @Description
 * @Since V1.0
 */

import com.chinamobile.rt.ws.bdoc_demo.bean.CommonResponseBean;
import com.chinamobile.rt.ws.bdoc_demo.bean.cluster.ClusterBean;
import com.chinamobile.rt.ws.bdoc_demo.service.AbstractCluster;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-09 11:06
 * @Description
 * @Since V1.0
 */
public class CmhCluster extends AbstractCluster {
    @Override
    protected boolean checkConnection(String userName, String password) {
        return false;
    }

    @Override
    public CommonResponseBean update(ClusterBean cluster) {
        return super.update(cluster);
    }

    @Override
    public CommonResponseBean updateClusterInternal() {
        return null;
    }

    @Override
    public CommonResponseBean add(ClusterBean cluster) {
        return null;
    }

    @Override
    public CommonResponseBean delete(ClusterBean cluster) {
        return null;
    }
}
