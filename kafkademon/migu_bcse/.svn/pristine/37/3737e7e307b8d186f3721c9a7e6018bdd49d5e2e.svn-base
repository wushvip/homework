package com.chinamobile.cmss.bcse.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.chinamobile.cmss.bcse.tool.config.ParamVerifyConf;
import com.chinamobile.cmss.bcse.validate.annotation.impl.InRangeValidator;


@Target({ElementType.TYPE,ElementType.FIELD})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy=InRangeValidator.class) 
public @interface InInclude{
	   String message() default ParamVerifyConf.PARAMS_FAILS;  
	     
	    Class<?>[] groups() default {};  
	     
	    Class<? extends Payload>[] payload() default {}; 
	    String[] rangeValue() ;
}
