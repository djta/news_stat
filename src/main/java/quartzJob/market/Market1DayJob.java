package quartzJob.market;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.MultiThreadService;

/**
 * Created by hzyuyongmao on 2018/2/28.
 *
 * @Description
 */
public class Market1DayJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(Market1MinJob.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        MultiThreadService mts = new MultiThreadService();
        mts.getMarketKlineInfo("1day", 5, "kline1day");
        logger.info("Market_1_Day_Job finish!!");
    }
}