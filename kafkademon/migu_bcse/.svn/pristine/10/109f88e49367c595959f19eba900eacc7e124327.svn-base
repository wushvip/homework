package com.chinamobile.cmss.bcse.config.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.chinamobile.cmss.bcse.config.bean.Rule;
/**
 * 推广工具类
 * @ClassName: ElevateUtil 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:02:31
 */
public class ElevateUtil {
	private static Logger logger=Logger.getLogger(ElevateUtil.class);
	
	public static Map<String,Set<String>> toMap(List<Rule> entity){
		if(null==entity||entity.size()<1){
			return null;
		}
		Map<String,Set<String>> map=new HashMap<String,Set<String>>();
		for(Rule e:entity){
			if(e==null){
				continue;
			}
			String[] words=e.getIncludeKeywords().split(";");
			for(String word:words){
				put(map, e.getSpreadIds(), word);
			}
		}
		return map;
	}

	
	private static void put(Map<String, Set<String>> map, String ids, String word) {
		//如果map里存在了该key
		if(map.containsKey(word)){
			String[] idArr=ids.split(";");
			for(String id:idArr){
				map.get(word).add(id);
			}
		}else{
			map.put(word, StringUtil.toSet(ids));
		}
	}
	
	
	/**
	 * 写xml文件
	 * @Title: writeElevate 
	 * @Description: TODO
	 * @param filePath
	 * @param map
	 * @throws Exception
	 * @return: void
	 */
	public static void writeElevate(String filePath,Map<String,Set<String>> map) throws Exception{
		Document document=DocumentHelper.createDocument();
		Element elevateElement=document.addElement("elevate");
		//判断map为空或者map的大小小于1
		if(null==map||map.size()<1){
			//写空文件
			FileUtil.writeXml(filePath, document);
			logger.info("write xml success...");
			return;
		}
		for(String str:map.keySet()){
			//如果str为空则不进行处理
			if(null==str||str.trim().equals("")){
				continue;
			}
			Element queryElement=elevateElement.addElement("query");
			queryElement.addAttribute("text", str);
			Set<String> codeList=map.get(str);
			for(String code:codeList){
				Element docElement=queryElement.addElement("doc");
				//只能是id
				docElement.addAttribute("id", code);
			}	
			
		}	
		//将doc写到指定文件中
	    FileUtil.writeXml(filePath, document);
		logger.info("write xml success...");
	}
	
	
	public static void main(String[] args) {
		List<Rule> list=new ArrayList<Rule>();
		Rule r1=new Rule();
		r1.setSpreadIds("1;2");
		r1.setIncludeKeywords("开心;女人");
		
		Rule r2=new Rule();
		r2.setSpreadIds("3;4");
		r2.setIncludeKeywords("开心;我的");
		
		Rule r3=new Rule();
		r3.setSpreadIds("1;5");
		r3.setIncludeKeywords("还好");
		
		list.add(r1);
		list.add(r2);
		list.add(r3);
		long start=System.currentTimeMillis();
		Map<String,?> map=ElevateUtil.toMap(list);
		for(String str:map.keySet()){
			System.out.println(str);
		}
		long stop=System.currentTimeMillis();
		System.out.println(stop-start);
	}
}
