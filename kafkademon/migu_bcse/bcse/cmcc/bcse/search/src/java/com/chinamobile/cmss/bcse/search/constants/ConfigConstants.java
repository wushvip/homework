package com.chinamobile.cmss.bcse.search.constants;

import java.util.ArrayList;



public class ConfigConstants {
	
	public static ArrayList<String> filterFlagStrings=new ArrayList<String>();
	public static ArrayList<String> filterFlagStringsZY=new ArrayList<String>();
	public static final String LOG_FOLDER_NAME = "./";
	public static final String LOG_FILE_SUFFIX = "log_";
	
	/*public static  String errorRecoveryUrl = "http://192.168.56.88:8999/test?word=";
	//public static  String zkHost = "10.133.5.151:2181,10.133.5.152:2181,10.133.5.153:2181,10.133.5.154:2181,10.133.5.155:2181";
	public static  String zkHost = "192.168.56.89:2181,192.168.56.90:2181,192.168.56.91:2181";
	public static  String defaultCollection = "gamecollection";
	public static  int zkClientTimeout = 200000;
	public static  int zkConnectTimeout = 100000;
	
	public static  String logSaveUrl="http://192.168.56.88:8085/test";*/
	
	public static  String errorRecoveryUrl = "";
	//public static  String zkHost = "10.133.5.151:2181,10.133.5.152:2181,10.133.5.153:2181,10.133.5.154:2181,10.133.5.155:2181";
	public static  String zkHost = "";
	public static  String defaultCollection = "gamecollection";
	public static  int zkClientTimeout = 200000;
	public static  int zkConnectTimeout = 100000;
	
	public static  String logSaveUrl="";
	
	public static  String XSD_VALIDATE="test.xsd"; 
	
    public static  String SUGGEST_TYPE="1";
    public static  String SEARCH_TYPE="2";
    public static  int SUGGEST_NUM=10;
    public static  int MAX_PAGE_NUM=40;
    public static  int DEFAULT_PAGE=1;
    public static  int DEFAULT_PAGE_NUM=10;
    
    public static  String AVOID_GAME_FIELD="!FILTER";
    public static  String AVOID_GAME_VALUE="1";
    public static  String PLATFORM_FIELD="!WHETHER_PLATFORM";
    public static  String NOT_PLATFORM_VALUE="0";
    
    public static  String CUS_TYPE_ID="";
    public final static   String NOT_ANDROID_VALUE="";
    public final static  String NOT_IOS_VALUE="302";
    public final static  String NOT_PCWWW_VALUE="293";
    public final static  String NOT_ANDROIDWWW_VALUE="303";
    
    public final static String FLASH_EDU_ID="f18";
    public final static String ANDROID_EDU_ID="10";
    
    
    
    public static  String CUS_TYPE_IOS="4";
    public static  String CUS_TYPE_PCWWW="1";
    public static  String CUS_TYPE_ANDROIDWWW="3";
    public static  String CUS_TYPE_ANDROID="2";
 
    //游戏屏蔽字段
    public static final  String GAME_TYPE_FIELD="GAME_TYPE";
    public static   String GAME_TYPE_VALUE="0";
    
    public static  String[] SORT_FIELDS={"STAR","ALL_DOWN_CNT","VALID_DATE"};
    public static  String[] CLASS_FIELD={"CONTENT_ID","BILL_TYPE","LANGUAGE"};
    public static  String[] SORT_RULE={"desc","asc"};
    public static  String SHOW_FIELD="OPERATOR_CODE";
   
    
}
