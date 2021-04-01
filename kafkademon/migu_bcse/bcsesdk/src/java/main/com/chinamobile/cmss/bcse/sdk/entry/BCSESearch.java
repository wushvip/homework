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
 * @ClassName: BCSESearch 
 * @Description: TODO 搜索接口
 * @author: chenmin
 * @date: 2016年4月13日 上午9:45:11  
 */
public class BCSESearch {
	
	private String searchQuery;
	private int pageNum=10;
	private String sortRule;
	private String ct;
	private String bookType;

	private String facetRule;
	private int pageIndex=1;
	private JSONObject filterJson=new JSONObject();
	private HashMap<String, Object> paramMap = new HashMap<String, Object>();
	private HashMap<String, Object> logMap = new HashMap<String, Object>();
	public String getFacetRule() {
		return facetRule;
	}
	public void setFacetRule(String facetRule) {
		this.facetRule = facetRule;
	}
	private BCSEClient client;
	
	private String path="/search";
	private String sortConfig;
	private String highLightFields;
	private  JSONObject advancedParams= new JSONObject();
	
	public BCSESearch(BCSEClient client){
		this.client=client;
	}
    public BCSESearch(){
		
	}
    
    public String search(Map<String,Object> opts) throws ClientProtocolException, IOException{
    	
    	 if(this.client==null||!this.client.isFlag()){
    		 return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
         }
    	extract(opts);
		String result=call();
		return  result;
    }
    
    public String search() throws ClientProtocolException, IOException{
    	return search(new HashMap());
    }
    
    
    
    /**
     * 
     * @Title: saveSearchLog 
     * @Description: 搜索日志由业务端进行写入
     * @param opts
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @return: String
     */
    public String saveSearchLog(Map<String,Object> opts) throws ClientProtocolException, IOException{
    	
   	 if(this.client==null||!this.client.isFlag()){
   		 return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
        }
   	 	extract(opts);
   	 	path = path+"/saveSearchLog";
		String result=call();
		return  result;
   }
    
    public String saveSearchLog() throws ClientProtocolException, IOException{
    	return saveSearchLog(new HashMap());
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
			if(opts.get("sortRule")!=null){
				this.setSortRule((String) opts.get("sortRule"));
			}
			
			if(opts.get("facetRule")!=null){
				this.setFacetRule((String)opts.get("facetRule"));
			}
			
			if(opts.get("filterJson")!=null){
				this.setFilterJson((JSONObject) opts.get("filterJson"));
			}
			if(opts.get("pageNum")!=null){
				try {
					this.setPageNum( (int) opts.get("pageNum"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			if(opts.get("pageIndex")!=null){
				try {
					this.setPageIndex( (int) opts.get("pageIndex"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			if(opts.get("sortConfig")!=null){
				this.setSortConfig((String) opts.get("sortConfig"));
			}
			if(opts.get("highLightFields")!=null){
				this.setHighLightFields((String) opts.get("highLightFields"));
			}
			if(opts.get("advancedParams")!=null){
				this.setAdvancedParams((JSONObject) opts.get("advancedParams"));
			}	
			if (opts.get("ct") != null) {
                this.setCt((String)opts.get("ct"));
			}
			if (opts.get("bookType") != null) {
                this.setBookType((String)opts.get("bookType"));
			}
			if (opts.get("paramMap") != null) {
                this.setParamMap((HashMap<String, Object>) (opts.get("paramMap")));
			}
			if (opts.get("logMap") != null) {
                this.setLogMap((HashMap<String, Object>) (opts.get("logMap")));
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
		opts.put("pageIndex", getPageIndex());
		opts.put("pageNum", getPageNum());
		opts.put("sortRule", getSortRule());
		opts.put("facetRule", getFacetRule());
		opts.put("filterJson", getFilterJson());
		opts.put("sortConfig", getSortConfig());
		opts.put("highLightFields", getHighLightFields());
		opts.put("advancedParams", getAdvancedParams());
		opts.put("ct", getCt());
		opts.put("bookType", getBookType());
		opts.put("paramMap", getParamMap());
		opts.put("logMap", getLogMap());
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
	
	public String getSortRule() {
		return sortRule;
	}
	public void setSortRule(String sortRule) {
		this.sortRule = sortRule;
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
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
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
	public String getHighLightFields() {
		return highLightFields;
	}
	public void setHighLightFields(String highLightFields) {
		this.highLightFields = highLightFields;
	}
	public JSONObject getAdvancedParams() {
		return advancedParams;
	}
	public void setAdvancedParams(JSONObject advancedParams) {
		this.advancedParams = advancedParams;
	}
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public HashMap<String, Object> getParamMap() {
		return paramMap;
	}
	public void setParamMap(HashMap<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	public HashMap<String, Object> getLogMap() {
		return logMap;
	}
	public void setLogMap(HashMap<String, Object> logMap) {
		this.logMap = logMap;
	}
	
	
	
	
}
