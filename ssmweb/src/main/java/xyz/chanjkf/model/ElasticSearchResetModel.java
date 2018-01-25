package xyz.chanjkf.model;

import java.util.Date;

/**
 * Created by yi on 2017/7/31.
 */
public class ElasticSearchResetModel {

    private String status = "none";

    private Date beginTime;

    private Date finishedTime;

    private String errorReason;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Date finishedTime) {
        this.finishedTime = finishedTime;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }
}
