package com.chinamobile.cmss.bcse.search.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.app.bean.FieldInfoBean;
import com.chinamobile.cmss.bcse.search.bean.GroupInfoBean;
import com.chinamobile.cmss.bcse.search.bean.HighLightBean;
import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.Tool;

public class DefaultDecorateFunction implements DecorateFunctionInterface{
	
	private QueryBean queryBean;
	private SearchResultBean searchResultBean;
	private QueryResponse response;
	
	
	public DefaultDecorateFunction(QueryBean queryBean,SearchResultBean searchResultBean
			){
		this.queryBean=queryBean;
		this.searchResultBean=searchResultBean;
	}
	

	public void resultListDecorate(){
		SolrDocumentList resultList = response.getResults();
		if (resultList == null) {
			SolrDocumentList solrDocumentList = new SolrDocumentList();
			searchResultBean.setResultList(solrDocumentList);
			return;
		} else {
			searchResultBean.setResultList(resultList);
		}
		searchResultBean.setTotalItems((int) response.getResults().getNumFound());
	}
	/**
	 * @Title: hightLightDecorate
	 * @Description: TODO
	 * @param response
	 * @param searchResultBean
	 * @return: void
	 */
	public void hightLightDecorate() {
		Map<String, Map<String, List<String>>> highLightResult = response.getHighlighting();
		if (highLightResult != null) {
			searchResultBean.setHighLightResult(response.getHighlighting());
		}
	}

	/**
	 * @Title: groupStaticDecorate
	 * @Description: TODO
	 * @param response
	 * @param searchResultBean
	 * @return: void
	 */
	public void groupStaticDecorate() {//		Map<String, SolrDocumentList> groupInfo = new HashMap<String, SolrDocumentList>();
		GroupResponse groupResponse = response.getGroupResponse();
		LinkedHashMap<String, GroupInfoBean> groupInfo = new LinkedHashMap<String, GroupInfoBean>();
		if (groupResponse == null) {
			return;
		}
		List<GroupCommand> groupList = groupResponse.getValues();
		GroupInfoBean bean = null;
		for (GroupCommand groupCommand : groupList) {
			List<Group> groups = groupCommand.getValues();
			for (Group group : groups) {
				if (group.getResult().getNumFound() > 0) {
					bean = new GroupInfoBean();
					bean.setNumFound(group.getResult().getNumFound());
					bean.setResult(group.getResult());
					groupInfo.put(group.getGroupValue(), bean);
				}
			}
		}
		searchResultBean.setGroupInfo(groupInfo);
		
	
	}

	/**
	 * @Title: facetDecorate
	 * @Description: TODO
	 * @param response
	 * @param searchResultBean
	 * @return: void
	 */
	public void facetDecorate() {
		if (response.getFacetFields() == null) {
			return;
		}
		JSONArray tempArrayq = new JSONArray();
		for (FacetField facetField : response.getFacetFields()) {
			JSONArray tempArray = new JSONArray();
			JSONObject tempone = new JSONObject();
			List<Count> counts = facetField.getValues();
			for (Count count : counts) {
				JSONObject temp = new JSONObject();
				temp.put(count.getName(), count.getCount());
				tempArray.add(temp);
			}
			tempone.put(facetField.getName(), tempArray);
			tempArrayq.add(tempone);
		}
		searchResultBean.setFacetList(tempArrayq);
	}
	@Override
	public void process(QueryResponse response) {
		this.response=response;
		
		resultListDecorate();
		facetDecorate();
		hightLightDecorate();
		groupStaticDecorate();
		decorate();
		
	}


	public QueryBean getQueryBean() {
		return queryBean;
	}


	public void setQueryBean(QueryBean queryBean) {
		this.queryBean = queryBean;
	}


	public SearchResultBean getSearchResultBean() {
		return searchResultBean;
	}


	public void setSearchResultBean(SearchResultBean searchResultBean) {
		this.searchResultBean = searchResultBean;
	}


	public QueryResponse getResponse() {
		return response;
	}


	public void setResponse(QueryResponse response) {
		this.response = response;
	}
	/**
	 * @Title: decorate
	 * @Description: TODO 结果封装,摘要显示
	 * @param queryBean
	 * @param searchResultBean
	 * @return: void
	 */
	public void decorate() {
		
		boolean showFlag=false;
		//摘要
		ArrayList<HighLightBean> highLightBeans=null;
		if (queryBean.getIsShow().equals("1")) {
			showFlag = true;
			highLightBeans = getMarkLight(queryBean, searchResultBean);
		}
		
//		Object ruleBean = queryBean.appInfoDataBean.get(RedisDataType.SHOWNABLEFIELDS);
		Object ruleBean = null;
		if (ruleBean == null) {
			return;
		}
		//获取时间类型字段A
		final String DATA_TYPE="DATE";
		ArrayList<String> dateFields=new ArrayList<String>();
		ArrayList<FieldInfoBean> fieldList=(ArrayList<FieldInfoBean>) ruleBean;
		for(FieldInfoBean fieldInfoBean : fieldList){
			if(fieldInfoBean.getFieldType().equals(DATA_TYPE)){
				dateFields.add(fieldInfoBean.getFieldName());
			}			
		}
		
		SolrDocumentList solrDocumentList = new SolrDocumentList();
		if(searchResultBean.getResultList()==null){
			searchResultBean.setResultList(solrDocumentList);
			return;
		}
		for (SolrDocument solrDocument : searchResultBean.getResultList()) {
			
			if(showFlag){
				for (HighLightBean highLightBean : highLightBeans) {
					setHighLight(solrDocument, queryBean, highLightBean);
				}
			}
			for(String temp:dateFields){
				Object temop= solrDocument.getFieldValue(temp);
				if(temop!=null){
					solrDocument.setField(temp, Tool.getStandTime(temop.toString()));
				}
			}
			solrDocumentList.add(solrDocument);
		}
		searchResultBean.setResultList(solrDocumentList);
	}

	/**
	 * 
	 * @Title: setHighLight
	 * @Description: TODO 摘要显示
	 * @param solrDocument
	 * @param queryBean
	 * @param highLightBean
	 * @return: void
	 * 
	 */
	public void setHighLight(SolrDocument solrDocument, QueryBean queryBean, HighLightBean highLightBean) {
		
		if (highLightBean.getFragSize() == 0||highLightBean.getFragNum()==0)
			return;
		String content=null;
		try {
			 content = (String) solrDocument.getFieldValue(highLightBean.getHighLightField());
		} catch (Exception e) {
			 LogUtil.loggerEntrance(queryBean.getUserId(), queryBean.getAppId(),"" , LogUtil.SEARCH_LOG, e);
		}
		
		if (content == null) {
			return;
		}
		String keyWord = queryBean.getOldInput();
		String markPost = highLightBean.getMarkPost();
		String markPre = highLightBean.getMarkPre();
		String newContent="";
		
		int index=content.indexOf(keyWord);
		if(index==-1){
			return;
		}
		int left=0;
		int temp=0;
		int right=0;
		int aroundCharsLen=highLightBean.getFragSize()-keyWord.length();
		//生成摘要
		for(int j=0;j<highLightBean.getFragNum();j++){
			//没有关键字直接跳出循环
			index=content.indexOf(keyWord);
			if(index==-1){
				break;
			}
			if(aroundCharsLen>0){
				 left=index-aroundCharsLen/2>0?(index-aroundCharsLen/2):0;
				 temp=(index+keyWord.length()+aroundCharsLen/2);
				 right=content.length()-temp>0?temp:content.length();
				 //从最左边开始的情况
				 if(right<content.length()&&right-left<highLightBean.getFragSize()){
					 right=(2*(right-1)-left)-content.length()>0?content.length():(2*(right-1)-left);
				 }
			}else{
				 left=0;
				 right=highLightBean.getFragSize();
			}
			//如果包含关键字的截取片段
			String tempContent=content.substring(left, right);
			if(tempContent.contains(keyWord)){
				if(!newContent.equals("")){
					newContent=newContent+highLightBean.getLinkFlag()+tempContent;
				}else{
					newContent=tempContent;
				}
			}
			content=content.substring(right, content.length());
		}
		
		//高亮显示
		if(newContent.contains(keyWord)){
			newContent = newContent.replaceAll(keyWord, markPre + keyWord + markPost);
			solrDocument.setField(highLightBean.getHighLightField(), newContent);
		}else{
			solrDocument.setField(highLightBean.getHighLightField(), content);
		}
		System.out.println(newContent);
		
	}

	/**
	 * @Title: getMarkLight
	 * @Description: TODO 获取标志
	 * @param queryBean
	 * @param searchResultBean
	 * @return: void
	 */
	private ArrayList<HighLightBean> getMarkLight(QueryBean queryBean, SearchResultBean searchResultBean) {/*

		ArrayList<HighLightBean> highLightBeans = new ArrayList<HighLightBean>();
		RuleBeanWithBLOBs ruleBean = (RuleBeanWithBLOBs) queryBean.appInfoDataBean.get(RedisDataType.EPITOMERULE);
		if (ruleBean == null ) {
			return highLightBeans;
		}

		RuleBeanWithBLOBs ruleBeanOne = ruleBean;
		String[] highLightFields = ruleBeanOne.getIncludeFields().split(";");
		String[] marks = ruleBeanOne.getMark().split(";");
		String[] linkFlags = ruleBeanOne.getFragmentJoin().split(";");
		String[] fragNums = ruleBeanOne.getFragmentNum().split(";");
		String[] fragLengths = ruleBeanOne.getFragmentLength().split(";");

		for (int i = 0; i < highLightFields.length; i++) {

			HighLightBean highLightBean = new HighLightBean();
			highLightBean.setHighLightField(highLightFields[i]);
			highLightBean.setLinkFlag(linkFlags[i]);
			highLightBean.setMark(marks[i]);
			highLightBean.setMarkPost(marks[i]);
			highLightBean.setMarkPre(marks[i]);
			
			try {
				highLightBean.setFragNum(Integer.parseInt(fragNums[i]));
			} catch (Exception e) {
				highLightBean.setFragNum(1);
				e.printStackTrace();
			}
			try {
				highLightBean.setFragSize(Integer.parseInt(fragLengths[i]));
			} catch (Exception e) {
				highLightBean.setFragSize(50);
				e.printStackTrace();
			}
			highLightBeans.add(highLightBean);
		}
		return highLightBeans;

	*/
		return null;
	}

	

	
}
