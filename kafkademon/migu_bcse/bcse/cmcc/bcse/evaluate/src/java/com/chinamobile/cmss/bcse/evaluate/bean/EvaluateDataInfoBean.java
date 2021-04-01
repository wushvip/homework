package com.chinamobile.cmss.bcse.evaluate.bean;

import java.util.List;

public class EvaluateDataInfoBean {
    private String id;

    private String sourceDir;
    
    private List<SingleDataBean> dataList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

	public String getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(String sourceDir) {
		this.sourceDir = sourceDir;
	}

	public List<SingleDataBean> getDataList() {
		return dataList;
	}

	public void setDataList(List<SingleDataBean> dataList) {
		this.dataList = dataList;
	}
	

}