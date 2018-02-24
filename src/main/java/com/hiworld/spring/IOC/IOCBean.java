package com.hiworld.spring.IOC;

@IocAnnotation("iocBean")
public class IOCBean {
    
    @AutowireAnnotation
    private AutowireBean autowireBean;
    
}
