package com.chinamobile.cmss.bcse.search.querytype;

import java.util.ArrayList;

import org.apache.solr.client.solrj.SolrQuery;

import com.chinamobile.cmss.bcse.tool.config.Config;

/**
 *
 * @author chenmin
 * @date   2015年11月28日
 *
 * TODO
 *
 */
public class UniqueIdSearchQuery extends SolrQuery{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 
	 * @Title:UniqueIdSearchQuery
	 * @Description:TODO 通过查询获得唯一键
	 * @param idList
	 * @param userName
	 * @param appName
	 * @param keyString 
	 */
	public UniqueIdSearchQuery(ArrayList<String> idList,String userName,
			String appName,String keyString){
		
		if(idList!=null&&keyString!=null){
			String queryString="";
			for(String id:idList){
				queryString=queryString+" OR "+keyString+":"+id;
			}
			if(!queryString.equals("")){
				queryString=queryString.replaceFirst("OR", "");
			}
			this.set("q", queryString);
			this.set("fl", Config.UNIQUE_ID+","+keyString);
		}
		
		
	}
    public static void main(String[] a){
    //	Str
    	String queryString="OR OPERATOR_CODE:123 OR OPERATOR_CODE:123 OR OPERATOR_CODE:123 ";
    	System.out.println(queryString.replaceFirst("OR", ""));
    }
}
