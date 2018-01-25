//package xyz.chanjkf.service;
//
//import org.quartz.SchedulerException;
//
//import java.util.Date;
//
///**
// * Created by yi on 2017/7/31.
// */
//public interface IQuartzService {
//    public void addJobByMinute(String jobName, Class clazz, int minute) throws SchedulerException;
//    public Boolean jobExist(String jobName) throws SchedulerException;
//    public Boolean isOnceTriggerExist(String triggerName)throws SchedulerException;
//    public void storeJob(String jobName, Class clazz)throws SchedulerException;
//    public void addJob(String jobName, Class clazz, String time) throws SchedulerException;
//    public void unscheuleJob(String jobName) throws SchedulerException;
//    public void modifyJobTime(String jobName, String time) throws SchedulerException;
//    public boolean isJobInSchedule(String jobName) throws SchedulerException;
//    public void removeJob(String jobName) throws SchedulerException;
//    public void startAlreadyExistJobOnce(String jobName, String triggerName) throws SchedulerException;
//    public void startAlreadyExistJobNewTrigger(String jobName, String expression) throws SchedulerException;
//    public void startOnce(String jobName, long recordId, Class clazz) throws SchedulerException;
//    public void startOnce(String jobName, Class clazz) throws SchedulerException;
//    public void interruptJob(String jobName) throws SchedulerException;
//    public Date getLastTriggerTime(String jobName) throws SchedulerException;
//    public Date getNextTriggerTime(String jobName) throws SchedulerException;
//
//}
