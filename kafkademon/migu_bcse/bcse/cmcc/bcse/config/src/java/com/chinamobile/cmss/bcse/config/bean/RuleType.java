package com.chinamobile.cmss.bcse.config.bean;
/**
 * 规则类别
 * @ClassName: RuleType 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:50:57
 */
public enum RuleType {
	ROUGH_RULE("ROUGH","0"),
	CARE_RULE("CARE","1"),
	SUGGEST_RULE("SUGGEST","2"),
	SHIELD_RULE("SHIELD","3"),
	SPREAD_RULE("SPREAD","4"),
	FACET_RULE("FACET","5");
	
	private String name;
	private String value;
	private RuleType(String name,String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
