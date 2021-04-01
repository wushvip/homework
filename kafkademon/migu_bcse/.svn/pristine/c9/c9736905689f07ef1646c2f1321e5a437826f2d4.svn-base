package com.chinamobile.cmss.bcse.config.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @ClassName: StringUtil 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:04:28
 */
public class StringUtil {
	
	/**
	 * 转小写
	 * @Title: toLowerCase 
	 * @Description: TODO
	 * @param str
	 * @return
	 * @return: String
	 */
	public static String toLowerCase(String str){
		return str.toLowerCase();
	}
	
	
	/**
	 * 判断是否是以*_开头或者是否以_*结尾
	 * @Title: containLetter 
	 * @Description: TODO
	 * @param str
	 * @return
	 * @return: boolean
	 */
	public static boolean isMatched(String str){
		if(str.startsWith("*_")||str.endsWith("_*")){
			return true;
		}else{
			return false;
		}
		
		
	
	}
	
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
	
	public static boolean isEmpty(List<String> list){
		if(list==null||list.size()<1){
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
	
	/**
	 * 将字符串转化为Set
	 * @Title: transferToSet 
	 * @Description: TODO
	 * @param str
	 * @return
	 * @return: Set<String>
	 */
	public static Set<String> toSet(String str){
		if(StringUtil.isEmpty(str)){
			return null;
		}
		String[] array=str.split(";");
		Set<String> set=new HashSet<String>();
		for(String keyword:array){
			//如果不为空
			if(StringUtil.isNotEmpty(keyword)){
				set.add(keyword);
			}
		}
		return set;
	}
	
	/**
	 * 
	 * @Title: toSet 
	 * @Description: TODO
	 * @param set
	 * @return
	 * @return: Set<String>
	 */
	public static Set<String> toSet(Set<String> set){
		if(null==set||set.size()<1){
			return null;
		}
		Set<String> words=new HashSet<String>();
		for(String str:set){
			//如果为空,继续下一次循环
			if(StringUtil.isEmpty(str)){
				continue;
			}
			//用分号切割
			String[] array=str.split(";");
			for(String s:array){
				//如果s不为空
				if(StringUtil.isNotEmpty(s)){
					words.add(s);
				}
			}	
		}
		return words;
	}
	
	public static Set<String> toSet(Set<String> oldWords, String str){
		if(StringUtil.isEmpty(str)){
			return null;
		}
		String[] array=str.split(";");
		Set<String> set=new HashSet<String>();
		for(String keyword:array){
			//如果不为空
			if(StringUtil.isNotEmpty(keyword)){
				set.add(keyword);
			}
		}
		//如果oldWords不为空并且大小大于0
		if(null!=oldWords&&oldWords.size()>0){
			for(String word:oldWords){
				set.add(word);
			}
			
		}
		return set;
	}
	
	
	
	
	/**
	 * 供elevate.xml使用
	 * @Title: transferToMap 
	 * @Description: TODO
	 * @param keywords
	 * @param ids
	 * @return
	 * @return: Map<String,Set<String>>
	 */
	public static Map<String,Set<String>> toMap(String keywords,String ids){
		if(StringUtil.isEmpty(keywords)||StringUtil.isEmpty(ids)){
			return null;
		}
		String[] keywordArray=keywords.split(";");
		Map<String,Set<String>> map=new HashMap<String,Set<String>>();
		Set<String> idSet=toSet(ids);
		for(String keyword:keywordArray){
			if(isNotEmpty(keyword)){
				map.put(keyword, idSet);
			}
		}
		return map;
	}
	/**
	 * 将数组转化为String,中间以空格分开
	 * @Title: transferToString 
	 * @Description: TODO
	 * @param list
	 * @return
	 * @return: String
	 */
	public static String toString(List<String> list){
		if(null==list||list.size()<1){
			return null;
		}
		StringBuffer sb=new StringBuffer();
		for(String str:list){
			if(StringUtil.isNotEmpty(str)){
				sb.append(str+" ");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 生成随机码
	 * @Title: getUuid 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String getUuid() {
		UUID id = UUID.randomUUID();
		String idString=id.toString();
		idString=idString.replaceAll("-", "");
		return idString;

	}
	
	public static Integer transform(String str){
		return Integer.parseInt(str);
	}
	public static String test(String str){
		String[] array=str.split("\\^");
		return array[0];
	}
	
	/**
	 * 获取solr需要的时间格式
	 * @Title: getDate 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String getDate(){
		Date date=new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
		return sdf1.format(date)+"T"+ sdf2.format(date)+"Z";
	}
	
	public static String getDate(Date date){
		SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
		return sdf1.format(date)+"T"+ sdf2.format(date)+"Z";
	}
	
	public static boolean isPattern(String str){
		 String regex = ".*#desc|.*#asc"; 
	     return Pattern.matches(regex, str); 
	
	}
	
	public static String cast(String str){
		return str.replace(";", "-");
		 
	}
	
	public static void main(String[] args) {
		String s=cast("屏蔽词1;屏蔽词2");
		System.out.println(s);
		
		//String s=ClientUtils.escapeQueryChars("rolling up");
		//System.out.println(s);
	}
		
	
}
