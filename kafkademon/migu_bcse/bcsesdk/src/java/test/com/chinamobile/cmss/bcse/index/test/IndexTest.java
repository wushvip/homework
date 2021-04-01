/*package com.chinamobile.cmss.bcse.index.test;


import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEClient;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEIndex;


public class IndexTest {

	private static final String APP_ID = "1d77f5f5dda94529ae05fd56a3299ece";
	//private static final String IP = "223.105.0.230";
	private static final String IP = "172.16.208.182";
	private static String FILE_PATH;
	private static final String DEL_FIELD = "pubtime";
	private static final String START_DATE = "2010-01-01T00:00:00Z";
	private static final String END_DATE = "2017-01-01T00:00:00Z";

	//private static final int PORT = 8888;
	private static final int PORT = 8080;

	static BCSEClient bcseClient = null;
	static BCSEIndex bcseIndexOp = null;

	@BeforeClass
	public static void init() {
		try {
			FILE_PATH = IndexTest.class.getResource("111.csv").getPath();
			bcseClient = new BCSEClient(APP_ID, IP, PORT, "bcse","secretAccessKey");
			bcseIndexOp = new BCSEIndex(bcseClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean getStatus(String string) {
		JSONObject jsonObject = JSONObject.parseObject(string);
		try {
			if (jsonObject.get("status").equals("SUCCESS")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//@Test
	public void upFileTest() {
		Assert.assertTrue(getStatus(bcseIndexOp.update(FILE_PATH)));
	}

	
	public void delFileTest() {
		Assert.assertTrue(getStatus(bcseIndexOp.deleteIndex(FILE_PATH)));
	}

	//@Test
	public void clearIndexTest() {
		Assert.assertTrue(getStatus(bcseIndexOp.clearIndex()));
	}

	
	public void deleteByDateTest() {
//		Assert.assertTrue(getStatus(bcseIndexOp.deleteByDate(DEL_FIELD, START_DATE, END_DATE)));
	}

}
*/