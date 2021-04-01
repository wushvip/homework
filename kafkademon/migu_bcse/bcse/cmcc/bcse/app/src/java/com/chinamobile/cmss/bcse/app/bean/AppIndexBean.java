package com.chinamobile.cmss.bcse.app.bean;

/**
 * 
 * @ClassName: AppIndexBean 
 * @Description: 应用的索引信息
 * @author: jinjing
 * @date: 2016年2月17日 上午9:12:58
 */
public class AppIndexBean {
    private String id;

    private String appId;

    private String createDate;

    private String ruleName;

    private String includeFields;

    private String clauseUsage;

    private String comment;

    private String appTempFlag;

    public String getId() {
        return id;
    }

    public void setIndexId(String indexId) {
        this.id = indexId == null ? null : indexId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public String getIncludeFields() {
        return includeFields;
    }

    public void setIncludeFields(String includeFields) {
        this.includeFields = includeFields == null ? null : includeFields.trim();
    }

    public String getClauseUsage() {
        return clauseUsage;
    }

    public void setClauseUsage(String clauseUsage) {
        this.clauseUsage = clauseUsage == null ? null : clauseUsage.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getAppTempFlag() {
        return appTempFlag;
    }

    public void setAppTempFlag(String appTempFlag) {
        this.appTempFlag = appTempFlag == null ? null : appTempFlag.trim();
    }
}