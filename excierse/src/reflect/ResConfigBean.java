package reflect;

import java.util.List;

/**
 * Created by wangyaping on 2017/9/19.
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@ApiModel(value="ResConfigBean", description = "资源配置对象")
public class ResConfigBean {
    public ResConfigBean(){}

    public ResConfigBean(float cpu, float ram, float storage, float portNum){
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
        this.portNum = portNum;
    }
    public ResConfigBean(float cpu, float ram, float storage){
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
    }
    public ResConfigBean(float storage){
        this.storage = storage;
    }
    private float cpu;
    private float ram;
    private float storage;
    private float count;
    private String name;
    private float size;
    //hbase package
    // native-hbase
    private long speeds;
    private long requests;
    private long tables;
    private long regions;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    private double rate;
    private String role;
//    private List<ResBean> mysqls;
//    private List<ResBean> gbases;
    public long getNodes() {
        return nodes;
    }

    public void setNodes(long nodes) {
        this.nodes = nodes;
    }
  // flume nodes
    private long nodes;

    public long getKfkNodes() {
        return kfkNodes;
    }

    public void setKfkNodes(long kfkNodes) {
        this.kfkNodes = kfkNodes;
    }

    private long kfkNodes;

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    private int orgId;
    private int roleId;
//    public List<ResBean> getMysqls() {
//        return mysqls;
//    }
//
//    public void setMysqls(List<ResBean> mysqls) {
//        this.mysqls = mysqls;
//    }
//
//    public List<ResBean> getGbases() {
//        return gbases;
//    }
//
//    public void setGbases(List<ResBean> gbases) {
//        this.gbases = gbases;
//    }
//
//    public List<ResBean> getVerticas() {
//        return verticas;
//    }
//
//    public void setVerticas(List<ResBean> verticas) {
//        this.verticas = verticas;
//    }
//
//    public List<ResBean> getHybrids() {
//        return hybrids;
//    }
//
//    public void setHybrids(List<ResBean> hybrids) {
//        this.hybrids = hybrids;
//    }
//
//    private List<ResBean> verticas;
//    private List<ResBean> hybrids;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getSpeeds() {
        return speeds;
    }

    public void setSpeeds(long speeds) {
        this.speeds = speeds;
    }

    public long getRequests() {
        return requests;
    }

    public void setRequests(long requests) {
        this.requests = requests;
    }

    public long getTables() {
        return tables;
    }

    public void setTables(long tables) {
        this.tables = tables;
    }

    public long getRegions() {
        return regions;
    }

    public void setRegions(long regions) {
        this.regions = regions;
    }

    public long getPartitions() {
        return partitions;
    }

    public void setPartitions(long partitions) {
        this.partitions = partitions;
    }

    public long getReplicas() {
        return replicas;
    }

    public void setReplicas(long replicas) {
        this.replicas = replicas;
    }

    public long getRetentions() {
        return retentions;
    }

    public void setRetentions(long retentions) {
        this.retentions = retentions;
    }

    // kafka package
    private long partitions;
    private long replicas;
    private  long retentions;

//    public ClusterBean getCluster() {
//        return cluster;
//    }
//
//    public void setCluster(ClusterBean cluster) {
//        this.cluster = cluster;
//    }
//
//    private ClusterBean cluster;
    private String volumeName;
    private String lbAddress;
    private String lbName;
    private String lbType;

    public String getLbAddress() {
        return lbAddress;
    }

    public void setLbAddress(String lbAddress) {
        this.lbAddress = lbAddress;
    }

    public String getLbName() {
        return lbName;
    }

    public void setLbName(String lbName) {
        this.lbName = lbName;
    }

    public String getLbType() {
        return lbType;
    }

    public void setLbType(String lbType) {
        this.lbType = lbType;
    }

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getPortsNumber() {
        return portsNumber;
    }

    public void setPortsNumber(int portsNumber) {
        this.portsNumber = portsNumber;
    }

    private int portsNumber;

    public float getPortNum() {
        return portNum;
    }

    public void setPortNum(float portNum) {
        this.portNum = portNum;
    }

    public float getSession() {
        return session;
    }

    public void setSession(float session) {
        this.session = session;
    }

   private String priorityLevel;

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    private float portNum;
    private float session;


    public float getParallel() {
        return parallel;
    }

    public void setParallel(float parallel) {
        this.parallel = parallel;
    }

    private float parallel;

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public double getPiece() {
        return piece;
    }

    public void setPiece(double piece) {
        this.piece = piece;
    }

    private double piece;
    public float getCpu() {
        return cpu;
    }

    public void setCpu(float cpu) {
        this.cpu = cpu;
    }

    public float getRam() {
        return ram;
    }

    public void setRam(float ram) {
        this.ram = ram;
    }

    public float getStorage() {
        return storage;
    }

    public void setStorage(float storage) {
        this.storage = storage;
    }

}
