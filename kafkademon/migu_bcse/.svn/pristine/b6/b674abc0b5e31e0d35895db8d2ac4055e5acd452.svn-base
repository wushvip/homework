package com.chinamobile.cmss.bcse.sdk.entry;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.chinamobile.cmss.bcse.sdk.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.sdk.tools.ResultDecorate;
import com.chinamobile.cmss.bcse.sdk.tools.UpdateDic;


public class BCSESynoDic {
	
	
	private BCSEClient client;
	
	private String path="/config/dic/synonym";
	
    public BCSESynoDic(BCSEClient client){
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
	public String upload(String fileName) throws ClientProtocolException, IOException{
		 if(this.client==null||!this.client.isFlag()){
    		 return ResultDecorate.decorate("", "FAIL", "非法用戶", ExceptionConstants.FAIL_SERIVICE_CODE);
         }
		return UpdateDic.upload(client,path,fileName);
	}
	
	
}
