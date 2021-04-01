package com.chinamobile.cmss.bcse.sdk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEClient;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEIndex;

public class TestSdk {
	
	public static void main(String[] args) throws Exception {
		BCSEClient client = new BCSEClient("98798881ac7a49f3b54a131cdedf135f","admin", "10.139.4.13", 8080,"2968eb1e1f0f45e1bbfa2905b4f15c3e");
		JSONArray datas = new JSONArray();
		
		for(int  i=0;i<1;i++){
			JSONObject obj1 = new JSONObject();
			obj1.put("a", "1");
			obj1.put("b", "开拓进取，取得了改革开放和社会主义现代化建设的历史性成就，推党的十八大以来，以习近平同志为核心的党中央迎难而上，开拓进取，取得了改革开放和社会主义现代化建设的历史性成就，推党的十八大以来，以习近平同志为核心的党中央迎难而上，开拓进取，取得了改革开放和社会主义现代化建设的历史性成就，推党的十八大以来");
			obj1.put("c", "天天酷跑");
			datas.add(obj1);
			
		}
		
		
		BCSEIndex index = new BCSEIndex(client);
			String resultBean=index.updateAllIndexByJson(datas);
			JSONObject object =JSONObject.parseObject(resultBean);
			System.out.println(object.get("status"));
	}

}
