package xyz.chanjkf.listener;

import org.quartz.SchedulerException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import xyz.chanjkf.service.IQuartzService;
import xyz.chanjkf.utils.DXPLog;
import xyz.chanjkf.utils.quarz.WriteVideoAddrJob;

import javax.annotation.Resource;

/**
 * Created by yi
 */

@Component("StartupListener")
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource(name = "QuartzService")
    private IQuartzService quartzService;
    private static boolean isStart = false;
    final static DXPLog dxpLog = new DXPLog(StartupListener.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(!isStart) {
            isStart = true;
            try {
                quartzService.addJob("WriteVideoAddrJob", WriteVideoAddrJob.class, "0 0 0 * * ?");
            } catch (SchedulerException e) {
                dxpLog.error(e);
            }
        }
    }
}
