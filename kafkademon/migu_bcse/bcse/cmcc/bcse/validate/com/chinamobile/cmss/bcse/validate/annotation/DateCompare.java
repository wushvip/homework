package com.chinamobile.cmss.bcse.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


import com.chinamobile.cmss.bcse.tool.config.ParamVerifyConf;
import com.chinamobile.cmss.bcse.validate.annotation.impl.DateCompareValidator;

@Target({ElementType.TYPE})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy=DateCompareValidator.class) 
/**
 * 
 * @author lijingjing
 * 针对格式为时间格式
 * 1.yyyy-MM-dd字符串类型
 * 2.数字类型(-1表示前一天，-2表示前第两天)
 * 默认为yyyy-MM-dd字符串类型
 *
 */
public @interface DateCompare {
	 String message() default ParamVerifyConf.PARAMS_FAILS;  
     
	    Class<?>[] groups() default {};  
	     
	    Class<? extends Payload>[] payload() default {}; 
	    String start();
	    String end();
	    DateType type();

}
