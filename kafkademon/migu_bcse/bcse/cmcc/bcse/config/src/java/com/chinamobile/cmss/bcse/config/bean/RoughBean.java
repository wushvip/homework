package com.chinamobile.cmss.bcse.config.bean;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 粗排接口请求需要的参数
 * @ClassName: RoughBean 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:50:19
 */
public class RoughBean {
	@NotBlank(message ="fieldName不能为空;") 
	private String fieldName;
	
	@NotBlank(message ="fieldWeight不能为空;") 
	private String fieldWeight;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldWeight() {
		return fieldWeight;
	}
	public void setFieldWeight(String fieldWeight) {
		this.fieldWeight = fieldWeight;
	}
	
	
	
}
