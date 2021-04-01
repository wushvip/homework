package com.chinamobile.cmss.bcse.sdk.index.indexManager;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.constant.Constant;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEClient;
import com.chinamobile.cmss.bcse.sdk.index.service.DataApi;
import com.chinamobile.cmss.bcse.sdk.util.IsGoodJson;


/**
 * 
 * @ClassName: ClearIndex
 * @Description: 清空索引
 * @author: jinjing
 * @date: 2016年2月16日 下午3:15:06
 */
public class ClearIndex {

	public static void main(String[] args) {
		/* System.out.println(process("neu.qbing@qq.com_test")); */
	}

	/**
	 * 
	 * @Title: process
	 * @Description: 删除核以及对应的数据文件，zookeeper的配置文件
	 * @param CoreName
	 * @return
	 * @return: boolean
	 */
	public static boolean process(String CoreName, BCSEClient client) {
		boolean clearflag = true;
		try {
			HashMap<String, Object> opts = new HashMap<String, Object>();
			opts.put("type", "all");
			//String result = client.call(Constant.APP_POST_URL + "/index/clear", opts, Constant.POST);
			String result = client.call(Constant.APP_POST_URL + "/"+Constant.appId+"/index", opts, Constant.DELETE);
			if(IsGoodJson.isGoodJson(result)){
				JSONObject jsonObject = JSONObject.parseObject(result);
				if (jsonObject.get("status").equals("FAIL")) {
					clearflag = false;
				}
			}else{
				clearflag = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			clearflag = false;
		}

		//new DataApi().setNumToDataBase(client);
		return clearflag;
	}

}
