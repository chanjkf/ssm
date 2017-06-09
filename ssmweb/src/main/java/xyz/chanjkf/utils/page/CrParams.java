package xyz.chanjkf.utils.page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xunzhi on 2015/8/15.
 */
public class CrParams {
    private String entityName;
    private List<Parameter> paramList = new ArrayList<Parameter>();

    public CrParams(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public List<Parameter> getParamList() {
        return paramList;
    }

    public void addParam(Parameter param) {
        paramList.add(param);
    }
}
