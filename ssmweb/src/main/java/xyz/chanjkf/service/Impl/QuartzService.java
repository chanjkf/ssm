//package xyz.chanjkf.service.Impl;
//
//
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.stereotype.Service;
//import xyz.chanjkf.service.IQuartzService;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.Enumeration;
//
///**
// * Created by yunwei0270 on 2015/7/2.
// */
//
//@Service("QuartzService")
//public class QuartzService implements IQuartzService {
//    private StdSchedulerFactory gSchedulerFactory ;
//    private Scheduler gScheduler;
//    private static final String JOB_GROUP_NAME = "DMALL_JOB";
//    private static final String TRIGGER_GROUP_NAME = "DMALL_TRIGGER";
//    public static final String QUARTZ_JOB_KEY = "JOB_NAME";
//    public static final String QUARTZ_JOB_RECORD_ID = "JOB_RECORD_ID";
//
//    public void addJobByMinute(String jobName, Class clazz, int minute) throws SchedulerException {
//        JobKey jobKey = new JobKey(jobName, TRIGGER_GROUP_NAME);
//        if (!jobExist(jobKey)){
//            JobDetail job = JobBuilder.newJob(clazz).withIdentity(jobKey).requestRecovery(true).build();
//
//            Date startTime = DateBuilder.nextGivenMinuteDate((Date) null, 1);
//            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
//                    .simpleSchedule()
//                    .withIntervalInMinutes(minute)
//                    .repeatForever();
//            SimpleTrigger trigger = TriggerBuilder.newTrigger()
//                    .withIdentity(jobName, TRIGGER_GROUP_NAME)
//                    . startAt(startTime)
//                    .withSchedule(scheduleBuilder)
//                    .build();
//            gScheduler.scheduleJob(job, trigger);
//        }
//
//        if (false == gScheduler.isStarted())
//        {
//            gScheduler.start();
//        }
//    }
//
//    public Boolean jobExist(String jobName) throws SchedulerException {
//        JobKey jobKey = new JobKey(jobName, TRIGGER_GROUP_NAME);
//        return jobExist(jobKey);
//    }
//
//    private Boolean jobExist(JobKey jobKey) throws SchedulerException {
//        if (null != gScheduler.getJobDetail(jobKey)) {
//            return true;
//        }
//        return false;
//    }
//
//    public Boolean isOnceTriggerExist(String triggerName)throws SchedulerException {
//        TriggerKey triggerKey = new TriggerKey(triggerName,TRIGGER_GROUP_NAME);
//        Trigger trigger = gScheduler.getTrigger(triggerKey);
//
//        if(trigger == null) {
//            return false;
//        }
//        return true;
//    }
//    public void storeJob(String jobName, Class clazz)throws SchedulerException {
//        if (isOnceTriggerExist(jobName)) {
//            return;
//        }
//        // Define a durable job instance (durable jobs can exist without triggers)
//        JobDetail job = JobBuilder.newJob(clazz)
//                .withIdentity(jobName, JOB_GROUP_NAME)
//                .storeDurably()
//                .build();
//        // Add the the job to the scheduler's store
//        gScheduler.addJob(job, false);
//    }
//    /* time string in cron format */
//    public void addJob(String jobName, Class clazz, String time) throws SchedulerException {
//        JobDetail job = JobBuilder.newJob(clazz).withIdentity(jobName, JOB_GROUP_NAME).build();
//        JobDataMap datamap = job.getJobDataMap();
//        datamap.put(QUARTZ_JOB_KEY, jobName);
//
//        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME)
//                .withSchedule(CronScheduleBuilder.cronSchedule(time).withMisfireHandlingInstructionIgnoreMisfires()).build();
//        gScheduler.scheduleJob(job, trigger);
//
//        if (false == gScheduler.isStarted())
//        {
//            gScheduler.start();
//        }
//    }
//
//    public void unscheuleJob(String jobName) throws SchedulerException {
//        gScheduler.interrupt(new JobKey(jobName, JOB_GROUP_NAME));
//        gScheduler.pauseTrigger(new TriggerKey(jobName, TRIGGER_GROUP_NAME));// 停止触发器
//        gScheduler.unscheduleJob(new TriggerKey(jobName, TRIGGER_GROUP_NAME));
//
//        if (!gScheduler.isStarted())
//        {
//            gScheduler.start();
//        }
//    }
//
//    public void modifyJobTime(String jobName, String time) throws SchedulerException {
//        TriggerKey triggerKey = new TriggerKey(jobName,TRIGGER_GROUP_NAME);
//        CronTrigger trigger = (CronTrigger) gScheduler.getTrigger(triggerKey);
//
//        if(trigger == null) {
//            return;
//        }
//        String oldTime = trigger.getCronExpression();
//
//        if (!oldTime.equalsIgnoreCase(time)) {
//            CronTrigger triggerNew = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME)
//                    .withSchedule(CronScheduleBuilder.cronSchedule(time).withMisfireHandlingInstructionIgnoreMisfires()).build();
//            gScheduler.rescheduleJob(trigger.getKey(),triggerNew);
//        }
//
//        if (false == gScheduler.isStarted())
//        {
//            gScheduler.start();
//        }
//
//        return;
//    }
//    public boolean isJobInSchedule(String jobName) throws SchedulerException {
//        boolean bIsJobInSchedule = true;
//        TriggerKey triggerKey = new TriggerKey(jobName,TRIGGER_GROUP_NAME);
//        Trigger trigger = gScheduler.getTrigger(triggerKey);
//
//        if(trigger == null) {
//            bIsJobInSchedule = false;
//        }
//
//        return bIsJobInSchedule;
//    }
//
//    public void removeJob(String jobName) throws SchedulerException {
//        gScheduler.interrupt(new JobKey(jobName, JOB_GROUP_NAME));
//        gScheduler.pauseTrigger(new TriggerKey(jobName, TRIGGER_GROUP_NAME));// 停止触发器
//        gScheduler.unscheduleJob(new TriggerKey(jobName, TRIGGER_GROUP_NAME));// 移除触发器
//        gScheduler.deleteJob(new JobKey(jobName, JOB_GROUP_NAME));// 删除任务
//
//        if (false == gScheduler.isStarted())
//        {
//            gScheduler.start();
//        }
//    }
//
//    public void startAlreadyExistJobOnce(String jobName,String triggerName) throws SchedulerException {
//        // Define a Trigger that will fire "now" and associate it with the existing job
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity(triggerName, TRIGGER_GROUP_NAME)
//                .startNow()
//                .forJob(JobKey.jobKey(jobName, JOB_GROUP_NAME))
//                .build();
//        // Schedule the trigger
//        gScheduler.scheduleJob(trigger);
//    }
//
//    public void startAlreadyExistJobNewTrigger(String jobName, String expression) throws SchedulerException {
//        CronTrigger triggerNew = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME)
//                .withSchedule(CronScheduleBuilder.cronSchedule(expression).withMisfireHandlingInstructionIgnoreMisfires()).forJob(JobKey.jobKey(jobName, JOB_GROUP_NAME)).build();
//
//        // Schedule the trigger
//        gScheduler.scheduleJob(triggerNew);
//    }
//
//    public void startOnce(String jobName, long recordId, Class clazz) throws SchedulerException {
//        JobDetail job = JobBuilder.newJob(clazz).withIdentity(jobName, JOB_GROUP_NAME).build();
//        JobDataMap datamap = job.getJobDataMap();
//        datamap.put(QUARTZ_JOB_KEY, jobName);
//        datamap.put(QUARTZ_JOB_RECORD_ID, recordId);
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME).startNow().build();
//
//        gScheduler.scheduleJob(job, trigger);
//
//        if (false == gScheduler.isStarted()) {
//            gScheduler.start();
//        }
//    }
//
//    public void startOnce(String jobName, Class clazz) throws SchedulerException {
//        JobDetail job = JobBuilder.newJob(clazz).withIdentity(jobName, JOB_GROUP_NAME).build();
//        JobDataMap datamap = job.getJobDataMap();
//        datamap.put(QUARTZ_JOB_KEY, jobName);
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME).startNow().build();
//
//        gScheduler.scheduleJob(job, trigger);
//
//        if (false == gScheduler.isStarted())
//        {
//            gScheduler.start();
//        }
//    }
//
//    public void interruptJob(String jobName) throws SchedulerException {
//
//        gScheduler.interrupt(new JobKey(jobName, JOB_GROUP_NAME));
//
//    }
//
//    public Date getLastTriggerTime(String jobName) throws SchedulerException {
//        Date ret = new Date();
//
//        ret.setTime(-1);
//
//        TriggerKey triggerKey = new TriggerKey(jobName,TRIGGER_GROUP_NAME);
//        Trigger trigger = gScheduler.getTrigger(triggerKey);
//
//        if(trigger == null) {
//            return ret;
//        }
//
//        ret = trigger.getPreviousFireTime();
//
//        return  ret;
//    }
//
//    public Date getNextTriggerTime(String jobName) throws SchedulerException {
//        Date ret = new Date();
//        ret.setTime(-1);
//        TriggerKey triggerKey = new TriggerKey(jobName,TRIGGER_GROUP_NAME);
//        Trigger trigger = gScheduler.getTrigger(triggerKey);
//
//        if(trigger == null) {
//            return ret;
//        }
//
//        ret = trigger.getNextFireTime();
//
//        return  ret;
//    }
//
//    public JobDetail getJobDetail(String jobName){
//        try {
//            return gScheduler.getJobDetail(new JobKey(jobName, JOB_GROUP_NAME));
//        } catch (SchedulerException e) {
//            // TODO Auto-generated catch block
//        }
//        return null;
//    }
//
//    private void startScheduler() throws SchedulerException {
//        gSchedulerFactory = new StdSchedulerFactory();
//        String path = this.getClass().getResource("/").getPath() + "quartz-job.properties";
//        gSchedulerFactory.initialize(path);
//
//        gScheduler = gSchedulerFactory.getScheduler();
//        if (false == gScheduler.isStarted())
//        {
//            gScheduler.start();
//        }
//    }
//
//    private void stopScheduler(){
//        try {
//            gScheduler.standby();
//            gScheduler.shutdown(true);
//        }
//        catch(SchedulerException e){
//        }
//        return ;
//    }
//
//    @PostConstruct
//    public void initQuartz() {
//        /* 初始化定时任务 */
//        try {
//            startScheduler();
//        } catch (SchedulerException e) {
//        }
//
////        try {
//            /* store one job, use later */
//            //control.storeJob("EsSyncJob", ESjob.class);
////        }catch (Exception e) {
////            logger.error("[Init] start EsSyncJob error.", e);
////        }
//    }
//
//    @PreDestroy
//    public void destroyQuartz() {
//
//
//        try {
//
//            stopScheduler();
//        } catch (Exception e) {
//        }
//
//        Enumeration<Driver> drivers = DriverManager.getDrivers();
//        while (drivers.hasMoreElements()) {
//            Driver driver = drivers.nextElement();
//            try {
//                DriverManager.deregisterDriver(driver);
//            } catch (SQLException e) {
//            }
//        }
//    }
//
//}
