package xyz.chanjkf.utils.quarz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import xyz.chanjkf.utils.Log;

import javax.servlet.ServletContext;

/**
 * Created by yi on 2017/7/31.
 */
@DisallowConcurrentExecution
public class WriteVideoAddrJob implements InterruptableJob{
    private Log logger = new Log(WriteVideoAddrJob.class);
    private volatile boolean _interrupted = false;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (_interrupted) {
            logger.info("[TableLinkageCheckJob] quzrtz interrupted");
            return;
        }
        Scheduler sched = null;
        try {
            sched = new StdSchedulerFactory().getScheduler();
            ServletContext context = (ServletContext)sched.getContext().get("servletContext");
            context.getRealPath("");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }





    }
    @Override
    public void interrupt() throws UnableToInterruptJobException {
        logger.info("[TableLinkageCheckJob] tableLinkageCheckJob interrupted");
        _interrupted = true;
    }
}
