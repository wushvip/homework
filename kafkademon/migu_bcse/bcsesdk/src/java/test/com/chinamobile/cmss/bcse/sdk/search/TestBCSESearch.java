package com.chinamobile.cmss.bcse.sdk.search;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.base.BaseUnit;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEClient;
import com.chinamobile.cmss.bcse.sdk.entry.BCSESearch;


/** 
 * @ClassName: TestBCSESearch 
 * @Description: TODO
 * @author: chenmin
 * @date: 2016年8月9日 上午10:53:05  
 */
public class TestBCSESearch extends BaseUnit{
	 
	 
	 @Test
	 public  void testRawSearch() throws ClientProtocolException, IOException{

		 BCSESearch search=new BCSESearch(client);
		 search.setSearchQuery("天下");
		 search.setSortConfig("select01");
		 

		 String result=search.search();
		 System.out.println(result);
		 JSONObject object =JSONObject.parseObject(result);
		 Assert.assertEquals(object.get("status"), "SUCCESS");
		}
	 
	 @Test
	 public  void testSearch() throws ClientProtocolException, IOException{

		 BCSESearch search=new BCSESearch(client);
		 HashMap<String, Object> logs = new HashMap<>();

		 String result=search.saveSearchLog();
		 System.out.println(result);
		 JSONObject object =JSONObject.parseObject(result);
		 Assert.assertEquals(object.get("status"), "SUCCESS");
		}
	 
	 //连接多个条件，（A字段大于1且小于8，B字段大于等1且小于等于8，C字段等于6，D字段不等于6，E字段大于...
	 @Test
	 public  void test001Search() throws ClientProtocolException, IOException{
		 BCSESearch search=new BCSESearch(client);
		 search.setSearchQuery("*");
		 search.setSortConfig("select02");
		 JSONObject filterJson  = new  JSONObject();
		 filterJson.put("STAR", "(!6)");
		 filterJson.put("STAR", "{1 TO 8}");
		 filterJson.put("STAR", "[6 TO 6]");
		 filterJson.put("STAR", "[6 TO ]");
		 filterJson.put("STAR", "[ TO 6]");
		 search.setFilterJson(filterJson);
		 
		 String result=search.search();
		 System.out.println(result);
		 JSONObject object =JSONObject.parseObject(result);
		 Assert.assertEquals(object.get("status"), "SUCCESS");
		}
	 //pageIndex设置为2
	 @Test
	 public  void test002Search() throws ClientProtocolException, IOException{
		 BCSESearch search=new BCSESearch(client); 
		 search.setSearchQuery("天下");
		 search.setSortConfig("select02");
		 search.setPageIndex(1);
		 
		 
		 String result=search.search();
		 System.out.println(result);
		 JSONObject object =JSONObject.parseObject(result);
		 Assert.assertEquals(object.get("status"), "SUCCESS");
		}
	 
	//pageNum设置为2
		 @Test
		 public  void test003Search() throws ClientProtocolException, IOException{
			 try { 
			 BCSESearch bcseSearch=new BCSESearch(client);
//			 BCSESearch bcseSearch = new BCSESearch(ClientTool.getClient("94833400904c4527b279002a55119c43"));
				JSONObject json = new JSONObject();
//				json.put("facet.query=hot", "[1 TO 10]&facet.query=hot:[11 TO 20]&facet=true&facet.field=hot");
//				json.put("_MULTIfacet.query", "FEE:[1 TO 10];FEE:[11 TO 20]");
//				json.put("facet", true);
//				json.put("facet.field", "FEE");
				json.put("STAR", "[0 TO 7]");
				bcseSearch.setFilterJson(json);
				bcseSearch.setSortRule("STAR desc");
				bcseSearch.setSearchQuery("*");
				String result = bcseSearch.search();
				
					System.out.println("facet:"+result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	 
		 
		 
		 public static void main(String[] args) {
			 BCSESearch bcseSearch=new BCSESearch(client);
//			 BCSESearch bcseSearch = new BCSESearch(ClientTool.getClient("94833400904c4527b279002a55119c43"));
				JSONObject json = new JSONObject();
				json.put("facet.query=hot", "[1 TO 10]&facet.query=hot:[11 TO 20]&facet=true&facet.field=hot");
				bcseSearch.setAdvancedParams(json);
				bcseSearch.setSearchQuery("*");
				try { 
					System.out.println(bcseSearch.search());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		 
	/* @Test
	 public  void testFieldSearch() throws ClientProtocolException, IOException{
			
			
		    BCSESearch search=new BCSESearch(client);
		    HashMap<String, Object> opts=new HashMap<>();
		    opts.put("searchQuery", "游戏");
		    	opts.put("pageIndex",0);
			opts.put("pageNum",5);
			opts.put("rankType", 0);
			opts.put("filterRules", "id:7");
			opts.put("fieldSearch", "default");

				String result= search.search(opts);
				System.out.println(result);
				JSONObject object =JSONObject.parseObject(result);
				Assert.assertEquals(object.get("status"), "SUCCESS");

		}
	 
	 @Test
	 public  void testSeniorSearch() throws ClientProtocolException, IOException{
			
			
		    BCSESearch search=new BCSESearch(client);
		    HashMap<String, Object> opts=new HashMap<>();
		    opts.put("searchQuery", "游戏");
		    	opts.put("pageIndex",0);
			opts.put("pageNum",5);
			opts.put("rankType",2);
			opts.put("filterRules", "id:7");
			opts.put("fieldSearch", "default");
				String result= search.search(opts);
				System.out.println(result);
				JSONObject object =JSONObject.parseObject(result);
				Assert.assertEquals(object.get("status"), "SUCCESS");

		}
	 */
	/* public static void main(String[] args){
		
		    	opts.put("pageIndex",0);
			opts.put("pageNum",5);
			//opts.put("rankType", 0);
			opts.put("filterRules", "id:7");
			opts.put("fieldSearch", "default");
		    try {
		    	    client=new BCSEClient("","","127.0.0.1",8080);
				    BCSESearch search=new BCSESearch(client);
				    HashMap<String, Object> opts=new HashMap<>();
				    opts.put("searchQuery", "游戏");
				    opts.put("rankType", 0);
				String result= search.search(opts);
				System.out.println(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }*/

	/* @Test
	 public  void testRawSearch111() throws Exception{
		 BCSEClient bcse = new BCSEClient("006726b2e9884160a0b3890a22706fe9", "admin", "10.139.4.232", 8080, "48142b47346b47af876667612ce60035");
			BCSESearch search = new BCSESearch(bcse);
			JSONObject object = new JSONObject();
			//object.put("q", "测试");
			object.put("qt", "/roughsort_f");
			search.setAdvancedParams(object);
			//search.setSortConfig("e");
			search.setSearchQuery("*");
			String result = search.search();
			System.out.println(result);
		}*/
	 
}
