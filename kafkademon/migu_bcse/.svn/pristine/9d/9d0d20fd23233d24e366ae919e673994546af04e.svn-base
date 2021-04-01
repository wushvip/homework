package com.chinamobile.cmss.bcse.index.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.base.BaseUnit;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEIndex;

public class TestBCSEIndex extends BaseUnit{
	private  String FILE_PATH;
	/**
	 * 测试利用本地文件更新索引
	 * @throws Exception 
	 */
	
	@Test
	public void test001Update() throws Exception {
		
		
			BCSEIndex index = new BCSEIndex(client);
			FILE_PATH = TestBCSEIndex.class.getResource("books.csv").getPath();
			//FILE_PATH = TestBCSEIndex.class.getResource("indexTest.csv").getPath();
			String result = index.update(FILE_PATH);
			System.out.print("result:"+result);
			JSONObject object =JSONObject.parseObject(result);
			Assert.assertEquals(object.get("status"), "SUCCESS");
		
	}
	
	/**
	 * 测试利用本地文件部分字段增量更新索引
	 * @throws Exception 
	 */
	/*@Test
	public void test002UpdatePartIndex() throws Exception {
		
		
			BCSEIndex index = new BCSEIndex(client);
			FILE_PATH = TestBCSEIndex.class.getResource("indexTest.csv").getPath();
			String result = index.updatePartIndex(FILE_PATH);
			//String result = index.updatePartIndex(FILE_PATH, "@", "|");
			System.out.print("result:"+result);
			JSONObject object =JSONObject.parseObject(result);
			Assert.assertEquals(object.get("status"), "SUCCESS");
		
	}*/
	
	
	/**
	 * 测试将指定应用的索引部分字段根据上传的数据进行更新
	 * @throws Exception 
	 */
	@Test
	public void test003UpdatePartIndexByJson() throws Exception {
		
		JSONArray datas = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "aaa");
		obj1.put("id", "1");
		//obj1.put("age", "10");
		BCSEIndex index = new BCSEIndex(client);
			datas.add(obj1);
			String result = index.updatePartIndexByJson(datas);
			System.out.print("result:"+result);
			JSONObject object =JSONObject.parseObject(result);
			Assert.assertEquals(object.get("status"), "SUCCESS");

	}
	
	@Test
	public void test003UpdateAllIndexByJson() throws Exception {
		
		JSONArray datas = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "a");
		obj1.put("id", "1");
		obj1.put("age", "100");
		
		BCSEIndex index = new BCSEIndex(client);
			datas.add(obj1);
			String result = index.updateAllIndexByJson(datas);
			System.out.print("result:"+result);
			JSONObject object =JSONObject.parseObject(result);
			Assert.assertEquals(object.get("status"), "SUCCESS");

	}
	/**
	 * 测试删除指定的app的数据，数据以json数组方式传递
	 * @throws Exception 
	 */
	@Test
	public void test004DeleteIndexs() throws Exception {
		BCSEIndex index;
		JSONArray datas = new JSONArray();
		JSONObject obj1 = new JSONObject();
		/*obj1.put("name", "aaa");
		obj1.put("id", "1");
		obj1.put("age", "10");*/
		obj1.put("authorid", "1000648413");
			index = new BCSEIndex(client);
			datas.add(obj1);
			String result = index.deleteIndexs(datas);
			System.out.print("result:"+result);
			JSONObject object =JSONObject.parseObject(result);
			Assert.assertEquals(object.get("status"), "SUCCESS");
		
	}
	/**
	 * 利用本地文件重建索引
	 * @throws ClientProtocolException
	 * @throws IOException
	 *//*
	@Test
	public void testRebuild() throws ClientProtocolException, IOException {
		BCSEIndex index;
		try {
			index = new BCSEIndex(client);
			FILE_PATH = TestBCSEIndex.class.getResource("indexTest.csv").getPath();
			String result = index.rebuild(FILE_PATH);
			System.out.print("result:"+result);
			JSONObject object =JSONObject.parseObject(result);
			Assert.assertEquals(object.get("status"), "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	/**
	 * 清空索引
	 * @throws Exception 
	 */
	/*@Test
	public void test006ClearIndex() throws Exception {
		
	
			BCSEIndex index = new BCSEIndex(client);
			String result = index.clearIndex();
			System.out.print("result:"+result);
			JSONObject object =JSONObject.parseObject(result);
			Assert.assertEquals(object.get("status"), "SUCCESS");
		
	}*/
	/**
	 * 删除文件中的索引
	 * @throws Exception 
	 */
	/*@Test
	public void test005DeleteIndex() throws Exception {
		
	
			BCSEIndex index = new BCSEIndex(client);
			FILE_PATH = TestBCSEIndex.class.getResource("indexTest.csv").getPath();
			String result = index.deleteIndex(FILE_PATH);
			System.out.print("result:"+result);
			JSONObject object =JSONObject.parseObject(result);
			Assert.assertEquals(object.get("status"), "SUCCESS");
		
	}*/

	@Test
	public void test007UpdateAllIndexByJson() throws Exception {
		String fileName = "F:\\工作记录\\17年4月\\视讯直播json.txt";
		String json = readFileByLines(fileName);
		 
		JSONArray datas = new JSONArray();
		datas =JSON.parseArray(json);
		
		BCSEIndex index = new BCSEIndex(client);
			
			String result = index.updateAllIndexByJson(datas);
			System.out.print("result:"+result);
			JSONObject object =JSONObject.parseObject(result);
			Assert.assertEquals(object.get("status"), "SUCCESS");

	}
	
	
	public static String readFileByLines(String fileName) {
        FileInputStream file = null;
        BufferedReader reader = null;
        InputStreamReader inputFileReader = null;
        String content = "";
        String tempString = null;
        try {
            file = new FileInputStream(fileName);
            inputFileReader = new InputStreamReader(file, "utf-8");
            reader = new BufferedReader(inputFileReader);
            while ((tempString = reader.readLine()) != null) {
                content += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content;
    }
}
	


