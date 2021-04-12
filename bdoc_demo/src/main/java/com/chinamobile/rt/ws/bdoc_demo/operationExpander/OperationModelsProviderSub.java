/*
package com.chinamobile.rt.ws.bdoc_demo.operationExpander;*/
/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-08 9:36
 * @Description
 * @Since V1.0
 *//*


import com.chinamobile.rt.ws.bdoc_demo.annotation.DataForm;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import springfox.documentation.schema.ResolvedTypes;
import springfox.documentation.schema.plugins.SchemaPluginsManager;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ViewProviderPlugin;
import springfox.documentation.spi.service.contexts.RequestMappingContext;
import springfox.documentation.spring.web.readers.operation.OperationModelsProvider;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

*/
/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-08 9:36
 * @Description
 * @Since V1.0
 *//*

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class OperationModelsProviderSub extends OperationModelsProvider {

    private static final Logger LOG = LoggerFactory.getLogger(OperationModelsProviderSub.class);

    private SchemaPluginsManager pluginsManager;

    @Autowired
    public OperationModelsProviderSub(SchemaPluginsManager pluginsManager, ApplicationContext applicationContext){
        super(pluginsManager);
        this.pluginsManager = pluginsManager;

//        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
//        defaultListableBeanFactory.removeBeanDefinition("operationModelsProvider");
    }

    public void apply(RequestMappingContext context) {
        this.collectFromReturnType(context);
        this.collectParameters(context);
        this.collectGlobalModels(context);
    }

    private void collectGlobalModels(RequestMappingContext context) {
        Iterator var2 = context.getAdditionalModels().iterator();

        while(var2.hasNext()) {
            ResolvedType each = (ResolvedType)var2.next();
            context.operationModelsBuilder().addInputParam(each);
            context.operationModelsBuilder().addReturn(each);
        }

    }

    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    private void collectFromReturnType(RequestMappingContext context) {
        ResolvedType modelType = context.getReturnType();
        modelType = context.alternateFor(modelType);
        LOG.debug("Adding return parameter of type {}", ResolvedTypes.resolvedTypeSignature(modelType).orElse("<null>"));
        context.operationModelsBuilder().addReturn(modelType, this.viewForReturn(context, modelType));
    }

    private void collectParameters(RequestMappingContext context) {
        LOG.debug("Reading parameters models for handlerMethod |{}|", context.getName());
        List<ResolvedMethodParameter> parameterTypes = context.getParameters();
        Iterator var3 = parameterTypes.iterator();

        while(true) {
            ResolvedMethodParameter parameterType;
            do {
                if (!var3.hasNext()) {
                    LOG.debug("Finished reading parameters models for handlerMethod |{}|", context.getName());
                    return;
                }

                parameterType = (ResolvedMethodParameter)var3.next();
            } while(!parameterType.hasParameterAnnotation(RequestBody.class) && !parameterType.hasParameterAnnotation(DataForm.class) && !parameterType.hasParameterAnnotation(RequestPart.class));

            ResolvedType modelType = context.alternateFor(parameterType.getParameterType());
            LOG.debug("Adding input parameter of type {}", ResolvedTypes.resolvedTypeSignature(modelType).orElse("<null>"));
            context.operationModelsBuilder().addInputParam(modelType, this.viewForParameter(context, modelType, parameterType), new HashSet());
        }
    }

    private Optional<ResolvedType> viewForReturn(RequestMappingContext context, ResolvedType regularModel) {
        ViewProviderPlugin viewProvider = this.pluginsManager.viewProvider(context.getDocumentationContext().getDocumentationType());
        return viewProvider.viewFor(regularModel, context);
    }

    private Optional<ResolvedType> viewForParameter(RequestMappingContext context, ResolvedType modelType, ResolvedMethodParameter parameter) {
        ViewProviderPlugin viewProvider = this.pluginsManager.viewProvider(context.getDocumentationContext().getDocumentationType());
        return viewProvider.viewFor(modelType, parameter);
    }
}
*/
