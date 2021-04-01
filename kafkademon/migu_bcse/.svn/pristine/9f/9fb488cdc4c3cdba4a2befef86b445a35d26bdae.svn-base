package com.chinamobile.cmss.bcse.validate.authority;

import java.util.HashMap;
/**
 * 
 * @ClassName: ValidatorApi 
 * @Description: TODO
 * @author: lijingjing
 * @date: 2017年3月3日 下午4:09:50
 */
public interface ValidatorApi {
	/**
	 * 对应操作的主体进行存在性验证，不存在的话就不能进行后续操作
	 * 
	 */
	public boolean existValidate(Object source) throws Exception;
	/**
	 * 对relation中的key与value关系进行校验，验证key是否有权限操作value
	 * 
	 */
	public boolean relationValidate(Object key,Object value) throws Exception;

	/**
	 * 权限校验,是否为管理员权限
	 * @param source
	 * @return
	 */
	public boolean authorityValidate(Object source) throws Exception;
	

}
