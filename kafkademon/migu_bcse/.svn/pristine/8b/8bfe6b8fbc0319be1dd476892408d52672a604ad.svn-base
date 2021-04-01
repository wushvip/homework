package com.chinamobile.cmss.bcse.search.sort;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SortBean;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 *
 * @author chenmin
 * @date   2015年10月12日
 *
 * TODO  排序策略
 *
 */
public class SortRule {
	//filterList保留排序后的列表
	public SolrDocumentList filterList;
	//elementList保留列表剩余的数据
	public SolrDocumentList elementList;
	public QueryBean queryBean;
	public SortBean sortBean;
	
	public SortRule(){
	  filterList=new SolrDocumentList();
	  elementList=new SolrDocumentList();
	}
	
	public SortRule(SortRule sortRule,QueryBean queryBean,SortBean sortBean){
		this.queryBean=queryBean;
		this.sortBean=sortBean;
		this.elementList=sortRule.elementList;
		this.filterList=sortRule.filterList;
	}
	/**
	 * 
	 * 排序因子处理逻辑
	 * @param tempSolrDocumentList
	 * @return
	 * 
	 * */
	public void sortFactor(SolrDocumentList tempSolrDocumentList) {
		// 排序因子，多个字段排序
		String[] sortArray =  this.sortBean.getSortArray();
		
		if (sortArray != null && sortArray.length > 0) {
			SolrDocumentList tempFilterDocumentList1 = new SolrDocumentList();
			ArrayList<SortFactor> list1 = new ArrayList<SortFactor>();
			
			for (int i = 0; i < tempSolrDocumentList.size(); i++) {
				SortFactor sortFactor = new SortFactor(i, tempSolrDocumentList.get(i), sortArray);
				list1.add(sortFactor);
			}
			java.util.Collections.sort(list1);
			for (SortFactor sortFactor : list1) {
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "按字段排序后结果>"+sortArray[0]+":"+tempSolrDocumentList.get(sortFactor.id).get(sortArray[0]));
				tempFilterDocumentList1.add(tempSolrDocumentList.get(sortFactor.id));
			}
			
			this.elementList.removeAll(tempFilterDocumentList1);
			this.filterList.addAll(tempFilterDocumentList1);
		} else {
			this.elementList.removeAll(tempSolrDocumentList);
			this.filterList.addAll(tempSolrDocumentList);
		}
	}
	public void filterRule() throws Exception{
		
	}

	public static void main(String[] args) {/*
		SolrDocumentList tempSolrDocumentList = new SolrDocumentList();
		SolrDocument s1 = new SolrDocument(); 
		SolrDocument s2 = new SolrDocument(); 
		SolrDocument s3 = new SolrDocument(); 
		SolrDocument s4 = new SolrDocument(); 
		SolrDocument s5 = new SolrDocument(); 
		SolrDocument s6 = new SolrDocument(); 
		SolrDocument s7 = new SolrDocument(); 
		SolrDocument s8 = new SolrDocument(); 
		SolrDocument s9 = new SolrDocument(); 
		SolrDocument s10 = new SolrDocument(); 
		SolrDocument s11 = new SolrDocument(); 
		SolrDocument s12 = new SolrDocument(); 
		SolrDocument s13 = new SolrDocument(); 
		SolrDocument s14 = new SolrDocument(); 
		SolrDocument s15 = new SolrDocument(); 
		SolrDocument s16 = new SolrDocument(); 
		SolrDocument s17 = new SolrDocument(); 
		
		s1.setField("OPERATOR_CODE", "110228618000");
		s1.setField("STAR", 0.0);

		s2.setField("OPERATOR_CODE", "130220536000");
		s2.setField("STAR", 6.0);
		
		s3.setField("OPERATOR_CODE", "130222414000");

		
		s4.setField("OPERATOR_CODE", "160121576000");

		s5.setField("OPERATOR_CODE", "700008642000");
		s5.setField("STAR", 0.0);
		
		s6.setField("OPERATOR_CODE", "110220284000");
		s6.setField("STAR", 6.0);
		
		s7.setField("OPERATOR_CODE", "110228612000");
		s7.setField("STAR", 8.0);
		
		s8.setField("OPERATOR_CODE", "110229053000");
		s8.setField("STAR", 3.0);
		
		s9.setField("OPERATOR_CODE", "160121603000");

		s10.setField("OPERATOR_CODE", "160121629000");

		s11.setField("OPERATOR_CODE", "700018424000");
		s11.setField("STAR", 6.0);
		
		s12.setField("OPERATOR_CODE", "700011421000");
		s12.setField("STAR", 6.0);
		
		s13.setField("OPERATOR_CODE", "700014777000");
		s13.setField("STAR", 6.0);
		
		s14.setField("OPERATOR_CODE", "160121870000");
		s14.setField("STAR", -6.0);
		
		s15.setField("OPERATOR_CODE", "700219259000");
		s15.setField("STAR", 0.0);
		
		s16.setField("OPERATOR_CODE", "760000001483");

		s17.setField("OPERATOR_CODE", "130223718000");
//		s17.setField("STAR", 0.0);
//		s16.setField("STAR", 0.0);
//		s10.setField("STAR", 0.0);
//		s9.setField("STAR", 0.0);
//		s4.setField("STAR", 0.0);
//		s3.setField("STAR", 0.0);
		tempSolrDocumentList.add(s1);
		tempSolrDocumentList.add(s2);
		tempSolrDocumentList.add(s3);
		tempSolrDocumentList.add(s4);
		tempSolrDocumentList.add(s5);
		tempSolrDocumentList.add(s6);
		tempSolrDocumentList.add(s7);
		tempSolrDocumentList.add(s8);
		tempSolrDocumentList.add(s9);
		tempSolrDocumentList.add(s10);
		tempSolrDocumentList.add(s11);
		tempSolrDocumentList.add(s12);
		tempSolrDocumentList.add(s13);
		tempSolrDocumentList.add(s14);
		tempSolrDocumentList.add(s15);
		tempSolrDocumentList.add(s16);
		tempSolrDocumentList.add(s17);
		List<SortFactor> list1 = new ArrayList<SortFactor>();
		SolrDocumentList tempFilterDocumentList1 = new SolrDocumentList();
		
		String[] sortArray = new String[1];
		sortArray[0] = "RESE0:desc";
		
		
		for (int i = 0; i < tempSolrDocumentList.size(); i++) {
			SortFactor sortFactor = new SortFactor(i, tempSolrDocumentList.get(i), sortArray);
			list1.add(sortFactor);
		}
		java.util.Collections.sort(list1);
		for (SortFactor sortFactor : list1) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "按字段排序后结果>"+sortArray[0]+":"+tempSolrDocumentList.get(sortFactor.id).get(sortArray[0].split(":")[0]));
			tempFilterDocumentList1.add(tempSolrDocumentList.get(sortFactor.id));
		}
		
	*/}
}
