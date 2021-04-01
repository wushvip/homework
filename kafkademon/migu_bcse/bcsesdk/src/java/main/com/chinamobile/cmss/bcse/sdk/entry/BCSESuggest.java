package com.chinamobile.cmss.bcse.sdk.entry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.sdk.tools.ResultDecorate;

/** 
 * @ClassName: BCSESuggest 
 * @Description: TODO 智能提示接口
 * @author: chenmin
 * @date: 2016年4月13日 上午9:46:35  
 */
public class BCSESuggest {
	
	private String searchQuery;
	
	private BCSEClient client;
	
	private String path="/suggestion";
	private JSONObject filterJson = new JSONObject();
	private String sortConfig;
	
	
	public BCSESuggest(String searchQuery,JSONObject filterJson,String sortConfig,BCSEClient client){
		this.client=client;
		this.filterJson=filterJson;
		this.searchQuery=searchQuery;
		this.sortConfig = sortConfig;
	}
	
   

	public BCSESuggest(){
		
	}
    public BCSESuggest(BCSEClient client){
    	this.client=client;
	}
    public String suggest(Map<String,Object> opts) throws ClientProtocolException, IOException{
    	 if(this.client==null||!this.client.isFlag()){
    		 return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
         }
    	extract(opts);
		String result=call();
		return  result;
    }
    
    public String suggest() throws ClientProtocolException, IOException{
    	return suggest(new HashMap());
    }
	
	/** 
	 * @Title: extract 
	 * @Description: TODO  通过map去set变量的值域
	 * @param opts
	 * @return: void
	 */
	public void extract(Map<String, Object> opts) {
		
		if ((opts != null) && (opts.size() > 0)) {
			if(opts.get("searchQuery")!=null){
				this.setSearchQuery((String) opts.get("searchQuery"));
			}
			
			if(opts.get("filterJson")!=null){
				this.setFilterJson( (JSONObject)opts.get("filterJson"));
			}
			if(opts.get("sortConfig")!=null){
				this.setSortConfig( (String)opts.get("sortConfig"));
			}
			
		}

	}
	/** 
	 * @Title: call 
	 * @Description: TODO 通过客户端发送请求
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @return: String
	 */
	private String call() throws ClientProtocolException, IOException{
		Map<String, Object> opts = new HashMap();
		opts.put("searchQuery", getSearchQuery());
		
		opts.put("filterJson", getFilterJson());
		opts.put("sortConfig", getSortConfig());
		return this.client.call(path, opts, "POST");
	}
	
	/** 
	 * @Title: resultDecorate 
	 * @Description: TODO 封装结果
	 * @param result
	 * @return
	 * @return: String
	 */
	public String resultDecorate(String result){
		 String status="SUCCESS";
	     String message="";
	     String code="";
	     try {
	    	 JSONObject object=JSON.parseObject(result);
	    	 message=(String) object.get("message");
	    	 code=(String) object.get("code");
	    	 boolean success=(boolean) object.get("success");
	    	 if(!success){
	    		 status="FAIL";
	    	 }
		 } catch (Exception e) {
			e.printStackTrace();
		 }
		return ResultDecorate.decorate(result, status, message, code);
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public BCSEClient getClient() {
		return client;
	}

	public void setClient(BCSEClient client) {
		this.client = client;
	}

	public JSONObject getFilterJson() {
		return filterJson;
	}

	public void setFilterJson(JSONObject filterJson) {
		this.filterJson = filterJson;
	}
	
	public String getSortConfig() {
		return sortConfig;
	}

	public void setSortConfig(String sortConfig) {
		this.sortConfig = sortConfig;
	}
}
