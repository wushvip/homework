package com.chinamobile.rt.ws.bdoc_demo.service.cluster;/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-09 11:06
 * @Description
 * @Since V1.0
 */

import com.chinamobile.rt.ws.bdoc_demo.bean.CommonResponseBean;
import com.chinamobile.rt.ws.bdoc_demo.bean.cluster.ClusterBean;
import com.chinamobile.rt.ws.bdoc_demo.service.Component.Component;
import org.springframework.stereotype.Service;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-09 11:06
 * @Description
 * @Since V1.0
 */
@Service(value = "cmhCluster")
public class CmhCluster extends AbstractCluster {

    public CmhCluster(){
        System.out.println("cmhCluster initing");
    }

    private ConnectVerifyService connectVerifyService;

    @Override
    public void setConnectVerifyService(ConnectVerifyService connectVerifyService){
        super.setConnectVerifyService(connectVerifyService);
        this.connectVerifyService = connectVerifyService;
    }

    @Override
    protected boolean checkConnection(String userName, String password) {
        return false;
    }

    @Override
    protected boolean standardAddCluster(ClusterBean cluster) {
        System.out.println("集群信息保存成功！");
        return true;
    }

//    @Override
//    public CommonResponseBean update(ClusterBean cluster) {
//        return super.update(cluster);
//    }

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




    private class CommonComponent implements Component{
        public CommonComponent(){
            System.out.println("CommonComponent initing");
        }
        @Override
        public String getServiceName() {
            return "commonComponent";
        }
    }


    public static void main(String[] args) {
        CmhCluster cmhCluster = new CmhCluster();
    }
}
