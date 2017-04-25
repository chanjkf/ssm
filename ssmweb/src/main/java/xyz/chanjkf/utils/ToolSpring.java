package xyz.chanjkf.utils;

/**
 * Created by Administrator on 2015/8/13.
 */

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class ToolSpring implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;
    @Override
    public  void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        if(ToolSpring.applicationContext == null){
            ToolSpring.applicationContext  = applicationContext;
        }
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }
}