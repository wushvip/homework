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
 * @ClassName: BCSERecovery 
 * @Description: TODO 糾錯接口
 * @author: chenmin
 * @date: 2016年4月13日 上午9:46:05  
 */
public class BCSECorrect {
	
	private BCSEClient client;
	private String path = "/recovery";
	private String searchQuery;
	private String isLoadDic="1";
	
	public BCSECorrect(String searchQuery,BCSEClient client){
    	this.client=client;
    	this.searchQuery=searchQuery;
	}
	public BCSECorrect(String searchQuery,BCSEClient client,String isLoadDic){
		this.isLoadDic=isLoadDic;
		this.client=client;
    	this.searchQuery=searchQuery;
	}
    
    public BCSECorrect(BCSEClient client){
    	this.client=client;
    }
    public BCSECorrect(){
    	
    }
    
    
    public String recovery(Map<String, Object> opts) throws ClientProtocolException, IOException{
    	
    	 if(this.client==null||!this.client.isFlag()){
    		 return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
         }
    	 if ((opts != null) && (opts.size() > 0)) {
    		 try{
    			 if (opts.get("isLoadDic")!=null && !"0".equals(opts.get("isLoadDic")) && !"1".equals(opts.get("isLoadDic"))) {
    				 return ResultDecorate.decorate("", "FAIL", "isLoadDic不在规定范围内", ExceptionConstants.FAIL_SERIVICE_CODE);
    			 }
    		 }catch(Exception e){
    			 e.printStackTrace();
    		 }
 		}
    	extract(opts);
		String result=call();
		return  result;
    }
    
    public String recovery() throws ClientProtocolException, IOException{
		return recovery(new HashMap());
    }
    
	/** 
	 * @Title: extract 
	 * @Description: TODO  通过map去set变量的值域
	 * @param opts
	 * @return: void
	 */
	public void extract(Map<String, Object> opts) {
		
		if ((opts != null) && (opts.size() > 0)) {
			if (opts.get("searchQuery") != null) {
                this.setSearchQuery((String) opts.get("searchQuery"));
			}
			if (opts.get("isLoadDic") != null) {
                this.setIsLoadDic((String) opts.get("isLoadDic"));			
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
		opts.put("isLoadDic", getIsLoadDic());
		return this.client.call(path, opts, "GET");
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
	    	 status=(String) object.get("status");
	    	 result=(String) object.get("recovery");
		 } catch (Exception e) {
			e.printStackTrace();
		 }
		return ResultDecorate.decorate(result, status, message, code);
	}



	public BCSEClient getClient() {
		return client;
	}




	public void setClient(BCSEClient client) {
		this.client = client;
	}
	public String getSearchQuery() {
		return searchQuery;
	}
	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	public String getIsLoadDic() {
		return isLoadDic;
	}
	public void setIsLoadDic(String isLoadDic) {
		this.isLoadDic = isLoadDic;
	}
	



	
}
