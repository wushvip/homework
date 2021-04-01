package com.chinamobile.cmss.bcse.search.cloudapi;

import java.util.HashMap;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.common.params.ModifiableSolrParams;

import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.SearchException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;


/** 
 * @ClassName: ConnectSolrCloud 
 * @Description: TODO 连接solrcloud服务
 * @author: chenmin
 * @date: 2016年2月26日 下午4:26:29  
 */
public class ConnectSolrCloud {
	
	private final static int zkClientTimeout=10000;
	private final static int zkConnectTimeout=20000;
	private static HashMap<String, CloudSolrClient> servers=new HashMap<String, CloudSolrClient>();
    /**
     * @param zkHost
     * @param collectionName
     * @return CloudSolrClient
     * @throws SearchException 
     * 
     * */
	public static  CloudSolrClient getCloudSolrServers(String zkHost, String userId , String collectionName) throws SearchException {
		
		ModifiableSolrParams params = new ModifiableSolrParams();  
	      /*params.set(HttpClientUtil.PROP_MAX_CONNECTIONS, 128);  
	      params.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, 32);  
	      params.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, false);  
	      params.set(HttpClientUtil.PROP_BASIC_AUTH_USER, "admin");  
	      params.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, "zhen.com");  
	      params.set(HttpClientUtil.PROP_MAX_CONNECTIONS, 1000);  
	      params.set(HttpClientUtil.PROP_ALLOW_COMPRESSION, true);  
	      params.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, 1000);*/  
	    CloseableHttpClient closeableHttpClient = HttpClientUtil.createClient(params);  
		
		String collectionString=collectionName;
		if(servers.containsKey(collectionString)){
			//servers.get(collectionString).
			return servers.get(collectionString);
		}else{
			CloudSolrClient cloudSolrServer =null;
		    synchronized (ConnectSolrCloud.class) {
		         cloudSolrServer = new  CloudSolrClient(zkHost,closeableHttpClient);
				try {
					cloudSolrServer
							.setDefaultCollection(collectionString);
					cloudSolrServer.setZkClientTimeout(zkClientTimeout);
					cloudSolrServer.setZkConnectTimeout(zkConnectTimeout);
					cloudSolrServer.connect();
					servers.put(collectionString, cloudSolrServer);
					System.out.println("The cloud Server has been connected !!!!");
				} catch (Exception e) {
					 LogUtil.loggerEntrance(userId, collectionName,ExceptionConstants.SearchError , LogUtil.SEARCH_LOG, e);
					 throw new SearchException(ExceptionConstants.SearchError);
				}
			}
			return cloudSolrServer;
		}
		
	}
	
	 /**
     * @param zkHost
     * @param collectionName
     * @return CloudSolrClient
     * 
     * */
	public static  CloudSolrClient getCloudSolrServers(String zkHost, String collectionName) {
		
		
		String collectionString=collectionName;
		if(servers.containsKey(collectionString)){
			//servers.get(collectionString).
			return servers.get(collectionString);
		}else{
			CloudSolrClient cloudSolrServer =null;
		    synchronized (ConnectSolrCloud.class) {
		         cloudSolrServer = new  CloudSolrClient(zkHost);
				try {
					cloudSolrServer
							.setDefaultCollection(collectionString);
					cloudSolrServer.setZkClientTimeout(zkClientTimeout);
					cloudSolrServer.setZkConnectTimeout(zkConnectTimeout);
					cloudSolrServer.connect();
					servers.put(collectionString, cloudSolrServer);
					System.out.println("The cloud Server has been connected !!!!");
				} catch (Exception e) {
				}
			}
			return cloudSolrServer;
		}
	}
}
