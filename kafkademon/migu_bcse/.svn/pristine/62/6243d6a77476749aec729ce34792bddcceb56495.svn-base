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
 * @ClassName: BCSEAnalysis
 * @Description: TODO 分詞接口
 * @author: chenmin
 * @date: 2016年4月13日 上午9:45:33
 */
public class BCSEAnalysis {

	private BCSEClient client;
	private String path = "/analyzer";
	private String searchQuery;
	private String fieldSearch;

	
	public BCSEAnalysis(String searchQuery, String fieldSearch, BCSEClient client) {
		this.searchQuery = searchQuery;
		this.fieldSearch = fieldSearch;
		this.client = client;
	}
	
	public BCSEAnalysis(BCSEClient client) {
		this.client = client;
	} 

	/** 
	 * @Title: analysis 
	 * @Description: TODO
	 * @param opts
	 * @return
	 * @return: String
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public String analysis(Map<String, Object> opts) throws ClientProtocolException, IOException {
        if(this.client==null||!this.client.isFlag()){
        	return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
        }
		extract(opts);
		String result=call();
		return  result;

	}

	public String analysis() throws ClientProtocolException, IOException {

		return analysis(new HashMap());

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
			if(opts.get("fieldSearch")!=null){
				this.setFieldSearch((String) opts.get("fieldSearch"));
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
		Map<String, Object> opts = new HashMap<String, Object>();
		opts.put("searchQuery", getSearchQuery());
		opts.put("fieldSearch", getFieldSearch());
		return this.client.call(path, opts, "GET");
	}

	/** 
	 * 
	 * @Title: resultDecorate 
	 * @Description: TODO 封装结果
	 * @param result
	 * @return
	 * @return: String
	 * 
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
	    	 result= object.get("analysis").toString();
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

	public String getFieldSearch() {
		return fieldSearch;
	}

	public void setFieldSearch(String fieldSearch) {
		this.fieldSearch = fieldSearch;
	}
	
	

}
