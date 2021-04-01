package com.chinamobile.cmss.bcse.config.service.impl;


import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
/**
 * RedisService 公共类，操作redis数据库
 * @ClassName: RedisService 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:08:44
 */
@Service("redisService")
public class RedisService {
	@Resource
	public  RedisTemplate<String,Object> redisTemplate;

	//添加或修改
	public void saveOrUpdate(String key,Object value){
			ValueOperations<String , Object> valueOper=redisTemplate.opsForValue();
			valueOper.set(key, value);
	}
	
	//获取
	public Object get(String key){
		try {
			ValueOperations<String , Object> valueOper=redisTemplate.opsForValue();
			return valueOper.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}
	
	//删除
	public void delete(String key){
			ValueOperations<String , Object> valueOper=redisTemplate.opsForValue();
			RedisOperations<String,Object>  redisOperations=valueOper.getOperations();
			redisOperations.delete(key);
	}
	
	public void delete(List<String> keys){
		ValueOperations<String , Object> valueOper=redisTemplate.opsForValue();
		RedisOperations<String,Object>  redisOperations=valueOper.getOperations();
		redisOperations.delete(keys);
	}

	public void delete(Set<String> keys){
			ValueOperations<String , Object> valueOper=redisTemplate.opsForValue();
			RedisOperations<String,Object>  redisOperations=valueOper.getOperations();
			redisOperations.delete(keys);
	}
	
	
	//模糊删除
	public boolean deleteApp(String appId){
		try {
			Set<String> keys=redisTemplate.keys(appId+"*");
			if(null!=keys&&keys.size()>0){
				redisTemplate.delete(keys);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
