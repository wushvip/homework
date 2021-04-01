package com.chinamobile.cmss.bcse.evaluate.bean.vo;

import com.chinamobile.cmss.bcse.evaluate.utils.annotation.Column;
import com.chinamobile.cmss.bcse.evaluate.utils.annotation.ExcelCol;
import com.chinamobile.cmss.bcse.evaluate.utils.annotation.ExcelTransfer;

/**
 * 评测体系得分导出转换类
 *
 */
@ExcelTransfer(start=1, describe = "评测体系得分表") 
public class PointResTransfer {
	@ExcelCol(column = Column.B, required = true,desc = "输入词")   
	private String searchWord ;	//输入词
	@ExcelCol(column = Column.C, required = true,desc="语料结果")   
	private String dataResult ;	//语料搜索结果
	@ExcelCol(column = Column.D, required = true,desc="实际搜索结果")   
	private String responseResult;	//实际搜索结果
	@ExcelCol(column = Column.E, required = true,desc="语料结果ID")   
	private String dataResultId;	//语料搜索结果Id
	@ExcelCol(column = Column.F, required = true,desc="实际搜索结果ID")   
	private String responseResultId;	//实际搜索结果
	@ExcelCol(column = Column.G, required = true,desc="准确率")   
	private String pPoint;//准确率
	
	@ExcelCol(column = Column.H, required = true,desc="召回率")   
	private String rPoint ;	//召回率
	@ExcelCol(column = Column.I, required = true,desc="平均排序倒数值")   
	private String arPoint ;	//平均召回率
	@ExcelCol(column = Column.J, required = true,desc="平均准确率均值")   
	private String apPoint ;	//平均准确率
	
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getDataResult() {
		return dataResult;
	}
	public void setDataResult(String dataResult) {
		this.dataResult = dataResult;
	}
	public String getResponseResult() {
		return responseResult;
	}
	public void setResponseResult(String responseResult) {
		this.responseResult = responseResult;
	}
	public String getDataResultId() {
		return dataResultId;
	}
	public void setDataResultId(String dataResultId) {
		this.dataResultId = dataResultId;
	}
	public String getResponseResultId() {
		return responseResultId;
	}
	public void setResponseResultId(String responseResultId) {
		this.responseResultId = responseResultId;
	}
	public String getpPoint() {
		return pPoint;
	}
	public void setpPoint(String pPoint) {
		this.pPoint = pPoint;
	}
	public String getrPoint() {
		return rPoint;
	}
	public void setrPoint(String rPoint) {
		this.rPoint = rPoint;
	}
	public String getApPoint() {
		return apPoint;
	}
	public void setApPoint(String apPoint) {
		this.apPoint = apPoint;
	}
	public String getArPoint() {
		return arPoint;
	}
	public void setArPoint(String arPoint) {
		this.arPoint = arPoint;
	}
	
	
}
