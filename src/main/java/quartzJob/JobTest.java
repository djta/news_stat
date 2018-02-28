package quartzJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by hzyuyongmao on 2018/2/28.
 *
 * @Description
 */
public class JobTest implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("job start!");

    }
}
