package com.chinamobile.cmss.bcse.sdk.entry;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.chinamobile.cmss.bcse.sdk.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.sdk.tools.ResultDecorate;
import com.chinamobile.cmss.bcse.sdk.tools.UpdateDic;
import com.chinamobile.cmss.bcse.sdk.util.StringUtil;


public class BCSESpellDic {
	
	
	private BCSEClient client;
	private String fileName;
	private String mode;
	
	private String path="/recovery/dic";
	
    public BCSESpellDic(BCSEClient client){
    	this.client=client;
	}
	
	/**
	 * upload
	 * @Title: upload 
	 * @Description: TODO
	 * @param file
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @return: String
	 */
	public String upload() throws ClientProtocolException, IOException{
		 if(this.client==null||!this.client.isFlag()){
    		 return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
         }
		return UpdateDic.upload(client,path,getFileName(),getMode());
	}
	
	
	public String upload(Map<String,Object> opts) throws ClientProtocolException, IOException{
   	 	if(this.client==null||!this.client.isFlag()){
   	 		return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
        }
   	 	String name=opts.get("fileName").toString();
   	 	if(StringUtil.isNotEmpty(name)){
   	 		fileName=name;
   	 	}
   	 	
   	 	String m=opts.get("mode").toString();
	 	if(StringUtil.isNotEmpty(m)){
	 		mode=m;
	 	}
   	 	return UpdateDic.upload(client,path,fileName,mode);
   }
	
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
}
