package com.hiworld.spring.IOC;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 所有被该Annotation
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月24日 下午3:10:42
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IocAnnotation {
    
    String value();
}
