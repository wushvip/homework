package com.chinamobile.rt.ws.bdoc_demo.operationExpander;/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-07 20:52
 * @Description
 * @Since V1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.readers.operation.OperationParameterReader;
import springfox.documentation.spring.web.readers.parameter.ModelAttributeParameterExpander;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-07 20:52
 * @Description
 * @Since V1.0
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class OperationParameterReaderSub extends OperationParameterReader {

    private final ModelAttributeParameterExpander expander;

//    private final EnumTypeDeterminer enumTypeDeterminer;

    @Autowired
    public OperationParameterReaderSub(ModelAttributeParameterExpander expander, ApplicationContext applicationContext) {
        super(expander);
        this.expander = expander;
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        defaultListableBeanFactory.removeBeanDefinition("operationParameterReader");
    }
    
}
