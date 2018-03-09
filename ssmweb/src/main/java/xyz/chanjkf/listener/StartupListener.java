package xyz.chanjkf.listener;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
//import xyz.chanjkf.service.IQuartzService;
import xyz.chanjkf.service.IUserService;
import xyz.chanjkf.utils.Log;
import xyz.chanjkf.utils.quarz.PrintJob;
import javax.annotation.Resource;

/**
 * Created by yi
 */

@Component("StartupListener")
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    @Resource(name = "UserService")
    private IUserService service;

//    @Resource(name = "QuartzService")
//    private IQuartzService quartzService;

    private static boolean isStart = false;
    final static Log log = new Log(StartupListener.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("--init user login status--");
        initUserStatus();
    }

    private void initUserStatus() {
        service.initUserStatus();
    }

    private void startPrint() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail detail = JobBuilder
                    .newJob(PrintJob.class)
                    .withIdentity("job1", "group1")
                    .build();
            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForever();
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0 * * ?");
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleScheduleBuilder)
                    .build();
            scheduler.scheduleJob(detail, trigger);
            scheduler.start();


        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }
}
