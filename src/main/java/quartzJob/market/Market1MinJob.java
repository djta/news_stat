package quartzJob.market;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.MultiThreadService;

/**
 * Created by 范志伟 on 2018-02-27.
 */
public class Market1MinJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(Market1MinJob.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        MultiThreadService mts = new MultiThreadService();
        mts.getMarketKlineInfo("1min", 5, "kline1min");
        logger.info("Market_1_Min_Job finish!!");
    }

    public static void main(String args[]) {

    }
}
