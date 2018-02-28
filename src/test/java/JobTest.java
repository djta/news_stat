/**
 * Created by hzyuyongmao on 2018/2/27.
 *
 * @Description
 */


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import quartzJob.MarketCrawlJob;

public class JobTest {
    public static void main(String args[]) {
//        System.out.println("【任务启动】开始(每10秒输出一次)...");
//        //创建任务
//        JobDetail jobDetail = JobBuilder.newJob(MarketCrawlJob.class).withIdentity("job1", "group1").build();
//        //创建调度器
//        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//        //创建触发器 每3秒钟执行一次
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(100).repeatForever())
//                .build();
//
//        //创建任务
//        JobDetail jobDetail2 = JobBuilder.newJob(MarketCrawlJob.class).withIdentity("job2", "group1").build();
//        //创建触发器 每10秒钟执行一次
//        Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger2", "group3")
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
//                .build();
//        try {
//            //将任务及其触发器放入调度器
//            schedulerFactory.getScheduler().scheduleJob(jobDetail, trigger);
//            //调度器开始调度任务
//            schedulerFactory.getScheduler().start();
//
//            //将任务及其触发器放入调度器
//            schedulerFactory.getScheduler().scheduleJob(jobDetail2, trigger2);
//            //调度器开始调度任务
//            schedulerFactory.getScheduler().start();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//

    }
}
