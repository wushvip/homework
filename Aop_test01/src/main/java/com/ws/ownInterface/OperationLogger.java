/**
 * 
 */
package com.ws.ownInterface;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wushuai
 * @date 2018年9月17日
 * @Description	TODO
 */
//用于描述注解的使用范围
@Target(ElementType.METHOD)
//用于描述注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
//对于继承标有该注解的父类的子类，不需要添加该注解，也可生效
@Inherited
//所以使用该标记就是告诉jdk让它也将annotation生成到doc中去
@Documented
public @interface OperationLogger {
	String getName();
	int getAge();
}
