package xyz.chanjkf.utils.quarz;

import org.quartz.*;
import xyz.chanjkf.utils.DXPLog;

/**
 * Created by yi on 2017/7/31.
 */
@DisallowConcurrentExecution
public class WriteVideoAddrJob implements InterruptableJob{
    private DXPLog logger = new DXPLog(WriteVideoAddrJob.class);
    private volatile boolean _interrupted = false;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (_interrupted) {
            logger.info("[TableLinkageCheckJob] quzrtz interrupted");
            return;
        }

    }
    @Override
    public void interrupt() throws UnableToInterruptJobException {
        logger.info("[TableLinkageCheckJob] tableLinkageCheckJob interrupted");
        _interrupted = true;
    }
}
