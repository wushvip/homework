package com.chinamobile.cmss.bcse.config.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
/**
 * 
 * @ClassName: ListRedisService 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:08:26
 */
@Service("listRedisService")
public class ListRedisService {
	@Resource
	public RedisTemplate<String,String> stringRedisTemplate; 
	
	//添加或修改
	public void saveOrUpdate(String key,String value){
		ListOperations<String , String> listOperations=stringRedisTemplate.opsForList();
		listOperations.leftPush(key, value);
	}
	
	public List<?> get(String key,long start,long end){
		ListOperations<String , String> listOperations=stringRedisTemplate.opsForList();
		List<String> list=listOperations.range(key,start, end);
		return list;
	}
	
	public void delete(String key){
		ListOperations<String,String> listOper=stringRedisTemplate.opsForList();
		RedisOperations<String, String> redisOper=listOper.getOperations();
		redisOper.delete(key);
	}
	
	public void delete(List<String> keys){
		ListOperations<String , String> valueOper=stringRedisTemplate.opsForList();
		RedisOperations<String, String> redisOper=valueOper.getOperations();
		redisOper.delete(keys);
	}
	
	//如果value是个List，获取列表的大小
	public Long getNum(String key){
		return stringRedisTemplate.opsForList().size(key);
		
	}
}
