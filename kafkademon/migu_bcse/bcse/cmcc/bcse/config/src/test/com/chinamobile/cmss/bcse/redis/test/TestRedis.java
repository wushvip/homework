package com.chinamobile.cmss.bcse.redis.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinamobile.cmss.bcse.base.BaseUnit;
import com.chinamobile.cmss.bcse.config.service.impl.RedisService;

public class TestRedis extends BaseUnit {
	@Resource
	private RedisService redisService;
	
	String appId="test";
	String key="test";
	String value="test";
	
	@Test
	public void test001SaveOrUpdate(){
		redisService.saveOrUpdate(key, value);
	}
	
	@Test
	public void test002Get(){
		Object obj=redisService.get(key);
		if(null!=obj){
			System.out.println(obj.getClass().getName());
		}
	}
	
	@Test
	public void test003Delete(){
		redisService.delete(key);
	}
	
	@Test
	public void test002DeleteApp(){
		boolean isOk=redisService.deleteApp(appId);
		System.out.println(isOk);
	}
}
