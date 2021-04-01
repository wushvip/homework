package com.chinamobile.cmss.bcse.sdk.util;

import java.io.File;
import java.util.List;

/**
 * 字符串工具类
 * @ClassName: StringUtil 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:04:28
 */
public class StringUtil {
	
	
	/**
	 * 判断是否为空
	 * @Title: isEmpty 
	 * @Description: TODO
	 * @param str
	 * @return
	 * @return: boolean
	 */
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断是否非空
	 * @Title: isNotEmpty 
	 * @Description: TODO
	 * @param str
	 * @return
	 * @return: boolean
	 */
	public static boolean isNotEmpty(String str){
		if((str!=null)&&!"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(List<String> list){
		if(list!=null&&list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean fileExists(String filePath){
		File file = new File(filePath);
		if (!file.exists()){
			return false;
		}
		return true;
	}
}
