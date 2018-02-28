import org.apache.ibatis.io.Resources;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.ClassLoadHelper;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by hzyuyongmao on 2018/2/23.
 *
 * @Description
 */
public class test {
    public static void main(String args[]) throws IOException, SchedulerException, ClassNotFoundException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        scheduler.start();
    }
}
