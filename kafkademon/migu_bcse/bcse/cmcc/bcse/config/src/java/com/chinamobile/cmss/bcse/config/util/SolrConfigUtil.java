package com.chinamobile.cmss.bcse.config.util;


import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.chinamobile.cmss.bcse.search.bean.ZkConfigFileType;
import com.chinamobile.cmss.bcse.search.cloudapi.ReloadSolrCloud;
import com.chinamobile.cmss.bcse.search.tool.ZookeeperUtil;
import com.chinamobile.cmss.bcse.tool.config.Config;
/**
 * 操作solrconfig文件的工具类
 * @ClassName: SolrConfigUtil 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:04:10
 */
public class SolrConfigUtil {
	private static Logger logger=Logger.getLogger(SolrConfigUtil.class);
	
	/**
	 * 
	 * @param appId
	 * @param handlerName
	 * @throws Exception
	 */
	public static void deleteHandler(String appId,String handlerName) throws Exception{
		if(StringUtil.isEmpty(appId)||StringUtil.isEmpty(handlerName)){
			return;
		}
		ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
		String filePath=Config.ZKCONFIG_LOCALDIR+File.separator+appId+File.separator+"solrconfig.xml";
		
		SAXReader reader = new SAXReader();
		Document doc = reader.read(filePath);
		StringBuffer sb=new StringBuffer();
		//config/requestHandler[@name='handlerName']
		sb.append("config/requestHandler[@name='/").append(handlerName).append("']");
		Element element=(Element)doc.selectSingleNode(sb.toString());
		if(null!=element){
			element.detach();
			logger.info("delete handler from solrconfig success");
		}else{
			logger.info("no such handler exists");
		}
		//将doc写到指定文件中
		FileUtil.writeXml(filePath, doc);
		ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
	    ReloadSolrCloud.reload(Config.ZK_HOST, appId);
	}
	
	/**
	 * 
	 * @param sourceFilePath
	 * @param destFilePath
	 * @param handler
	 * @throws Exception
	 */
	public static void addSuggetHandler(String appId,SearchHandler handler) throws Exception{
		ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
		String filePath=Config.ZKCONFIG_LOCALDIR+"/"+appId+"/solrconfig.xml";
		
		SAXReader reader = new SAXReader();
		Document doc = reader.read(filePath);
		Element root=doc.getRootElement();
		//requestHandler
		Element first=root.addElement("requestHandler").addAttribute("name","/"+handler.getHandlerName())
		    .addAttribute("class", "solr.SearchHandler");
		//defaults appends的区别
		Element second=first.addElement("lst").addAttribute("name", "defaults");
		second.addElement("str").addAttribute("name", "echoParams").setText("explicit");
		//由于停用词需要，这里改为dismax,由于屏蔽功能不用停用词的方式来做，此处均为edismax
		second.addElement("str").addAttribute("name", "defType").setText("edismax");
		second.addElement("str").addAttribute("name", "wt").setText("json");
		second.addElement("str").addAttribute("name", "indent").setText("true");
		//添加qf参数
		if(StringUtil.isNotEmpty(handler.getQf())){
			second.addElement("str").addAttribute("name", "qf").setText(handler.getQf());
		}
		if(StringUtil.isNotEmpty(handler.getShowField())){
			second.addElement("str").addAttribute("name", "fl").setText(handler.getShowField());
		}
		//添加其他参数
		second.addElement("str").addAttribute("name", "rows").setText("10");
		//将doc写到指定文件中
		FileUtil.writeXml(filePath, doc);
		ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
	    ReloadSolrCloud.reload(Config.ZK_HOST, appId);
	}
	
	/**
	 * 
	 * @param sourceFilePath
	 * @param destFilePath
	 * @param handler
	 * @throws Exception
	 */
	public static void addSearchHandler(String appId,SearchHandler handler) throws Exception{
		
		ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
		String filePath=Config.ZKCONFIG_LOCALDIR+"/"+appId+"/solrconfig.xml";
		
		SAXReader reader = new SAXReader();
		Document doc = reader.read(filePath);
		Element root=doc.getRootElement();
		//requestHandler
		Element first=root.addElement("requestHandler").addAttribute("name","/"+handler.getHandlerName())
		    .addAttribute("class", "solr.SearchHandler");
		Element second=first.addElement("lst").addAttribute("name", "defaults");
		second.addElement("str").addAttribute("name", "echoParams").setText("explicit");
		//由于停用词需要，这里改为dismax,由于屏蔽功能不用停用词的方式来做，此处均为edismax
		second.addElement("str").addAttribute("name", "defType").setText("edismax");
		second.addElement("str").addAttribute("name", "wt").setText("json");
		second.addElement("str").addAttribute("name", "indent").setText("true");
		//添加qf参数
		if(StringUtil.isNotEmpty(handler.getQf())){
			second.addElement("str").addAttribute("name", "qf").setText(handler.getQf());
		}
		//添加其他参数
		second.addElement("str").addAttribute("name", "rows").setText("10");
		
		second.addElement("str").addAttribute("name", "fl").setText(handler.getFl());
		
		
		//添加fq参数
		List<String> fqs=handler.getFqs();
		if(null!=fqs&&fqs.size()>0){
			Element third=first.addElement("lst").addAttribute("name", "appends");
			for(String fq:fqs){
				third.addElement("str").addAttribute("name","fq").setText(fq);
			}
		}
		
		//添加elevate支持,需要放在lst节点的下面
		first.addElement("arr").addAttribute("name", "last-components")
	     	  .addElement("str").setText("elevator");
		//将doc写到指定文件中
		FileUtil.writeXml(filePath, doc);
		ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
	    ReloadSolrCloud.reload(Config.ZK_HOST, appId);
	}
	
	/**
	 * 修改handler
	 * @Title: modifyHandler 
	 * @Description: TODO
	 * @param sourceFilePath
	 * @param destFilePath
	 * @param handler
	 * @throws Exception
	 * @return: void
	 */
	public static void modifyRoughSortHandler(String appId,SearchHandler handler) throws Exception{
		if(StringUtil.isEmpty(handler.getHandlerName())){
			return;
		}
		ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
		String filePath=Config.ZKCONFIG_LOCALDIR+"/"+appId+"/solrconfig.xml";
		SAXReader reader = new SAXReader();
		Document doc = reader.read(filePath);
		
		StringBuffer sb=new StringBuffer();
		sb.append("config/requestHandler[@name='/").append(handler.getHandlerName()).append("']");
	    Element element=(Element)doc.selectSingleNode(sb.toString());
	    if(null==element){
	    	return;
	    }
		//requestHandler
		//修改qf和bf参数
		if(StringUtil.isNotEmpty(handler.getQf())){
			modifyText(element,"qf",handler.getQf());	
		}
		if(StringUtil.isNotEmpty(handler.getShowField())){
			modifyText(element,"fl",handler.getShowField());	
		}
		
		Element e=(Element)element.selectSingleNode("lst[@name='appends']");
		if(null!=e){
		    @SuppressWarnings("unchecked")
			List<Element> fqEles=e.elements("str");
			for(Element ele:fqEles){
				if("fq".equals(ele.attributeValue("name"))){
					 ele.detach();
				} 
			}
			List<String> fqs=handler.getFqs();	 //设置fq参数
			if(StringUtil.isNotEmpty(fqs)){
				for(String fq:fqs){
					e.addElement("str").addAttribute("name", "fq").setText(fq);
				}    
			}
		 }else{
		      //添加appends节点
			 e=element.addElement("lst").addAttribute("name", "appends");
			 List<String> fqs=handler.getFqs();	 //设置fq参数
			 if(StringUtil.isNotEmpty(fqs)){
				 for(String fq:fqs){
					e.addElement("str").addAttribute("name", "fq").setText(fq);
				} 
			 }
		 }
		
		//将doc写到指定文件中
		FileUtil.writeXml(filePath, doc);
		ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
		ReloadSolrCloud.reload(Config.ZK_HOST, appId);
	}

	public static void modifySuggestHandler(String appId,SearchHandler handler) throws Exception{
		if(StringUtil.isEmpty(handler.getHandlerName())){
			return;
		}
		ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
		String filePath=Config.ZKCONFIG_LOCALDIR+"/"+appId+"/solrconfig.xml";
		SAXReader reader = new SAXReader();
		Document doc = reader.read(filePath);
		
		StringBuffer sb=new StringBuffer();
		sb.append("config/requestHandler[@name='/").append(handler.getHandlerName()).append("']");
	    Element element=(Element)doc.selectSingleNode(sb.toString());
	    if(null==element){
	    	return;
	    }
		//requestHandler
		//修改qf和bf参数
		if(StringUtil.isNotEmpty(handler.getQf())){
			modifyText(element,"qf",handler.getQf());	
		}
		if(StringUtil.isNotEmpty(handler.getShowField())){
			modifyText(element,"fl",handler.getShowField());	
		}
		
		//将doc写到指定文件中
		FileUtil.writeXml(filePath, doc);
		ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
		ReloadSolrCloud.reload(Config.ZK_HOST, appId);
	}
	//设置字段，供修改操作使用
	private static void modifyText(Element element,String para,String text) {
		StringBuffer sb=new StringBuffer();
		sb.append("lst/str[@name='").append(para).append("']");
		System.out.println(sb.toString());
		Node node=element.selectSingleNode(sb.toString());
		if(null==node){
			logger.error("配置文件有误!!!");
			return;
		}else{
			//原来的配置里有这条信息，只需要修改text的内容
			node.setText(text);
		}
	}
	
	/**
	 * 屏蔽功能使用
	 * @Title: modifyHandlers 
	 * @Description: TODO
	 * @param appId
	 * @param handlers
	 * @throws Exception
	 * @return: void
	 */
	public static void modifyHandlers(String appId,List<SearchHandler> handlers) throws Exception{
		ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
		String filePath=Config.ZKCONFIG_LOCALDIR+"/"+appId+"/solrconfig.xml";
		SAXReader reader = new SAXReader();
		Document doc = reader.read(filePath);
		
		for(SearchHandler handler:handlers){
			List<String> fqs=handler.getFqs();
			StringBuffer sb=new StringBuffer();
			sb.append("config/requestHandler[@name='/").append(handler.getHandlerName()).append("']");
		    Element element=(Element)doc.selectSingleNode(sb.toString());
		    if(null==element){
		    	continue;
		    }
		   //Element e=element.element("lst");
		   Element e=(Element)element.selectSingleNode("lst[@name='appends']");
		   if(null!=e){
			   @SuppressWarnings("unchecked")
			   List<Element> fqEles=e.elements("str");
			   for(Element ele:fqEles){
			    	if("fq".equals(ele.attributeValue("name"))){
			    		ele.detach();
			    	} 
			    }
			   //设置fq参数
			   for(String fq:fqs){
					e.addElement("str").addAttribute("name", "fq").setText(fq);
			   }
		   }else{
			   //添加appends节点
			   e=element.addElement("lst").addAttribute("name", "appends");
			   for(String fq:fqs){
					e.addElement("str").addAttribute("name", "fq").setText(fq);
			   }
		   }
		}
		//将doc写到指定文件中
		FileUtil.writeXml(filePath, doc);
		ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
		ReloadSolrCloud.reload(Config.ZK_HOST, appId);
	}
	
	
	/**
	 * 屏蔽功能使用
	 * @Title: modifyHandlers 
	 * @Description: TODO
	 * @param appId
	 * @param handlers
	 * @throws Exception
	 * @return: void
	 */
	public static void deleteFq(String appId,List<String> handlerNames) throws Exception{
		ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
		String filePath=Config.ZKCONFIG_LOCALDIR+"/"+appId+"/solrconfig.xml";
		SAXReader reader = new SAXReader();
		Document doc = reader.read(filePath);
		
		for(String handlerName:handlerNames){
			StringBuffer sb=new StringBuffer();
			sb.append("config/requestHandler[@name='/").append(handlerName).append("']");
		    Element element=(Element)doc.selectSingleNode(sb.toString());
		    if(null==element){
		    	continue;
		    }
		   //Element e=element.element("lst");
		   Element e= (Element) element.selectSingleNode("lst[@name='appends']");
		   if(null!=e){
			   @SuppressWarnings("unchecked")
			   List<Element> fqEles=e.elements("str");
			   for(Element ele:fqEles){
			    	if("fq".equals(ele.attributeValue("name"))){
			    		ele.detach();
			    	} 
			    } 
		   }
		}
		//将doc写到指定文件中
		FileUtil.writeXml(filePath, doc);
		ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
		ReloadSolrCloud.reload(Config.ZK_HOST, appId);
	}
	
	
	public static void main(String[] args) throws Exception{
		String filePath="D://solrconfig.xml";
		SAXReader reader = new SAXReader();
		Document doc = reader.read(filePath);
		
		String str ="config/requestHandler[@name='/select']";
	    Element element=(Element)doc.selectSingleNode(str);
	    //Element appendElement=element.element("lst[@name='appends']");
	    Element appendElement=(Element)element.selectSingleNode("lst[@name='appends']");
	    Node e=appendElement.selectSingleNode("str[@name='fq']");
	    System.out.println(e.getText());
	}
	
	
	/**
	 * 修改默认配置
	 * @Title: modifySelectHandler 
	 * @Description: TODO
	 * @param appId
	 * @param qf
	 * @throws Exception
	 * @return: void
	 */
    public static void addSelectHandler(String appId,String qf,String fl) throws Exception{
    	ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
		String filePath=Config.ZKCONFIG_LOCALDIR+"/"+appId+"/solrconfig.xml";
		
		SAXReader reader = new SAXReader();
		Document doc = reader.read(filePath);
		Element root=doc.getRootElement();
		//requestHandler
		Element first=root.addElement("requestHandler").addAttribute("name","/select")
		    .addAttribute("class", "solr.SearchHandler");
		Element second=first.addElement("lst").addAttribute("name", "defaults");
		second.addElement("str").addAttribute("name", "echoParams").setText("explicit");
		//由于停用词需要，这里改为dismax,由于屏蔽功能不用停用词的方式来做，此处均为edismax
		second.addElement("str").addAttribute("name", "defType").setText("edismax");
		second.addElement("str").addAttribute("name", "wt").setText("json");
		second.addElement("str").addAttribute("name", "indent").setText("true");
		//添加qf参数
		second.addElement("str").addAttribute("name", "qf").setText(qf);
		//添加其他参数
		second.addElement("str").addAttribute("name", "rows").setText("10");
		//添加fl参数
		if(StringUtil.isNotEmpty(fl)){
			second.addElement("str").addAttribute("name", "fl").setText(fl);
		}
		
		//添加elevate支持,需要放在lst节点的下面
		first.addElement("arr").addAttribute("name", "last-components")
			     	  .addElement("str").setText("elevator");
		//将doc写到指定文件中
		FileUtil.writeXml(filePath, doc);
		ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
	    ReloadSolrCloud.reload(Config.ZK_HOST, appId);
	}
    
    
    public static void modifySelectHandler(String appId,List<String> fqs) throws Exception{
		ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
		String filePath=Config.ZKCONFIG_LOCALDIR+"/"+appId+"/solrconfig.xml";
		SAXReader reader = new SAXReader();
		Document doc = reader.read(filePath);
		
		StringBuffer sb=new StringBuffer();
		sb.append("config/requestHandler[@name='/select']");
	    Element element=(Element)doc.selectSingleNode(sb.toString());
	    if(null==element){
	    	return;
	    }	
	    //Element e=element.element("lst");
	    Element e=(Element)element.selectSingleNode("lst[@name='appends']");
	    if(null!=e){
	    	@SuppressWarnings("unchecked")
			List<Element> fqEles=e.elements("str");
			for(Element ele:fqEles){
				if("fq".equals(ele.attributeValue("name"))){
				    ele.detach();
				} 
			 }
			 //设置fq参数
			if(StringUtil.isNotEmpty(fqs)){
				for(String fq:fqs){
					e.addElement("str").addAttribute("name", "fq").setText(fq);
			   }    
			}
	    }else{
	    	 //添加appends节点
			 e=element.addElement("lst").addAttribute("name", "appends");
			 for(String fq:fqs){
				 e.addElement("str").addAttribute("name", "fq").setText(fq);
			 }
	    }
	    //将doc写到指定文件中
		FileUtil.writeXml(filePath, doc);
		ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
		ReloadSolrCloud.reload(Config.ZK_HOST, appId);
	}

	
}
