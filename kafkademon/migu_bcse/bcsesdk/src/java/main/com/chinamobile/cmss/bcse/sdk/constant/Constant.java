package com.chinamobile.cmss.bcse.sdk.constant;

/**
 * 工程配置文件类
 * 
 * @author Quan
 * 
 */
public class Constant {

	public static final String APP_POST_URL = "/apps";
	public static final String LOG_POST_URL = "/WriteLogServlet";
	public static final String POST = "POST";
	public static final String GET = "GET";
	public static final String DELETE = "DELETE";
	public static String appId;
	public static String userId;
	public static String secretAccessKey;
	public static final String FILESPLIT = ",*|#$%&./\\";
	public static final String MUTIVALUESPLIT = ",*|#$%&./\\";

	public static String ZK_HOST;
	public static String BCSE_URL;

	//MSG
	public static final String CODE_SYS = "1000000";
	public static final String CODE_SERVICE = "2000000";
	public static final String STATUS_FAIL = "FAIL";
	public static final String STATUS_SUCCESS = "SUCCESS";
	public static final String MSG_SPLIT_NG = "字段分隔符和多值分割符目前支持的字符为,*|#$%&./\\且只能选任意不相等的一个字符";
	
	
	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir"));
		System.out.println("fileSplit:"+FILESPLIT);
		System.out.println("mutiValueSplit:"+MUTIVALUESPLIT);
		System.out.println("MSG_SPLIT_NG:"+MSG_SPLIT_NG);
	}

}
