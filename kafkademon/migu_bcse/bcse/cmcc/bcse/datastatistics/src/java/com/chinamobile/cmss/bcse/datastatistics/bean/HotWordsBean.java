package com.chinamobile.cmss.bcse.datastatistics.bean;




/** 
 * @ClassName: HotWordsBean 
 * @Description: TODO 热词
 * @author: chenmin
 * @date: 2016年1月29日 下午5:43:52  
 */
public class HotWordsBean {


    private String operTime;

    private String keywords;

    private Integer searchCount;


   



    public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Integer getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
    }

}