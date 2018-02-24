package com.hiworld.spring.IOC;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.hiworld.spring.PackageScanner;

/**
 * DI(Dependecy Injection) 和 IOC(Inversion of Control)是同一个概念
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月24日 上午11:27:51
 */
public class IOC {

    public static void main(String[] args) {
        String scanPath = "com.hiworld.spring.IOC";
        Container container = new Container(scanPath);
        
        IOCBean iocBean = container.getBean(IOCBean.class);
        System.out.println("Get bean by class(IOCBean) : " + iocBean);
        iocBean = container.getBean("iocBean");
        System.out.println("Get bean by name(IOCBean) : " + iocBean);
    }
}

class Container {
    
    private String scanPath;
    private Map<String, Object> containerName2Bean;
    private Map<Class, Object> containerClass2Bean;
    
    public Container(String scanPath) {
        this.scanPath = scanPath;
        containerName2Bean = new HashMap<>();
        containerClass2Bean = new HashMap<>();
        refresh();
    }
    
    /**
     * 
     */
    private void refresh() {
        initBean();
    }
    
    /**
     * 
     */
    private void initBean() {
        Set<Class<?>> clazzs = PackageScanner.scan(scanPath);
        for (Class<?> clazz : clazzs) {
            IocAnnotation iocAnnotation = clazz.getAnnotation(IocAnnotation.class);
            if(null!=iocAnnotation) {
                String beanName = iocAnnotation.value();
                Object obj = null;
                try {
                    obj = clazz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                
                containerClass2Bean.put(clazz, obj);
                containerName2Bean.put(beanName, obj);
            }
        }
    }
    
    /**
     * 
     */
    private void injectField() {
        for (Map.Entry<Class, Object> entry : containerClass2Bean.entrySet()) {
            
        }
    }
    
    public <T> T getBean(Class<T> clazz) {
        return (T)containerClass2Bean.get(clazz);
    }
    
    public <T> T getBean(String name) {
        return (T)containerName2Bean.get(name);
    }
}

