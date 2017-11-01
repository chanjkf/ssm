package xyz.chanjkf.aop;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;

/**
 * Created by yi on 2017/9/1.
 */
public class dataSourceAspect {
    public void before(JoinPoint point){
        String methodName = point.getSignature().getName();
        if (isSalve(methodName)){
            DynamicDataSourceHolder.setSlave();
        } else {
            DynamicDataSourceHolder.setMaster();
        }
    }

    private boolean isSalve(String methodName) {
        String[] salveMethod = new String[]{"find","get","query"};
        return StringUtils.startsWithAny(methodName,salveMethod);
    }
}
