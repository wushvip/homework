package com.chinamobile.cmss.bcse.sdk.entry;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.chinamobile.cmss.bcse.sdk.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.sdk.tools.ResultDecorate;
import com.chinamobile.cmss.bcse.sdk.util.StringUtil;


public class BCSEShield {
	
	private String ruleName;
	private List<String> ruleNames;
	private List<String> includeKeywords;
	
	private BCSEClient client;
	
	private String path="/config/shield";
	
    public BCSEShield(BCSEClient client){
    	this.client=client;
	}
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: TODO
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @return: String
	 */
	public String insert() throws ClientProtocolException, IOException{
		Map<String, Object> map = extract();
		return insert(map);
	}
	
	
	public String insert(Map<String,Object> opts) throws ClientProtocolException, IOException{
    	 if(this.client==null||!this.client.isFlag()){
    		 return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
         }
    	 return this.client.call(path, opts, "POST");
    }
	
	public String update() throws ClientProtocolException, IOException{
		Map<String, Object> map = extract();
		return update(map);
	}
	
	
	public String update(Map<String,Object> opts) throws ClientProtocolException, IOException{
    	 if(this.client==null||!this.client.isFlag()){
    		 return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
         }
    	 if(opts.get("ruleName") != null) {
             this.setRuleName((String) opts.get("ruleName"));
		}
    	String updatePath=path+"/"+ruleName;
    	return this.client.call(updatePath, opts, "PUT");
    }
	

	public String get() throws ClientProtocolException, IOException{
   	 	if(this.client==null||!this.client.isFlag()){
   		  return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
        }
   	 	Map<String,Object> map=new HashMap<String,Object>();
   	 	return this.client.call(path, map, "GET");
   }
	
	
	public String delete(String ruleName) throws ClientProtocolException, IOException{
   	 	if(this.client==null||!this.client.isFlag()){
   		  return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
        }
   	 	String deletePath=path+"/"+ruleName;
   	 	Map<String,Object> map=new HashMap<String,Object>();
   	 	return this.client.call(deletePath, map, "DELETE");
    }
	
	//批量删除
	public String delete(List<String> ruleNames) throws ClientProtocolException, IOException{
   	 	if(this.client==null||!this.client.isFlag()){
   		  return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
        }
   	 	Map<String,Object> map=new HashMap<String,Object>();
   	 	map.put("ruleNames",ruleNames);
   	 	String bashdeletePath=path+"/bash";
   	 	return this.client.call(bashdeletePath, map, "DELETE");
    }
	
	//批量删除
	public String bashDelete(List<String> ruleNames) throws ClientProtocolException, IOException{
   	 	if(this.client==null||!this.client.isFlag()){
   		  return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
        }
   	 	Map<String,Object> map=new HashMap<String,Object>();
   	 	map.put("ruleNames",ruleNames);
   	 	String bashdeletePath=path+"/bash";
   	 	return this.client.call(bashdeletePath, map, "POST");
    }	
	
	
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	
	public List<String> getRuleNames() {
		return ruleNames;
	}

	public void setRuleNames(List<String> ruleNames) {
		this.ruleNames = ruleNames;
	}

	public List<String> getIncludeKeywords() {
		return includeKeywords;
	}
	public void setIncludeKeywords(List<String> includeKeywords) {
		this.includeKeywords = includeKeywords;
	}
	
	private Map<String, Object> extract() {
		Map<String,Object> map=new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(getRuleName())){
			map.put("ruleName", getRuleName());
		}
		if(StringUtil.isNotEmpty(getIncludeKeywords())){
			map.put("includeKeywords", getIncludeKeywords());
		}
		return map;
	}
}
