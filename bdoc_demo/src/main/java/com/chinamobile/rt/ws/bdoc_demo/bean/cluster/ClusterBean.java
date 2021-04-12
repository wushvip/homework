package com.chinamobile.rt.ws.bdoc_demo.bean.cluster;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="ClusterBean", description = "Cluster 信息")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClusterBean extends  BaseBean {

    @ApiModelProperty(value ="cluster type")
    private String type;

    @ApiModelProperty(value ="cluster name")
    @NotNull(message = "名字不能为空")
    private String name;//存储池可以用这个作为名称

    @ApiModelProperty(value ="involved k8s cluster name")
    private String involvedclusterName;//负载均衡器绑定的k8s集群

    @ApiModelProperty(value ="involved k8s cluster id")
    private long kuberneteid;//负载均衡器绑定的k8s集群id

    @ApiModelProperty(value ="cluster Bottom name")
    private String bottomName;

    @ApiModelProperty(value ="cluster display name")
    private String displayName;

//    @ApiModelProperty(value ="cluster path")
//    private ResPoolBean path;

    @ApiModelProperty(value ="cluster description")
    private String description;

    @ApiModelProperty(value ="cluster url")
    private String url;

    @ApiModelProperty(value ="cluster user")
    private String user;


    @ApiModelProperty(value ="cluster password")
    private String password;

//    @ApiModelProperty(value ="cluster info")
//    private ComptBean cluster;
//
//    @ApiModelProperty(value ="services info"/*, dataType = "java.util.Map[String, com.chinamobile.cmss.bdoc.om.bean.cluster.ComptBean]"*/)
//    private List<ComptBean> services;

//    @ApiModelProperty(value ="cluster hosts")
//    private List<HostBean> hosts;

    private String namespace;

    /** 添加存储卷 */
    private Boolean useStorage;
    private double storage;

    /** 网络 */
//    private Boolean useNet;
//    private List<ClusterNetBean> nets;

    private long oracleClusterId;

    private String sid;

    private String domain;

    private String version;

    private String uuid;

    private String tag;

    private Integer  parentclusterid;

    //private List<ProvinceBean> province;

//    private List<ClusterProvincesBean> province;

    private String addType;

    private String brokerAddress;

    private String brokerType;

    private Integer ipCount;//kafka、flume的节点个数

    private List<ClusterBean> children;

    @ApiModelProperty(value ="cluster config file map")
    private Map<String, String> configFileMap;


    private String nsList;

    private boolean nodelabelstag;

    private String nodelabels;

    //是否支持集群bcid
    private Boolean bcidtag;

    private String bcid;

    //是否为公有还是私有镜像仓库

    private Boolean ispublic;

    private long storagesize;

//    private List<LabelBean> labels;

    //负载均衡器IP
    private String ip;

    //存储池
//    private GfsParamsBean gfsParams;
//
//    private CephParamsBean cephParams;

    //https支持的token
    private String tokenInfo;

    public String getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    //镜像仓库的主机数
    private int hostNum;


    //组件特性类别
    private String compomentType;

    private int compomentFeature;

    public long getStoragesize() {
        return storagesize;
    }

    public void setStoragesize(long storagesize) {
        this.storagesize = storagesize;
    }


    public Boolean getBcidtag() {
        return bcidtag;
    }

    /*public long getStoragesize() {
        return storagesize;
    }

    public void setStoragesize(long storagesize) {
        this.storagesize = storagesize;
    }

    public String getSecretkeydomain() {
        return secretkeydomain;
    }

    public void setSecretkeydomain(String secretkeydomain) {
        this.secretkeydomain = secretkeydomain;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getRecoverystrategy() {
        return recoverystrategy;
    }

    public void setRecoverystrategy(String recoverystrategy) {
        this.recoverystrategy = recoverystrategy;
    }

    public String getBondSchema() {
        return bondSchema;
    }

    public void setBondSchema(String bondSchema) {
        this.bondSchema = bondSchema;
    }
*/


//    private KubernetesClusterBean kubernetes;
//
//
//    public MirrorStorgeBean getStoragevalue() {
//        return storagevalue;
//    }
//
//    public void setStoragevalue(MirrorStorgeBean storagevalue) {
//        this.storagevalue = storagevalue;
//    }
//
//    MirrorStorgeBean storagevalue;
//




    public double getStorage() {
        return storage;
    }

    public ClusterBean setStorage(double storage) {
        this.storage = storage;
        return this;
    }

    public Boolean getUseStorage() {
        return useStorage;
    }

    public ClusterBean setUseStorage(Boolean useStorage) {
        this.useStorage = useStorage;
        return this;
    }


    public String getNamespace() {
        return namespace;
    }

    public ClusterBean setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

//    public ComptBean getCluster() {
//        return cluster;
//    }
//
//    public void setCluster(ComptBean cluster) {
//        this.cluster = cluster;
//    }
//
//    public List<ComptBean>  getServices() {
//        return services;
//    }
//
//    public void setServices(List<ComptBean>  services) {
//        this.services = services;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBottomName() {
        return bottomName;
    }

    public void setBottomName(String bottomName) {
        this.bottomName = bottomName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public ResPoolBean getPath() {
//        return path;
//    }
//
//    public void setPath(ResPoolBean path) {
//        this.path = path;
//    }
//
//    public List<HostBean> getHosts() {
//        return hosts;
//    }
//
//    public void setHosts(List<HostBean> hosts) {
//        this.hosts = hosts;
//    }

    public String getDisplayName() {
        return displayName;
    }

    public ClusterBean setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public long getOracleClusterId() {
        return oracleClusterId;
    }

    public ClusterBean setOracleClusterId(long oracleClusterId) {
        this.oracleClusterId = oracleClusterId;
        return this;
    }

    public String getSid() {
        return sid;
    }

    public ClusterBean setSid(String sid) {
        this.sid = sid;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

//    @Override
//    public String toString() {
//        return "ClusterBean{" +
//                "type='" + type + '\'' +
//                ", name='" + name + '\'' +
//                ", displayName='" + displayName + '\'' +
//                ", path=" + path +
//                ", description='" + description + '\'' +
//                ", url='" + url + '\'' +
//                ", user='" + user + '\'' +
//                ", password='" + password + '\'' +
//                ", cluster=" + cluster +
//                ", services=" + services +
//                ", hosts=" + hosts +
//                ", namespace='" + namespace + '\'' +
//                ", useStorage=" + useStorage +
//                ", storage=" + storage +
//                ", net=" + nets +
//                ", oracleClusterId=" + oracleClusterId +
//                ", sid='" + sid + '\'' +
//                ", domain='" + domain + '\'' +
//                '}';
//    }

    public Map<String, String> getConfigFileMap() {
        return configFileMap;
    }

    public void setConfigFileMap(Map<String, String> configFileMap) {
        this.configFileMap = configFileMap;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUuid() {
        if (uuid == null) {
            uuid = "";
        }
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getParentclusterid() {
        return parentclusterid;
    }

    public void setParentclusterid(Integer parentclusterid) {
        this.parentclusterid = parentclusterid;
    }


    public String getAddType() {
        return addType;
    }

    public void setAddType(String addType) {
        this.addType = addType;
    }



    public String getNsList() {
        return nsList;
    }

    public void setNsList(String nsList) {
        this.nsList = nsList;
    }


    public boolean isNodelabelstag() {
        return nodelabelstag;
    }

    public void setNodelabelstag(boolean nodelabelstag) {
        this.nodelabelstag = nodelabelstag;
    }

    public String getNodelabels() {
        return nodelabels;
    }

    public void setNodelabels(String nodelabels) {
        this.nodelabels = nodelabels;
    }

    //public List<ProvinceBean> getProvince() {
    //    return province;
   // }

    //public void setProvince(List<ProvinceBean> province) {
   //     this.province = province;
   // }

    public List<ClusterBean> getChildren() {
        return children;
    }

    public void setChildren(List<ClusterBean> children) {
        this.children = children;
    }

    public String getBrokerAddress() {
        return brokerAddress;
    }

    public void setBrokerAddress(String brokerAddress) {
        this.brokerAddress = brokerAddress;
    }

    public String getBrokerType() {
        return brokerType;
    }

    public void setBrokerType(String brokerType) {
        this.brokerType = brokerType;
    }

    public Integer getIpCount() {
        return ipCount;
    }

    public void setIpCount(Integer ipCount) {
        this.ipCount = ipCount;
    }

    public Boolean isBcidtag() {
        return bcidtag;
    }

    public void setBcidtag(Boolean bcidtag) {
        this.bcidtag = bcidtag;
    }

    public String getBcid() {
        return bcid;
    }

    public void setBcid(String bcid) {
        this.bcid = bcid;
    }

    public Boolean getIspublic() {
        return ispublic;
    }

    public void setIspublic(Boolean ispublic) {
        this.ispublic = ispublic;
    }

    public String getInvolvedclusterName() {
        return involvedclusterName;
    }

    public void setInvolvedclusterName(String involvedclusterName) {
        this.involvedclusterName = involvedclusterName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

   /* public String getStoragevendor() {
        return storagevendor;
    }

    public void setStoragevendor(String storagevendor) {
        this.storagevendor = storagevendor;
    }*/



    /*public Long getGidMin() {
        return gidMin;
    }

    public void setGidMin(Long gidMin) {
        this.gidMin = gidMin;
    }

    public Long getGidMax() {
        return gidMax;
    }

    public void setGidMax(Long gidMax) {
        this.gidMax = gidMax;
    }*/



    public long getKuberneteid() {
        return kuberneteid;
    }

    public void setKuberneteid(long kuberneteid) {
        this.kuberneteid = kuberneteid;
    }

    public int getHostNum() {
        return hostNum;
    }

    public void setHostNum(int hostNum) {
        this.hostNum = hostNum;
    }

    public String getCompomentType() {
        return compomentType;
    }

    public void setCompomentType(String compomentType) {
        this.compomentType = compomentType;
    }

    public int getCompomentFeature() {
        return compomentFeature;
    }

    public void setCompomentFeature(int compomentFeature) {
        this.compomentFeature = compomentFeature;
    }

   /* public Boolean getIsAdvanced() {
        return isAdvanced;
    }

    public void setIsAdvanced(Boolean advanced) {
        isAdvanced = advanced;
    }

    public Boolean getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Boolean isauth) {
        isAuth = isauth;
    }

    public String getAuthuser() {
        return authuser;
    }

    public void setAuthuser(String authuser) {
        this.authuser = authuser;
    }




    public List<LabelBean> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelBean> labels) {
        this.labels = labels;
    }

    public List<String> getMonitors() {
        return monitors;
    }

    public void setMonitors(List<String> monitors) {
        this.monitors = monitors;
    }

    public String getAdminsecret() {
        return adminsecret;
    }

    public void setAdminsecret(String adminsecret) {
        this.adminsecret = adminsecret;
    }

    public String getUsersecret() {
        return usersecret;
    }

    public void setUsersecret(String usersecret) {
        this.usersecret = usersecret;
    }



    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }



    public String getFsType() {
        return fsType;
    }

    public void setFsType(String fsType) {
        this.fsType = fsType;
    }

    public String getImageformat() {
        return imageformat;
    }

    public void setImageformat(String imageformat) {
        this.imageformat = imageformat;
    }

    public String getImagecharacter() {
        return imagecharacter;
    }

    public void setImagecharacter(String imagecharacter) {
        this.imagecharacter = imagecharacter;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }*/
}
