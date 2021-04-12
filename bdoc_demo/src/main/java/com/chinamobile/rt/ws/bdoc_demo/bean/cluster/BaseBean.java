package com.chinamobile.rt.ws.bdoc_demo.bean.cluster;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by wukuanzhan on 2017/4/5.
 */
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseBean {
    private Integer id;

    private String description;

    private Integer createdBy;

    private Integer updatedBy;

    private Long createTime;

    private Long updateTime;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * @return the createdBy
     */
    public Integer getCreatedBy() {
        return createdBy;
    }
    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * @return the updatedBy
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }
    /**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
    /**
     * @return the createTime
     */
    public Long getCreateTime() {
        return createTime;
    }
    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    /**
     * @return the updateTime
     */
    public Long getUpdateTime() {
        return updateTime;
    }
    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
