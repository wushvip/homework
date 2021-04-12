/*
package com.chinamobile.rt.ws.bdoc_demo.operationExpander;*/
/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-07 20:52
 * @Description
 * @Since V1.0
 *//*


import com.chinamobile.rt.ws.bdoc_demo.annotation.DataForm;
import com.fasterxml.classmate.ResolvedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.Collections;
import springfox.documentation.schema.Maps;
import springfox.documentation.schema.Types;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.EnumTypeDeterminer;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.OperationParameterReader;
import springfox.documentation.spring.web.readers.parameter.ExpansionContext;
import springfox.documentation.spring.web.readers.parameter.ModelAttributeParameterExpander;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

*/
/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-07 20:52
 * @Description
 * @Since V1.0
 *//*

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class OperationParameterReaderSub extends OperationParameterReader {

    private final ModelAttributeParameterExpander expander;

    private final EnumTypeDeterminer enumTypeDeterminer;

    private DocumentationPluginsManager pluginsManager;

    @Autowired
    public OperationParameterReaderSub(ModelAttributeParameterExpander expander,
                                       EnumTypeDeterminer enumTypeDeterminer,
                                       DocumentationPluginsManager pluginsManager,
                                       ApplicationContext applicationContext) {
        super(expander,enumTypeDeterminer);
        this.expander = expander;
        this.enumTypeDeterminer = enumTypeDeterminer;
        this.pluginsManager = pluginsManager;
//        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
//        defaultListableBeanFactory.removeBeanDefinition("operationParameterReader");
    }


    public void apply(OperationContext context) {
        context.operationBuilder().parameters(context.getGlobalOperationParameters());
        context.operationBuilder().parameters(this.readParameters(context));
    }

    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    private List<Parameter> readParameters(final OperationContext context) {
        List<ResolvedMethodParameter> methodParameters = context.getParameters();
        List<Parameter> parameters = new ArrayList();
        Iterator var4 = methodParameters.iterator();

        while(var4.hasNext()) {
            ResolvedMethodParameter methodParameter = (ResolvedMethodParameter)var4.next();
            ResolvedType alternate = context.alternateFor(methodParameter.getParameterType());
            if (!this.shouldIgnore(methodParameter, alternate, context.getIgnorableParameterTypes())) {
                ParameterContext parameterContext = new ParameterContext(methodParameter, new ParameterBuilder(), context.getDocumentationContext(), context.getGenericsNamingStrategy(), context);
                if (this.shouldExpand(methodParameter, alternate)) {
                    parameters.addAll(this.expander.expand(new ExpansionContext("", alternate, context)));
                } else {
                    parameters.add(this.pluginsManager.parameter(parameterContext));
                }
            }
        }

        return (List)parameters.stream()
//                .filter((Parameter::isHidden).negate())
                .filter(item->item.isHidden())
                .collect(Collectors.toList());
    }

    private boolean shouldIgnore(final ResolvedMethodParameter parameter, ResolvedType resolvedParameterType, final Set<Class> ignorableParamTypes) {
        if (ignorableParamTypes.contains(resolvedParameterType.getErasedType())) {
            return true;
        } else {
            Stream var10000 = ignorableParamTypes.stream();
            Annotation.class.getClass();
            var10000 = var10000.filter(cls -> Annotation.class.isAssignableFrom(cls.getClass()));
            parameter.getClass();
            return var10000.anyMatch(item->parameter.hasParameterAnnotation(Annotation.class));
        }
    }

    private boolean shouldExpand(final ResolvedMethodParameter parameter, ResolvedType resolvedParamType) {
        return !parameter.hasParameterAnnotation(RequestBody.class)
                && !parameter.hasParameterAnnotation(DataForm.class)
                && !parameter.hasParameterAnnotation(RequestPart.class)
                && !parameter.hasParameterAnnotation(RequestParam.class)
                && !parameter.hasParameterAnnotation(PathVariable.class)
                && !Types.isBaseType(Types.typeNameFor(resolvedParamType.getErasedType()))
                && !this.enumTypeDeterminer.isEnum(resolvedParamType.getErasedType())
                && !Collections.isContainerType(resolvedParamType)
                && !Maps.isMapType(resolvedParamType);
    }
}
*/
