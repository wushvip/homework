package com.chinamobile.cmss.bcse.redis.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinamobile.cmss.bcse.base.BaseUnit;
import com.chinamobile.cmss.bcse.config.service.impl.ListRedisService;

public class TestHotword extends BaseUnit {
	@Resource
	private ListRedisService listRedisService;

	private String key="9d5341dcbde741bdac959450582e1ed026e508a621e04a2fa7b847af6d408f75HOTWORD_DAY";
	
	@Test
	public void test001Get(){
		Object obj=listRedisService.get(key,0,-1);
		if(null!=obj){
			System.out.println(obj.getClass().getName());
		}
	}
	
	@Test
	public void test002GetNum(){
		Long num=listRedisService.getNum(key);
		System.out.println(num);
		
	}
	
}
